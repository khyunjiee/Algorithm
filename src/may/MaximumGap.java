package may;

import java.util.Arrays;

public class MaximumGap {
    public static void main(String[] args) {
        SolutionMaximumGap solution = new SolutionMaximumGap();
        System.out.println(solution.maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(solution.maximumGap(new int[]{10}));
    }
}

class SolutionMaximumGap {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;

        Arrays.sort(nums);
        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int gap = Math.abs(nums[i] - nums[i + 1]);
            if (maxGap < gap) maxGap = gap;
        }

        return maxGap;
    }
}
