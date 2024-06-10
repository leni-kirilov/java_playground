package v14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * PREVIEW features
 * Records
 * instanceof smarter  if
 * Packaging Tool - to test in other projects
 */
public class NewFeatures {

    private record JavaFeatures(String name, boolean isCool, int numberOfFeatures) {
    }

    private record RecordWithCustomToString(String a, String b) {
        @Override
        public String toString() {
            return a;
        }
    }

    @Test
    public void record() {
        var java11 = new JavaFeatures("Java11", true, 100);
        Assertions.assertEquals("Java11", java11.name);

        var java12 = new JavaFeatures("Java12", false, 1);
        Assertions.assertNotEquals(java11, java12);
    }

    @Test
    public void recordWithCustomToString() {
        var recordWithCustomToString = new RecordWithCustomToString("cat", "car");
        //Assertions.assertEquals("RecordWithCustomToString[a=cat, b=car]", recordWithCustomToString.toString());
        Assertions.assertEquals("cat", recordWithCustomToString.toString());
    }

    @Test
    public void smartInstanceOf() {
        var unknownType = new Random(20).nextInt() > 10 ? 100 : "digit";
        if (unknownType instanceof String text) {
            Assertions.assertEquals("digit", text);
        } else {
           Assertions.assertEquals(100, unknownType);
        }
    }
}
