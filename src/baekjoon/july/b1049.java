package baekjoon.july;

import java.util.Scanner;

public class b1049 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int setM = Integer.MAX_VALUE;
        int individualM = Integer.MAX_VALUE;
        int result;

        for(int i = 0; i < M; i++) {
            int set = sc.nextInt();
            int individual = sc.nextInt();

            if (set < setM) setM = set;
            if (individual < individualM) individualM = individual;
        }

        int setOnly = setM * (int)(Math.ceil((double)N/6));
        int individualOnly = N * individualM;

        if (setOnly < individualOnly) result = setOnly;
        else result = individualOnly;


        if (N > 6) {
            int mix = setM * (N/6) + individualM * (N%6);
            if (mix < result) {
                result = mix;
            }
        }

        System.out.println(result);
    }
}
