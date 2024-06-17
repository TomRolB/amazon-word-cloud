package org.example.amazonwordcloud.words;

public interface WordFilter {
    boolean isAvoidable(String text, int start, int end);
}
