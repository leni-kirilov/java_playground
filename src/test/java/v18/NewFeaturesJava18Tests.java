package v18;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;

/**
 * Simple web server :
 * <p>
 * Javadoc snippets:
 * <p>
 * A simple program.
 * {@snippet :
 * class HelloWorld {
 *     public static void main(String... args) {            // @highlight region regex = "\bargs\b"
 *         System.out.println("Hello World!" + args);      // @highlight substring="println"
 *     }
 * }//@end
 *}
 */

public class NewFeaturesJava18Tests {

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

        // Wait for the server to start
        while (server.getAddress().isUnresolved()) {
            try {
                Thread.sleep(25); // Poll every 100ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

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
    public void cleanup() {
        server.stop(1);
    }

}
