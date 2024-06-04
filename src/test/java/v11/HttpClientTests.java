package v11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class HttpClientTests {

    @Test
    public void newHttpClientGet() throws URISyntaxException, IOException, InterruptedException {
        //GIVEN creating a GET HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .version(HttpClient.Version.HTTP_2) //
                .header("X-Leni-made-up", "skip-me")
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        //WHEN executing a GET operation
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        //THEN
        Assertions.assertFalse(response.body().isBlank());
    }

    @Test
    public void newHttpClientPost() throws URISyntaxException, IOException, InterruptedException {
        byte[] sampleData = "Sample request body".getBytes();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofByteArray(sampleData))
                .build();

        //WHEN executing a GET operation
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        //THEN
        Assertions.assertTrue(response.body().contains("\"data\": \"Sample request body\""));
        Assertions.assertFalse(response.body().isBlank());
    }

    @Test
    public void newHttpClientAsync() throws URISyntaxException, IOException, InterruptedException {
        //GIVEN multiple requests
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<URI> targets = Arrays.asList(
                new URI("https://postman-echo.com/get?foo1=bar1"),
                new URI("https://postman-echo.com/get?foo2=bar2"));
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault()) //setting proxy
                .executor(executorService)
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE))
                .build();

        //EXPECT to execute them async and assert their responses
//        targets.stream()
//                .forEach(target -> client
//                        .sendAsync(
//                                HttpRequest.newBuilder(target).GET().build(),
//                                HttpResponse.BodyHandlers.ofString())
//                        .thenAccept(response -> Assertions.assertFalse(response.body().isBlank())));

        List<CompletableFuture<String>> futures = targets.stream()
                .map(target -> client.sendAsync(
                                HttpRequest.newBuilder(target).GET().build(),
                                HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> {
                            Assertions.assertFalse(response.body().isBlank());
                            System.out.println(response.body());
                            return response.body();
                        }))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        Assertions.assertTrue(((CookieManager) client.cookieHandler().get()).getCookieStore().getCookies().isEmpty());
    }

    //TODO authenticator
//    HttpResponse<String> response = HttpClient.newBuilder()
//            .authenticator(new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(
//                            "username",
//                            "password".toCharArray());
//                }
//            }).build()
//            .send(request, BodyHandlers.ofString());

    //TODO Handling Push Promises in HTTP/2 - https://www.baeldung.com/java-9-http-client#handling-push-promises-in-http2

    @Test
    public void redirect() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest wrongGoogleRequest = HttpRequest.newBuilder()
                .uri(new URI("http://gooogle.com/"))
                .GET()
                .build();

        //WHEN calling gooogle without "redirect"
        HttpResponse<String> responseNoRedirect = HttpClient.newBuilder()
                //.followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(wrongGoogleRequest, HttpResponse.BodyHandlers.ofString());

        //THEN expect 301 google.com
        Assertions.assertTrue(responseNoRedirect.body().toString().contains("The document has moved"));
        Assertions.assertEquals(301, responseNoRedirect.statusCode());
        Assertions.assertEquals("https://www.google.com/", responseNoRedirect.headers().map().get("location").getFirst());

        //AND should redirect to google.com
        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(wrongGoogleRequest, HttpResponse.BodyHandlers.ofString());

        //THEN expect to 200 and  google.com
        Assertions.assertEquals("https://www.google.com/", response.uri().toString());
        Assertions.assertEquals(200, response.statusCode());
    }
}