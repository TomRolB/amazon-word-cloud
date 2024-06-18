package org.example.amazonwordcloud.words;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class FrequencyRanker implements WordRanker {

    private final WordFilter filter;
    private final Set<Character> delimiters;

    public FrequencyRanker(WordFilter filter, Set<Character> delimiters) {
        this.filter = filter;
        this.delimiters = delimiters;
    }

    @Override
    public Map<String, Integer> getRankedWords(String text) {
        Map<String, Integer> result = new HashMap<>();

        // To maximize efficiency, we will have to handle words as
        // substrings, but without even creating the substrings at first.
        // Instead, we will create these substrings once we are
        // interested in the word in question.

        for (int start = 0, end = 0; end < text.length(); end++) {
            //TODO: last word is ignored
            if (delimiters.contains(text.charAt(end))) {
                if (!filter.isAvoidable(text, start, end)) {
                    countWordUp(text.substring(start, end).toLowerCase(), result);
                }
                start = end + 1;
            }
        }

        return result;
    }

    private static void countWordUp(String word, Map<String, Integer> result) {
        result.put(word, result.getOrDefault(word, 0) + 1);
    }
}
