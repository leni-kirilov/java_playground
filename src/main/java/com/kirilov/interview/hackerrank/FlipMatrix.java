package com.kirilov.interview.hackerrank;

import java.util.List;

public class FlipMatrix {

    // calculates the theoretical max for each element . not really doing the flips
    public static int flippingMatrix(List<List<Integer>> matrix) {
        int maxSum = 0;

        for (int rowIndex = 0; rowIndex < matrix.size() / 2; rowIndex++) {
            for (int colIndex = 0; colIndex < matrix.get(rowIndex).size() / 2; colIndex++) {
                //find max of 4 numbers;
                int limit = matrix.size() - 1;

                int upperLeft = matrix.get(rowIndex).get(colIndex);
                int upperRight = matrix.get(rowIndex).get(limit - colIndex);
                int lowerLeft = matrix.get(limit - rowIndex).get(colIndex);
                int lowerRight = matrix.get(limit - rowIndex).get(matrix.size() - 1 - colIndex);

                maxSum += Math.max(upperLeft,
                        Math.max(upperRight,
                                Math.max(lowerLeft, lowerRight)
                        )
                );
            }
        }


        return maxSum;
    }
}
