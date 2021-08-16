package baekjoon.aug.aug16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3975 롤케이크
 */

public class b3985 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int[] roll = new int[L+1];
        int expectMax = 0, expectPerson = 0;
        int max = 0, person = 0;

        for (int i = 1; i <= N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (end-start > expectMax) {
                expectMax = end-start;
                expectPerson = i;
            }

            int cnt = 0;
            for (int j = start; j <= end; ++j) {
                if (roll[j] == 0) {
                    roll[j] = i;
                    cnt++;
                }
            }

            if (cnt > max) {
                max = cnt;
                person = i;
            }
        }

        System.out.println(expectPerson);
        System.out.println(person);
    }
}
