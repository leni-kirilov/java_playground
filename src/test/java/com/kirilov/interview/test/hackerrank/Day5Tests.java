package com.kirilov.interview.test.hackerrank;

import com.kirilov.interview.hackerrank.Day5;
import com.kirilov.interview.hackerrank.Day5.SinglyLinkedListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day5Tests {

    @Test
    public void oneEmptyList() {
        SinglyLinkedListNode node1 = new SinglyLinkedListNode();
        Assertions.assertEquals(node1, Day5.mergeLists(node1, null));
        Assertions.assertEquals(node1, Day5.mergeLists(null, node1));
    }

    @Test
    public void mergeSmallNodes() {

        SinglyLinkedListNode node1 = buildList(List.of(0));
        SinglyLinkedListNode node2 = buildList(List.of(5));

        assertNodeChain(List.of(0, 5), Day5.mergeLists(node1, node2));
    }

    @Test
    public void mergeListsNodes() {

        SinglyLinkedListNode node1 = buildList(List.of(0, 5));
        SinglyLinkedListNode node3 = buildList(List.of(3));

        assertNodeChain(List.of(0, 3, 5), Day5.mergeLists(node1, node3));
    }

    @Test
    public void merge2ListsNodes() {

        SinglyLinkedListNode node1 = buildList(List.of(1, 5));
        SinglyLinkedListNode node3 = buildList(List.of(3, 6,7));

        assertNodeChain(List.of(1, 3, 5, 6, 7), Day5.mergeLists(node1, node3));
    }

    private void assertNodeChain(List<Integer> expectedList, SinglyLinkedListNode resultNode) {
        for (int expected : expectedList) {
            Assertions.assertEquals(expected, resultNode.data);
            resultNode = resultNode.next;
        }

        Assertions.assertNull(resultNode);
    }

    private SinglyLinkedListNode buildList(List<Integer> input) {
        SinglyLinkedListNode root = new SinglyLinkedListNode();
        SinglyLinkedListNode node = root;

        for (int i = 0; i < input.size(); i++) {
            node.data = input.get(i);

            if (i < input.size() - 1) {
                node.next = new SinglyLinkedListNode();
                node = node.next;
            }

        }

        return root;
    }
}
