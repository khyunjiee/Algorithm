package leetcode.may;

public class RangeSumQueryImmutable {
    public static void main(String[] args) {
        NumMatrix obj = new NumMatrix(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        System.out.println(obj.sumRegion(2, 1, 4, 3));  // 8
        System.out.println(obj.sumRegion(1, 1, 2, 2));  // 11
        System.out.println(obj.sumRegion(1, 2, 2, 4));  // 12
    }
}

class NumMatrix {
    int[][] numMatrix;

    public NumMatrix(int[][] matrix) {
        numMatrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                numMatrix[i][j] = numMatrix[i][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int result = 0;

        for (int row = row1; row <= row2; row++) {
            if (col1 > 0) {
                result += numMatrix[row][col2] - numMatrix[row][col1 - 1];
            } else {
                result += numMatrix[row][col2];
            }
        }

        return result;
    }
}
