package v13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * PREVIEW features
 * Switch with string and conditionals
 * String blocks
 */
public class NewFeatures {

    @Test
    public void switchString() {
        var input = "one";

        var result = switch (input) {
            case "one":
                yield true;
            case "two":
                yield 2;
            default:
                yield false;
        };

        if (result instanceof Boolean) {
            Assertions.assertTrue((Boolean) result);
        } else {
            Assertions.fail();
        }
    }

    @Test
    public void switchStringRegex() {
        Function<String, Object> aSwitch = (input) -> switch (input) {

            case String containsO when containsO.contains("o"):
                yield containsO.repeat(2);

            case String moreThan5Letters when moreThan5Letters.length() > 5:
                yield moreThan5Letters.substring(0, moreThan5Letters.length() / 2);

            case String s:
                yield s.strip();
        };

        Assertions.assertEquals("wordword", aSwitch.apply("word"));
        Assertions.assertEquals(" split me", aSwitch.apply(" split me in half "));
        Assertions.assertEquals("NB!", aSwitch.apply(" NB! "));
    }

    @Test
    public void stringBlocks(){
        var block = """
                This is a multiline
                "Special" text
                about 'chars'.
                """;

        Assertions.assertTrue(block.contains("\"Special\""));
        Assertions.assertEquals(3, block.lines().count());
    }
}
