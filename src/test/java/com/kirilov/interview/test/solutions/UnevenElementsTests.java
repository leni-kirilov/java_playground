package com.kirilov.interview.test.solutions;

import com.kirilov.interview.solutions.UnevenElements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnevenElementsTests {

    @Test
    public void emptyList() {
        Assertions.assertTrue(UnevenElements.findUneven(new ArrayList<>()).isEmpty());
    }

    @Test
    public void oneElementList() {
        List<Integer> result = UnevenElements.findUneven(List.of(1));
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1, result.getFirst());
    }

    @Test
    public void oneElementListFiltered() {
        Assertions.assertTrue(UnevenElements.findUneven(List.of(2)).isEmpty());
    }

    @Test
    public void manyFilteredManyReturned() {
        List<Integer> result = UnevenElements.findUneven(List.of(1, 2, 3, 4, 5));
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(1, result.get(0));
        Assertions.assertEquals(3, result.get(1));
        Assertions.assertEquals(5, result.get(2));
    }
}
