package june;

import java.util.Arrays;
import java.util.Collections;

public class MatchsticksToSquare {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.makesquare(new int[]{3, 3, 3, 3, 4}));
        System.out.println(solution.makesquare(new int[]{1, 1, 2, 2, 2}));
    }
}

class Solution {
    public boolean makesquare(int[] matchsticks) {
        boolean result = false;

        if (matchsticks == null || matchsticks.length < 4) return result;
        int sum = 0;
        for (int stick: matchsticks) {
            sum += stick;
        }
        if (sum % 4 != 0) return false;

        Integer[] matchsticksArray = Arrays.stream(matchsticks).boxed().toArray(Integer[]::new);
        Arrays.sort(matchsticksArray, Collections.reverseOrder());

        return dfs(matchsticksArray, new int[4], 0, sum / 4);
    }

    private boolean dfs(Integer[] matchsticks, int[] sums, int index, int target) {
        if (index == matchsticks.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i] + matchsticks[index] > target) continue;
            sums[i] += matchsticks[index];
            if (dfs(matchsticks, sums, index + 1, target)) return true;
            sums[i] -= matchsticks[index];
        }

        return false;
    }
}