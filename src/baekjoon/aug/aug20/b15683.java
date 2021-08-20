package baekjoon.aug.aug20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 15683 감시
 *
 * 접근 방법:
 * 우선 각 CCTV 번호에 맞게 가능한 방향을 배열로 선언했습니다.
 * 그 후에 입력 값으로 들어오는 0은 min에 추가하여 빈 공간의 개수를 셌습니다.
 * 입력 값으로 들어오는 1, 2, 3, 4, 5 는 CCTV 이므로 cctvList에 몇 번 CCTV가 들어왔는지 저장하고,
 * cctvLoc에는 해당 인덱스의 CCTV가 어느 좌표에 있는지 저장합니다.
 * 그 후에 watch 메소드를 순회하면서 가능한 모든 공간에 # 표시합니다.
 *
 */

public class b15683 {

    static int R, C, SIZE, min, cctv[][][];
    static char map[][];
    static List<Integer> cctvList;      // 맵에 존재하는 CCTV
    static List<int[]> cctvLoc;         // cctvList와 인덱스를 동일하게 가져가며, CCTV의 위치를 저장

    // 상 하 좌 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        // 입력이 배열이므로 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cctvInit();             // 각 CCTV가 감시할 수 있는 방향을 저장하는 cctv 배열을 초기화
        map = new char[R][C];
        cctvList = new ArrayList<>();
        cctvLoc = new ArrayList<>();

        // 맵 초기화 & 사각지대 체크 & cctv 좌표, 번호 저장
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                char c = st.nextToken().charAt(0);
                if (c == '0') min++;
                else if (c != '6'){
                    cctvList.add(Character.getNumericValue(c));     // 몇 번 CCTV인지 저장
                    cctvLoc.add(new int[]{i, j});                   // 해당 CCTV의 좌표 저장
                }
                map[i][j] = c;
            }
        }
        SIZE = cctvList.size();     // 재귀 기저조건인 SIZE 저장
        watch(0, min, map);

        System.out.println(min);
    }

    // 감시할 수 있는 위치에 # 체크 후 재귀
    private static void watch(int no, int cnt, char[][] currMap) {
        // 만약 CCTV SIZE만큼 확인하거나 빈 공간이 이미 없을 때 return
        if (no == SIZE || cnt == 0) {
            min = Math.min(cnt, min);
            return;
        }

        // 해당 CCTV가 위치한 좌표 (r,c)
        int r = cctvLoc.get(no)[0];
        int c = cctvLoc.get(no)[1];
        int[][] currCCTV = cctv[cctvList.get(no)];  // 해당 번호의 CCTV가 확인할 수 있는 방향을 가져옴

        // 방향이 여러개가 가능하면 하나씩 체크
        for (int i = 0; i < currCCTV.length; i++) {
            // 해당 CCTV가 감시한 영역을 체크할 배열 하나 더 선언 후 currMap 값을 copy
            char[][] temp = new char[R][C];
            for (int j = 0; j < R; j++) {
                System.arraycopy(currMap[j], 0, temp[j], 0, C);
            }

            int sum = 0;    // 감시할 수 있는 영역들의 합

            // 방향을 차례로 체크하면서 감시 가능한 모든 영역을 표시
            for (int j = 0; j < currCCTV[i].length; j++) {
                int d = currCCTV[i][j];     // 체크할 방향
                int nr = r + dr[d];         // next r
                int nc = c + dc[d];         // next c

                // 해당 좌표가 유효하고, 벽이 아니면 계속 반복 수행
                while (isValid(nr, nc) && temp[nr][nc] != '6') {
                    // 만약 CCTV라면 continue
                    if (temp[nr][nc] != '0' && temp[nr][nc] != '#') {
                        nr += dr[d];
                        nc += dc[d];
                        continue;
                    }

                    // 빈 공간이라면 sum - 1
                    // #은 건너뜀
                    if (temp[nr][nc] == '0') {
                        sum++;
                    }
                    temp[nr][nc] = '#';

                    nr += dr[d];
                    nc += dc[d];
                }
            }

            // 다음 CCTV 재귀호출
            watch(no+1, cnt-sum, temp);
        }

    }

    // 좌표의 유효성을 체크하는 메소드
    private static boolean isValid(int r, int c) {
        if (r<0 || r>=R || c<0 || c>=C) return false;
        return true;
    }

    // cctv의 번호마다 가능한 방향 인덱스를 저장함
    private static void cctvInit() {
        cctv = new int[6][][];
        cctv[1] = new int[][]{ {0}, {1}, {2}, {3} };
        cctv[2] = new int[][]{ {0, 1}, {2, 3} };
        cctv[3] = new int[][]{ {0, 2}, {0, 3}, {1, 2}, {1, 3} };
        cctv[4] = new int[][]{ {0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3} };
        cctv[5] = new int[][]{ {0, 1, 2, 3} };
    }

}
