package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientTools {
    private final HttpClient client = HttpClient.newHttpClient();
    public final Board board = new Board();

    public Board getBoard() {
        return board;
    }

    public void startGame(String myPort, String adversaryUrl) {
        HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers
                .ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"hello\"}"))
            .build();

        try {
            client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    // Sends fire request to adversary.
    public void fire(String adversaryUrl, String cell) {
        String requestUrl = adversaryUrl + "/api/game/fire?cell=" + cell;
        HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(requestUrl))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json").GET().build();

        try {
            // Send request and get response from server.
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            // End game depending on response.
            // TODO

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
