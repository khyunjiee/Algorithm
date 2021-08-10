package baekjoon.aug;

import java.util.Scanner;

/**
 * 백준 2563 색종이
 *
 * 접근 방법 :
 * 100X100 배열을 만든다.
 * 그 후에 색종이의 열, 행에 맞게 10X10 으로 배열을 0에서 1로 채워준다.
 * 이미 1인 위치는 지나간다.
 * 1로 채웠을 때만 area 변수를 1씩 증가시킨다.
 *
 * 시간 복잡도 :
 * O(N^2)
 */
public class b2563 {
    static int N, area, paper[][];

    public static void main(String[] args) {
        N = 100;
        paper = new int[N][N];

        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();

        for (int i = 0; i < M; i++) {
            // paper에 색종이 붙이기
            attach(sc.nextInt(), sc.nextInt());
        }

        System.out.println(area);

    }

    // 색종이를 붙이는 메소드
    private static void attach(int r, int c) {
        for (int i = r; i < r+10; i++) {
            for (int j = c; j < c+10; j++) {
                // 색종이가 안붙은 부분만 1로 변경 후 area+1
                if (paper[i][j] == 0) {
                    paper[i][j] = 1;
                    area++;
                }
            }
        }
    }
}
