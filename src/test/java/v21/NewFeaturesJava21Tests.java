package v21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * String Literals (PREVIEW) - thus only pseudo-code {@link #stringTemplate()}
 */
public class NewFeaturesJava21Tests {

    @Test
    @Disabled
    public void stringTemplate() {
        //STR is the default secure string template processor
//        STR."Welcome to \{name}";

        //FMT - to display a formatted table
//        String table = FMT."""
//			    Description     Width    Height     Area
//			    %-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
//			    %-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
//			    %-12s\{zone[2].name}  %7.2f\{zone[2].width}  %7.2f\{zone[2].height}     %7.2f\{zone[2].area()}
//			    \{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area() + zone[2].area()}
//			    """;
//			| """
//			| Description     Width    Height     Area
//			| Alfa            17.80    31.40      558.92
//			| Bravo            9.60    12.40      119.04
//			| Charlie          7.10    11.23       79.73
//			|                              Total  757.69
//| """

        //RAW processor - will return a template object with fragmets() and values() that interweave
        //StringTemplate st = RAW."\{x} plus \{y} equals \{x + y}";
        //			List<Object> values = st.values();
        //st.fragments()
        //STR.process(st)) //needs a processor to finish

        //CUSTOM processors:

        /// Intern string:
        //	 var INTERN = StringTemplate.Processor.of(st -> st.interpolate().intern());


        ///JSON processor that doesn't produce a String:
        //			var JSON = StringTemplate.Processor.of(
        //			        (StringTemplate st) -> new JSONObject(st.interpolate())
        //    );
        //
        //JSONObject doc = JSON."""
        //    {
        //        "name":    "\{name}",
        //        "phone":   "\{phone}",
        //        "address": "\{address}"
        //    };
        //    """;
    }

    @Test
    public void sequencedCollections() {
        //GIVEN a list and a set
        var sequencedList = new ArrayList<String>(5);
        sequencedList.add("first");

        var sequencedSet = new LinkedHashSet<String>(10);
        sequencedSet.add("second");
        sequencedSet.add("third");

        //EXPECT processing the 2 collections in sequence is possible
        Assertions.assertEquals("second", findBiggerFirstElement(List.of(sequencedList, sequencedSet)));
    }

    private String findBiggerFirstElement(List<SequencedCollection<String>> sequencedCollections) {
        return sequencedCollections.stream()
                .map(SequencedCollection::getFirst)
                .min((o1, o2) -> o1.length() > o2.length() ? -1 : 1)
                .get();
    }
}
