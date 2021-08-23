package baekjoon.aug.aug23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1260 DFS와 BFS
 *
 * 접근 방식:
 * 그래프를 리스트로 구현해서 접근했습니다.
 * 문제에 인접 정점 중 정점의 숫자가 작은 숫자부터 방문한다는 제한조건이 있기 때문에
 * 최종 리스트를 오름차순으로 정렬해 접근했습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 *
 */

public class b1260 {

    static int N, M, V;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(V, new boolean[N+1]);
        System.out.println();
        bfs(V);
    }
    
    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        
        boolean[] visit = new boolean[N+1];
        visit[v] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int i = 0; i < graph.get(current).size(); i++) {
                int index = graph.get(current).get(i);
                if (!visit[index]) {
                    visit[index] = true;
                    queue.offer(index);
                }
            }
        }
    }
    
    private static void dfs(int v, boolean[] visit) {
        visit[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < graph.get(v).size(); i++) {
            int index = graph.get(v).get(i);
            if (!visit[index]) {
                visit[index] = true;
                dfs(index, visit);
            }
        }
    }
}
