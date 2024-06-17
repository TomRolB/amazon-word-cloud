package org.example.amazonwordcloud.words;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class FrequencyRanker implements WordRanker {

    private final WordFilter filter;

    public FrequencyRanker(WordFilter filter) {
        this.filter = filter;
    }

    @Override
    @Cacheable("ranks")
    public Map<String, Integer> getRankedWords(String text) {
        Map<String, Integer> result = new HashMap<>();

        // To maximize efficiency, we will have to handle words as
        // substrings, but without even creating the substrings at first.
        // Instead, we will create these substrings only when we are
        // interested in the word in question.

        // TODO: should be injected
        Set<Character> delimiters = new HashSet<>(List.of(' ', ':', '.', ',', '.'));
        for (int start = 0, end = 0; end < text.length(); end++) {
            //TODO: last word is ignored
            if (delimiters.contains(text.charAt(end))
                && !filter.isAvoidable(text, start, end)) {
                incrementAssociatedValue(text.substring(start, end), result);
                start = end + 1;
            }
        }

        return result;
    }

    private static void incrementAssociatedValue(String word, Map<String, Integer> result) {
        result.put(word, result.getOrDefault(word, 0) + 1);
    }
}
