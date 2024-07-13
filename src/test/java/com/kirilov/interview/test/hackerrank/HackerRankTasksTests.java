package com.kirilov.interview.test.hackerrank;

import com.kirilov.interview.hackerrank.HackerRankTasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HackerRankTasksTests {

    @Test
    public void lonelyintegerOneElement() {
        Assertions.assertEquals(5, HackerRankTasks.lonelyinteger(List.of(5)));
    }

    @Test
    public void lonelyintegerMultipleElements() {
        Assertions.assertEquals(5, HackerRankTasks.lonelyinteger(List.of(1, 5, 1)));
    }

    @Test
    public void lonelyintegerMultipleElements2() {
        Assertions.assertEquals(3, HackerRankTasks.lonelyinteger(List.of(1, 5, 5, 3, 1)));
    }

    @Test
    public void diagonalDifferenceElement1() {
        Assertions.assertEquals(0, HackerRankTasks.diagonalDifference(
                        List.of(
                                List.of(1)
                        )
                )
        );
    }

    @Test
    public void diagonalDifferenceTwoRows() {
        Assertions.assertEquals(1, HackerRankTasks.diagonalDifference(
                        List.of(
                                List.of(1, 2),
                                List.of(3, 5)
                        )
                )
        );
    }

    @Test
    public void countingSort_1elemnet() {
        Assertions.assertArrayEquals(
                List.of(0, 1).toArray(),
                HackerRankTasks.countingSort(List.of(1)).toArray()
        );
    }

    @Test
    public void countingSort_2elements_sorted() {
        Assertions.assertArrayEquals(
                List.of(0, 1, 1).toArray(),
                HackerRankTasks.countingSort(List.of(1, 2)).toArray()
        );
    }

    @Test
    public void countingSort_2elements_nonsorted() {
        Assertions.assertArrayEquals(
                List.of(0, 1, 1).toArray(),
                HackerRankTasks.countingSort(List.of(2, 1)).toArray()
        );
    }

    @Test
    public void countingSort_3elements_nonsorted_repeating() {
        Assertions.assertArrayEquals(
                List.of(1, 1, 2).toArray(),
                HackerRankTasks.countingSort(List.of(2, 1, 2, 0)).toArray()
        );
    }
}
