package sw_expert.sep.sep17;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1263 사람 네트워크2
 * 
 * 접근 방식:
 * 플로이드-워샬 알고리즘 활용
 *
 * 소요 시간: 20분
 */

public class swea1263 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        int TC = Integer.parseInt(br.readLine());
        int INF = 99999;

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int[][] min = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    min[i][j] = Integer.parseInt(st.nextToken());
                    if (i != j && min[i][j] == 0) min[i][j] = INF;
                }
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    if (k == i) continue;
                    for (int j = 0; j < N; j++) {
                        if (min[i][j] > min[i][k] + min[k][j]) {
                            min[i][j] = min[i][k] + min[k][j];
                        }
                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int count = 0;
                for (int j = 0; j < N; j++) {
                    count += min[i][j];
                }
                if (count < result) result = count;
            }

            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }
}
