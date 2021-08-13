package baekjoon.aug.before11;

import java.util.Scanner;

public class b2615 {

    static int[][] map = new int[19][19];

    // 우, 하, 우하, 우상
    static int[] dr = { 0, 1, 1, -1 };
    static int[] dc = { 1, 0, 1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 배열 초기화
        for (int i = 0; i < 19; ++i) {
            for (int j = 0; j < 19; ++j) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < 19; ++i) {
            for (int j = 0; j < 19; ++j) {
                if (map[i][j] != 0 && isWinner(i, j, map[i][j])) {
                    System.out.println(map[i][j]);
                    System.out.println((i+1) + " " + (j+1));
                    return;
                }
            }
        }

        System.out.println(0);
    }

    // 승자 탐색
    private static boolean isWinner(int r, int c, int num) {
        for (int i = 0; i < 4; ++i) {
            int count = 0;
            int row = r;
            int column = c;
            while (validPoint(row, column, i) && map[row][column] == num) {
                count++;
                row += dr[i];
                column += dc[i];
            }
            if (count == 5 && isFive(r, c, i, num)) return true;
        }
        return false;
    }

    // 유효한 인덱스인지 확인
    private static boolean validPoint(int r, int c, int index) {
        if (r < 0 || r >= 19 || c < 0 || c >= 19) {
            return false;
        } else {
            return true;
        }
    }

    // 바둑알이 5개 이상인지 확인
    private static boolean isFive(int r, int c, int index, int num) {
        int pr = dr[index];
        int pc = dc[index];

        if (validPoint(r+pr*5, c+pc*5, index) && map[r+pr*5][c+pc*5] == num) {
            return false;
        }
        if (validPoint(r-pr, c-pc, index) && map[r-pr][c-pc] == num) {
            return false;
        }
        return true;
    }

}

