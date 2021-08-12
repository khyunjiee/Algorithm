package baekjoon.aug;

import java.util.Scanner;

/**
 * 백준 3040 백설 공주와 일곱 난장이
 *
 * 접근 방법 :
 * 9개의 입력 정수 중에 7개의 조합을 고르는 문제다.
 * 조합 중 해당 원소들의 합이 100이 되어야 출력한다.
 * 그래서 next permutation 을 조합에 적용해 풀이했다.
 * bits 배열은 비트마스킹을 의미하고, 처음에는 마지막 원소부터 7개의 원소가 1로 세팅되고,
 * 앞부분의 2 원소는 0으로 초기화되어있다.
 * 여기서 np 메소드를 반복하면서 bits 배열이 순열로 재조합되고
 * 그 중에서 원소가 1인 인덱스를 구해 arr에 해당 인덱스 값을 sum에 더한다.
 * sum이 100이 되었다면 답을 도출했으므로, break로 while문을 빠져나오고 배열을 출력한다.
 *
 * 시간 복잡도 :
 * O(2^N)
 *
 */

public class b3040 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = 9;
        int[] arr = new int[N];
        int[] bits = new int[N];

        for (int i = 0; i < 9; ++i) {
            arr[i] = sc.nextInt();
        }

        for (int i = N-1; i >= 2; --i) {
            bits[i] = 1;
        }

        do {
            int sum = 0;
            for (int i = 0; i < N; ++i) {
                if (bits[i] != 0) sum += arr[i];
            }
            if (sum == 100) break;
        } while(np(bits));

        for (int i = 0; i < bits.length; ++i) {
            if (bits[i] != 0) System.out.println(arr[i]);
        }

    }

    private static boolean np(int[] arr) {
        int N = arr.length-1;

        int i = N;
        while ((i > 0) && (arr[i-1] >= arr[i])) --i;
        if (i == 0) return false;

        int j = N;
        while (arr[i-1] >= arr[j]) --j;
        swap(arr, i-1, j);

        int k = N;
        while (i < k) swap(arr, i++, k--);

        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
