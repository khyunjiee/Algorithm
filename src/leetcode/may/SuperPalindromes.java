package may;

public class SuperPalindromes {
    public static void main(String[] args) {
        SolutionSuperPalindromes solution = new SolutionSuperPalindromes();
        System.out.println(solution.superpalindromesInRange("4", "1000"));
        System.out.println(solution.superpalindromesInRange("1", "2"));
    }
}

class SolutionSuperPalindromes {
    public int superpalindromesInRange(String left, String right) {
        int count = 0;
        long leftNum = Long.parseLong(left);
        long rightNum = Long.parseLong(right);
        int MAX = 100000;

        // 길이가 홀수인 palindrom
        for (int i = 1; i < MAX; ++i) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            for (int j = sb.length() - 2; j >= 0; --j) {
                sb.append(sb.charAt(j));
            }
            long num = Long.parseLong(sb.toString());
            num *= num;

            if (num > rightNum) break;
            if (num >= leftNum && isPalindrom(num)) count++;
        }

        // 길이가 짝수인 palindrom
        for (int i = 1; i < MAX; ++i) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            for (int j = sb.length() - 1; j >= 0; --j) {
                sb.append(sb.charAt(j));
            }
            long num = Long.parseLong(sb.toString());
            num *= num;

            if (num > rightNum) break;
            if (num >= leftNum && isPalindrom(num)) count++;
        }


        return count;
    }

    private boolean isPalindrom(long num) {
        return num == reverse(num);
    }

    private long reverse(long num) {
        long result = 0;

        while (num > 0) {
            result = 10 * result + num % 10;
            num /= 10;
        }

        return result;
    }
}
