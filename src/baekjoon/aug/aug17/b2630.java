package baekjoon.aug.aug17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2630 색종이 만들기
 */

public class b2630 {

    static int blue, white, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, N, 0, N);

        System.out.println(white);
        System.out.println(blue);

    }

    private static void cut(int sr, int er, int sc, int ec) {
        if (isColoredPaper(sr, er, sc, ec)) {
            if (map[sr][sc] == 0) white++;
            else blue++;
            return;
        }

        cut(sr, sr+(er-sr)/2, sc, sc+(ec-sc)/2);
        cut(sr, sr+(er-sr)/2, sc+(ec-sc)/2, ec);
        cut(sr+(er-sr)/2, er, sc, sc+(ec-sc)/2);
        cut(sr+(er-sr)/2, er, sc+(ec-sc)/2, ec);

    }

    private static boolean isColoredPaper(int sr, int er, int sc, int ec) {
        int num = map[sr][sc];

        for (int i = sr; i < er; ++i) {
            for (int j = sc; j < ec; ++j) {
                if (num != map[i][j]) return false;
            }
        }

        return true;
    }
}
