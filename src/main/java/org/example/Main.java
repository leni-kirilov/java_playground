package org.example;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("Java", "JavaScript", "C", "C#", "Python", "JavaScrip2", "Ruby");
//
//        Set<String> longestStrings = list.stream().collect(
//                Collectors.collectingAndThen(
//                        Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()),
//                        integerSetTreeMap -> integerSetTreeMap.isEmpty() ? Collections.<String>emptySet() : integerSetTreeMap.lastEntry().getValue()
//                )
//        );
//
//        System.out.println(longestStrings);


        Collector<String, ?, String> concatenatingWithSpaceCollector =
                Collector.of(
                        StringBuilder::new,
                        (sb, str) -> sb.append(str).append(" "),
                        StringBuilder::append,
                        StringBuilder::toString
                );

        String sentence = Stream.of("Java", "8", "Streams", "API", "Examples")
                .collect(concatenatingWithSpaceCollector);
        System.out.println(sentence); // Prints "Java 8 Streams API Examples "


        int sum = Stream.of(1, 2, 3, 4, 5)
                .parallel()
                .mapToInt(Integer::intValue)
                .sum(); // 1
        System.out.println(sum);
    }
}