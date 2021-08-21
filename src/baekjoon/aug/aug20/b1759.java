package baekjoon.aug.aug20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 1759 암호 만들기
 * 
 * 접근 방법:
 *
 * 시간 복잡도:
 *
 *
 */

public class b1759 {

    static int L, C;
    static char alpha[];
    static boolean isSelect[];
    static StringBuilder sb;
    static List<Character> vows;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        L = sc.nextInt();
        C = sc.nextInt();
        alpha = new char[C];
        isSelect = new boolean[C];

        for (int i = 0; i < C; i++) {
            alpha[i] = sc.next().charAt(0);
        }

        vows = new ArrayList<>();
        vows.add('a'); vows.add('e'); vows.add('i'); vows.add('u'); vows.add('o');

        Arrays.sort(alpha);
        perm(0, 0, 0, 0, "");
        System.out.println(sb);
    }

    private static void perm(int cnt, int start, int vow, int cons, String result) {
        if (cnt == L) {
            if (vow >= 1 && cons >= 2) sb.append(result + "\n");
            return;
        }

        for (int i = start; i < C; i++) {
            if (isSelect[i]) continue;

            isSelect[i] = true;
            if (vows.contains(alpha[i])) perm(cnt+1, i, vow+1, cons, result+alpha[i]);
            else perm(cnt+1, i, vow, cons+1, result+alpha[i]);
            isSelect[i] = false;
        }
    }
}
