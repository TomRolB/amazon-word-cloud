package org.example.amazonwordcloud.words;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        Arrays.stream(text.split(" "))
                .filter(word -> !filter.isAvoidable(word))
                .forEach(word -> incrementAssociatedValue(word, result));

        return result;
    }

    private static void incrementAssociatedValue(String word, Map<String, Integer> result) {
        result.put(word, result.getOrDefault(word, 0) + 1);
    }
}
