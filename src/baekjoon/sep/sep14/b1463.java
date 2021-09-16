package baekjoon.sep.sep14;

import java.util.Scanner;

/**
 * 백준 1463 1로 만들기
 */

public class b1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int[] D = new int[X+1];

        for (int i = 2; i <= X; i++) {
            D[i] = D[i-1]+1;
            if (i % 3 == 0 && D[i] > D[i/3]+1) D[i] = D[i/3]+1;
            if (i % 2 == 0 && D[i] > D[i/2]+1) D[i] = D[i/2]+1;
        }

        System.out.println(D[X]);
    }
}
