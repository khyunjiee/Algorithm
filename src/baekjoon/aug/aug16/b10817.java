package baekjoon.aug.aug16;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 10817 세 수
 */

public class b10817 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[3];
        nums[0] = sc.nextInt();
        nums[1] = sc.nextInt();
        nums[2] = sc.nextInt();

        Arrays.sort(nums);

        System.out.println(nums[1]);
    }
}
