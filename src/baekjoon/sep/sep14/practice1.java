package baekjoon.sep.sep14;

import java.util.Scanner;

/**
 * 연습문제 1 - 아파트 색칠하기
 */

public class practice1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] y = new int[N+1];
        y[0] = 1;
        y[1] = 1;

        int[] apt = new int[N+1];
        apt[0] = 0;
        apt[1] = 2;

        for (int i = 2; i <= N; i++) {
            y[i] = y[i-2] + y[i-1];
            apt[i] = apt[i-1] + y[i-1];
        }

        System.out.println(apt[N]);
    }
}
