package leetcode.may;

public class ValidNumber {
    public static void main(String[] args) {
        SolutionValidNumber solution = new SolutionValidNumber();
        System.out.println(solution.isNumber("0"));
        System.out.println(solution.isNumber("2"));
        System.out.println(solution.isNumber("-0.1"));
        System.out.println(solution.isNumber("2e10"));
        System.out.println(solution.isNumber("+6e-1"));
        System.out.println(solution.isNumber("3e+7"));
        System.out.println(solution.isNumber("53.5e93"));
        System.out.println(solution.isNumber("-90E3"));
        System.out.println(solution.isNumber("abc"));
        System.out.println(solution.isNumber("1a"));
        System.out.println(solution.isNumber("99e2.5"));
        System.out.println(solution.isNumber("--6"));
        System.out.println(solution.isNumber("95a54e53"));
        System.out.println(solution.isNumber(".-1"));
        System.out.println(Double.parseDouble("99e2.5"));
    }
}

class SolutionValidNumber {
    public boolean isNumber(String s) {
//        try {
//            Double.parseDouble(s);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//
//        for (String string: s.toLowerCase().split("")) {
//            if (invalidAlpha.contains(string)) return false;
//        }

        String string = s.toLowerCase().trim();
        boolean dot = false, exp = false, digit = false;

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isDigit(c)) digit = true;
            else if (c == '.') {
                if (exp || dot) return false;
                dot = true;
            }
            else if (c == 'e') {
                if (exp || digit == false) return false;
                exp = true;
                digit = false;
            }
            else if (c == '+' || c == '-') {
                if (i > 0 && string.charAt(i - 1) != 'e') return false;
            }
            else return false;
        }

        return digit;
    }
}
