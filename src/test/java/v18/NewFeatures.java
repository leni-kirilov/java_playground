package v18;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Simple web server
 */
public class NewFeatures {

    public static final int TEST_PORT = 18088;
    HttpServer server;

    @Test
    public void createSimpleWebServer() throws URISyntaxException, IOException, InterruptedException {
        server = SimpleFileServer.createFileServer(
                new InetSocketAddress(TEST_PORT),
                Path.of(".").toAbsolutePath(),
                SimpleFileServer.OutputLevel.VERBOSE
        );
        server.start();
        Thread.sleep(1000); //wait until it boots up

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:" + TEST_PORT)) // Changed to http
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(2))
                .GET()
                .build();

        // Execute a GET operation
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // THEN it shows the contents of the current folder with source code
        Assertions.assertTrue(response.body().contains("README.md"));
    }

    @AfterEach
    public void cleanup(){
        server.stop(1000);
    }

}
