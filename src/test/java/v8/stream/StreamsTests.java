package v8.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://www.baeldung.com/java-8-streams-introduction
 * https://www.baeldung.com/java-8-streams
 * <p>
 * Can be created from an Array or Collection.
 * Can be a parallel stream
 * <p>
 * Creating operations -> of, stream, parallelStrean, iterator, generator, collect
 * Intermediate operations - > return a stream. Example:  filter, distinct, sorted, map, peek
 * Combining operations -> reduce (converts to 1), collect
 * Terminal operations -> allMatch, anyMatch, noneMatch, count, toArray; forEach
 * <p>
 * TODO implement in groovy and in kotlin
 */
public class StreamsTests {


    @Test
    public void createStream() {
        //GIVEN an array
        String[] stringArray = new String[]{"a", "b", "c"};

        //WHEN built from array
        Stream<String> streamFromArray1 = Stream.of(stringArray);
        Stream<String> streamFromArray2 = Arrays.stream(stringArray);

        //THEN they are equal
        assertArrayEquals(stringArray, streamFromArray1.toArray());
        assertArrayEquals(stringArray, streamFromArray2.toArray());

        //AND a collection
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Stream<Integer> numbersStream = numbers.stream();
        assertArrayEquals(numbers.toArray(), numbersStream.toArray());

        //AND empty stream
        assertArrayEquals(new String[0], Stream.empty().toArray());
    }

    @Test
    public void createStreamByBuilder() {
        assertEquals(3,
                Stream.<String>builder()
                        .add("a")
                        .add("b")
                        .add("c")
                        .build()
                        .map(String::length)
                        .reduce(Integer::sum)
                        .get());

        //equal to
        assertEquals(3,
                Stream.<String>builder()
                        .add("a")
                        .add("b")
                        .add("c")
                        .build()
                        .collect(Collectors.summarizingInt(String::length))
                        .getSum());

        assertEquals(3,
                (Integer) Stream.<String>builder()
                        .add("a")
                        .add("b")
                        .add("c")
                        .build()
                        .mapToInt(String::length)
                        .sum());
    }

    @Test
    public void filterOutOddnumbers() {
        //given a stream of integers
        //when I filter out the even numberswi
        //then I should be left with the odd numbers

        assertArrayEquals(new Integer[]{2, 4, 6, 8, 10},

                Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                        .filter(i -> i % 2 == 0)
                        .toArray()
        );
    }

    @Test
    public void filterLongStrings() {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Ivanhow");
        names.add("Jane");
        names.add("Peter");

        assertEquals("Ivanhow",
                names.stream()
                        .filter(name -> name.length() > 6)
                        .toList()
                        .getFirst()
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
    public void mapToDifferentStreamType() {
        String[] names = {"John", "Ivan", "Jane", "Peter"};

        assertTrue(
                Stream.of(names)
                        .map(String::length) instanceof Stream<Integer>
        );

        assertTrue(
                Stream.of(names)
                        .map(String::length)
                        .allMatch(i -> i instanceof Integer)
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
    public void groupStringsByTheirLength() {
        String[] strings = {"a", "ab", "c", "abc", "blaaaaa", "abcd", "blaaaau", "abcde"};

        Map<Integer, List<String>> map = Arrays
                .stream(strings)
                .collect(Collectors.groupingBy(String::length));

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 7},
                map.keySet().stream().mapToInt(Integer::intValue).toArray());
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
                Stream.of(new Integer[]{1, 4, 5, 1, 2, 3, 4, 5, 1})
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
                                        Pattern.compile("").splitAsStream(i.toString())
//                                Stream.of(i.toString().split(""))  //equivalent to the above splitting
                                                .map(Integer::parseInt)
                        )
                        .distinct()
//                        .peek(System.out::println)
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
    public void iterate100numbers() {
        Integer[] ints = {0, 1, 2, 3, 4};
        assertArrayEquals(
                ints,
                Stream.iterate(0, i -> i + 1)
                        .limit(5)
                        .toArray());

        //or with intStream to primitive stream
        assertArrayEquals(
                IntStream.range(0, 5).toArray(),
                Stream.iterate(0, i -> i + 1).limit(5).mapToInt(Integer::intValue).toArray()
        );
    }

    @Test
    public void generate100numbers() {
        //no assertion as it is generic
        Stream
                .generate(() -> System.currentTimeMillis() % 100)
                .limit(100)
                .forEach(System.out::println);

        //or use the Random
        System.out.println("before stream");
        new Random().ints(5).peek(System.out::println).toArray(); //NB! if you use peek you need a terminating operator!
        System.out.println("after stream");
    }

    @Test
    public void skip100numbers() {
        assert 100 == Stream.iterate(0, i -> i + 1)
                .skip(100)
                .findFirst()
                .get();
    }


    @Disabled("Because file path is not correct")
    @Test
    void splitFiles() throws IOException {
        Path path = Paths.get("StreamsTests.java");
        Stream<String> streamOfStrings = Files.lines(path);
    }
}
