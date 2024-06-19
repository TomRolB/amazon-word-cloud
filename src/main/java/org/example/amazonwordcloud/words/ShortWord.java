package org.example.amazonwordcloud.words;

public class ShortWord implements WordFilter {
    @Override
    public boolean isWordAvoidable(String text, int start, int end) {
        return end - start <= 4;
    }
}
