package baekjoon.aug;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 1158 요세푸스 순열
 *
 * 접근 방법 :
 * 큐를 사용해서 풀었다.
 * count가 K인 순간, 큐에서 원소를 빼서 결과 문자열에 추가한다.
 * count가 K가 아니라면 큐에서 원소를 빼서 다시 끝으로 집어넣는다.
 * 큐가 Empty가 될 때까지 진행한다.
 *
 * 시간 복잡도 :
 * O(N)
 */

public class b1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String result = "<";
        Queue<Integer> queue = new LinkedList<>();

        int N = sc.nextInt();
        int K = sc.nextInt();
        int count = 1;

        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            count %= K; // count == K 라면 0
            if (count++ == 0) { // 조건 처리와 동시에 후위 연산자를 사용해 count+1 처리를 해준다.
                result += queue.poll() + ", ";
                continue;
            }
            int num = queue.poll();
            queue.offer(num);
        }

        result = result.substring(0, result.length()-2) + ">";  // 마지막 숫자의 ", " 부분을 제거해주고 ">"를 추가해준다.
        System.out.println(result);
    }
}
