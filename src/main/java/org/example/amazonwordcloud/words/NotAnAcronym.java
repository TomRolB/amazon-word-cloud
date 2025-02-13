package org.example.amazonwordcloud.words;

public class NotAnAcronym implements WordFilter {
    @Override
    public boolean isWordAvoidable(String text, int start, int end) {
        char startingChar = text.charAt(start);
        char endingChar = text.charAt(end - 1);
        return !((Character.isUpperCase(startingChar) || Character.isDigit(startingChar))
                && (Character.isUpperCase(endingChar) || Character.isDigit(endingChar)));
    }
}
