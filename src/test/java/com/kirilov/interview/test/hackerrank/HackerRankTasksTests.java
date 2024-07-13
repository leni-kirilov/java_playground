package com.kirilov.interview.test.hackerrank;

import com.kirilov.interview.hackerrank.HackerRankTasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HackerRankTasksTests {

    @Test
    public void oneElement() {
        Assertions.assertEquals(5, HackerRankTasks.lonelyinteger(List.of(5)));
    }

    @Test
    public void multipleElements() {
        Assertions.assertEquals(5, HackerRankTasks.lonelyinteger(List.of(1, 5, 1)));
    }

    @Test
    public void multipleElements2() {
        Assertions.assertEquals(3, HackerRankTasks.lonelyinteger(List.of(1, 5, 5, 3, 1)));
    }


    @Test
    public void element1() {
        Assertions.assertEquals(0, HackerRankTasks.diagonalDifference(
                        List.of(
                                List.of(1)
                        )
                )
        );
    }

    @Test
    public void twoRows() {
        Assertions.assertEquals(1, HackerRankTasks.diagonalDifference(
                        List.of(
                                List.of(1, 2),
                                List.of(3, 5)
                        )
                )
        );
    }
}
