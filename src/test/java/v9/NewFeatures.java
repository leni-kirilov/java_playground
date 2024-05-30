package v9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * https://www.baeldung.com/new-java-9
 * https://www.digitalocean.com/community/tutorials/java-9-features-with-examples
 *
 *
 * <p>
 * - TODO Jigsaw modular java - https://openjdk.org/projects/jigsaw/quick-start
 * - TODO Multi-Release JAR Files - https://www.baeldung.com/java-multi-release-jar
 * <p>
 * - Stream extensions - dropWhile, takeWhile, Optional to Stream, https://www.baeldung.com/java-9-stream-api . {@link StreamTests}
 * - Collection Factory Methods - {@link CollectionsFactoryTests}
 * - Process API - {@link #testProcessHandling}
 * - try-with-resources {@link #testTryWithResources()}
 * - Interface Private Method - {@link #testPrivateInterfaceMethod()}
 * - @Deprecated with more methods - {@link DeprecatedTests#testClassIsMarkedWithNewDeprecatedAnnotation()}
 */
public class NewFeatures {

    @Test
    public void testProcessHandling() {
        //GIVEN this is a JVM process
        //WHEN we get the current process
        ProcessHandle processHandle = ProcessHandle.current();

        //THEN we should see a JVM process, time it was running etc
        Assertions.assertTrue(processHandle.pid() > 0);
        Assertions.assertTrue(processHandle.info().command().get().contains("java"));
        processHandle.children().forEach(System.out::println);
    }

    @Test
    public void testTryWithResources() throws IOException {
        //GIVEN creating a temp file
        Path tempFile = Files.createTempFile("tempFile", ".txt");
        final String tempFileAbsolutePath = tempFile.toAbsolutePath().toString();

        //WHEN opening the file with try-closeble
        try (FileReader reader = new FileReader(tempFileAbsolutePath)) {
            assertEquals(-1, reader.read()); //as the file is empty
        }
        //THEN the file is closed
    }

    @Test
    public void testPrivateInterfaceMethod() {
        interface WithPrivateMethods {
            private int complexCalculation(int input) {
                return 2 * input;
            }

            default int defaultMethodIsTooBig(int input) {
                return complexCalculation(input);
            }
        }

        //WHEN nobody ovewrites it
        WithPrivateMethods obj = new WithPrivateMethods() {
        };
        assertEquals(10, obj.defaultMethodIsTooBig(5));

        //AND if you overwrite it
        WithPrivateMethods multipliesBy2 = new WithPrivateMethods() {
            @Override
            public int defaultMethodIsTooBig(int input) {
                return WithPrivateMethods.super.defaultMethodIsTooBig(input) * 2;
            }
        };
        assertEquals(20, multipliesBy2.defaultMethodIsTooBig(5));
    }

}