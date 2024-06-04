package v11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * https://www.baeldung.com/java-11-new-features
 * <p>
 * - new HttpClient - https://www.baeldung.com/java-9-http-client {@link HttpClientTests}
 * - new String methods - isBlank, lines, strip, stripLeading, stripTrailing, {@link StringTests}
 * - New Files methods - writeString, readString {@link StringTests}
 * - Collection toArray
 */
public class NewFeatures {

    @Test
    public void notPredicate() {
        //GIVEN process a string as a stream
        //THEN negate the check
        Predicate<String> isVowel = (var letter) -> List.of("o", "u", "e").contains(letter);

        //EXPECT 3 not-vowels
        Assertions.assertEquals(3,
                Arrays.stream("Word".split(""))
                        .filter(Predicate.not(isVowel))
                        .count()
        );

        //pre Java11
        Predicate<String> isNotVowel = (var letter) -> !List.of("o", "u", "e").contains(letter);
        Assertions.assertEquals(3,
                Arrays.stream("Word".split(""))
                        .filter(isNotVowel)
                        .count()
        );
    }

}