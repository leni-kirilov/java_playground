package com.kirilov.interview.test.netflix;

import com.kirilov.interview.netflix.SearchSortedRotatedArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchSortedRotatedArrayTests {

    @Test
    public void notPresent() {
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(new int[]{7, 8}, 5));
    }

    @Test
    public void shiftedOnce() {
        int[] shiftedOnce = {100, 1, 2, 3, 4, 6 , 7, 8, 10};
        for (int i = 0; i < shiftedOnce.length; i++) {
            Assertions.assertEquals(i, SearchSortedRotatedArray.search(shiftedOnce, shiftedOnce[i]));
        }

        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(shiftedOnce, 0));
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(shiftedOnce, 5));
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(shiftedOnce, 40));
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(shiftedOnce, 150));
    }

    @Test
    public void negativeShiftedMore() {
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(new int[]{55, 100, 1, 2, 7, 8}, 99));
    }

    @Test
    public void shiftedMore() {
        Assertions.assertEquals(1, SearchSortedRotatedArray.search(new int[]{55, 100, 1, 2, 7, 8}, 100));
    }

    @Test
    public void shiftedMoreDescending() {
        int[] shiftedOnce = {20, 1, 100, 90 , 88, 66, 64 ,63, 60, 30};
        for (int i = 0; i < shiftedOnce.length; i++) {
            Assertions.assertEquals(i, SearchSortedRotatedArray.search(shiftedOnce, shiftedOnce[i]));
        }
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(new int[]{20, 1, 100, 60, 30}, 5));
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(new int[]{20, 1, 100, 60, 30}, 110));
        Assertions.assertEquals(-1, SearchSortedRotatedArray.search(new int[]{20, 1, 100, 60, 30}, -4));
    }
}
