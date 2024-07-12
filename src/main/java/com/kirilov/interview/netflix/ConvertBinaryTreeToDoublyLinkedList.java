package com.kirilov.interview.netflix;

import java.util.LinkedList;
import java.util.TreeSet;

public class ConvertBinaryTreeToDoublyLinkedList {

    public static LinkedList<Integer> convert(TreeSet<Integer> tree) {
        LinkedList<Integer> result = new LinkedList<>();
        result.addAll(tree);
        return result;
    }
}
