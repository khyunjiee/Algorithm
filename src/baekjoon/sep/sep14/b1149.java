package baekjoon.sep.sep14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1149 RGB 거리
 *
 * 접근 방식:
 * 2차원 배열을 활용해서 DP를 구현했습니다.
 * 2차원 배열은 RGB 경우를 3가지로 나눈 두 배열을 저장하는 방식입니다. (int[2][3])
 * N까지 모두 구하는데, 반복문을 순회하면서 i가 짝수면 0번째 배열, 홀수면 1번째 배열에 접근해 거리를 갱신합니다.
 *
 * 시간 복잡도: O(N) -> 정렬은 원소 3개의 정렬이라서 무시해도 될 것 같습니다.
 * 소요 시간: 20분
 *
 */

public class b1149{
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] paint = new int[2][3];  // RGB 3가지 경우를 2 배열을 스위칭하면서 제어
        int curr = 0;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] input = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            for (int c = 0; c < 3; c++) {
                int min = Integer.MAX_VALUE;
                for (int t = 0; t < 3; t++) {
                    if (c == t) continue;   // 이전 집과 같은 색을 칠하면 안됨
                    curr = i % 2;   // 현재 인덱스, i가 짝수면 0 홀수면 1
                    min = Math.min(min, paint[1-curr][t]+input[c]); // 작은 비용으로 갱신
                }
                paint[curr][c] = min;
            }
        }

        Arrays.sort(paint[curr]);
        System.out.println(paint[curr][0]);
    }
}
