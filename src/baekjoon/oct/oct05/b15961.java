package baekjoon.oct.oct05;

import java.util.Scanner;

/**
 * 백준 15961 회전초밥 [G4]
 *
 * 접근 방식:
 * 슬라이딩 윈도우 알고리즘으로 풀었습니다.
 * 우선 0번부터 연속으로 k개의 초밥을 먹은 상태를 초기 상태로 놓고
 * 윈도우를 한칸씩 옆으로 밀어가면서 초밥의 개수를 셉니다.
 *
 * 시간 복잡도:
 * O(N * logk)
 * 323360KB 2404ms
 */

public class b15961 {

    static int N, max, d, k, c, belt[], sushi[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();                   // 벨트 길이
        d = sc.nextInt();                   // 초밥 종류
        k = sc.nextInt();                   // 연속으로 먹을 수 있는 개수
        c = sc.nextInt();                   // 쿠폰 초밥 번호
        belt = new int[N];                  // 벨트 위의 초밥들을 담을 배열
        sushi = new int[d+1];               // 해당 인덱스의 스시가 현재 몇 개인지 체크하는 배열

        // 입력값을 belt에 저장합니다.
        for (int i = 0; i < N; i++) {
            belt[i] = sc.nextInt();
        }

        eat();
        System.out.println(max);
    }

    private static void eat() {
        int cnt = 0;    // 초밥 카운팅

        // 초기값으로는 0번부터 연속 k개까지
        for (int i = 0; i < k; i++) {
            // 만약 현재 고려할 스시가 하나도 먹지 않았다면 cnt+1
            if (sushi[belt[i]]++ == 0) cnt++;
        }

        // 쿠폰 당첨 번호를 연속으로 먹지 않았다면 cnt+1
        if (sushi[c] == 0) cnt++;
        // max값 갱신
        max = Math.max(cnt, max);
        // 다시 줄여준다
        if (sushi[c] == 0) cnt--;

        int start = 0;  // 앞쪽 인덱스
        for (int i = 1; i < N; i++) {
            // 앞쪽 인덱스를 제거할건데, 제거했을 때 초밥 개수가 0이라면 cnt-1
            if (--sushi[belt[start]] == 0) cnt--;
            // 뒤쪽 인덱스는 원형이므로 (i+k-1)%N 으로 계산
            // 만약 뒤에 고려할 초밥을 안먹었던 초밥이라면 cnt+1
            if (sushi[belt[(i+k-1)%N]]++ == 0) cnt++;

            // 쿠폰 초밥 고려
            if (sushi[c] == 0) cnt++;
            max = Math.max(cnt, max);
            if (sushi[c] == 0) cnt--;

            // 앞쪽 인덱스 +1
            start++;
        }
    }
}
