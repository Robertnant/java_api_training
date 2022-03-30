package fr.lernejo.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.Board;
import fr.lernejo.navy_battle.ClientTools;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class FireRequestHandler implements HttpHandler {
    /**
     * Since this handler needs to update the game board, an instance of the
     * game is given to the handler on instantiation.
     */

    private final ClientTools client;

    // Finds indexes of first available cell (move not already made) in adversary board.
    private String pickCell() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (client.board.adversaryMarkBoard[i][j] == 0) {
                    client.board.adversaryMarkBoard[i][j] = 1;
                    return new StringBuilder().append((char) (j + 'A')).append((char) (i + '0')).toString();
                }
            }
        }

        return null;
    }
    public FireRequestHandler(ClientTools client) {
        this.client = client;
    }

    private void sendResponse(JSONObject response, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(202, response.toString().length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.toString().getBytes());
        }

        if (response.getBoolean("shipLeft")) {
            client.fire(client.adversaryURL.get(0), pickCell());
        } else {
            System.out.println("Game is over!");
        }
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> queryPairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        String cell = queryPairs.get("cell");

        if (!client.board.isCellValid(cell)) return;

        JSONObject response = client.board.updateBoard(cell);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(response, exchange);
    }
}
