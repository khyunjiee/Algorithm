package baekjoon.aug.aug16;

import java.util.Scanner;

/**
 * 백준 8958 OX퀴즈
 */

public class b8958 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        for (int n = 0; n < N; ++n) {
            char[] ox = sc.next().toCharArray();

            int score = 0;
            int sum = 0;
            for (char c: ox) {
                if (c == 'O') {
                    sum += ++score;
                } else {
                    score = 0;
                }
            }
            sb.append(sum + "\n");
        }
        System.out.println(sb);
    }
}
