package baekjoon.aug.aug13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17135 캐슬 디펜스
 *
 * 접근 방법 :
 * 궁수들을 놓을 수 있는 조합의 경우의 수는 next permutation을 활용했다.
 * 입력으로 들어온 맵은 map에 저장하고, 그 값들을 temp에 복사한다.
 * 그 후에 궁수들이 화살을 쏘는 경우의 수를 구한다.
 *
 * temp를 순회하면서 궁수들이 쏠 수 있는 최소 거리의 적을 구해
 * 해당 적까지의 거리가 D 이하라면, 적을 공격여부를 나타내는 attack 배열을 true로 바꾼다.
 *
 * 궁수들이 모두 공격할 적을 선택하면, attack 배열에서 true인 인덱스만 temp에서 0으로 처리한다.
 * 0으로 바꾸면서 처리한 적의 수를 세어서 max값보다 큰지 비교 후 수정한다.
 * 행을 하나 내리고 위의 과정을 N번 반복한다.
 *
 */

public class b17135 {

    static int N, M, D, maxAttackCnt, archer[], map[][], temp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        temp = new int[N][M];
        archer = new int[M];

        // 배열 초기화
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // archer[i]가 1이면 궁수가 있는 곳, 0이면 궁수가 없는 곳
        for (int i = 1; i <= 3; ++i) {
            archer[M-i] = 1;
        }

        // 모든 조합에 대한 경우의 수를 계산한다.
        do {
            // 적을 처리하기 전에 temp 배열에 오리지널 배열 복사
            copyMap();
            // 화살 발사
            shoot();
        } while (np());

        System.out.println(maxAttackCnt);
    }

    private static void shoot() {
        int cnt = 0;    // 현재 궁수들의 조합에서 없앨 수 있는 적의 수

        // 궁수들은 최대 N번 쏠 수 있다. (행의 개수가 N이므로)
        for (int n = 1; n <= N; ++n) {
            boolean[][] visited = new boolean[N][M];

            for (int k = 0; k < M; ++k) {
                if (archer[k] == 0) continue;   // 궁수가 없으면 continue

                // 궁수가 존재하는 인덱스라면 최소 거리에 있는 적을 찾아야 함
                int d = Integer.MAX_VALUE;  // 적까지의 최소 거리
                int r = Integer.MAX_VALUE;  // 적의 위치 r
                int c = Integer.MAX_VALUE;  // 적의 위치 c

                // temp 배열을 순회하면서 최소 거리에 있는 적을 찾아서 d, r, c 변수에 넣는다.
                for (int i = N-1; i >= 0; --i) {
                    for (int j = 0; j < M; ++j) {
                        if (temp[i][j] == 1) {
                            int toEnemy = Math.abs(i-N) + Math.abs(j-k);
                            if (d > toEnemy) {  // 여태까지 구한 최소 거리보다 현재 적과의 거리가 더 짧을 때는 변경
                                d = toEnemy;
                                r = i;
                                c = j;
                            } else if (d == toEnemy && c > j) { // 만약 거리가 같다면 왼쪽에 있는 적을 선택
                                r = i;
                                c = j;
                            }
                        }
                    }
                }

                // 거리가 D 이하라면 visited 배열을 true 처리
                if (d <= D) {
                    visited[r][c] = true;
                }
            }

            // 전체 temp 배열을 순회하면서 visited 가 true인 것만 0으로 처리 -> 적을 제거했다
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (visited[i][j]) {
                        temp[i][j] = 0;
                        cnt++;
                    }
                }
            }

            // 배열을 밑으로 한 줄 내리는 작업
            downMap();

        }

        maxAttackCnt = Math.max(maxAttackCnt, cnt);
    }

    // 배열의 행을 아래로 한 칸씩 움직인 후, 첫 행을 0으로 채운다
    private static void downMap() {
        for (int i = N-1; i > 0; --i) {
            for (int j = 0; j < M; ++j) {
                temp[i][j] = temp[i-1][j];
            }
        }

        for (int i = 0; i < M; ++i) {
            temp[0][i] = 0;
        }
    }

    // 원래 배열인 map 을 temp 배열에 복사하는 메소드
    private static void copyMap() {
        for (int i = 0; i < N; ++i) {
            System.arraycopy(map[i], 0, temp[i], 0, M);
        }
    }

    // next permutation 으로 조합 생성
    private static boolean np() {
        int L = M - 1;

        int i = L;
        while ((i > 0) && (archer[i-1] >= archer[i])) --i;
        if (i == 0) return false;

        int j = L;
        while (archer[i-1] >= archer[j]) --j;
        swap(i-1, j);

        int k = L;
        while (i < k) swap(i++, k--);

        return true;
    }

    // np 메소드에 활용되는 swap
    private static void swap(int i, int j) {
        int temp = archer[i];
        archer[i] = archer[j];
        archer[j] = temp;
    }

}
