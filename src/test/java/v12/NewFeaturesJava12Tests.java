package v12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Collectors.teeing - do 2 separate calculations that are combined at the end
 * String - indent, transform
 * Files - mismatch
 */
public class NewFeaturesJava12Tests {

    @Test
    public void collectorTeeing() {
        //GIVEN Comparator maxSalary
        Collector<Employee, ?, Optional<Employee>> findWithMaxSalary = Collectors.maxBy(Comparator.comparingInt(Employee::salary));

        //AND a list of Employees with their salaries
        var employees = List.of(
                new Employee("Peter", 10000),
                new Employee("Ivan", 5000),
                new Employee("Patricia", 15000)
        );

        //EXPECT 1/2 paid to Patricia -  we tee to calculate % of total salaries are paid to the most expensive employee
        Assertions.assertEquals(0.5d, employees.stream()
                .collect(Collectors.teeing(
                        Collectors.summingInt(Employee::salary),
                        findWithMaxSalary,
                        (sumOfSalaries, employeeWithmaxSalary) -> (employeeWithmaxSalary.get().salary + 0d) / sumOfSalaries)
                ), 0.0001d);
    }

    record Employee(String name, int salary) {}

    @Test
    public void stringIndent() {
        //GIVEN some string
        var nonIndentedString = "text";
        var expected = String.format(" %s\n", nonIndentedString);

        //EXPECT
        Assertions.assertEquals(expected, nonIndentedString.indent(1));
    }

    @Test
    public void stringTransform() {
        //GIVEN an encrypt function
        Function<String, String> encrypt = s -> s.chars()
                .filter(Character::isAlphabetic)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        //EXPECT
        Assertions.assertEquals("furrycat", encrypt.apply(" furry! cat!   "));
        Assertions.assertEquals("furrycat", "..furry! cat!   ".transform(encrypt));
    }

    @Test
    public void fileMismatch() throws IOException {
        //given 2 temp files with almost identical content
        String content = "hello world!";
        Path tempFile = createTempFileWithContent("tempFile1.txt", content);
        Path tempFile2 = createTempFileWithContent("tempFile2.txt", "hello World and aliens!");

        Assertions.assertEquals(content.indexOf('w'), Files.mismatch(tempFile, tempFile2));
    }

    private Path createTempFileWithContent(String fileName, String content) throws IOException {
        Path tempFile = Files.createTempFile("", fileName);
        return Files.writeString(tempFile, content);
    }
}
