package baekjoon.aug.aug13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 15686 치킨 배달
 *
 * 접근 방법 :
 * 입력에서 배열 원소들이 들어올 때, 2(치킨집) 이 들어오면 좌표를 저장하고 0으로 배열에 저장한다.
 * 그 후 next permutation 을 이용한 조합 생성을 위해 chickens와 인덱스가 같은 배열 comb를 생성한다.
 * np() 메소드로 조합이 생성될 때마다 direction() 메소드를 이용해 최소 거리를 구한다.
 *
 * direction 메소드에서는 map을 순회하면서 1을 찾고, 그 사람한테 가장 가까운 치킨집을 찾는다.
 * 거리들을 모두 더해서 최소라면, minDirection을 변경한다.
 *
 */

public class b15686 {
    static int minDirection, N, M, map[][], comb[];
    static List<int[]> chickens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];                // 입력으로 들어오는 배열
        chickens = new ArrayList<>();       // 치킨집의 위치 (r,c) 를 저장할 리스트
        minDirection = Integer.MAX_VALUE;

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; ++j) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 2) {     // 2가 들어오면 좌표를 리스트에 저장하고, map 배열에는 0을 저장한다.
                    chickens.add(new int[]{i, j});
                    map[i][j] = 0;
                } else {
                    map[i][j] = num;
                }

            }
        }

        // 들어온 치킨집의 사이즈만큼 배열을 만들어서 초기 순열을 만든다
        int len = chickens.size();
        comb = new int[len];
        for (int i = 1; i <= M; ++i) {
            comb[len-i] = 1;
        }

        // 조합이 생성될 때마다 거리 계산
        do {
            direction();
        } while (np());

        System.out.println(minDirection);
    }

    // 최소 거리를 계산하는 메소드
    private static void direction() {
        int sum = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (map[i][j] != 1) continue;   // 1이 아니면 continue

                int d = Integer.MAX_VALUE;

                for (int k = 0; k < comb.length; ++k) {
                    if (comb[k] != 1) continue;     // 선택된 치킨집이 아니라면 continue

                    int cr = chickens.get(k)[0];    // 리스트에 저장한 치킨집의 위치 (r,c) 를 꺼내온다.
                    int cc = chickens.get(k)[1];
                    int cd = Math.abs(i-cr) + Math.abs(j-cc);   // 거리를 계산한다.

                    // 최소 거리라면 변수 d를 업데이트
                    if (cd < d) d = cd;
                }

                // 도시의 최소 거리에 더한다
                sum += d;
            }
        }

        // 최소 도시 거리를 minDirection에 저장
        minDirection = Math.min(minDirection, sum);
    }

    // next permutation 으로 조합 생성
    private static boolean np() {
        int L = comb.length - 1;

        int i = L;
        while ((i > 0) && (comb[i-1] >= comb[i])) --i;
        if (i == 0) return false;

        int j = L;
        while (comb[i-1] >= comb[j]) --j;
        swap(i-1, j);

        int k = L;
        while (i < k) swap(i++, k--);

        return true;
    }

    // np 메소드에 활용되는 swap
    private static void swap(int i, int j) {
        int temp = comb[i];
        comb[i] = comb[j];
        comb[j] = temp;
    }
}
