package com.kirilov.interview.solutions;

import java.util.Collections;
import java.util.List;

public class FindMedian {

    public static int findMedian(List<Integer> arr) {
        Collections.sort(arr);
        int median = arr.get((arr.size() -1)/ 2);
        return median;
    }
}
