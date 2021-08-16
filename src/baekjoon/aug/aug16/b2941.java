package baekjoon.aug.aug16;

import java.util.Scanner;

/**
 * 백준 2941 크로아티아 알파벳
 *
 * 접근 방식:
 * 부분 문자열을 확인해, 크로아티아 알파벳을 뜻하는 부분이 포함되어있으면 cnt를 1 증가시킨다.
 * 그 후에 문자열을 그 이후부터로 잘라서 다시 while문을 순회한다.
 */

public class b2941 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.next();
        int cnt = 0;

        while (msg.length() > 0) {
            if (msg.length() >= 3) {
                String s2 = msg.substring(0, 3);

                if (s2.equals("dz=")) {
                    msg = msg.substring(3);
                    cnt++;
                    continue;
                }
            }

            if (msg.length() >= 2) {
                String s1 = msg.substring(0, 2);

                if (s1.equals("c=") || s1.equals("c-") || s1.equals("d-") || s1.equals("lj") || s1.equals("nj") || s1.equals("s=") || s1.equals("z=")) {
                    msg = msg.substring(2);
                    cnt++;
                    continue;
                }
            }

            msg = msg.substring(1);
            cnt++;
        }

        System.out.println(cnt);

    }
}
