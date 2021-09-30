package baekjoon.sep.sep30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1707 이분 그래프 [G4]
 */

public class b1707 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());   // 정점의 개수
            int E = Integer.parseInt(st.nextToken());   // 간선의 개수
            ArrayList<Integer>[] adjList = new ArrayList[V+1];
            boolean[][] groups = new boolean[2][V+1];

            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adjList[a].add(b);
                adjList[b].add(a);
            }

            // 다시 짜기

            String result = "NO";
            for (int i = 1; i <= V; i++) {
                int myGroup = -1;
                for (int j = 0; j < 2; j++) {
                    for (int k = 1; k <= V; k++) {
                        if (groups[j][k] && adjList[i].contains(k)) break;
                        if (k == V) {
                            myGroup = j;
                        }
                    }
                }
                if (myGroup < 0) {
                    result = "NO";
                    break;
                } else {
                    groups[myGroup][i] = true;
                    result = "YES";
                }
            }

            sb.append(result + "\n");
        }
        System.out.println(sb);
    }
}
