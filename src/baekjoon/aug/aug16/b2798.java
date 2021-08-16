package baekjoon.aug.aug16;

import java.util.Scanner;

/**
 * 백준 2798 블랙잭
 *
 * 접근 방법:
 * next permutation 을 활용해 카드의 조합을 만든 후
 * 그 조합의 카드를 합해 M보다 작은 수중에 가장 큰 수를 만들어 반환한다.
 */

public class b2798 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int max = 0;
        int[] cards = new int[N];
        int[] comb = new int[N];

        for (int i = 0; i < N; ++i) {
            cards[i] = sc.nextInt();
        }

        comb[N-1] = comb[N-2] = comb[N-3] = 1;

        do {
            int sum = 0;
            for (int i = 0; i < N; ++i) {
                if (comb[i] == 1) sum += cards[i];
            }
            if (sum <= M && max < sum) max = sum;
        } while (np(comb));

        System.out.println(max);
    }

    private static boolean np(int[] arr) {
        int L = arr.length - 1;

        int i = L;
        while ((i > 0) && (arr[i-1] >= arr[i])) --i;
        if (i == 0) return false;

        int j = L;
        while (arr[i-1] >= arr[j]) --j;
        swap(arr, i-1, j);

        int k = L;
        while (i < k) swap(arr, i++, k--);

        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
