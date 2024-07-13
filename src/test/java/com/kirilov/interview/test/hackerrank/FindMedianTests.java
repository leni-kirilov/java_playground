package com.kirilov.interview.test.hackerrank;

import com.kirilov.interview.solutions.FindMedian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FindMedianTests {

    @Test
    public void oneElement() {
        Assertions.assertEquals(1, FindMedian.findMedian(new ArrayList<>(List.of(1))));
    }

    @Test
    public void twoElement() {
        Assertions.assertEquals(1, FindMedian.findMedian(new ArrayList<>(List.of(1, 2))));
    }

    @Test
    public void oddCount() {
        Assertions.assertEquals(4, FindMedian.findMedian(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7))));
        Assertions.assertEquals(4, FindMedian.findMedian(new ArrayList<>(List.of(7, 1, 6, 2, 3, 5, 4))));
    }

    @Test
    public void evenCount() {
        Assertions.assertEquals(3, FindMedian.findMedian(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))));
        Assertions.assertEquals(3, FindMedian.findMedian(new ArrayList<>(List.of(1, 5, 6, 4, 2, 3))));
    }
}
