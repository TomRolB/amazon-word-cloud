package org.example.amazonwordcloud.words;

public interface WordFilter {
    boolean isAvoidable(String word);
}
