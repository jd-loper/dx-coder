import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private final String apiKey;
    private final HttpClient httpClient;
    private final String baseUrl;

    public Client() {
        this.apiKey = System.getenv("API_KEY");
        this.baseUrl = System.getenv("BASE_URL");
        this.httpClient = HttpClient.newHttpClient();
    }

    public String sendRequest(String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "?key=" + apiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}