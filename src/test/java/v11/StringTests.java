package v11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StringTests {

    @Test
    public void stringEnhancementsBlank() {
        //is blank ignores whitespace
        Assertions.assertTrue("".isBlank());
        Assertions.assertTrue(" ".isBlank() != " ".isEmpty());
        Assertions.assertEquals(" ".isBlank(), " ".trim().isEmpty());

        //strip() almost the same as trim(). except for special Unicode whitespace. In the future - use strip()
        String withWhitespace = " asd ffff ";
        Assertions.assertEquals(withWhitespace.trim(), withWhitespace.strip());
    }

    @Test
    public void stringEnhancementsLines() {
        //lines() == splits("\n") - strings into array of lines
        String longString = "first paragraph \n second \n new paragraph";
        Assertions.assertEquals(3, longString.lines().count());
        Assertions.assertArrayEquals(longString.split("\n"), longString.lines().toArray());
    }

    @Test
    public void fileStringReadWrite() throws IOException {
        //GIVEN a temp file with text in it
        File tempFile = File.createTempFile("forwriting", "txt");
        String expected = "This text is written in the file";
        Files.writeString(tempFile.toPath(), expected);

        //WHEN reading the file
        String resultPreJava11 = new String(Files.readAllBytes(tempFile.toPath()));
        String result = Files.readString(tempFile.toPath());

        //THEN
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected, resultPreJava11);
    }

}