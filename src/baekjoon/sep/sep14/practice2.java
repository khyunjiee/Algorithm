package baekjoon.sep.sep14;

import java.util.Scanner;

/**
 * 연습문제 2 - 막대 색칠하기
 */

public class practice2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 2;
        dp[2] = 5;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i-1] * 2 + dp[i-2];
        }

        System.out.println(dp[N]);
    }
}
