package baekjoon.aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 16926 배열돌리기1
 *
 * 접근 방법 :
 * 배열 전체를 한 번 회전시킬 때마다 temp 배열에 회전한 값을 넣어둔다.
 * 그 후에 원래 배열인 map 배열에 deep copy를 진행해 배열을 업데이트 시킨다.
 * 회전은 (1,1)부터 시작하며, 아래-오른쪽-위-왼쪽 순서로 한바퀴를 돈다.
 * 한바퀴를 회전했다면 더 회전할 수 있는지 확인 후 모두 회전했으면 메소드를 temp 배열을 map 으로 copy한 후 메소드를 끝낸다.
 *
 * 시간 복잡도 :
 * O(N^2)..?
 *
 */

public class b16926 {
    static int d, N, M, map[][], temp[][];
    // 하 우 상 좌 -> 회전 방향
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());       // 행
        M = Integer.parseInt(st.nextToken());       // 열
        int R = Integer.parseInt(st.nextToken());   // 회전 횟수
        d = 0;  // 방향

        map = new int[N+2][M+2];    // 듈래애 padding을 줘서 벽임을 의미함

        // original 배열 초기화
        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int m = 1; m <= M; ++m) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 수만큼 rotate
        for (int r = 0; r < R; ++r) {
            rotate();
        }

        // 결과값 저장
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    // 배열 회전 메소드
    private static void rotate() {
        temp = new int[N+2][M+2];   // 임시 배열에 회전한 결과를 적재한다

        int start = 1;  // (1,1) 부터 회전 시작
        int nr = start;
        int nc = start;
        int standard = Math.min(N, M) / 2;  // 4방향을 몇 번 돌아야 하는지

        // 바퀴를 회전하는 기준이 start, start가 standard 가 넘어가면 모두 회전시킨 것
        while (start <= standard) {
            int num = map[nr][nc];  // 현재 위치의 숫자를 받아온다

            // 다음 방향에 있는 숫자가 map에서 0이면 벽, temp에서 0이 아니면 이미 채워졌으므로 방향 전환
            if (map[nr+dr[d]][nc+dc[d]] == 0 || temp[nr+dr[d]][nc+dc[d]] != 0) {
                if (d == 3) {   // 4방향 다 돌았으면 한 바퀴 회전한 것
                    start++;
                    nr = start;
                    nc = start;
                    d = 0;
                    continue;
                } else {
                    d++;
                }
            }
            // 방향만큼 좌표를 더해서 temp 배열에 넣어줌
            nr += dr[d];
            nc += dc[d];
            temp[nr][nc] = num;
        }

        // temp 배열을 map 으로 deep copy
        copyArray();
    }

    // 2차원 배열 복사 메소드
    private static void copyArray() {
        for (int i = 1; i <= N; i++) {
            System.arraycopy(temp[i], 0, map[i], 0, temp[i].length);
        }
    }

}
