package com.kirilov.interview.solutions;

import java.util.*;

//For an array of integers and unique values, write a program code to decipher if the sum of any two integers in the array is equal to a given value.
public class AnyTwoNumbersSum {

    public static boolean isSumPresent(final List<Integer> integers, int target) {
        Set<Integer> processedNumbers = new HashSet<>();
        for (Integer number : integers) {
            if (processedNumbers.contains(target - number)) {
                return true;
            }
            processedNumbers.add(number);
        }
        return false;
    }

    //using sorting + 2-pointer search.
    public static boolean isSumPresentOvercomplicatedAndSlower(final List<Integer> integers, int target) {

        //sorted list allows us to optimize our moves
        List<Integer> sortedNumbers = new ArrayList<>(integers);
        sortedNumbers.sort(Integer::compareTo);

        int halfTarget = target / 2;
        if (!(sortedNumbers.getFirst() <= halfTarget && halfTarget <= sortedNumbers.getLast())) {
            return false;
        }

        int halfTargetIndex = Math.abs(Collections.binarySearch(sortedNumbers, halfTarget) + 1);
        if (halfTargetIndex >= sortedNumbers.size()) {
            return false; //we have no duplicates, if half is the upper half,
        }

        int leftIndex = Math.max(0, halfTargetIndex - 1);
        int rightIndex = Math.min(halfTargetIndex, sortedNumbers.size() - 1);
        Set<String> usedIndexPairs = new HashSet<>();

        while (leftIndex >= 0 && rightIndex < sortedNumbers.size()) {
            int left = sortedNumbers.get(leftIndex);
            int right = sortedNumbers.get(rightIndex);
            int sum = left + right;

            String indexPair = "" + leftIndex + "," + rightIndex;
            if (usedIndexPairs.contains(indexPair)) {
                //we're repeating pairs
                return false;
            }

            if (sum == target) {
                return true;
            }

            if (sum > target) {
                //we must decrease left as it can get smaller
                if (leftIndex == 0) {
                    //if right as the limit, move right
                    rightIndex--;
                } else {
                    leftIndex--;
                }
            } else {
                //we must increase right
                if (rightIndex == sortedNumbers.size() - 1) {
                    //if right as the limit, move left
                    leftIndex++;
                } else {
                    rightIndex++;
                }
            }

            usedIndexPairs.add(indexPair);
        }
        return false;
    }
}
