package v15;

import com.kirilov.v15.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Sealed Classes - https://openjdk.org/jeps/409
 */
public class NewFeaturesJava15Tests {

    @Test
    public void sealedHierarchy() {
        SealedChild sealedChild = new SealedChild();
        Assertions.assertTrue(sealedChild instanceof SealedParent);
        Assertions.assertTrue(sealedChild.getClass().isSealed());
        Assertions.assertArrayEquals(new Class[]{FinalGrandChild.class}, sealedChild.getClass().getPermittedSubclasses());

        FinalChild finalChild = new FinalChild();
        Assertions.assertTrue(finalChild instanceof SealedParent);
        Assertions.assertFalse(finalChild.getClass().isSealed());

        NonSealedChild nonSealedChild = new NonSealedChild() {
        };
        Assertions.assertTrue(nonSealedChild instanceof SealedParent);
        Assertions.assertFalse(nonSealedChild.getClass().isSealed());
        Assertions.assertTrue(!nonSealedChild.getClass().getName().equals("NonSealedChild"));

    }

    @Test
    public void sealedSameFile() {
        Assertions.assertEquals("Bark", new Speaks.Dog().speak());

        //records can only implement interfaces
        Speaks.Fish fish = new Speaks.Fish(5);
        Assertions.assertEquals(5, fish.age);
        Assertions.assertTrue(fish.getClass().isRecord());

        //not possible. Compilation error
//        new Speaks() {
//            @Override
//            public String speak() {
//                return "";
//            }
//        }
    }

    //defined inside each other. no need for "permits"
    sealed private interface Speaks {
        default String speak() {
            return "";
        }

        final class Dog implements Speaks {
            public String speak() {
                return "Bark";
            }
        }

        sealed abstract class Cat implements Speaks permits Jaguar {
        }

        final class Jaguar extends Cat {
            @Override
            public String speak() {
                return "Meow!!!!";
            }
        }

        //only possible because default method implementation and Speaks is an interface
        record Fish(int age) implements Speaks {
        }
    }
}
