package v10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import v8.OptionalTests;

import java.util.List;
import java.util.Random;

/**
 * https://www.baeldung.com/java-10-overview
 * <p>
 * - Local Variable type inference - https://openjdk.org/projects/amber/guides/lvti-style-guide
 * <p>
 * - Optional.orElseThrow -> {@link OptionalTests#testOptionalIsNull()}
 * <p>
 * - Container Awareness:
 * ---XX:-UseContainerSupport
 * ---XX:ActiveProcessorCount=count
 * ---XX:InitialRAMPercentage
 * ---XX:MaxRAMPercentage
 * ---XX:MinRAMPercentage
 * <p>
 * - Various performance improvements such as GC1 in parallel and AppClassDataSharing
 */
public class NewFeatures {

    @Test
    public void varString() {
        var shouldBeString = "bla";
        Assertions.assertInstanceOf(String.class, shouldBeString);
    }

    @Test
    public void varCollection() {
        var shouldBeCollection = List.of("5", 10, new Object());
        Assertions.assertInstanceOf(List.class, shouldBeCollection);
    }

    @Test
    public void varChangingTypes_bad_practice() {
        var canBeManyTypes = new Random().nextInt(100) > 50 ? "" : true;
        canBeManyTypes = "";
        //expect exception ?
        Assertions.assertInstanceOf(String.class, canBeManyTypes);
    }

    @Test
    public void varNotKeyword() {
        var var = 10; //this is legal!
        Assertions.assertInstanceOf(Integer.class, var);
    }

    //compilationErrors(){
//    public var = "hello";
//    public void compilationErrors(){
//        var a;
//        var b = null;
//        var p = (String s) -> s.length() > 10;
//    }
}