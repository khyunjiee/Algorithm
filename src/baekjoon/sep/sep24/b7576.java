package baekjoon.sep.sep24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 7576 토마토 (실버1)
 *
 * 접근 방식:
 * 익은 토마토의 좌표를 큐에 저장한 후에 bfs 탐색으로 사방탐색을 했습니다.
 * 무작정 for문을 돌려 brute-force로 접근하면 시간초과가 발생합니다.
 *
 * 소요 시간:
 * 40분
 */

public class b7576 {

    static int row, col, remain, day, tomato[][];
    static boolean isAble, visit[][];
    // 상하좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        tomato = new int[row][col];
        visit = new boolean[row][col];
        isAble = true;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < col; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 0) remain++;
                else if (tomato[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        // 처음부터 모든 토마토가 익은 상태일 때
        if (remain == 0) System.out.println(0);
        else {
            // 토마토 익는 과정
            ripe(queue);
            if (!isAble) System.out.println(-1);    // remain이 0이 넘으면 모두 익지 못하는 상황
            else System.out.println(day);           // remain이 0이라면 모두 익었으므로 day 출력
        }
    }

    // bfs 탐색
    private static void ripe(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] loc = queue.poll();
            int r = loc[0];
            int c = loc[1];
            int dayCnt = loc[2];    // 현재 날짜

            if (visit[r][c]) continue;
            if (dayCnt > 0) remain--;   // 초기 상태의 1이 아니라면 remain--;

            visit[r][c] = true; // 방문처리
            day = dayCnt;

            // 사방탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isValid(nr, nc) && tomato[nr][nc] == 0) {
                    tomato[nr][nc] = 1;
                    queue.offer(new int[]{nr, nc, dayCnt+1});
                }
            }
        }

        // 익히는 과정이 끝났는데 remain이 0 이상이라면 토마토를 모두 익히는 과정이 불가능
        if (remain > 0) isAble = false;
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < row && c >= 0 && c < col) return true;
        return false;
    }
}
