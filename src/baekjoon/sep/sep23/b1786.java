package baekjoon.sep.sep23;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 1786 찾기
 *
 * 접근 방법:
 * KMP 알고리즘 사용.
 *
 * 시간 복잡도:
 * O(M+N)
 */

public class b1786 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] text = sc.nextLine().toCharArray();
        char[] pattern = sc.nextLine().toCharArray();

        int tLen = text.length, pLen = pattern.length;
        int[] pi = new int[pLen];

        for (int i = 1, j = 0; i < pLen; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j-1];
            }
            if (pattern[i] == pattern[j]) pi[i] = ++j;
        }
//        System.out.println(Arrays.toString(pi));

        List<Integer> result = new ArrayList<>();
        for (int i = 0, j = 0; i < tLen; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j-1];
            }
            if (text[i] == pattern[j]) {
                if (j == pLen - 1) {
                    result.add((i+1) - pLen);
                    j = pi[j];
                } else {
                    ++j;
                }
            }
        }

        System.out.println(result.size());
        for (int num: result) {
            System.out.print((num+1) + " ");
        }
        System.out.println();
    }
}
