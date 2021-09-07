package leetcode.may;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        SolutionEvaluateReversePolishNotation solution = new SolutionEvaluateReversePolishNotation();
//        System.out.println(solution.evalRPN(new String[]{"2","1","+","3","*"}));
//        System.out.println(solution.evalRPN(new String[]{"4","13","5","/","+"}));
        System.out.println(solution.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
}

class SolutionEvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> calculation = new Stack<>();

        for (String token: tokens) {
            if (token.equals("+")) {
                int a = calculation.pop();
                int b = calculation.pop();
                calculation.push(b + a);
            } else if (token.equals("-")) {
                int a = calculation.pop();
                int b = calculation.pop();
                calculation.push(b - a);
            } else if (token.equals("*")) {
                int a = calculation.pop();
                int b = calculation.pop();
                calculation.push(b * a);
            } else if (token.equals("/")) {
                int a = calculation.pop();
                int b = calculation.pop();
                calculation.push(b / a);
            } else {
                calculation.push(Integer.parseInt(token));
            }
        }

        return calculation.pop();
    }
}
