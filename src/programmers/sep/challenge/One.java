package programmers.sep.challenge;

import java.util.Arrays;

public class One {
    public static void main(String[] args) {
        SolutionOne s = new SolutionOne();
        System.out.println(s.solution(new int[]{1,2,3,4,6,7,8,0}));
        System.out.println(s.solution(new int[]{5,8,4,0,6,7,9}));
    }
}

class SolutionOne {
    public int solution(int[] numbers) {
        int answer = 0;
        int index = 0;
        int num = 0;
        Arrays.sort(numbers);

        while (num < 10) {
            if (index >= numbers.length || numbers[index] != num) {
                answer += num;
            } else {
                index++;
            }
            num++;
        }

        return answer;
    }
}
