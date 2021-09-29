package baekjoon.sep.sep29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17472 다리 만들기2 [G2]
 *
 * 접근 방식:
 * 섬 탐색과 다리 만드는 과정은 bfs를 사용했고
 * 다리를 선택하는 과정은 크루스칼 알고리즘을 사용했습니다.
 * 입력으로 들어오는 행렬에는 모든 섬이 다 1로 표시되어있기 때문에
 * 1, 2, 3, 4 ... 와 같이 섬을 다시 만들어주고
 * 다리를 놓을 수 있는 방향으로 탐색하면서 weight를 계산해 pq에 넣어줍니다.
 * 그렇게 만든 다리를 크루스칼로 선택해 총 weight를 출력합니다.
 *
 * 소요 시간:
 * 1시간
 *
 * 11724KB 80ms
 */

public class b17472 {

    // 각 섬을 잇는 다리
    static class Load implements Comparable<Load>{
        int start, end, weight;

        public Load(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Load o) {
            return this.weight - o.weight;
        }
    }

    static int N, M, cnt, parents[], map[][], input[][];
    // 상 하 좌 우
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        input = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init(); // 섬 초기화
        makeLoad(); // 다리를 만들어서 선택
    }

    // 각 섬마다 번호를 붙인다.
    private static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 섬인데 한번도 탐색하지 않았으면
                if (input[i][j] != 0 && map[i][j] == 0) {
                    map[i][j] = ++cnt;
                    // bfs 탐색
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + delta[d][0];
                            int nc = c + delta[d][1];

                            // 섬인데 map에 체크하지 않았으면 같은 섬이므로 cnt로 저장하고 큐에 넣는다
                            if (isValid(nr, nc) && input[nr][nc] != 0 && map[nr][nc] == 0) {
                                map[nr][nc] = cnt;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
    }

    // 각 섬을 이을 수 있는 다리를 만들어서 선택한다
    private static void makeLoad() {
        // 다리들을 weight가 작은 순서대로 담을 pq
        PriorityQueue<Load> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {   // 섬을 만났다면
                    for (int d = 0; d < 4; d++) {   // 사방탐색
                        int nr = i + delta[d][0];
                        int nc = j + delta[d][1];
                        int weight = 0;

                        // 다음 위치가 유효하고 0이라면 다리를 놓을 수 있으므로 weight+1 & 그 방향으로 직진
                        while (isValid(nr, nc) && map[nr][nc] == 0) {
                            weight++;
                            nr += delta[d][0];
                            nc += delta[d][1];
                        }

                        // 위치가 유효하고, 바다가 아니고, weight가 1 초과라면 다리를 생성할 수 있다
                        if (isValid(nr, nc) && map[nr][nc] != 0 && weight > 1) {
                            pq.offer(new Load(map[i][j], map[nr][nc], weight));
                        }
                    }
                }
            }
        }

        System.out.println(pick(pq));   // 다리를 선택해 총 weight를 출력한다
    }

    // 가중치가 낮은 간선부터 선택한다. (크루스칼)
    private static int pick(PriorityQueue<Load> pq) {
        make(); // 자기 자신이 root인 서로소 집합 만들기

        int loadCnt = 0;    // 선택한 도로의 수
        int result = 0;     // 총 weight

        while(!pq.isEmpty()) {
            Load load = pq.poll();

            // 다리를 놓을 수 있다면. (다른 집합이라면)
            if (union(load.start, load.end)) {
                result += load.weight;
                loadCnt++;
            }
            if (loadCnt == cnt-1) break;    // cnt-1 개의 도로를 선택했으면 그것이 최소이므로 break
        }

        // 다리의 수가 cnt-1이 아니라면 놓을 수 없는 경우. -1 리턴
        // 다리의 수가 cnt-1이라면 다리를 모두 놓았으므로 result 리턴
        return (loadCnt == cnt - 1)? result: -1;
    }

    // 자기자신이 root인 서로소 집합 만들기
    private static void make() {
        parents = new int[cnt+1];

        for (int i = 1; i <= cnt; i++) {
            parents[i] = i;
        }
    }

    // 해당 원소의 root 찾기
    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    // 다른 집합이라면 합치고 true 리턴
    // 같은 집합이라면 false 리턴
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    // map 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < M) return true;
        return false;
    }
}
