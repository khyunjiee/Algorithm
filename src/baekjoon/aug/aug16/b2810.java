package baekjoon.aug.aug16;

import java.util.Scanner;

/**
 * 백준 2810 컵홀더
 */

public class b2810 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = 1;
        char[] seating = sc.next().toCharArray();

        int couple = 0;
        for (char c: seating) {
            if (c == 'S') ++result;
            else {
                ++couple;
                if (couple % 2 == 0) {
                    ++result;
                    couple %= 2;
                }
            }
        }

        System.out.println((result>N)? N: result);
    }
}
