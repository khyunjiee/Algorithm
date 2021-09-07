package june;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OutOfBoundaryPaths {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.findPaths(2, 2, 2, 0, 0));
//        System.out.println(solution.findPaths(1, 3, 3, 0, 1));
        System.out.println(solution.findPaths(8, 7, 16, 1, 5));
    }
}

class Solution {
    Map<String, ArrayList<Integer>> cornerCells = new HashMap<>();
    int[][] cells;
    int result = 0;
    int cornerRow, cornerColumn;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        cells = new int[m+2][n+2];
        cornerRow = m;
        cornerColumn = n;

        // 초기화
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cells[i][j] = 1;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (isCorner(i, j)) checkPaths(i, j, 0, maxMove);
                else {

                }
            }
        }

        return result;
    }

    private boolean isCorner(int row, int colunm) {
        if (row == 1 || row == cornerRow) return true;
        else if (colunm == 1 || colunm == cornerColumn) return true;
        else return false;
    }

    private void checkPaths(int row, int column, int currentMove, int maxMove) {
        if (currentMove == maxMove) return;

        if (cells[row][column] == 0) {
            result++;
            if (!cornerCells.containsKey(row+""+column)) cornerCells.put(row+""+column, new ArrayList<>());
            cornerCells.get(row+""+column).add(currentMove);
        }

        checkPaths(row, column-1, currentMove+1, maxMove);
        checkPaths(row, column+1, currentMove+1, maxMove);
        checkPaths(row-1, column, currentMove+1, maxMove);
        checkPaths(row+1, column, currentMove+1, maxMove);
    }
}