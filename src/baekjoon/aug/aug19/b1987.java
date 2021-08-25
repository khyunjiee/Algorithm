package baekjoon.aug.aug19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1987 알파벳
 *
 * 접근 방식:
 * DFS 로 풀었습니다.
 * 이미 갔던 알파벳은 비트마스킹을 활용해서 visit 처리를 했습니다.
 * 인덱스는 알파벳을 A로 뺀 값을 인덱스로 저장해 쉽게 접근했습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 */

public class b1987 {

    static int R, C, max;
    static char map[][];

    // 상 하 좌 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        max = Integer.MIN_VALUE;
        map = new char[R][C];

        // 맵 초기화
        for (int i = 0; i < R; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 1<<map[0][0]-'A', 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y, int flag, int cnt) {
        max = Math.max(max, cnt);
        int nr, nc;
        for (int d = 0; d < 4; d++) {
            nr = x + dr[d];
            nc = y + dc[d];
            if (isValid(nr, nc) && (flag & 1<<map[nr][nc]-'A') == 0) {
                dfs(nr, nc, flag | 1<< (map[nr][nc]-'A'), cnt+1);
            }
        }
    }

    // 좌표 위치 값 확인
    private static boolean isValid(int r, int c) {
        if (r<0 || r>=R || c<0 || c>=C) return false;
        return true;
    }

}
