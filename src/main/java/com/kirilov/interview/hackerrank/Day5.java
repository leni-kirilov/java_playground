package com.kirilov.interview.hackerrank;

public class Day5 {

    public static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        SinglyLinkedListNode resultRootNode = head1;
        SinglyLinkedListNode iteratedNode = head2;
        if (head1.data > head2.data) {
            resultRootNode = head2;
            iteratedNode = head1;
        }

        SinglyLinkedListNode currentResultNode = resultRootNode;
        while (iteratedNode != null) {

            if (currentResultNode.next == null) {
                //just attach the other nodeList
                currentResultNode.next = iteratedNode;
                break;
            }

            if (currentResultNode.next.data < iteratedNode.data) {
                currentResultNode = currentResultNode.next;

            } else {
                //separate
                SinglyLinkedListNode separateNode = iteratedNode;
                iteratedNode = separateNode.next;

                //merge
                separateNode.next = currentResultNode.next;
                currentResultNode.next = separateNode;
            }
        }

        return resultRootNode;
    }

    public static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        @Override
        public String toString() {
            return "SinglyLinkedListNode{" +
                    "data=" + data +
                    ", next=" + (next != null ? next.data : "null") +
                    '}';
        }
    }
}
