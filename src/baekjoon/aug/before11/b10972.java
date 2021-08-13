package baekjoon.aug.before11;

import java.util.Scanner;

/**
 * 백준 10972 다음 순열
 */

public class b10972 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];

        for (int n = 0; n < N; n++) {
            nums[n] = sc.nextInt();
        }

        if (np(nums)) {
            for (int i = 0; i < N; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println(-1);
        }
    }

    private static boolean np(int[] numbers) {
        int L = numbers.length-1;

        int i = L;
        while (i > 0 && numbers[i-1] >= numbers[i]) --i;

        if (i == 0) return false;

        int j = L;
        while (numbers[i-1] >= numbers[j]) --j;
        swap(numbers, i-1, j);

        int k = L;
        while (i < k) swap(numbers, i++, k--);

        return true;
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
