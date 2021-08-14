package baekjoon.aug.aug14;

import java.util.Scanner;
import java.util.Stack;

/**
 * 백준 17413 단어뒤집기2
 *
 * 접근 방법 :
 * 우선 단어일 경우, stack에 저장하고 공백 또는 '<' 문자가 나올 경우 스택을 pop 하면서 StringBuilder 에 추가합니다.
 * 그 후에 '<' 일 경우에는 '>' 가 나올 때까지 StringBuidler에 추가합니다.
 * 공백일 경우에는 스택을 비우고 공백을 추가한 후 반복합니다.
 * 그 외에 경우에는 stack에 char을 넣습니다.
 *
 * 시간 복잡도 :
 * O(N) -> 문장 길이만큼
 *
 */

public class b17413 {

    static StringBuilder sb;
    static Stack<Character> stack;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.nextLine().toCharArray();
        sb = new StringBuilder();
        stack = new Stack<>();

        int index = 0;
        while (index < chars.length) {
            if (chars[index] == '<') {
                reverseWord();
                sb.append(chars[index]);

                do {
                    sb.append(chars[++index]);
                } while (chars[index] != '>');

            } else if (chars[index] == ' ') {
                reverseWord();
                sb.append(chars[index]);
            } else {
                stack.push(chars[index]);
            }
            index++;
        }

        reverseWord();
        System.out.println(sb);

    }

    private static void reverseWord() {
        while (!stack.isEmpty()) sb.append(stack.pop());
    }
}
