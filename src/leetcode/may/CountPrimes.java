package leetcode.may;

public class CountPrimes {
    public static void main(String[] args) {
        SolutionCountPrimes solution = new SolutionCountPrimes();
        System.out.println(solution.countPrimes(10));
        System.out.println(solution.countPrimes(0));
        System.out.println(solution.countPrimes(1));
        System.out.println(solution.countPrimes(499979));
    }
}

class SolutionCountPrimes {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        int result = 0;

        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) result++;
        }

        return result;
    }
}
