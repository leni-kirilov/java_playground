package com.kirilov.interview.solutions;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class TimeConversion {

    public static String timeConversion(String s) {
        DateTimeFormatter formatterAmPm = DateTimeFormatter.ofPattern("hh:mm:ssa");
        TemporalAccessor inputDate = formatterAmPm.parse(s);

        DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String result = formatter24.format(inputDate);

        return result;
    }
}
