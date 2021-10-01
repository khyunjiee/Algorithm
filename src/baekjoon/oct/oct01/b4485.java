package baekjoon.oct.oct01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 4485 녹색 옷 입은 애가 젤다지? [G4]
 *
 * 접근 방식:
 * 다익스트라 알고리즘을 활용했습니다.
 * 해당 좌표까지 모은 블랙 루피의 개수를 최소로 하는 경로를 찾습니다.
 * 입력이 특이하게 들어와서 do~while 문을 사용해줬습니다.
 *
 * 시간 복잡도:
 *
 * 소요 시간: 15분
 * 17204KB 332ms
 */

public class b4485 {
    // 검은 루피를 pq에 담을 것이므로 클래스 생성
    static class BlackRupee implements Comparable<BlackRupee>{
        int r, c, rupee;

        public BlackRupee(int r, int c, int rupee) {
            this.r = r;
            this.c = c;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(BlackRupee o) {
            return this.rupee - o.rupee;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, map[][], min[][];
    // 상 하 좌 우
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        int tc = 1;

        do {
            map = new int[N][N];
            min = new int[N][N];

            // 입력 처리
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(min[i], INF);   // 최소값 배열은 INF로 채운다
            }
            // 다익스트라
            dijkstra();
            // 결과값 저장
            sb.append("Problem " + tc++ + ": " + min[N-1][N-1] + "\n");
            N = Integer.parseInt(br.readLine());
        } while (N > 0);

        System.out.println(sb);
    }

    // 다익스트라 알고리즘
    private static void dijkstra() {
        min[0][0] = map[0][0];  // 시작 정점을 해당 좌표의 검은 루피값으로 min 업데이트
        boolean[][] visited = new boolean[N][N];    // 방문 체크 배열

        PriorityQueue<BlackRupee> pq = new PriorityQueue<>();
        pq.offer(new BlackRupee(0, 0, min[0][0]));  // 시작 정점을 pq에 삽입

        while (!pq.isEmpty()) {
            BlackRupee current = pq.poll();
            int r = current.r;
            int c = current.c;
            int rupee = current.rupee;

            // 방문 처리
            if (visited[r][c]) continue;
            visited[r][c] = true;

            // 도착지에 도달했다면 break
            if (r == N-1 && c == N-1) break;

            // 사방 탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

                // 좌표가 유효하고, 방문하지 않았고
                // 해당 좌표의 min 값이 현재까지의 rupee + 해당 좌표의 rupee 보다 크다면 업데이트 후 pq에 삽입
                if (isValid(nr, nc) && !visited[nr][nc] && min[nr][nc] > rupee + map[nr][nc]) {
                    min[nr][nc] = rupee + map[nr][nc];
                    pq.offer(new BlackRupee(nr, nc, min[nr][nc]));
                }
            }
        }
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < N) return true;
        return false;
    }
}
