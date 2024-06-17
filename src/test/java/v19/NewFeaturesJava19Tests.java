package v19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.List;

/**
 * VirtualThreads {@link com.kirilov.v19.VirtualThreads} <p\>
 * MethodHandles - since Java 1.7  {@link NewFeaturesJava19Tests#methodHandleInsteadOfReflection()} <p\>
 * Nested Records expressions - {@link NewFeaturesJava19Tests#nestedRecords()}
 */
public class NewFeaturesJava19Tests {

    @Test
    public void methodHandleInsteadOfReflection() throws Throwable {
        MethodHandle duplicate = MethodHandles.lookup()
                .findStatic(
                        MethodHandleDemoClass.class,
                        "duplicate",
                        MethodType.methodType(String.class, String.class)
                );
        Assertions.assertEquals("dogdog", duplicate.invoke("dog"));
    }

    class MethodHandleDemoClass {
        static String duplicate(String s) {
            return s.repeat(2);
        }
    }

    @Test
    public void nestedRecords() {
        HouseHold.Human peter = new HouseHold.Human("Peter");
        HouseHold.Dog spot = new HouseHold.Dog("Spot", peter);
        HouseHold houseHold = new HouseHold(spot, peter);

        //THEN
        Assertions.assertEquals("Peter", houseHold.pets.getFirst().owner.name);
        if (spot instanceof HouseHold.Dog(String name, HouseHold.Human owner)) {
            Assertions.assertEquals("Spot", name);
            Assertions.assertEquals(peter, owner);
        }
    }

    class HouseHold {
        record Human(String name) {
        }

        record Dog(String name, Human owner) {
        }

        List<Human> residents = new ArrayList<>();
        List<Dog> pets = new ArrayList<>();

        HouseHold(Dog dog, Human owner) {
            residents.add(owner);
            pets.add(dog);
        }
    }
}
