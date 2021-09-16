package baekjoon.sep.sep16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 2636 치즈
 *
 * 접근 방식:
 * 우선 공기부터 먼저 체크합니다.
 * 테두리인 (0,0)은 무조건 공기이므로 (0,0)과 이어진 좌표를 모두 탐색해 0인 것을 찾아내 공기라는 표시를 합니다.
 * 해당 과정에서 bfs를 사용합니다.
 * 공기를 체크한 후에 map을 탐색해 치즈가 공기랑 닿아있으면 해당 좌표의 map 값을 0으로 바꾼 후 totalCheeze 값을 1씩 감소합니다.
 * 치즈가 녹는 과정이 끝나면 time을 1시간 추가해주고, 다시 공기 부분을 체크합니다.
 * totalCheeze 가 0이 되는 순간 beforeCheeze와 time을 출력합니다.
 */

public class b2636 {

    static int row, col, totalCheeze, beforeCheeze, time, map[][];
    static boolean air[][];

    // 상, 하, 좌, 우
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        air = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < col; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    totalCheeze++;  // 총 치즈 개수
                    map[i][j] = num;    // 맵 초기화
                }
            }
        }

        while (totalCheeze > 0) {
            beforeCheeze = totalCheeze; // totalCheeze값이 0이 아니라면 치즈가 녹는 과정이 진행중이므로 beforeCheeze값을 totalCheeze값으로 바꿔줍니다.
            setAir();   // 공기 부분을 체크합니다.
            melt();     // 공기와 닿아있는 치즈는 녹습니다.
        }

        System.out.println(time);
        System.out.println(beforeCheeze);
    }

    private static void melt() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 1) {   // 치즈라면
                    for (int d = 0; d < 4; d++) {   // 사방탐색
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (isValid(nr, nc) && air[nr][nc]) {   // 공기와 닿아있다면
                            map[i][j] = 0;  // 치즈가 녹음
                            totalCheeze--;  // 치즈 개수 감소
                            break;  // 사방탐색 탈출
                        }
                    }
                }
            }
        }
        time++; // 1시간 경과 처리합니다.
    }

    // bfs 탐색
    private static void setAir() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{ 0, 0 });

        boolean[][] visited = new boolean[row][col];    // 방문 여부 배열

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if (visited[r][c]) continue;

            visited[r][c] = air[r][c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isValid(nr, nc) && map[nr][nc] == 0) queue.offer(new int[]{ nr, nc });
            }
        }
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r>=0 && r<row && c>=0 && c<col) return true;
        return false;
    }
}