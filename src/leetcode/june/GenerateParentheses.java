package leetcode.june;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        SolutionGenerateParentheses solution = new SolutionGenerateParentheses();
//        printList(solution.generateParenthesis(1));
//        printList(solution.generateParenthesis(3));
//        printList(solution.generateParenthesis(4));
    }

    public static void printList(List<String> list) {
        for (String str: list) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}

class SolutionGenerateParentheses {
    ArrayList<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        makeParenthesis("", n, 0);

        return result;
    }

    private void makeParenthesis(String s, int n, int check) {
        if (check < 0 || check > n) return;
        if (s.length() == n * 2) {
            if (check == 0) result.add(s);
            return;
        }

        makeParenthesis(s + "(", n, check + 1);
        makeParenthesis(s + ")", n, check - 1);
    }
}