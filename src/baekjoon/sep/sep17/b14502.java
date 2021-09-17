package baekjoon.sep.sep17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 14502 연구소
 *
 * 접근 방식:
 * dfs 방식으로 벽을 세울 수 있는 모든 경우의 수를 체크합니다.
 * 3개 벽을 모두 세웠다면, 그 때의 map으로 바이러스를 bfs 알고리즘을 활용해 확산시켜보고
 * 안전 구역을 체크합니다.
 * 위의 과정을 반복합니다.
 *
 * 주의할 점:
 * 매개변수로 넘겨진 배열의 초기화를 조심해야 합니다.
 *
 * 시간 복잡도:
 * N, M의 최대가 24이므로 완탐해도 시간 무리가 없을 것 같아
 * 고려하지 않고 완전탐색 했습니다.
 *
 * 소요 시간:
 * 40분
 */

public class b14502 {

    static int N, M, totalSafe, birusCnt, map[][];
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) totalSafe++;  // 빈 공간이라면 totalSafe 변수에 1 더해줍니다.
                map[i][j] = num;
            }
        }

        birusCnt = totalSafe;   // 처음 바이러스가 확산되는 영역을 모든 안전구역+3개의 벽 이라고 생각합니다.
        wall(0, map);    // 벽을 세웁니다

        System.out.println(totalSafe - birusCnt - 3);    // 총 안전구역에서 바이러스 구역과 벽 3개를 제외하면 답입니다.
    }

    // 3개의 벽을 세우는데 dfs 사용
    private static void wall(int cnt, int[][] map) {
        if (cnt == 3) { // 3개의 벽을 모두 세웠다면
            // 바이러스를 확산시킵니다.
            diffuse(map);
            return;
        }

        // map 배열을 복사해서 사용합니다.
        int[][] temp = copy(map);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) continue;   // 빈 공간이 아니라면 벽을 세울 수 없으므로 continue

                temp[i][j] = 1; // temp 배열을 1로 변경하고
                wall(cnt+1, temp);  // 이어서 벽을 세웁니다.
                temp[i][j] = 0; // 재귀를 빠져나오면 temp 배열을 다시 0으로 만들고 다른 벽을 세우는 시도를 합니다.
            }
        }
    }

    // 바이러스 확산 (bfs)
    private static void diffuse(int[][] map) {
        int count = 0;  // 현재 map에서 확산된 바이러스 수
        boolean[][] visited = new boolean[N][M];
        int[][] temp = copy(map);   // 배열을 복사해서 사용합니다.

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) { // 바이러스라면
                    // bfs 탐색을 시도합니다.
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{ i, j });

                    while (!queue.isEmpty() ) {
                        // 만약 현재까지 구한 바이러스 수보다 크거나 같다면 return. (가지치기)
                        // 더 이상 계산할 필요가 없음
                        if (count >= birusCnt) return;

                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];

                        if (visited[r][c]) continue;    // 이미 방문한 곳이면 continue
                        visited[r][c] = true;

                        // 사방 탐색
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            // 빈 공간이면 temp 배열에서 2로 변경하고 queue에 삽입
                            if (isValid(nr, nc) && temp[nr][nc] == 0) {
                                count++;
                                temp[nr][nc] = 2;
                                queue.offer(new int[]{ nr, nc });
                            }
                        }
                    }
                }
            }
        }

        // birusCnt 갱신
        birusCnt = count;
    }

    // 배열 복사 부분 메소드로 분리
    private static int[][] copy(int[][] map) {
        int[][] temp = new int[N][M];

        for(int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(map[i], M);
        }
        return temp;
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r>=0 && r<N && c>=0 && c<M) return true;
        return false;
    }
}
