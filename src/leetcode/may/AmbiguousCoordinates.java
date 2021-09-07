package leetcode.may;

import java.util.ArrayList;
import java.util.List;

public class AmbiguousCoordinates {
    public static void main(String[] args) {
        SolutionAmbiguousCoordinates solution = new SolutionAmbiguousCoordinates();
        solution.ambiguousCoordinates("(123)");
        solution.ambiguousCoordinates("(00011)");
        solution.ambiguousCoordinates("(0123)");
        solution.ambiguousCoordinates("(100)");
    }
}

class SolutionAmbiguousCoordinates {
    private List<String> answer, firstStrings;

    public List<String> ambiguousCoordinates(String s) {
        answer = new ArrayList<>();

        for (int i = 2; i < s.length() - 1; i++) {
            String[] strings = {s.substring(1, i), s.substring(i, s.length() - 1)};
            firstStrings = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                isValid(strings[j], j);
            }
        }

        return answer;
    }

    private void isValid(String string, int index) {
        if (string.length() == 1 || string.charAt(0) != '0') addToList(string, index);
        if (string.length() > 1 && string.charAt(string.length() - 1) != '0')
            addToList(string.substring(0, 1) + "." + string.substring(1), index);
        if (string.length() > 2 && string.charAt(0) != '0' && string.charAt(string.length() - 1) != '0') {
            for (int i = 2; i < string.length(); i++) {
                addToList(string.substring(0, i) + "." + string.substring(i), index);
            }
        }
    }

    private void addToList(String string, int index) {
        if (index > 0) {
            for (String s: firstStrings) {
                answer.add("(" + s + ", " + string + ")");
            }
        } else {
            firstStrings.add(string);
        }
    }
}
