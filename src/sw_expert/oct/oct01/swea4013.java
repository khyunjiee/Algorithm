package sw_expert.oct.oct01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 4013 특이한 자석
 *
 * 접근 방식:
 * 시뮬레이션 문제라고 생각해서
 * 한 단계씩 해나갔습니다.
 * 비교할 위치, 비교할 자석의 인덱스를 체크하는 배열을 정적으로 선언해서 사용해주었습니다.
 * 또한 현재 자석이 회전할 때 같이 회전해야할 자석은 bfs 방식으로 찾아줬습니다.
 * 결과값 계산은 비트 연산으로 해결했습니다.
 *
 * 시간 복잡도:
 *
 * 소요 시간:
 * 35분
 */

public class swea4013 {

    static int magnets[][];                             // 자석들을 담을 배열
    static int[] adjDir = { -1, 1 };                    // 현재 자석의 왼쪽과 오른쪽을 비교 (방향을 담은 배열)
    // 왼쪽 자석을 비교할때 현재 자석의 6번과 왼쪽 자석의 2번 인덱스를 비교
    // 오른쪽 자석을 비교할 때 현재 자석의 2번과 오른쪽 자석의 6번 인덱스를 비교
    static int[][] compareIndex = { {6, 2}, {2, 6} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            int K = Integer.parseInt(br.readLine());
            magnets = new int[4][8];

            // 자석 입력값 초기화
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int num = Integer.parseInt(st.nextToken()) - 1; // 회전할 기준 자석
                int dir = Integer.parseInt(st.nextToken());     // 방향
                // 동시에 회전할 자석을 찾아서 한꺼번에 회전
                bfs(num, dir);
            }

            int result = 0;
            // 총 4개의 자석을 모두 비교
            for (int i = 0; i < 4; i++) {
                if (magnets[i][0] == 1) {   // 0번 인덱스가 1(S) 라면
                    result |= (1 << i);     // 비트를 해당 자석 번호만큼 시프트해서 result에 더함
                }
            }
            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }

    // 함께 회전할 자석을 bfs로 탐색
    private static void bfs(int start, int dir) {
        boolean[] visited = new boolean[4];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, dir});
        // 자석이 한꺼번에 회전해야하기 때문에 리스트에 저장
        List<int[]> willRotate = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int num = curr[0];  // 현재 자석의 인덱스 번호
            int d = curr[1];    // 현재 자석이 회전할 방향

            // 방문 체크
            if (visited[num]) continue;
            visited[num] = true;
            // 리스트에 추가
            willRotate.add(new int[]{num, d});

            // 이전 자석과 이후 자석을 탐색
            for (int i = 0; i < 2; i++) {
                int nextNum = num + adjDir[i];
                // 다음 자석과 현재 자석의 비교할 위치를 변수로 선언
                int currCompare = compareIndex[i][0];
                int nextCompare = compareIndex[i][1];

                // 자석이 0번부터 3번 인덱스 사이에 있고
                // 비교할 인덱스가 서로 다른 극이라면
                // 큐에 저장
                if (nextNum >= 0 && nextNum < 4 && magnets[num][currCompare] != magnets[nextNum][nextCompare]) {
                    queue.offer(new int[]{nextNum, d * -1});
                }
            }
        }

        // 리스트에 들어있는 자석들을 회전시킨다.
        for (int[] curr: willRotate) {
            rotate(magnets[curr[0]], curr[1]);
        }
    }

    // 자석 회전 함수
    private static void rotate(int[] magnet, int direction) {
        if (direction == 1) {       // 시계 방향
            int before = magnet[0];
            for (int i = 1; i < 8; i++) {
                int current = magnet[i];
                magnet[i] = before;
                before = current;
            }
            magnet[0] = before;
        } else {                    // 반시계 방향
            int after = magnet[7];
            for (int i = 6; i >= 0; i--) {
                int current = magnet[i];
                magnet[i] = after;
                after = current;
            }
            magnet[7] = after;
        }
    }
}