package may;

public class NQueens {
    public static void main(String[] args) {
        SolutionNQueens solution = new SolutionNQueens();
//        System.out.println(solution.totalNQueens(4));
        System.out.println(solution.totalNQueens(5));
//        System.out.println(solution.totalNQueens(6));
    }
}

class SolutionNQueens {
    int result = 0;

    // 0: 비어있음 1: 퀸 있음 -1: 퀸 못놓음
    public int totalNQueens(int n) {
        placeQueens(new int[n], 0, n);
        return result;
    }

    private void placeQueens(int[] array, int row, int n) {
        if (row == n) {
            result++;
            return;
        }
        for (int i = 0; i < n; i++) {
            array[row] = i;
            if (checkValidation(array, row)) {
                placeQueens(array, row + 1, n);
            }
        }
    }

    private boolean checkValidation(int[] array, int row) {
        for (int i = 0; i < row; i++) {
            if (array[i] == array[row] || row - i == Math.abs(array[i] - array[row])) {
                return false;
            }
        }
        return true;
    }
}
