package leetcode.may;

import java.util.PriorityQueue;
import java.util.Queue;

public class TargetArrayWithMultipleSums {
    public static void main(String[] args) {
        SolutionTargetArrayWithMultipleSums solution = new SolutionTargetArrayWithMultipleSums();
        System.out.println(solution.isPossible(new int[]{9, 3, 5}));
        System.out.println(solution.isPossible(new int[]{1, 1, 1, 2}));
        System.out.println(solution.isPossible(new int[]{8, 5}));
        System.out.println(solution.isPossible(new int[]{5, 2}));
        System.out.println(solution.isPossible(new int[]{1, 1, 99}));
    }
}

class SolutionTargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int totalSum = 0;
        for (int i: target) {
            totalSum += i;
            queue.add(i);
        }

        while (queue.peek() != 1) {
            int num = queue.poll();
            totalSum -= num;

            if (num <= totalSum || totalSum < 1) return false;

            num %= totalSum;
            totalSum += num;
            queue.add(num > 0 ? num: totalSum);
        }

        return true;
    }
}