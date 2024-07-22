package com.kirilov.interview.hackerrank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CosineSimilarity {
    /**
     * Complete the 'cosine_similarity' function below.
     * <p>
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY a_keys
     * 2. DOUBLE_ARRAY a_values
     * 3. INTEGER_ARRAY b_keys
     * 4. DOUBLE_ARRAY b_values
     */
    public static double cosine_similarity(List<Integer> a_keys, List<Double> a_values, List<Integer> b_keys, List<Double> b_values) {
        double dotProduct = summarizeProductOfMatchingIndexes(a_keys, a_values, b_keys, b_values);
        double magnitudeA = calculateMagnitude(a_values);
        double magnitudeB = calculateMagnitude(b_values);

        return dotProduct / (magnitudeA * magnitudeB);
    }

    private static double summarizeProductOfMatchingIndexes(List<Integer> a_keys, List<Double> a_values, List<Integer> b_keys, List<Double> b_values) {

        Map<Integer, Double> vectorA = buildVectorMap(a_keys, a_values);
        Map<Integer, Double> vectorB = buildVectorMap(b_keys, b_values);

        double sum = 0;
        for (int aKey : vectorA.keySet()) {
            if (vectorB.get(aKey) != null) {
                sum += vectorA.get(aKey) * vectorB.get(aKey);
            }
        }
        return sum;
    }

    //building a map to more easily get corresponding values
    private static Map<Integer, Double> buildVectorMap(List<Integer> keys, List<Double> values) {
        Map<Integer, Double> vector = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            vector.put(keys.get(i), values.get(i));
        }

        return vector;
    }

    private static double calculateMagnitude(List<Double> values) {

        //TODO is double precise enough?
        double sumOfSquares = values.parallelStream().reduce(0d, (s, value) -> s + value * value, Double::sum);
        values.parallelStream().map(value -> Math.pow(value, 2)).mapToDouble(Double::doubleValue).sum();
        return Math.sqrt(sumOfSquares);
    }
}