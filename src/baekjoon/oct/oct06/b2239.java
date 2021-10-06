package baekjoon.oct.oct06;

/*
백준 2399 스도쿠 [G4]

접근 방식:
dfs 방식으로 0인 칸에 숫자를 하나씩 집어넣어보고 그 숫자가 가능한지 확인합니다.
숫자 포함 여부 배열을 행, 열, 사각형(3X3) 별로 만들어서 반복문 없이 체크했습니다.

15836KB 424ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b2239 {

    final static int ROW = 9, COL = 9;
    static int map[][];
    static boolean flag, contain[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[ROW][COL];
        // 0: 행 1: 열 2: 네모칸
        // 숫자 사용 여부
        contain = new boolean[3][ROW][10];

        for (int i = 0; i < ROW; i++) {
            String input = br.readLine();
            for (int j = 0; j < COL; j++) {
                int num = input.charAt(j) - '0';
                if (num > 0) {  // 숫자가 적혀있다면
                    // 3X3 네모칸 넘버링
                    int square = 3 * (i / 3) + (j / 3);
                    map[i][j] = num;

                    // 포함 배열에 true로 설정
                    contain[0][i][num] = contain[1][j][num] = contain[2][square][num] = true;
                }
            }
        }

        dfs(0); // 0,0 부터 dfs 시작

        // 결과물 출력
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void dfs(int cnt) {
        if (cnt == 81) {        // 숫자 81개 모두 채웠다면 dfs 종료
            flag = true;        // 종료 플래그 변수를 true로 설정
            return;
        }

        int r = cnt / 9;    // 행
        int c = cnt % 9;    // 열
        int square = 3 * (r / 3) + (c / 3);     // 3X3 사각형 넘버링

        if (map[r][c] != 0) dfs(cnt+1);    // 숫자가 채워져있으면 cnt+1
        else {  // 숫자가 비어있으면 채워야 함
            for (int num = 1; num <= 9; num++) {
                // num이 해당 자리에 불가능하다면 반복문 continue
                if (!isPossible(r, c, num)) continue;

                // 아래는 가능한 경우들
                map[r][c] = num;
                contain[0][r][num] = contain[1][c][num] = contain[2][square][num] = true;

                dfs(cnt+1);
                if (flag) return;   // dfs에서 돌아왔는데 flag가 true라면 함수 종료이므로 return

                // flag가 false라면 다른 숫자도 대입해야하므로 다시 초기화
                map[r][c] = 0;
                contain[0][r][num] = contain[1][c][num] = contain[2][square][num] = false;
            }
        }
    }

    private static boolean isPossible(int r, int c, int num) {
        int square = 3 * (r / 3) + (c / 3);
        return !contain[0][r][num] && !contain[1][c][num] && !contain[2][square][num];
    }
}
