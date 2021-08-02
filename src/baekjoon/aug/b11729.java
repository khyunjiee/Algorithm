package baekjoon.aug;

import java.util.Scanner;

public class b11729 {
    static StringBuilder sb = new StringBuilder();
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        hanoi(N, 1, 2, 3);

        System.out.println(count);
        System.out.println(sb.toString());
    }

    private static void hanoi(int N, int current, int temp, int dest) {
        if (N == 1) {
            sb.append(current + " " + dest + "\n");
            count++;
            return;
        }
        hanoi(N-1, current, dest, temp);
        sb.append(current + " " + dest + "\n");
        count++;
        hanoi(N-1, temp, current, dest);
    }
}
