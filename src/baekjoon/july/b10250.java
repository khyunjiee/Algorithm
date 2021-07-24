package baekjoon.july;

import java.util.Scanner;

public class b10250 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        String result = "";

        for (int tc = 0; tc < T; tc++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            int N = sc.nextInt();

            for (int i = 1; i <= W; i++) {
                for (int j = 1; j <= H; j++) {
                    N--;
                    if (N == 0) {
                        result += j;
                        result += (i < 10)? "0"+i: i;
                        break;
                    }
                }
            }
            result += "\n";
        }

        System.out.println(result);
    }
}
