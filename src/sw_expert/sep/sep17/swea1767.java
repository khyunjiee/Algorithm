package sw_expert.sep.sep17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 1767 프로세서 연결하기
 *
 * 접근 방식:
 * core가 입력으로 들어오면 해당 위치를 리스트에 저장합니다.
 * 그 후 dfs 탐색을 통해 각 core의 사방탐색을 실시하고 maxCore가 업데이트된다면 lineLen을 변경합니다.
 * maxCore가 동일한데 lineLen이 더 짧다면 업데이트합니다.
 * 또한 가지치기를 진행하는데, 앞으로 체크해야할 코어 개수와 현재까지 연결된 코어 개수를 합쳐도
 * maxCore가 되지 않으면 바로 return하여 백트래킹합니다.
 *
 * 시간 복잡도:
 * 코어의 최대 개수인 4^12 인데 가지치기로 조금 줄어듭니다.
 *
 * 소요 시간:
 * 50분
 */

public class swea1767 {

    static int N, maxCore, lineLen;
    static List<int[]> coreList;
    // 상하좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[N][N];

            maxCore = 0;    // 현재까지 연결된 최대 코어 개수
            lineLen = Integer.MAX_VALUE;    // maxCore일 때 가장 짧은 전선 길이
            coreList = new ArrayList<>();   // 코어가 위치한 좌표 저장

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if (num == 1) coreList.add(new int[]{ i, j });  // 코어라면 리스트에 좌표 추가
                }
            }

            recursive(coreList.size(), 0, 0, map);  // dfs 탐색
            sb.append("#" + tc + " " + lineLen + "\n");
        }
        System.out.println(sb);
    }

    // dfs
    private static void recursive(int cnt, int connected, int len, int[][] map) {
        // 앞으로 체크할 코어 개수와 연결된 코어 개수를 합쳤을 때 maxCore보다 작다면
        // 이후에 체크할 필요가 없으므로 가지치기
        if (maxCore > (cnt + connected)) return;
        // 모든 코어를 고려했다면
        if (cnt == 0) {
            if (connected > maxCore) {  // 현재 연결된 코어 개수가 여태까지의 max보다 크다면
                maxCore = connected;
                lineLen = len;
            } else if (connected == maxCore && len < lineLen) {     // 코어 개수는 동일한데 전선 길이가 더 짧다면
                lineLen = len;
            }
            return;
        }

        int[] currCore = coreList.get(cnt-1);   // 현재 체크할 코어의 위치
        int r = currCore[0];
        int c = currCore[1];

        if (r == 0 || r == N-1 || c == 0 || c == N-1) { // 가장자리라면 이미 연결된 것
            recursive(cnt-1, connected+1, len, map);
        } else {
            // 가장자리가 아니라면 사방탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int line = 0;
                int[][] temp = copyArr(map);

                while (isValid(nr, nc)) {
                    if (temp[nr][nc] != 0) {
                        line = 0;
                        break;
                    }

                    line++;
                    temp[nr][nc] = 2;
                    nr += dr[d];
                    nc += dc[d];
                }

                if (line == 0) {    // 연결할 수 없는 방향
                    recursive(cnt-1, connected, len, map);
                }
                else {              // 연결할 수 있는 방향
                    recursive(cnt-1, connected+1, len+line, temp);
                }
            }
        }
    }

    private static int[][] copyArr(int[][] arr) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = Arrays.copyOf(arr[i], N);
        }
        return result;
    }

    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < N) return true;
        return false;
    }
}
