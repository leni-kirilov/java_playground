package com.kirilov.interview.hackerrank;

import java.util.Scanner;
import java.util.Stack;

public class QueueWithStacks {

    public static final int ENQUEUE = 1;
    public static final int DEQUEUE = 2;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int operations = scan.nextInt();

        MyQueue queue = new MyQueue();

        for (int i = 0; i < operations; i++) {

            int operation = scan.nextInt();
            if (operation == ENQUEUE) {
                int number = scan.nextInt();
                queue.enqueue(number);
            } else if (operation == DEQUEUE) {
                queue.dequeue(false);
            } else {
                queue.printFrontItem();
            }

            if (i != operations - 1) {
                scan.nextLine();
            }
        }

    }

    static class MyQueue {

        Stack<Integer> enqueue = new Stack<>();
        Stack<Integer> forDequeue = new Stack<>();

        //push to the first stack
        void enqueue(int number) {
            enqueue.push(number);
        }

        int dequeue(boolean printOnly) {
            //inverse the stack only if it's not empty
            if (forDequeue.isEmpty()) {
                while (!enqueue.isEmpty()) {
                    forDequeue.push(enqueue.pop());
                }
            }

            int result = printOnly ? forDequeue.peek() : forDequeue.pop();

            return result;
        }

        void printFrontItem() {
            System.out.println(dequeue(true));
        }
    }
}
