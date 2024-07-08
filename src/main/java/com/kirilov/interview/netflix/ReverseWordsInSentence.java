package com.kirilov.interview.netflix;

import java.util.List;

//Netflix - Given a sentence, reverse words’ order.
public class ReverseWordsInSentence {

    public static final String WORD_SEPARATOR = "\\s+"; //TODO use regex whitespace?

    public static String reverse(String sentence) {
        List<String> words = List.of(sentence.split(WORD_SEPARATOR));
        return String.join(" ", words.reversed());
    }
}
