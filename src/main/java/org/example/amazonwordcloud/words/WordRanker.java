package org.example.amazonwordcloud.words;

import java.util.Map;

public interface WordRanker {
    Map<String, Integer> getRankedWords(String text);
}
