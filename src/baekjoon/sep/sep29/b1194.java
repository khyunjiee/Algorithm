package baekjoon.sep.sep29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1194 달이 차오른다, 가자. [G1]
 *
 * 접근 방식:
 * key가 총 6개이므로 비트 마스킹을 사용했습니다.
 * 좌표마다 key 보유 상태를 인덱스를 취해 true면 고려한 상황, false면 고려하지 않은 상황입니다.
 * bfs로 탐색해주었으며, 1인 부분이 여러개 있을 수 있으므로 계속 min을 업데이트해줘야 합니다.
 * 현재 depth 가 여태까지의 최적 min 보다 크다면 가지치기가 가능합니다.
 *
 * 시간 복잡도:
 *
 *
 * 소요 시간: 1시간 30분
 * 18084KB 124ms
 */

public class b1194 {

    static int N, M, min;
    static char map[][];
    // 상 하 좌 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        final int INF = Integer.MAX_VALUE;

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        map = new char[N][M];                   // 맵
        min = INF;                              // 초기 최솟값은 INFINITE로 할당

        int startR = 0, startC = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                // 시작 위치가 유동적이므로 변수에 저장
                if (map[i][j] == '0') {
                    startR = i;
                    startC = j;
                }
            }
        }

        bfs(startR, startC);    // 시작 위치부터 bfs 탐색
        System.out.println((min == INF)? -1: min);
    }

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0, 0});    // index 0:행 1:열 2:현재까지의 걸음수(depth) 3:현재 키 보유 상태(비트마스크)

        // 해당 좌표에서 key의 상태를 고려했는지 여부
        // 키가 총 6개이므로 크기는 1 << 6으로 할당
        boolean[][][] visit = new boolean[N][M][1<<6];

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currR = curr[0];    // 행
            int currC = curr[1];    // 열
            int depth = curr[2];    // 현재까지의 걸음 수
            int key = curr[3];      // 현재 key 보유 상태

            // 방문 처리
            if (visit[currR][currC][key]) continue;
            visit[currR][currC][key] = true;

            // 만약 min이 현재까지의 depth보다 작다면, 가지치기 가능
            if (depth >= min) continue;
            // 도착지에 도착했다면 min 업데이트
            if (map[currR][currC] == '1') {
                min = Math.min(min, depth);
                continue;
            }

            // 사방탐색
            for (int d = 0; d < 4; d++) {
                int nr = currR + dr[d];
                int nc = currC + dc[d];

                // 유효하지 않은 좌표이거나, 벽일 경우 갈 수 없다
                if (!isValid(nr, nc) || map[nr][nc] == '#') continue;

                // A~F : 문
                if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                    // 문을 열 수 있다면 큐에 다음 상태를 추가
                    if (openDoor(key, map[nr][nc])) {
                        queue.offer(new int[]{ nr, nc, depth+1, key });
                    }
                    continue;
                }

                int nextKey = key;  // key가 추가될 수도 있으니 변수 생성
                // a~f : 키를 얻을 수 있음
                if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                    nextKey = getKey(key, map[nr][nc]);
                }
                queue.offer(new int[]{ nr, nc, depth+1, nextKey }); // 다음 상태 저장 (키를 받는 경우 + 빈칸인 경우)
            }
        }

    }

    // 키를 추가하는 함수
    private static int getKey(int current, char key) {
        int bit = (1 << (key - 'a'));
        return current | bit;
    }

    // 키를 받을 수 있는지 여부
    private static boolean openDoor(int current, char door) {
        int bit = (1 << (door - 'A'));
        return (current & bit) > 0; // & 연산 시 0 초과라면 키를 보유하고 있는 것
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < M) return true;
        return false;
    }
}
