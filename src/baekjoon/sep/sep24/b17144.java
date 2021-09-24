package baekjoon.sep.sep24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 17144 미세먼지 안녕! (골드5)
 *
 * 접근 방식:
 * 시뮬레이션으로 접근했습니다.
 * 미세먼지가 동시에 확산되어야하기 때문에 임시 배열과 원래 배열 간의 구분이 중요합니다.
 * 공기청정기 작동은 위쪽과 아래쪽 구분만 잘한다면, 어렵지 않았습니다.
 *
 * 시간 복잡도:
 * O(R*C*T)
 *
 * 소요 시간:
 * 1시간
 */

public class b17144 {

    static int R, C, T, cleaner[], map[][];
    // 상하좌우
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static int[] upper = {3, 0, 2, 1};  // 반시계방향
    static int[] down = {3, 1, 2, 0};   // 시계방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        cleaner = new int[2];

        // 맵 초기화
        for (int i = 0, k = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) cleaner[k++] = i;      // 공기청정기 좌표 저장
            }
        }

        while (T-- > 0) {
            // 미세먼지 확산
            diffuse();
            // 공기청정기 작동
            cleanAir(upper, cleaner[0], 0); // 위쪽 공기청정기. 반시계방향
            cleanAir(down, cleaner[1], 0);  // 아래쪽 공기청정기. 시계방향
        }

        System.out.println(totalDust());
    }

    // 미세먼지 확산
    private static void diffuse() {
        int[][] temp = copyArray(); // 임시 배열

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {    // 미세 먼지가 존재하면
                    int dust = map[i][j];   // 원래 미세먼지의 양
                    int diffuseDust = dust/5;   // 확산될 미세먼지의 양
                    int cnt = 0;    // 확산된 장소 수

                    // 사방으로 확산
                    for (int d = 0; d < 4; d++) {
                        int nr = i + delta[d][0];
                        int nc = j + delta[d][1];
                        if (isValid(nr, nc) && map[nr][nc] != -1) { // 좌표가 유효하고 공기청정기가 아니면 확산 가능
                            temp[nr][nc] += diffuseDust;    // 임시 배열에 미세먼지 확산량 누적
                            cnt++;  // 장소 수 +1
                        }
                    }

                    temp[i][j] -= (diffuseDust * cnt);  // 임시 배열에서 확산된 미세먼지 양 감소시킴
                }
            }
        }

        map = temp; // 임시 배열을 map으로 치환
    }

    // 공기 청정기 작동
    // index : 반시계 또는 시계 방향 순서에 맞춘 인덱스 (upper, down)
    // r, c : 공기 청정기의 좌표
    private static void cleanAir(int[] index, int r, int c) {
        int d = 0;
        int currDust = 0;

        do {
            int nr = r + delta[index[d]][0];
            int nc = c + delta[index[d]][1];

            if (!isValid(nr, nc)) { // 좌표가 유효하지 않으면 방향 전환
                d++;
                continue;
            }
            else if (map[nr][nc] == -1) break;  // 공기청정기를 만나면 반복문 종료

            // 미세먼지 이동
            int temp = map[nr][nc];
            map[nr][nc] = currDust;
            currDust = temp;
            r = nr;
            c = nc;
        } while (true);
    }

    // 총 먼지의 수
    private static int totalDust() {
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) result += map[i][j];
            }
        }
        return result;
    }

    // 배열 복사 메소드
    private static int[][] copyArray() {
        int[][] result = new int[R][C];
        for (int i = 0; i < R; i++) {
            result[i] = Arrays.copyOf(map[i], C);
        }
        return result;
    }

    // 좌표 유효성검사
    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < R && c >= 0 && c < C) return true;
        return false;
    }
}
