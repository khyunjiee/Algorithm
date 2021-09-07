package june;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        SolutionNumberOfMatchingSubsequences solution = new SolutionNumberOfMatchingSubsequences();
//        System.out.println(solution.numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace"}));
//        System.out.println(solution.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"}));
    }
}

class SolutionNumberOfMatchingSubsequences {
    public int numMatchingSubseq(String s, String[] words) {
        int result = 0;

        Map<Character, Queue<String>> map = new HashMap<>();

        // MAP 초기화
        for (char c: s.toCharArray()) {
            if (!map.containsKey(c)) map.put(c, new LinkedList<>());
        }

        for (String word: words) {
            if (word.length() > s.length()) continue;

            if (map.containsKey(word.charAt(0))) map.get(word.charAt(0)).add(word);
        }

        for (char c: s.toCharArray()) {
            Queue<String> queue = map.get(c);
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.remove();

                if (word.length() == 1) ++result;
                else if (map.containsKey(word.charAt(1))) map.get(word.charAt(1)).add(word.substring(1));
            }
        }

        return result;
    }
}
