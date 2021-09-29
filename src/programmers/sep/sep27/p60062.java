package programmers.sep.sep27;

import java.util.Arrays;
import java.util.Collections;

public class p60062 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
//        System.out.println(solution.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}

class Solution {

    int answer, wLen, dLen, n, weak[];
    Integer distCopy[];

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;

        answer = Integer.MAX_VALUE;
        wLen = weak.length;
        dLen = dist.length;

        // 친구들의 이동 거리를 기준으로 내림차순
        distCopy = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        Arrays.sort(distCopy, Collections.reverseOrder());

        check(0, 0, new boolean[wLen]);

        return answer;
    }

    private void check(int cnt, int friends, boolean[] checked) {
        System.out.println("현재 친구: " + friends + " 배열: " + Arrays.toString(checked));

        if (cnt == wLen) {
            answer = Math.min(answer, friends);
            System.out.println(friends + " 명의 경우 " + answer);
            return;
        }
        if (friends >= answer) return;

        int d = distCopy[friends];

        // 시계 방향으로 점검
        for (int i = 0; i < wLen; i++) {
            if (checked[i]) continue;

            // 시계 방향으로 점검
            boolean[] copyChecked = checked.clone();
            int available = count(weak[i], weak[i] + d, 0, copyChecked);
            System.out.println(Arrays.toString(copyChecked));
            check(cnt+available, friends+1, copyChecked);

            // 반시계 방향으로 점검
            copyChecked = checked.clone();
            available = count(weak[i] - d, weak[i], 0, copyChecked);
            System.out.println(Arrays.toString(copyChecked));
            check(cnt+available, friends+1, copyChecked);
        }
    }

    private int count(int from, int to, int available, boolean[] checked) {
        System.out.println(from + "~" + to);

        for (int i = 0; i < wLen; i++) {
            if (checked[i]) continue;

            if (from < 0) {
                if (weak[i] <= from + n || weak[i] >= to) {
                    checked[i] = true;
                    available++;
                }
            } else if (to >= n) {
                if (weak[i] >= from || weak[i] <= (to % n)) {
                    checked[i] = true;
                    available++;
                }
            } else {
                if (weak[i] >= from && weak[i] <= to) {
                    checked[i] = true;
                    available++;
                }
            }
        }
        return available;
    }
}
