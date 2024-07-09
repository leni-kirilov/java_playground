package com.kirilov.interview.test.solutions;

import com.kirilov.interview.solutions.ReverseCharactersOnly;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Given a string S, return the "reversed" string. All letters will reverse their positions, and characters that are not a letter stay in the same place.
public class ReverseCharactersOnlyTests {

    @Test
    public void numberRemainsTheSame(){
        Assertions.assertEquals("1234", ReverseCharactersOnly.reverse("1234"));
    }

    @Test
    public void reverseWord(){
        Assertions.assertEquals("dog", ReverseCharactersOnly.reverse("god"));
    }

    @Test
    public void reverseWordWithSpecialCharactersEndOfWord(){
        Assertions.assertEquals("dog!", ReverseCharactersOnly.reverse("god!"));
    }

    @Test
    public void reverseWordWithDigits(){
        Assertions.assertEquals("c1ba2", ReverseCharactersOnly.reverse("a1bc2"));
    }

    @Test
    public void reverseWordWithSpecialCharacters(){
        Assertions.assertEquals("...do,gon?!", ReverseCharactersOnly.reverse("...no,god?!"));
    }
}
