package june;

public class NumberOfSubarraysWithBoundedMaximum {
    public static void main(String[] args) {
        SolutionNumberOfSubarraysWithBoundedMaximum solution = new SolutionNumberOfSubarraysWithBoundedMaximum();
//        System.out.println(solution.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
    }
}

class SolutionNumberOfSubarraysWithBoundedMaximum {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) continue;
            else if (nums[i] <= right && nums[i] >= left) result++;

            int max = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > right) break;
                if (max < nums[j]) max = nums[j];

                if (max <= right && max >= left) result++;
            }
        }

        return result;
    }
}