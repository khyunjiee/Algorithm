package sw_expert.oct.oct06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
SWEA 4014 활주로 건설

접근 방식:
단순 구현으로 접근했습니다.
행/열을 구분해서 접근하고 이전 높이와 높이의 길이가 얼마나 지속되는지 확인하고
다음 높이가 현재보다 1 높은지, 현재 높이보다 큰지 작은지 대소비교를 하면서
구현해주었습니다.

시간 복잡도:
O(N^2)

소요시간:
32분
 */

public class swea4014 {

    static int N, X, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            // 맵 입력 초기화
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("#" + tc + " " + build() + "\n");
        }
        System.out.println(sb);
    }

    // 활주로 건설
    private static int build() {
        int result = 0; // 경우의 수

        // 가로
        ROW: for (int i = 0; i < N; i++) {
            int num = map[i][0];    // 현재 절벽 높이
            int cnt = 1;            // 현재 높이의 길이
            for (int j = 1; j < N; j++) {
                // 오른쪽이 높은 경우
                if (map[i][j] > num) {
                    // 높이가 1 차이나야하고 여태까지의 cnt가 X 이상이어야 함
                    if (map[i][j] - num > 1 || cnt < X) continue ROW;
                    num = map[i][j];
                    cnt = 1;
                }
                // 오른쪽이 낮은 경우
                else if (map[i][j] < num) {
                    // 높이가 1 차이나야하고 현재 행부터 +X 길이까지 유효한 범위어야 함
                    if (num - map[i][j] > 1 || !isValid(i, j+X-1)) continue ROW;

                    // 이후 X 길이까지 모두 같은 높이인지 확인
                    for (int k = j+1; k < j+X; k++) {
                        if (map[i][j] != map[i][k]) continue ROW;
                    }
                    // 열을 늘려주고 높이와 cnt를 업데이트
                    // 활주로를 건설한 이후로 가야하므로 cnt는 0
                    j += X-1;
                    num = map[i][j];
                    cnt = 0;
                } else {    // 높이가 같을 경우는 cnt+1
                    cnt++;
                }
            }
            result++;
        }

        // 세로
        COL: for (int j = 0; j < N; j++) {
            int num = map[0][j];
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                // 아래쪽이 높은 경우
                if (map[i][j] > num) {
                    // 높이가 1 차이나고 여태까지의 길이가 X 이상이어야 함
                    if (map[i][j] - num > 1 || cnt < X) continue COL;
                    num = map[i][j];
                    cnt = 1;
                }
                // 위쪽이 높은 경우
                else if (map[i][j] < num) {
                    // 높이가 1 차이나고 현재 행부터 +X 길이까지 유효한 범위어야 함
                    if (num - map[i][j] > 1 || !isValid(i+X-1, j)) continue COL;

                    // 이후 X길이만큼 모두 같은 높이인지 확인
                    for (int k = i+1; k < i+X; k++) {
                        if (map[i][j] != map[k][j]) continue COL;
                    }
                    // 활주로를 건설할 수 있다면 행을 늘려주고 길이 업데이트.
                    // cnt는 활주로를 건설했으므로 0
                    i += X-1;
                    num = map[i][j];
                    cnt = 0;
                } else {    // 높이가 같을 경우는 cnt+1
                    cnt++;
                }
            }
            result++;
        }

        return result;
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
