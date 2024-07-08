package com.kirilov.interview.test.netflix;

import com.kirilov.interview.netflix.ReverseWordsInSentence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseWordsInSentenceTests {

    @Test
    public void reverseOneWord() {
        Assertions.assertEquals("dog", ReverseWordsInSentence.reverse("dog"));
    }

    @Test
    public void reverseTwoWords() {
        Assertions.assertEquals("dog big", ReverseWordsInSentence.reverse("big dog"));
    }

    @Test
    public void reverseTwoWordsSpecialWhitespace() {
        Assertions.assertEquals("dog big", ReverseWordsInSentence.reverse("big   dog"));
    }
}
