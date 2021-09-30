package sw_expert.sep.sep30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1953 탈주범 검거
 *
 * 접근 방식:
 * bfs의 전형적인 문제라고 생각해서 풀었는데,
 * 파이프의 모양으로 움직이는 것 외에도 다음 칸의 파이프가 연결되어있는지 체크하는 것을 놓쳐서
 * 많이 헤맸습니다..
 * 꼼꼼함을 더 기르는걸로..!
 *
 * 소요 시간:
 * 1시간 30분
 */

public class swea1953 {

    static int result, map[][], N, M, L;
    // 상, 하, 좌, 우
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    // 파이프 종류
    static final int[][] pipes = {
            {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}
    };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            result = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(R, C);
            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int sr, int sc) {
        boolean[][] visited = new boolean[N][M];    // 방문 체크
        Queue<int[]> queue = new LinkedList<>();    // 큐
        queue.offer(new int[]{sr, sc, map[sr][sc], 0}); // 인덱스 0: 행, 1: 열, 2: 파이프 종류, 3: 길이(len)

        while (!queue.isEmpty()) {
            // 현재 고려할 위치
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int p = curr[2];
            int l = curr[3];

            // 길이가 L 이상이거나, 이미 고려한 좌표라면 continue
            if (l >= L || visited[r][c]) continue;
            visited[r][c] = true;   // 방문 체크
            result++;               // 파이프 개수 +1

            int[] direction = pipes[p-1];   // 현재 위치의 파이프가 이동 가능한 인덱스들
            for (int d = 0; d < direction.length; d++) {
                int index = direction[d];   // 인덱스를 차례로 검사
                int nr = r + delta[index][0];
                int nc = c + delta[index][1];

                // 좌표가 유효하고,
                // 파이프가 없는 공간이 아니고,
                // 현재 파이프와 연결되어있다면 큐에 넣기
                if (isValid(nr, nc) && map[nr][nc] != 0 && isConnected(index, pipes[map[nr][nc]-1])) {
                    queue.offer(new int[]{nr, nc, map[nr][nc], l+1});
                }
            }
        }
    }

    // 파이프의 연결 상태를 체크하는 메소드
    // from : 기준 위치의 파이프가 연결된 상태
    // to : 다음 위치의 파이프가 연결된 방향 배열
    private static boolean isConnected(int from, int[] to) {
        int expect = 0; // 상 -> 하, 하 -> 상, 좌 -> 우, 우 -> 좌 여야 함

        switch (from) {
            case 0:
                expect = 1;
                break;
            case 1:
                expect = 0;
                break;
            case 2:
                expect = 3;
                break;
            case 3:
                expect = 2;
                break;
        }

        for (int index: to) {
            if (index == expect) return true;
        }
        return false;
    }

    // 좌표 유효성 검사 메소드
    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < M) return true;
        return false;
    }
}
