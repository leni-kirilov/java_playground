package com.kirilov.interview.test.hackerrank;

import com.kirilov.interview.hackerrank.CosineSimilarity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CosineSimilarityTests {

    @Test
    public void testCosineSimilarity() {

        Assertions.assertEquals(1.0,
                CosineSimilarity.cosine_similarity(
                        List.of(689944194), List.of(346.932302390659),
                        List.of(689944194), List.of(780.418827561857)
                ));
    }
}
