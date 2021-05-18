package may;

import java.util.*;

public class LongestStringChain {
    public static void main(String[] args) {
        SolutionLongestStringChain solution = new SolutionLongestStringChain();
//        System.out.println(solution.longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
//        System.out.println(solution.longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"}));
//        System.out.println(solution.longestStrChain(new String[]{"abcd","dbqca"}));
    }
}

class SolutionLongestStringChain {
    public int longestStrChain(String[] words) {
        int maxChain = 0;
        Set<String> wordSet = new HashSet<>();
        Map<String, Integer> wordChainMap = new HashMap<>();

        for (String word: words) {
            wordSet.add(word);
        }

        for (String word: words) {
            maxChain = Math.max(maxChain, dfs(wordChainMap, wordSet, word));
        }

        return maxChain;
    }

    private int dfs(Map<String, Integer> map, Set<String> set, String word) {
        if (map.containsKey(word)) return map.get(word);

        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            String compareWord = word.substring(0, i) + word.substring(i + 1);
            if (set.contains(compareWord)) count = Math.max(count, dfs(map, set, compareWord));
        }

        map.put(word, count + 1);
        return count + 1;
    }
}
