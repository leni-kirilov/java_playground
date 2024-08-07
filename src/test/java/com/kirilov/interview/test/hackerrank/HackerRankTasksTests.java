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

    @Test
    public void zigZag() {
        Assertions.assertArrayEquals(
                new int[]{1, 3, 2},
                HackerRankTasks.findZigZagSequence(new int[]{3, 2, 1})
        );
    }

    @Test
    public void ceaserLetter(){
        Assertions.assertEquals("b", HackerRankTasks.caesarCipher("a", 1));
        Assertions.assertEquals("z", HackerRankTasks.caesarCipher("a", 25));
        Assertions.assertEquals("a", HackerRankTasks.caesarCipher("z", 1));
        Assertions.assertEquals("v", HackerRankTasks.caesarCipher("a", 99));
    }

    @Test
    public void ceaserMultiple(){
        Assertions.assertEquals("cd", HackerRankTasks.caesarCipher("ab", 2));
        Assertions.assertEquals("DE", HackerRankTasks.caesarCipher("AB", 3));
    }

    @Test
    public void ceaserSkipNonletters(){
        Assertions.assertEquals("fg-ii", HackerRankTasks.caesarCipher("ab-dd", 5));
    }
}
