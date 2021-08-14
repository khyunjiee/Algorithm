package baekjoon.aug.aug14;

import java.util.Scanner;

/**
 * 백준 2999 비밀 이메일
 *
 * 접근 방법 :
 * 메세지의 길이를 수끼리의 곱으로 만들 수 있는 R, C 중에서 R이 가장 크고 C보다 작거나 같은 값을 구한다.
 * 그 후에 char 배열을 R행 C열이 되도록 만들고 열을 기준으로 입력받은 메세지를 저장한다.
 * 출력은 행을 기준으로 출력한다.
 *
 * 시간 복잡도 :
 * O(N) -> 메세지의 길이
 *
 */

public class b2999 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        char[] msg = sc.nextLine().toCharArray();
        int len = msg.length;
        int R = 0, C = 0;

        for (int i = 1; i <= len; ++i) {
            if (len % i == 0 && i > R && (i <= (len/i))) {
                R = i;
                C = len/i;
            }
        }

        char[][] map = new char[R][C];
        int index = 0;

        for (int i = 0; i < C; ++i) {
            for (int j = 0; j < R; ++j) {
                map[j][i] = msg[index++];
            }
        }

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                sb.append(map[i][j]);
            }
        }

        System.out.println(sb);

    }
}
