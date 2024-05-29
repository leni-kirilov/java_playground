import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO create repo, push to GH, add CI/CD
 * TODO add links to tutorials about the newly learn thing so that I can revise it quickly
 */
public class Java8StreamsTests {

    @Test
    public void filter_outOddnumbers() {
        //given a stream of integers
        //when I filter out the even numberswi
        //then I should be left with the odd numbers

        assertArrayEquals(new Integer[]{2, 4, 6, 8, 10},
                Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                        .filter(i -> i % 2 == 0)
                        .toArray(Integer[]::new)
        );
    }

    @Test
    public void map_toUpperCase() {
        //given a stream of strings
        //when I map them to upper case
        //then I should have a stream of upper case strings
        assertArrayEquals(new String[]{"A", "B", "C"},
                Stream.of(new String[]{"a", "b", "c"})
                        .map(String::toUpperCase)
                        .toArray(String[]::new)
        );
    }

    @Test
    public void reduce_calculateSumOfAgeOfMales() {
        //given a stream of people objects with sex and age
        //then filter out females
        //and summarize their age

        Human[] humans = {
                new Human(true, 30),
                new Human(true, 40),
                new Human(false, 30),
                new Human(false, 20),
                new Human(true, 30),
        };

        //nice
        assertEquals(100,
                Arrays.stream(humans)
                        .filter(human -> human.isMale)
                        .map(human -> human.age)
                        .reduce(Integer::sum)
                        .get());

        //uglier/less readable because we didn't use map
        assertEquals(100,
                Arrays.stream(humans)
                        .filter(human -> human.isMale)
                        .reduce(0, (sum, human) -> sum + human.age, Integer::sum));
    }

    private static class Human {

        boolean isMale;
        int age;

        public Human(boolean isMale, int age) {
            this.isMale = isMale;
            this.age = age;
        }
    }

    @Test
    public void find_biggest_word_in_array() {
        //given a stream of strings with different lengths
        //then find the longest string
        //and return it

        String[] strings = {"a", "ab", "abc", "blaaaaa", "abcd", "blaaab", "abcde"};
        assertEquals("blaaaaa",
                Arrays.stream(strings)
                        .reduce((a, b) -> a.length() > b.length() ? a : b)
                        .get()
        );

        assertEquals("blaaaaa",
                Arrays.stream(strings)
                        .max((a, b) -> a.length() > b.length() ? 1 : -1)
                        .get()
        );
    }

    @Test
    public void find_all_biggest_words_in_array() {
        //given a stream of strings with different lengths
        //then find all strings with the longest length
        //and return it

        String[] strings = {"a", "ab", "abc", "blaaaaa", "abcd", "blaaaau", "abcde"};
        String[] expected = {"blaaaaa", "blaaaau"};

        Assertions.assertArrayEquals(expected,
                Arrays.stream(strings)
                        .collect(
                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(String::length /*key*/, TreeMap::new /*put into a tree*/, Collectors.toList() /*values*/),
                                        integerListTreeMap -> integerListTreeMap.isEmpty() ? Collections.<String>emptyList() : integerListTreeMap.lastEntry().getValue()
                                )//end of collectingAndThen
                        )//end of collect
                        .toArray(String[]::new)
        );
    }

    @Test
    public void distinct_sorted_removeDuplicateElements() {
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5},
                Stream.of(new Integer[]{1, 4, 1, 2, 3, 4, 5, 5, 1})
                        .distinct()
                        .sorted()
                        .toArray(Integer[]::new)
        );
    }

    @Test
    public void flatMap_findUniqueDigitsInNumbers() {
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5},
                Stream.of(new Integer[]{14, 23, 4, 5, 50})
                        .flatMap(i ->
                                Stream
                                        .of(i.toString().split(""))
                                        .map(Integer::parseInt)
                        )
                        .distinct()
                        .peek(System.out::println)
                        .sorted()
                        .toArray(Integer[]::new)
        );
    }

    @Test
    public void parallel_peek_findSumOfNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(55, numbers
                .parallelStream()
                .peek(System.out::println)
                .reduce(0, Integer::sum));
    }

    @Test
    public void generate100numbers() {
        Stream.iterate(0, i -> i + 1)
                .limit(100)
                .forEach(System.out::println);
    }

    @Test
    public void skip100numbers() {
        assert 100 == Stream.iterate(0, i -> i + 1)
                .skip(100)
                .findFirst()
                .get();
    }
}
