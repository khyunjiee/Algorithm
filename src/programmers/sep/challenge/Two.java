package programmers.sep.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Two {
    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(Arrays.toString(s.solution(new String[]{"SL","LR"})));
//        System.out.println(Arrays.toString(s.solution(new String[]{"S"})));
//        System.out.println(Arrays.toString(s.solution(new String[]{"R","R"})));
    }
}

class Solution {

    // 상 우 하 좌
    // R : +1, L : -1, S : 그대로
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int R, C, mask[][];
    static List<Integer> result = new ArrayList<>();

    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        char[][] map = new char[R][C];
        mask = new int[R][C];

        // 맵 초기화
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = grid[i].charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 4; d++) {
                    light(map, i, j, d, 0, i, j);
                }
            }
        }

        for (int i = result.size()-1; i >= 0; i--) {
            if (result.get(i) == 0) result.remove(i);
        }
        int[] answer = result.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    private void light(char[][] map, int row, int col, int dir, int cnt, int sr, int sc) {
        if (row == sr && col == sc && (mask[sr][sc] & (1 << dir)) != 0) {
            result.add(cnt);
            return;
        }
        mask[row][col] |= (1 << dir);

        switch (map[row][col]) {
            case 'L':
                dir = (dir-1 < 0)? 3: dir-1;
                break;
            case 'R':
                dir = (dir+1 >= 4)? 0: dir+1;
                break;
        }

        int[] next = loc(row+dr[dir], col+dc[dir]);
        light(map, next[0], next[1], dir, cnt+1, sr, sc);
    }

    private static int[] loc(int r, int c) {
        int[] result = {r, c};

        if (r < 0) result[0] = R-1;
        else if (r >= R) result[0] = 0;
        if (c < 0) result[1] = C-1;
        else if (c >= C) result[1] = 0;

        return result;
    }
}
