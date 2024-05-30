package v9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kirilov.v9.DeprecatedClass;

public class DeprecatedTests {
    @Test
    public void testClassIsMarkedWithNewDeprecatedAnnotation() {
        Deprecated annotation = DeprecatedClass.class.getAnnotation(Deprecated.class);
        Assertions.assertFalse(annotation.forRemoval());
        Assertions.assertEquals("2024.05.30", annotation.since());
    }
}
