package programmers.sep.sep15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1600 말이 되고픈 원숭이
 *
 * 접근 방식:
 * bfs와 메모이제이션을 활용했습니다.
 * 또한 이동 가능한 방향이 상하좌우와 말처럼 이동했을 때 이기 때문에
 * dr, dc의 길이 4까지는 인접한 곳, 이후로는 말처럼 이동했을 때로 나누었습니다.
 * 한번 체크한 곳은 visited로 방문처리해서 다시 방문하지 않았습니다.
 *
 * 시간 복잡도:
 * 잘 모르겠지만.. O(W*H*K)..?
 * 최대가 200 * 200 * 30 = 1200000 이므로 시간 초과가 발생하진 않고,
 * 메모이제이션을 하지 않는다면 메모리 초과가 발생합니다.
*/

public class b1600 {

    static int W, H, K, input[][];
    static boolean visited[][][];
    // 상, 하, 좌, 우 + 말처럼 이동하기
    static int[] dr = { -1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1, 1, 2 };

    // 큐에 넣을 원숭이들의 위치
    static class Monkey {
        int r, c, cnt, k;

        public Monkey(int r, int c, int cnt, int k) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());    // 말처럼 이동할 수 있는 최대 횟수

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());   // 가로 길이 = C
        H = Integer.parseInt(st.nextToken());   // 세로 길이 = R

        input = new int[H][W];  // 입력으로 들어오는 map을 저장
        visited = new boolean[H][W][K+1];   // 방문 처리 배열은 말처럼 이동했을 때가 모두 다르므로, 같이 처리
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Monkey> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.offer(new Monkey(0, 0, 0, 0));

        while (!queue.isEmpty()) {  // 큐가 빌 때까지 반복
            Monkey curr = queue.poll();

            if (curr.r == H-1 && curr.c == W-1) {   // 도착점까지 도착한 경우
                return curr.cnt;
            }

            int dir = curr.k >= K? 4: 12;   // 말처럼 이동할 수 있는지 체크
            for (int d = 0; d < dir; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if (!isValid(nr, nc)) continue; // 유효한 좌표이고, map에서 장애물이 있는 위치가 아니여야 함

                if (d < 4) {    // 원숭이처럼 이동
                    if (visited[nr][nc][curr.k]) continue;  // 이미 방문한 곳은 재방문하지 않음
                    visited[nr][nc][curr.k] = true;
                    queue.offer(new Monkey(nr, nc, curr.cnt+1, curr.k));
                } else {    // 말처럼 이동
                    if (visited[nr][nc][curr.k+1]) continue;
                    visited[nr][nc][curr.k+1] = true;
                    queue.offer(new Monkey(nr, nc, curr.cnt+1, curr.k+1));
                }
            }
        }

        return -1;
    }

    // 좌표 유효성 검사 + 해당 좌표에 장애물이 있는지 체크
    private static boolean isValid(int r, int c) {
        if (r>=0 && r<H && c>=0 && c<W && input[r][c] != 1) return true;
        return false;
    }
}
