package v16;

import jdk.incubator.vector.IntVector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Streams - can be converted toList() instead of using a Collector
 * Records - as inner class members
 * Part of Day - parsing verbose
 * (incubator) Vector API multiplication
 */
public class NewFeatures {

    @Test
    public void streamToList() {
        //GIVEN list of digits
        List<Integer> digits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        //WHEN stream converted to list
        List<Integer> result = IntStream.range(0, 10).mapToObj(Integer::valueOf).toList();

        //THEN each digit is there
        for (Integer i : digits) {
            Assertions.assertTrue(result.contains(i));
        }
    }

    @Test
    public void prettyDayPeriod() {
        //morning
        LocalTime date = LocalTime.parse("10:00:01");
        DateTimeFormatter shortFormatter = DateTimeFormatter.ofPattern("h B");
        Assertions.assertEquals("10 in the morning", date.format(shortFormatter));

        //afternoon
        LocalTime dateAfternoon = LocalTime.parse("18:00:01");
        DateTimeFormatter full = DateTimeFormatter.ofPattern("h BBBB");
        Assertions.assertEquals("6 in the evening", dateAfternoon.format(full));

        //evening
        LocalTime dateEvening = LocalTime.parse("23:00:01");
        DateTimeFormatter narrow = DateTimeFormatter.ofPattern("k BBBBB"); // 0-23 hours
        Assertions.assertEquals("23 at night", dateEvening.format(narrow));
    }

    @Test
    public void vectorMultiplication() {
        //GIVEN 2 arrays being multiplied
        int[] a = IntStream.range(0, 16).toArray();
        int[] b = IntStream.range(16, 32).toArray();

        var c = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i];
        }

        //AND we build vectors of enough size without looping
        IntVector intVectorA = IntVector.fromArray(IntVector.SPECIES_512, a, 0);
        IntVector intVectorB = IntVector.fromArray(IntVector.SPECIES_512, b, 0);

        //WHEN
        IntVector intVectorResult = intVectorA.mul(intVectorB);

        //THEN two arrays are equal
        Assertions.assertArrayEquals(c, intVectorResult.toArray());
    }

    @Test
    public void recordInnerClass() {
        School highSchool = new School();
        highSchool.title = "Highschool Sofia";

        highSchool.createClassOfStudents("Class A");
        School.ClassOfStudents classA = highSchool.classOfStudents.getFirst();
        classA.students.add(new School.Student("Peter"));
        classA.students.add(new School.Student("George"));

        Assertions.assertEquals(2, highSchool.classOfStudents.getFirst().students.size());
    }

    static final class School {
        String title;
        List<ClassOfStudents> classOfStudents;

        public void createClassOfStudents(String nameOfClass) {
            classOfStudents = new ArrayList<>();
            ClassOfStudents newClass = new ClassOfStudents();
            newClass.name = nameOfClass;
            classOfStudents.add(newClass);
        }

        class ClassOfStudents {
            String name;
            public List<Student> students = new ArrayList<>();
        }

        record Student(String name) {
        }
    }
}
