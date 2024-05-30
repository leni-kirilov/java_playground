package v9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StreamTests {

    @Test
    public void testStreamTakeWhile() {
        List<Integer> initialList = IntStream.range(1, 10).boxed().toList();

        assertArrayEquals(
                initialList.subList(0, 4).toArray(),
                initialList.stream().takeWhile(i -> i < 5).toArray()
        );
    }

    @Test
    public void testStreamDropWhile() {
        List<Integer> initialList = IntStream.range(1, 10).boxed().toList();

        assertArrayEquals(
                initialList.subList(1, initialList.size()).toArray(),
                initialList.stream().dropWhile(i -> i % 2 == 1).toArray() //drops just the first element
        );
    }

    @Test
    public void testPreservesNulls() {
        String aWordFromCharacters = "europe";
        Map<String, Object> map = Map.of("e", 10, "u", "5", "p", 3);

        assertArrayEquals(new Object[]{10, "5", null, null, 3, 10},
                Arrays
                        .stream(aWordFromCharacters.split(""))
                        .flatMap((s) -> Stream.of(map.get(s)))
                        .toArray()
        );

    }

    @Test
    public void testSkipsNulls() {
        String aWordFromCharacters = "europe";
        Map<String, Object> map = Map.of("e", 10, "u", "5", "p", 3);

        assertArrayEquals(new Object[]{10, "5", 3, 10},
                Arrays
                        .stream(aWordFromCharacters.split(""))
                        .flatMap((s) -> {
                            Object o = map.get(s);
                            return (o == null) ? Stream.empty() : Stream.of(o);
                        })
                        .toArray()
        );

        assertArrayEquals(new Object[]{10, "5", 3, 10},
                Arrays
                        .stream(aWordFromCharacters.split(""))
                        .flatMap((s) -> Stream.ofNullable(map.get(s)))
                        .toArray()
        );

    }

}
