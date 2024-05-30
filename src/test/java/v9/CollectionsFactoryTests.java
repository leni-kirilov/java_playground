package v9;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionsFactoryTests {

    @Test
    public void testListFactoryMethods() {
        List<Integer> list = new ArrayList();
        list.add(5);
        list.add(10);

        assertArrayEquals(
                list.toArray(),
                List.of(5, 10).toArray()
        );

        assertArrayEquals(
                IntStream.rangeClosed(1, 2).mapToObj(i -> i * 5).toArray(),
                List.of(5, 10).toArray()
        );
    }

    @Test
    public void testSetFactoryMethod() {
        Set<String> set = new HashSet();
        set.add("b");
        set.add("a");
        set.add("c");
        set.add("a");

        assertTrue(Set.of("b", "c", "a")
                .containsAll(set));
        assertTrue(
                Stream.of("b", "a", "c", "a").collect(Collectors.toSet())
                        .containsAll(set));
    }

    @Test
    public void testMapFactoryMethod() {
        Map<String, Long> map = new HashMap();
        map.put("key1", 10L);
        map.put("plus", 0L);

        Map.of("key1", 10L, "plus", 0L).forEach((key, value) -> {
            assertEquals(map.get(key), value);
        });
    }
}
