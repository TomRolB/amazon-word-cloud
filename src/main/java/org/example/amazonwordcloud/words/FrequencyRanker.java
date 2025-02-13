package org.example.amazonwordcloud.words;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class FrequencyRanker implements WordRanker {

    private final WordFilter filter;
    private final Set<Character> delimiters;
    private final BreakCondition breakCondition;

    public FrequencyRanker(WordFilter filter, Set<Character> delimiters, BreakCondition breakCondition) {
        this.filter = filter;
        this.delimiters = delimiters;
        this.breakCondition = breakCondition;
    }

    @Override
    public Map<String, Integer> getRankedWords(String text) {
        Map<String, Integer> result = new HashMap<>();

        // To maximize efficiency, we will have to handle words as
        // substrings, but without even creating them at first.
        // Instead, we will create these substrings once we are
        // interested in the word in question.

        int length = text.length();
        for (int start = 0, end = 0; end < length; end++) {
            if (readWordCompletely(text, end)) {
                if (!filter.isWordAvoidable(text, start, end)) {
                    String word = text.substring(start, end).toLowerCase();
                    int frequency = incrementByOne(result, word);
                    result.put(word, frequency);

                    if (breakCondition.isMet(frequency, start, end, length)) break;
                }
                start = end + 1;
            }

        }

        return result;
    }

    private boolean readWordCompletely(String text, int end) {
        return delimiters.contains(text.charAt(end));
    }

    private static int incrementByOne(Map<String, Integer> result, String word) {
        return result.getOrDefault(word, 0) + 1;
    }
}
