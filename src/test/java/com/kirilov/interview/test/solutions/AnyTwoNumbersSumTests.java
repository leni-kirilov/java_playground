package com.kirilov.interview.test.solutions;

import com.kirilov.interview.solutions.AnyTwoNumbersSum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class AnyTwoNumbersSumTests {

    @Test
    public void singleElement() {
        List<Integer> integers = List.of(0);

        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 0));
    }

    @Test
    public void sequentialNumbers() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7);
        IntStream.range(3, 14)
                .forEach(sum -> Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, sum), "Expected " + sum));

        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 0), "Expected 0");
        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 15), "Expected 15");
        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 100), "Expected 100");
    }

    @Test
    public void bigList() {
        List<Integer> integers = IntStream.range(5, 200).boxed().toList();
        IntStream.range(11, 206)
                .forEach(sum -> Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, sum), "Expected " + sum));

        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 0), "Expected 0");
        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 1000), "Expected 1000");
    }

    @Test
    public void sumExistsUnordered() {
        List<Integer> integers = List.of(5, 10, 0);

        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 1));
        Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, 5));
        Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, 10));
        Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, 15));
    }

    @Test
    public void splitSeries_negative() {
        List<Integer> integers = List.of(1, 2, 8, 10);

        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 5));
        Assertions.assertFalse(AnyTwoNumbersSum.isSumPresent(integers, 15));
    }

    @Test
    public void splitSeries() {
        List<Integer> integers = List.of(1, 2, 8, 10);

        Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, 9));
        Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, 10));
        Assertions.assertTrue(AnyTwoNumbersSum.isSumPresent(integers, 12));
    }
}
