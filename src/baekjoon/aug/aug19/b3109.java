package baekjoon.aug.aug19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3109 빵집
 *
 * 접근 방식:
 * 백트래킹과 메모이제이션을 활용했습니다.
 * 또한 빵집에서부터 출발합니다.
 * 빵집에서 출발해서 1열, 즉 가스관이 있는 열에 도달하면
 * flag 변수를 true로 설정하고 result값을 하나 증가시킨 후 return합니다.
 * 그리고 지나온 위치들은 모두 memo합니다.
 * 파이프가 겹치는 경우가 없고 해당 위치에 무조건 하나의 파이프만 위치할 수 있으므로
 * flag 여부와 상관 없이 한번 확인한 위치는 다시 확인할 필요가 없습니다.
 * 따라서 재귀호출 후에 해당 위치를 memo하고 flag가 true라면 return합니다.
 *
 */

public class b3109 {

    static char[][] map;
    static int result, R, C;
    static boolean flag, memo[][];

    // 좌상, 좌, 좌하
    static int[] dr = { -1, 0, 1 };
    static int[] dc = { -1, -1, -1 };

    public static void main(String[] args) throws IOException {
        // 입력으로 배열을 초기화해야 하므로 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열

        // 행과 열을 여유있게 설정해서 굳이 좌표의 유효성검사 없이 분기 처리를 '.' 이면 가능한 것으로 했습니다.
        map = new char[R+2][C+2];
        memo = new boolean[R+2][C+2];
        result = 0;

        // 맵 초기화
        for (int i = 1; i <= R; ++i) {
            char[] input = br.readLine().toCharArray();
            for (int j = 1; j <= C; ++j) {
                map[i][j] = input[j-1];
            }
        }

        // 각 열에서 출발하는데, 해당 열에서 유효한 파이프라인이 나왔을 수도 있으니
        // pipeLine 메소드 호출 전에 flag 변수를 false로 설정
        for (int i = 1; i <= R; ++i) {
            flag = false;
            pipeLine(i, C);
        }

        System.out.println(result);
    }

    // 파이프라인 메소드
    private static void pipeLine(int r, int c) {
        if (map[r][c] == '.' && !memo[r][c]) {  // 한 번도 확인하지 않은 빈 공간만 가능
            // 가스관에 도달하면 모두 체크한 것이므로 result 값 증가 후 falg true로 설정
            if (c == 1) {
                result++;
                flag = true;
                return;
            }
            // 세 방향을 확인
            for (int d = 0; d < 3; ++d) {
                // 빈 공간이 아니면 확인할 필요가 없으니 continue
                if (map[r+dr[d]][c+dc[d]] != '.') continue;

                // 다음이 빈 공간이라면 재귀호출
                pipeLine(r+dr[d], c+dc[d]);
                // 호출이 돌아온 후에 방문했다는 memo
                memo[r][c] = true;

                // flag 가 true라면 파이프라인이 완성된 것이므로 return
                if (flag) return;
            }
        }
    }
}
