package baekjoon.aug.aug14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2567 색종이-2
 *
 * 접근 방법 :
 * 색종이가 위치하는 곳에 모두 1로 표시한다.
 * 둘레는, 1이 위치한 곳을 찾아서 상 하 좌 우 의 0의 개수를 찾아서 둘레에 추가한다.
 *
 * 시간 복잡도 :
 * O(N^2)
 *
 */

public class b2567 {

    static int[][] map;
    static int N;
    // 상 하 좌 우 대각선
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[102][102];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            fillMap(x, y);
        }

        System.out.println(find());

    }

    private static void fillMap(int x, int y) {
        for (int i = x; i < x+10; ++i) {
            for (int j = y; j < y+10; ++j) {
                if (map[i][j] == 1) continue;
                map[i][j] = 1;
            }
        }
    }

    private static int find() {
        int result = 0;

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (map[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        if (map[i+dr[k]][j+dc[k]] == 0) {
                            result++;
                        }
                    }
                }
            }
        }

        return result;
    }

}