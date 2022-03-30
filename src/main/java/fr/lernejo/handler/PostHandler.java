package fr.lernejo.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.ClientTools;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;

public class PostHandler implements HttpHandler {
    private final ClientTools serverClient;

    public PostHandler(ClientTools serverClient) {
        this.serverClient = serverClient;
    }

    private void sendResponse(HttpExchange exchange, String body, int responseCode) throws IOException {
        exchange.sendResponseHeaders(responseCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }

        if (responseCode == 202)
            serverClient.fire(serverClient.adversaryURL.get(0), "A1");
    }

    private JSONObject validateSchema(InputStream inputStream, HttpExchange exchange) throws ValidationException {
        JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));

        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject request = new JSONObject(new JSONTokener(exchange.getRequestBody()));
        schema.validate(request);
        return request;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body;
        int responseCode;
        try (InputStream inputStream = getClass().getResourceAsStream("/schema.json")) {
            JSONObject request = validateSchema(inputStream, exchange);
            body = "{\"id\":\"1\", \"url\":\"http://localhost/:" + exchange.getLocalAddress().getPort() +
                "\", \"message\":\"hello\"}";
            responseCode = 202;
            serverClient.adversaryURL.add(request.getString("url"));
        }
        catch (ValidationException e) {
            body = "Bad Request";
            responseCode = 400;
        }
        sendResponse(exchange, body, responseCode);
    }
}
