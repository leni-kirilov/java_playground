package com.kirilov.interview.test.hackerrank;

import com.kirilov.interview.hackerrank.FlipMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FlipMatrixTests {

    @Test
    public void oneElement() {
        Assertions.assertEquals(4,
                FlipMatrix.flippingMatrix(
                        new ArrayList<>(List.of(
                                new ArrayList<>(List.of(1, 2)),
                                new ArrayList<>(List.of(3, 4))
                        ))
                )
        );
    }

    @Test
    public void fourElement() {
        Assertions.assertEquals(414,
                FlipMatrix.flippingMatrix(
                        new ArrayList<>(List.of(
                                new ArrayList<>(List.of(112, 42, 83, 119)),
                                new ArrayList<>(List.of(56, 125, 56, 49)),
                                new ArrayList<>(List.of(15, 78, 101, 43)),
                                new ArrayList<>(List.of(62, 98, 114, 108))
                        ))
                )
        );
    }

}
