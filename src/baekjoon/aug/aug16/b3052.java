package baekjoon.aug.aug16;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 백준 3052 나머지
 */

public class b3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 10; ++i) {
            int num = sc.nextInt() % 42;
            set.add(num);
        }

        System.out.println(set.size());
    }
}
