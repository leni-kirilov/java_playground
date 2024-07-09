package com.kirilov.interview.solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Given a string S, return the "reversed" string. All letters will reverse their positions, and characters that are not a letter stay in the same place.
public class ReverseCharactersOnly {

    //there is 1-pass loop that uses 2 index pointers and substitutes chars in place

    //2 loop passes and using chars
    public static String reverse(String s) {

        char[] chars = s.toCharArray();

        Stack<Character> letters = new Stack<>();
        for (char c : chars) {
            if (Character.isLetter(c)) {
                letters.push(c);
            }
        }

        for (int i = 0; i < chars.length; i++) {

            if (Character.isLetter(chars[i])) {
                //letters pop in reverse order
                chars[i] = letters.pop();
            }
        }

        return new String(chars);
    }

    //uses one too many collections and string builders
    public static String reverseWithMoreCollections(String s) {

        List<String> reversedCharacters = s.chars()
                .filter(Character::isLetter)
                .mapToObj(Character::toString)
                .toList()
                .reversed();
        LinkedList<String> reversedCharactersStack = new LinkedList<>(reversedCharacters);

        StringBuilder result = new StringBuilder();
        for (String characterToAdd : s.split("")) {

            if (Character.isLetter(characterToAdd.charAt(0))) {
                //substitute with reversed
                characterToAdd = reversedCharactersStack.pop();
            }

            result.append(characterToAdd);
        }

        return result.toString();
    }
}
