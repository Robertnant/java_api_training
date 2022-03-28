package fr.lernejo.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class PostHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body;
        int responseCode;
        try (InputStream inputStream = getClass().getResourceAsStream("/schema.json")) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));

            Schema schema = SchemaLoader.load(rawSchema);
            JSONObject obj = new JSONObject(new JSONTokener(exchange.getRequestBody()));
            schema.validate(obj);

            // Send server response if JSON validates schema.
            body = "Accepted";
            responseCode = 202;
        }
        catch (ValidationException e) {
            body = "Bad Request";
            responseCode = 400;
        }

        exchange.sendResponseHeaders(responseCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }
}
