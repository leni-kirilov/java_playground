package v8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTests {

    @Test
    public void testOptionalPresent() {

        //GIVEN we create an Optional with a real string
        String EXPECTED = "Hello, World!";
        Optional<String> optionalValue = Optional.ofNullable(EXPECTED);

        //THEN if present returns the value
        optionalValue.ifPresent(value -> Assertions.assertEquals(EXPECTED, value));

        //AND orElse returns the same value
        String elseValue = optionalValue.orElse("Default Value");
        Assertions.assertEquals(EXPECTED, elseValue);

        //AND Using orElseThrow never throws an exception
        try {
            optionalValue.orElseThrow(() -> new RuntimeException("Value not present"));
        } catch (RuntimeException e) {
            Assertions.fail("no exception thrown");
        }
    }

    @Test
    public void testOptionalIsNull() {
        //GIVEN optional with a null
        Optional<String> optionalValue = Optional.ofNullable(null);

        //THEN ifPresent doesn't return a value
        optionalValue.ifPresent(value -> Assertions.fail());

        //AND orElse returns the else value
        String EXPECTED_ELSE = "ELSE value!";
        String elseValue = optionalValue.orElse(EXPECTED_ELSE);
        Assertions.assertEquals(EXPECTED_ELSE, elseValue);

        //AND Using orElseThrow throws an exception
        Assertions.assertThrowsExactly(
                RuntimeException.class,
                () -> optionalValue.orElseThrow(() -> new RuntimeException("Value not present")),
                "Exception was not thrown!"
        );
    }
}

//TODO Future Tests - examples - play this through
//TODO event-driven architecture (EDA).