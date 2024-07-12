package com.kirilov.interview.test.solutions;

import com.kirilov.interview.solutions.TimeConversion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeConversionsTests {

    @Test
    public void pm() {
        Assertions.assertEquals("19:05:45", TimeConversion.timeConversion("07:05:45PM"));
    }

    @Test
    public void am() {
        Assertions.assertEquals("07:05:45", TimeConversion.timeConversion("07:05:45AM"));
    }

    @Test
    public void noon() {
        Assertions.assertEquals("12:00:00", TimeConversion.timeConversion("12:00:00PM"));
    }

    @Test
    public void midnight() {
        Assertions.assertEquals("00:00:00", TimeConversion.timeConversion("12:00:00AM"));
    }
}
