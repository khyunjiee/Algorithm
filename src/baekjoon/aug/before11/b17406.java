package baekjoon.aug.before11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17406 배열 돌리기4
 *
 * 접근 방식 :
 * 회전 연산을 순서를 다르게 해서 최솟값을 도출해야 하므로 회전 연산에 대해 순열을 조합한다.
 * 순열을 완성해나가는 과정에서 해당 회전 연산에 맞게 2차원 배열을 회전시킨다.
 * 순열을 모두 완료하면 최솟값을 도출할 수 있다.
 *
 * 시간 복잡도 :
 * O(N!) -> 순열 시간 복잡도
 *
 */

public class b17406 {

    static int d, N, M, K, min, map[][], calc[][];
    static boolean isSelect[];

    // 시계방향 우 하 좌 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        K = Integer.parseInt(st.nextToken());   // 연산 갯수
        min = Integer.MAX_VALUE;    // 리턴할 답
        d = 0;  // 현재 방향

        map = new int[N+2][M+2];    // 입력받을 배열 (+2만큼 설정해 0은 벽)
        calc = new int[K][3];       // 연산 배열
        isSelect = new boolean[K];  // 연산 선택 여부

        // 배열 초기화
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; ++j) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        // 연산 배열 초기화
        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; ++j) {
                calc[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 연산 순열 탐색
        perm(0, map);
        System.out.println(min);
    }

    // 연산 순열
    private static void perm(int cnt, int[][] arr) {
        if (cnt == K) {
            minValue(arr);
            return;
        }
        for (int i = 0; i < K; i++) {
            if (!isSelect[i]) {
                isSelect[i] = true;

                // 매개변수로 넘겨줄 배열을 perm으로 받은 arr에서 복사
                int[][] param = new int[N+2][M+2];
                for (int j = 1; j <= N; j++) {
                    System.arraycopy(arr[j], 0, param[j], 0, arr[j].length);
                }
                perm(cnt+1, rotate(calc[i][0], calc[i][1], calc[i][2], param)); // 회전 수행 후 결과 배열로 재귀
                isSelect[i] = false;
            }
        }
    }

    // 배열 회전 메소드
    private static int[][] rotate(int r, int c, int s, int[][] arr) {
        int sr = r-s;
        int sc = c-s;
        int er = r+s;
        int ec = c+s;
        int nr = sr;
        int nc = sc;
        int cnt = 0;
        int[][] rotated = new int[N+2][M+2];
        int center = arr[r][c]; // center는 무조건 숫자 하나로, 회전이 끝난 후에 삽입

        while(cnt < s) {
            int num = arr[nr][nc];  // 현재 위치의 숫자를 받아온다

            // rotated가 0이 아니면 이미 채워진 것. 회전할 시작 좌표와 끝 좌표로 위치 유효성 탐색
            if (!isValid(sr, sc, er, ec, nr+dr[d], nc+dc[d]) || rotated[nr+dr[d]][nc+dc[d]] != 0) {
                if (d == 3) {   // 4방향 다 돌았으면 한 바퀴 회전한 것
                    cnt++;
                    nr = sr + cnt;
                    nc = sc + cnt;
                    d = 0;
                    continue;
                } else {
                    d++;
                }
            }
            // 방향만큼 좌표를 더해서 rotated 배열에 넣어줌
            nr += dr[d];
            nc += dc[d];
            rotated[nr][nc] = num;
        }
        rotated[r][c] = center; // 회전 후 center 삽입

        // 매개변수로 받은 arr에 회전 결과 복사
        for (int i = sr; i <= er; ++i) {
            System.arraycopy(rotated[i], sc, arr[i], sc, ec-sc+1);
        }

        return arr;
    }

    // 좌표 유효성 체크
    private static boolean isValid(int sr, int sc, int er, int ec, int r, int c) {
        if (r>=sr && c>=sc && r<=er && c<=ec) return true;
        return false;
    }

    // 배열을 순회하면서 최솟값 찾기
    private static void minValue(int[][] arr) {
        for (int i = 1; i <= N; ++i) {
            int sum = 0;
            for(int j = 1; j <= M; ++j) {
                sum += arr[i][j];
                if (sum > min) break;
            }
            if (sum < min) min = sum;
        }
    }

}
