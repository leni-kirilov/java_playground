package com.kirilov.interview.hackerrank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlusMinus {
    public static void plusMinus(List<Integer> arr) {

        Map<Integer, Integer> numbers2Count = new HashMap<>(Map.of(0, 0, 1, 0, -1, 0)); //new HashMap<>(3);

        for (Integer i : arr) {
            int key = 0;
            if (i > 0) {
                key = 1;
            } else if (i < 0) {
                key = -1;
            }
            numbers2Count.put(key, numbers2Count.get(key) + 1);
        }

        System.out.printf("%.6f%n", ((double) numbers2Count.get(1) / arr.size()));
        System.out.printf("%.6f%n", ((double) numbers2Count.get(-1) / arr.size()));
        System.out.printf("%.6f%n", ((double) numbers2Count.get(0) / arr.size()));

    }

    public static void main(String[] args) {
        plusMinus(List.of(0));
        plusMinus(List.of(0, 0));
        plusMinus(List.of(-13, 1, 0, 0, 10, 1, 12, 1, 1, 0));
    }
}
