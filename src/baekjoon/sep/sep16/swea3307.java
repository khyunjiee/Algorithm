package baekjoon.sep.sep16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 3307 최장 증가 부분 수열
 */

public class swea3307 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] LIS = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int size = 0;
            for (int i = 0; i < N; i++) {
                int index = Math.abs(Arrays.binarySearch(LIS, 0, size, arr[i])) - 1;
                LIS[index] = arr[i];
                if (size == index) ++size;
            }

            sb.append("#" + tc + " " + size + "\n");
        }
        System.out.println(sb);
    }
}
