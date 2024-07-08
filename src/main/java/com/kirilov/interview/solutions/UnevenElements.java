package com.kirilov.interview.solutions;

import java.util.List;

//Given a list of integers, write a program to find the integers that are uneven.
public class UnevenElements {

    public static List<Integer> findUneven(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 1)
                .toList();
    }
}
