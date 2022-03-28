package fr.lernejo.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.Board;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class FireRequestHandler implements HttpHandler {
    /**
     * Since this handler needs to update the game board, an instance of the
     * game is given to the handler on instantiation.
     */
    private final Board board;

    public FireRequestHandler(Board board) {
        this.board = board;
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

        // Check if cell is valid.
        // TODO
        if (!board.isCellValid(cell)) {
            return;
        }

        // Update board.
        // The updateBoard function creates the response String to be sent to the client.
        String body = board.updateBoard(cell);

        exchange.sendResponseHeaders(200, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }
}
