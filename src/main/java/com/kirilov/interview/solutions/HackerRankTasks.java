package com.kirilov.interview.solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HackerRankTasks {

    public static int lonelyinteger(List<Integer> inputNumbers) {

        Set<Integer> found = new HashSet<>(inputNumbers.size() / 2);
        for (int i : inputNumbers) {
            if (!found.add(i)) {
                found.remove(i);
            }
        }

        return found.stream().findFirst().get();
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        int diangonalTopRight = 0;
        for (int column = 0, row = arr.size() - 1; column < arr.size(); column++, row--) {
            diangonalTopRight += arr.get(row).get(column);
        }

        int diagonalTopLeft = 0;
        for (int row = 0, column = 0; row < arr.size(); column++, row++) {
            diagonalTopLeft += arr.get(row).get(column);
        }

        return Math.abs((diangonalTopRight - diagonalTopLeft));
    }
}
