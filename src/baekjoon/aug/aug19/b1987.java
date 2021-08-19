package baekjoon.aug.aug19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1987 알파벳
 *
 * 접근 방식:
 * DFS 로 풀었습니다.
 * 이미 갔던 알파벳은 isVisit 배열에 boolean으로 저장했습니다.
 * 인덱스는 알파벳을 A로 뺀 값을 인덱스로 저장해 쉽게 접근했습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 */

public class b1987 {

    static int R, C, max;
    static char map[][];
    static boolean isVisit[];

    // 상 하 좌 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        max = Integer.MIN_VALUE;
        map = new char[R][C];
        isVisit = new boolean[26];  // 총 26개의 알파벳에 대한 방문 여부를 체크하는 배열

        // 맵 초기화
        for (int i = 0; i < R; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        isVisit[map[0][0]-'A'] = true;  // 말이 처음 있는 자리를 true로 설정
        go(0, 0, 1);        // 처음 있었던 자리부터 1 카운트
        System.out.println(max);
    }

    private static void go(int r, int c, int cnt) {
        max = Math.max(cnt, max);       // 호출받을 때마다 max값을 계산

        // 사방을 탐색한다.
        for (int d = 0; d < 4; ++d) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 다음 좌표가 유효하고 한번도 방문하지 않은 알파벳이라면
            if (isValid(nr, nc) && !isVisit[map[nr][nc]-'A']) {
                isVisit[map[nr][nc]-'A'] = true;    // 방문 처리 후
                go(nr, nc, cnt+1);            // 재귀
                isVisit[map[nr][nc]-'A'] = false;  // 방문 처리 해제
            }
        }
    }

    // 좌표 위치 값 확인
    private static boolean isValid(int r, int c) {
        if (r<0 || r>=R || c<0 || c>=C) return false;
        return true;
    }

}
