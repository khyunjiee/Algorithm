package baekjoon.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class b2108 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        double sum = 0;

        int[] positive = new int[4002];
        int[] negative = new int[4001];

        for (int n = 0; n < N; ++n) {
            int num = sc.nextInt();
            arr[n] = num;
            sum += num;

            if (num < 0) negative[-num]++;
            else positive[num]++;
        }

        int count = 0;
        List<Integer> modeList = new ArrayList<>();

        for (int i = negative.length-1; i >= 0; --i) {
            if (negative[i] != 0) {
                if (count == negative[i]) {
                    modeList.add(-i);
                }
                else if (count < negative[i]) {
                    count = negative[i];
                    modeList = new ArrayList<>();
                    modeList.add(-i);
                }
            }
        }

        for (int j = positive.length-1; j >= 0; --j) {
            if (positive[j] != 0) {
                if (count == positive[j]) {
                    modeList.add(j);
                }
                else if (count < positive[j]) {
                    count = negative[j];
                    modeList = new ArrayList<>();
                    modeList.add(j);
                }
            }
        }

        Arrays.sort(arr);

        System.out.println(Math.round((double)sum/N));
        System.out.println(arr[(N-1)/2]);

        if (modeList.size() == 1) System.out.println(modeList.get(0));
        else System.out.println(modeList.get(1));

        System.out.println(arr[N-1] - arr[0]);

    }
}
