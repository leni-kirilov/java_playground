package com.kirilov.interview.solutions;

import java.util.List;
import java.util.LongSummaryStatistics;

public class MinMaxSum {
    public static void minMaxSumFrom5Numbers(List<Integer> arr) {

        LongSummaryStatistics longSummaryStatistics = arr.stream().mapToLong(i -> (long) i).summaryStatistics();
        long min = longSummaryStatistics.getMin();
        long max = longSummaryStatistics.getMax();

        long sum = longSummaryStatistics.getSum();
        long minSum = sum - max;
        long maxSum = sum - min;
        System.out.print(minSum + " " + maxSum);
    }

    public static void main(String[] args) {
        minMaxSumFrom5Numbers(List.of(793810624, 895642170, 685903712, 623789054, 468592370));
        minMaxSumFrom5Numbers(List.of(5, 5, 5, 5, 5));
    }
}
