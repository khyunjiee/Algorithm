package sw_expert.sep.sep29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 5643 키 순서
 *
 * 접근 방식:
 * 플로이드-와샬 알고리즘을 활용했습니다.
 * students를 이차원 배열로 만들어서 인접해있다면 1로 저장합니다.
 * 그 후에 플로이드-와샬 알고리즘으로 출발/경유/도착지를 고려해 저장합니다.
 * 후에 students 배열에서 INF가 아닌 것은 알 수 있는 경우이므로, count에 1을 추가해주었습니다
 * count에서 N-1 인 경우는 모든 경우를 알 수 있는 학생이므로 result를 +1 해주었습니다.
 *
 * 시간 복잡도:
 * O(N^3)
 *
 * 소요 시간:
 * 2시간 30분
 */

public class swea5643 {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            int[][] students = new int[N+1][N+1];
            int result = 0;

            for (int i = 1; i <= N; i++) {
                Arrays.fill(students[i], INF);
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                students[a][b] = 1;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (i == k) continue;
                    for (int j = 1; j <= N; j++) {
                        if (students[i][j] > students[i][k] + students[k][j])
                            students[i][j] = students[i][k] + students[k][j];
                    }
                }
            }

            int[] count = new int[N+1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (students[i][j] != INF) {
                        count[i]++;
                        count[j]++;
                    }
                }
            }
            for (int i = 1; i <= N; i++) {
                if (count[i] == N-1) result++;
            }

            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }
}
