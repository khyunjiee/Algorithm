package baekjoon.aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 2493 탑
 *
 * 접근 방법:
 * stack에 입력받는 모든 값들을 저장하면 메모리 초과가 발생한다.
 * 또한, N이 1억이기 때문에 중복 for문으로 구현하면 시간 초과가 발생한다.
 * 따라서 탑의 위치르 저장하는 location 스택과, 탑을 저장하는 top 스택을 관리한다.
 * 탑을 저장할 때, 자신의 양 옆이 현재 값보다 크면 지워준다.
 * 즉, stack.peek() 했을 때 현재 숫자보다 크다면 결과 StringBuilder에 추가해주고 숫자를 스택에 저장한다.
 * 하지만, stack.peek()했을 때 현재 값보다 숫자가 작다면 해당 값을 pop한다.
 * 그리고 현재 값보다 큰 값이 stack의 top에 있을 때까지 반복문을 순회한다.
 *
 * 시간복잡도:
 * O(N)
 *
 */

public class b2493 {
    static Stack<Integer> topStack, locationStack;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        topStack = new Stack<>();
        locationStack = new Stack<>();

        // 초기화
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            findTop(num, n+1);
        }

        System.out.println(sb.toString());
    }

    private static void findTop(int number, int index) {
        while (true) {
            if (topStack.isEmpty()) {   // 스택이 비어있으면 현재 값을 push (top, location)
                sb.append(0 + " ");
                topStack.push(number);
                locationStack.push(index);
                break;
            }
            if (topStack.peek() >= number) {    // 스택 top 값이 현재 값보다 크다면, 결과를 저장하고 스택에 push (top, location)
                sb.append(locationStack.peek() + " ");
                locationStack.push(index);
                topStack.push(number);
                break;
            } else {    // 스택 top 값이 현재 값보다 작으면, 해당 값을 제거 (break가 없는 이유는 해당 값보다 큰 값이 나올때까지 찾는다.)
                topStack.pop();
                locationStack.pop();
            }
        }
    }

}
