package com.kirilov.interview.netflix;

//Search for a given number in a sorted rotated array by some arbitrary number. Return -1 if the number does not exist. Assumption- the array does not contain duplicates.
public class SearchSortedRotatedArray {

    public static final int NOT_FOUND = -1;

    public static int search(int[] numbers, int target) {
        return split(numbers, target, 0, numbers.length - 1);
    }

    //-2 - search elsewhere.
    // -1 not found. End
    private static int split(int[] numbers, int target, int beginning, int end) {
        if (target == numbers[beginning]) {
            return beginning;
        }
        if (target == numbers[end]) {
            return end;
        }

        if (end - beginning <= 1) {
            return NOT_FOUND;
        }

        int middle = (beginning + end) / 2;
        boolean sortedAscending = numbers[beginning] < numbers[middle] && numbers[middle] < numbers[end];
        boolean sortedDescending = numbers[beginning] > numbers[middle] && numbers[middle] > numbers[end];
        boolean sorted = sortedDescending || sortedAscending;

        if (sorted) {
            if (sortedAscending && target <= numbers[middle] || (sortedDescending && target >= numbers[middle])) {
                //for sure on the left
                return split(numbers, target, beginning + 1, middle);
            } else {
                //for sure on the right
                return split(numbers, target, middle, end - 1);
            }

        } else {
            //not sorted. check both sides
            int left = split(numbers, target, beginning + 1, middle);

            return left != NOT_FOUND ? left : split(numbers, target, middle, end - 1);
        }
    }
}
