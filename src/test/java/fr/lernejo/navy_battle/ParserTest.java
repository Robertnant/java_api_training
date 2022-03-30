package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

public class ParserTest {
    @Test
    void parser() {
        String body = "{\"id\":\"1\", \"url\":\"http://localhost/:" + 9876 + "\", \"message\":\"hello\"}";

        JSONObject rawSchema = new JSONObject(new JSONTokener(getClass().getResourceAsStream("/schema.json")));

        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject request = new JSONObject(body);
        Assertions.assertDoesNotThrow(() -> schema.validate(request));
    }
}
