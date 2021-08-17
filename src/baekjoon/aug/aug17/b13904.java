package baekjoon.aug.aug17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 13904 과제
 */

public class b13904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] homework = new int[N][2];

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            homework[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(homework, (o1, o2) -> {
            if (o1[0] == o2[0]) return o2[1]-o1[1];
            else return o1[0]-o2[0];
        });

        System.out.println(Arrays.deepToString(homework));

        int currDay = 0;
        int score = 0;
        int index = 0;

        while (index < N) {
            if (homework[index][0] >= currDay) {
                currDay++;
                score += homework[index][1];
            }
            index++;
        }

        System.out.println(score);

    }
}
