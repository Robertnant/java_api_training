package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ClientTools {
    private final HttpClient client = HttpClient.newHttpClient();
    public final ArrayList<String> adversaryURL = new ArrayList<>();
    public final Board board = new Board();

    public boolean startGame(String myPort, String adversaryUrl) {
        HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers
                .ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"hello\"}"))
            .build();

        try {
            client.sendAsync(postRequest, HttpResponse.BodyHandlers.ofString());
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sends fire request to adversary. Returns false if game is over.
    public void fire(String adversaryUrl, String cell) {
        String requestUrl = adversaryUrl + "/api/game/fire?cell=" + cell;
        HttpRequest getRequest = HttpRequest.newBuilder().uri(URI.create(requestUrl))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json").GET().build();

        try {
            // Send request and get response from server.
            client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
