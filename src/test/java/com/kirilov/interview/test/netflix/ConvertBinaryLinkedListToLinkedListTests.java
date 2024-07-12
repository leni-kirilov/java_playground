package com.kirilov.interview.test.netflix;

import com.kirilov.interview.netflix.ConvertBinaryTreeToDoublyLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class ConvertBinaryLinkedListToLinkedListTests {

    @Test
    public void sortedInput() {
        test(List.of(1, 2, 3, 4, 5));
    }

    @Test
    public void sortedBackwardsInput() {
        test(List.of(5, 4, 3, 2, 1));
    }

    @Test
    public void shuffledInput() {
        test(List.of(3, 1, 2, 5, 4));
    }

    //TODO 1 element, 

    private void test(List<Integer> inputNumbers) {

        //given binary tree
        TreeSet<Integer> tree = new TreeSet<>();
        tree.addAll(inputNumbers);

        //when convert
        LinkedList<Integer> resultSortedList = ConvertBinaryTreeToDoublyLinkedList.convert(tree);

        //expect linked list
        List<Integer> expectedSortedInput = inputNumbers.stream().sorted().toList();
        for (int index = 0; index < inputNumbers.size(); index++) {
            Assertions.assertEquals(expectedSortedInput.get(index), resultSortedList.get(index));
        }
    }

}
