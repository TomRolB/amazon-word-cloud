package org.example.amazonwordcloud.words;

public interface BreakCondition {
    boolean isMet(int frequency, int start, int end, int length);
}
