package com.kirilov.interview.hackerrank;

import java.util.ArrayList;
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

    /**
     * Count number of occurences of numbers per index and then "print" the numbers
     *
     * @param arr
     * @return
     */
    public static List<Integer> countingSort(List<Integer> arr) {
        int MAX_COUNT_LIST_SIZE = 100;

        List<Integer> counts = new ArrayList<>(MAX_COUNT_LIST_SIZE);
        //initialize with 0s
        for (int i = 0; i < MAX_COUNT_LIST_SIZE; i++) {
            counts.add(0);
        }

        //increase each index for each occurrence
        for (int input : arr) {
            counts.set(input, counts.get(input) + 1);
        }

        //drop all trailing 0s - but the hackerrank expected to include them always
        for (int i = counts.size() - 1; i >= 0; i--) {
            if (counts.get(i) == 0) {
                counts.remove(i);
            } else {
                break;
            }
        }

        return counts;
    }
}
