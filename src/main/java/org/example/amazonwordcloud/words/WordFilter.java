package org.example.amazonwordcloud.words;

public interface WordFilter {
    boolean isWordAvoidable(String text, int start, int end);
}
