package jungol.sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정올 1681 해밀턴 순환회로
 *
 * 접근 방식:
 * 브루트 포스 방식으로 접근했습니다.
 * N이 12까지로 작기 때문에 시간적인 면에서도 나쁘지 않다고 생각했습니다.
 * 그리고 여태까지 구한 min보다 커지는 순간은 가지치기를 적용해 백트래킹 알고리즘을 적용했습니다.
 *
 * 시간 복잡도:
 * O(N^3)
 */

public class j1681 {

    static int N, min, matrix[][];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        min = Integer.MAX_VALUE;
        matrix = new int[N][N];
        visited = new boolean[N];
        StringTokenizer st = null;

        // 입력 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0] = true;  // 출발 장소 방문처리
        dfs(0, 0, 0);
        System.out.println(min);
    }

    // current: 현재 위치, cnt: 몇번째 경로를 찾는 중인지, weight: 현재까지 구한 가중치
    private static void dfs(int current, int cnt, int weight) {
        if (cnt == N-1) {   // 모든 위치를 다 돌았다면 시작 장소로 가야함
            if (matrix[current][0] == 0) return;    // 마지막 장소가 시작 장소라면 불가능
            weight += matrix[current][0];   // 가중치에 현재 장소에서 시작 장소까지의 비용을 더해줌
            min = Math.min(weight, min);    // min 계산
        }
        if (weight > min) return;   // 여태까지 구한 가중치가 min보다 크다면 유망하지 않으므로 return

        // 모든 장소에 대해 구함
        for (int i = 0; i < N; i++) {
            if (!visited[i] && matrix[current][i] != 0) {   // 방문하지 않았고, 갈 수 없는 경로가 아니라면
                visited[i] = true;  // 방문처리
                dfs(i, cnt+1, weight+matrix[current][i]);   // dfs 체크
                visited[i] = false; // 방문처리 해제
            }
        }
    }
}
