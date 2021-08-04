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
        // 쌤 코드
        hanoi_TH(3, 1, 2, 3);
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

    private static void hanoi_TH(int N, int start, int temp, int dest) {
        if (N == 0) return;
        // 자신으이 위쪽의 N-1개 원판 들어내기 : 임시 기둥으로 옮기기
        hanoi_TH(N-1, start, dest, temp);
        // 자신의 원판 옮기기 : 목적 기둥
        System.out.println(N + " : " + start + " > " + dest);
        // 들어냈던 임시 기둥의 N-1개 원판 자신 위에 쌓기 : 목적 기둥으로 옮기기
        hanoi_TH(N-1, temp, start, dest);
    }
}
