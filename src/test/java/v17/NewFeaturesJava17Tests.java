package v17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Switch pattern extended - https://openjdk.org/jeps/441 as of Java 21 because switches changed a few times
 */
public class NewFeaturesJava17Tests {

    @Test
    public void patternMatching_constant() {
        Assertions.assertEquals("just a small text", switchConstantLegacy("small"));
        Assertions.assertEquals("this is a number", switchType(5));
        Assertions.assertEquals("very big number", switchExpression(1000));
        Assertions.assertEquals("NULL is supported now!", switchExpression(null));
    }

    private String switchConstantLegacy(String text) {
        return switch (text) {
            case "big number":
                yield "big number!!";
            case "small":
                yield "just a small text";
            default:
                yield "";
        };
    }

    private String switchType(Object obj) {
        return switch (obj) {
            case Integer intObj -> "this is a number";
            case String text -> "this is a text";
            default -> "";
        };
    }

    private String switchExpression(Object obj) {

        //Equivalent to:
        //multiple cases
//        case Integer intObj: {
//            if (intObj > 100) {
//                yield "very big number";
//            } else if (intObj > 10) {
//                yield "big number";
//            }
//        }

        return switch (obj) {
            case Integer intObj
                    when (10 < intObj && intObj < 100) -> "big number";
            case Integer intObj
                    when (intObj > 100) -> "very big number";
            case String text
                    when (text.length() > 10) -> "long text";
            case null -> "NULL is supported now!";
            default -> "";
        };
    }


    sealed interface I<T> permits A, B { }

    final class A<X> implements I<String> {}

    final class B<Y> implements I<Y> {}

    @Test
    public void switchGenericSwitch() {
        Assertions.assertEquals(42, genericSealedExhaustive(new B<Integer>()));
    }

    static int genericSealedExhaustive(I<Integer> i) {
        return switch (i) {
            // Exhaustive as no A case possible!
            case B<Integer> bi -> 42;
        };
    }
}
