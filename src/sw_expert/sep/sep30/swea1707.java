package sw_expert.sep.sep30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * SWEA 1707 보급로 [D4]
 *
 * 접근 방식:
 * 처음에 dfs로 접근했다가 타임오버가 발생해
 * pq를 사용한 bfs로 풀었습니다.
 *
 * 소요 시간:
 * 30분
 *
 */

public class swea1707 {

    static int result, total, N, map[][];
    static final int INF = Integer.MAX_VALUE;
    // 상 하 좌 우
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    // 우선순위 큐에 저장할 현재까지의 위치와 메꾼 바닥의 cnt
    static class Position implements Comparable<Position> {
        int r, c, cnt;

        public Position(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Position o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            result = INF;

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j) - '0';
                    total += map[i][j];
                }
            }

            bfs();
            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }

    // 최소비용의 경로 bfs 탐색
    private static void bfs() {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.offer(new Position(0, 0, 0));

        while (!pq.isEmpty()) {
            Position curr = pq.poll();
            int r = curr.r;
            int c = curr.c;
            int cnt = curr.cnt;

            // 목적지에 도착하면 result 업데이트
            if (r == N-1 && c == N-1) {
                result = Math.min(result, cnt);
                continue;
            }
            if (visited[r][c]) continue;
            visited[r][c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

                // 애초에 cnt가 result보다 크다면 큐에 넣지 않음
                if (isValid(nr, nc) && cnt+map[nr][nc] < result) {
                    pq.offer(new Position(nr, nc, cnt + map[nr][nc]));
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < N) return true;
        return false;
    }
}
