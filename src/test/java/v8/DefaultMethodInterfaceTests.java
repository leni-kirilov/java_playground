package v8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static v8.WithDefaultMethod.DEFAULT_IMPLEMENTATION;


public class DefaultMethodInterfaceTests {

    @Test
    public void testDefaultMethodInterface() {
        //GIVEN interface with default method

        //WHEN class doesn't overwrite it
        WithDefaultMethod notOvewriting = new NotOverwriting();

        //THEN expect the default method implementation
        Assertions.assertEquals(DEFAULT_IMPLEMENTATION, notOvewriting.getType());
    }

    @Test
    public void testOverwritingDefaultMethodInterface() {
        OverwritingInterface instance = new OverwritingInterface();
        String result = instance.getType();
        Assertions.assertEquals("I'm ovewriting this!", result);
    }

}

interface WithDefaultMethod {

    String DEFAULT_IMPLEMENTATION = "no type defined";

    default String getType(){
        return DEFAULT_IMPLEMENTATION;
    }
}

class NotOverwriting implements WithDefaultMethod {}
class OverwritingInterface implements WithDefaultMethod {
    @Override
    public String getType() {
        return "I'm ovewriting this!";
    }
}
