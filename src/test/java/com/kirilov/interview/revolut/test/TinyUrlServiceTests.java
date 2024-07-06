package com.kirilov.interview.revolut.test;

import com.kirilov.interview.revolut.TinyUrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TinyUrlServiceTests {
    public static final int EXPECTED_MAX_SIZE_URL = 40;

    public static final String LONG_URL = "https://www.google.com/maps/place/%D0%A1%D0%BE%D1%84%D0%B8%D1%8F+%D1%86%D0%B5%D0%BD%D1%82%D1%8A%D1%80,+%D1%83%D0%BB.+%E2%80%9E%D0%9E%D1%82%D0%B5%D1%86+%D0%9F%D0%B0%D0%B8%D1%81%D0%B8%D0%B9%E2%80%9C+81,+1303+%D0%A1%D0%BE%D1%84%D0%B8%D1%8F/@42.6959474,23.315978,1180a,35y,39.05t/data=!3m1!1e3!4m6!3m5!1s0x40aa855cc04f12b9:0x95c6cbd55278dae4!8m2!3d42.7035441!4d23.3137446!16s%2Fg%2F11kq0cvk16?entry=ttu";
    public static final String SHORT_URL = "https://gemini.google.com/app/e545cbee9eabb007";

    TinyUrlService service;

    @BeforeEach
    public void setUp() {
        service = new TinyUrlService();
    }

    @Test
    public void shorten() {
        //WHEN shortening short string
        String result = service.shorten(SHORT_URL);

        //THEN expect different less that 15 characters long
        Assertions.assertNotEquals(SHORT_URL, result);
        Assertions.assertTrue(result.length() < EXPECTED_MAX_SIZE_URL);

        //WHEN shorting again the same string
        String result2 = service.shorten(result);

        //THEN produced a different string
        Assertions.assertNotEquals(result, result2);
    }

    @Test
    public void shortenLong() {
        //WHEN shortening a long string
        String result = service.shorten(LONG_URL);

        //THEN expect different less that 15 characters long
        Assertions.assertNotEquals(LONG_URL, result);
        Assertions.assertTrue(result.length() < EXPECTED_MAX_SIZE_URL);
    }

    @Test
    public void shortenLongCustom() {
        //WHEN shortening a long string to a custom name
        String customName = "custom_name";
        String result = service.shorten(LONG_URL, customName);

        //THEN expect different less that 15 characters long
        Assertions.assertNotEquals(LONG_URL, result);
        Assertions.assertEquals(customName, result);
        Assertions.assertTrue(result.length() < EXPECTED_MAX_SIZE_URL);
    }

    @Test
    public void shortenCustomDuplicate() {
        //GIVEN a shortening a long string to a custom name
        String customName = "custom_name";
        String result = service.shorten(LONG_URL, customName);

        //WHEN shorting again to the same custom name

        Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> service.shorten(SHORT_URL, customName),
                "Exception was not thrown!"
        );
    }

    @Test
    public void handleShortenedUrl_redirectToOriginalUrl(){
        //GIVEN a long url
        //AND a short url was generated
        String shortenedUrl = service.shorten(LONG_URL);

        //WHEN accessing the short url
        String result = service.expand(shortenedUrl);

        //THEN the long url was returned
        Assertions.assertEquals("Redirect to " + LONG_URL, result);
    }

    @Test
    public void handleShortenedUrl_redirectTo404(){
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> service.expand("not-a-short-url"));
    }


    //TODO qr code?, empty url - input validation
}
