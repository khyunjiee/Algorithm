package may;

import java.util.Arrays;

public class MinimumMovestoEqualArrayElements {
    public static void main(String[] args) {
        SolutionMinMoves2 solution = new SolutionMinMoves2();
//        System.out.println(solution.minMoves2(new int[]{1,3,2}));
//        System.out.println(solution.minMoves2(new int[]{1,10,2,9}));
//        System.out.println(solution.minMoves2(new int[]{1,0,0,8,6}));
//        System.out.println(solution.minMoves2(new int[]{203125577,-349566234,230332704,48321315,66379082,386516853,50986744,-250908656,-425653504,-212123143}));
    }
}

class SolutionMinMoves2 {
    public int minMoves2(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        int standardNum = nums[nums.length / 2];

        for (int num: nums) {
            result += Math.abs(num - standardNum);
        }

        return result;
    }
}
