package may;

public class MaximumPointsFromCards {
    public static void main(String[] args) {
        SolutionMaximumPointsFromCards solution = new SolutionMaximumPointsFromCards();
        System.out.println(solution.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));   // 12
        System.out.println(solution.maxScore(new int[]{2, 2, 2}, 2));   // 4
        System.out.println(solution.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));   // 55
        System.out.println(solution.maxScore(new int[]{1, 1000, 1}, 1));    // 1
        System.out.println(solution.maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));    // 202
        System.out.println(solution.maxScore(new int[]{100, 40, 17, 9, 73, 75}, 3));    // 248
    }
}

class SolutionMaximumPointsFromCards {
    public int maxScore(int[] cardPoints, int k) {
        int max = 0;
        int[] leftSumArr = new int[k + 1];
        int[] rightSumArr = new int[k + 1];

        leftSumArr[1] = cardPoints[0];
        rightSumArr[1] = cardPoints[cardPoints.length - 1];

        for (int i = 1; i <= k; i++) {
            leftSumArr[i] = leftSumArr[i - 1] + cardPoints[i - 1];
            rightSumArr[i] = rightSumArr[i - 1] + cardPoints[cardPoints.length - i];
        }

        for (int i = 0; i <= k; i++) {
            max = Math.max(max, leftSumArr[i] + rightSumArr[k - i]);
        }

        return max;
    }
}
