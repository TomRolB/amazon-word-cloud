package org.example.amazonwordcloud.words;

public class And implements WordFilter {
    private final WordFilter first;
    private final WordFilter second;

    public And(WordFilter first, WordFilter second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isAvoidable(String text, int start, int end) {
        return first.isAvoidable(text, start, end) && second.isAvoidable(text, start, end);
    }
}
