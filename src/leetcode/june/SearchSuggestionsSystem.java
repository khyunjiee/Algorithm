package leetcode.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionsSystem {
    public static void main(String[] args) {
        SolutionSearchSuggestionsSystem solution = new SolutionSearchSuggestionsSystem();
        solution.suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
    }
}

class SolutionSearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);

        for (int i = 1; i < searchWord.length() + 1; i++) {
            ArrayList<String> suggest = new ArrayList<>();
            String word = searchWord.substring(0, i);

            for (int j = 0; j < products.length; j++) {
                if (suggest.size() >= 3) break;
                if (products[j].startsWith(word)) suggest.add(products[j]);
            }

            result.add(suggest);
        }

        return result;
    }
}
