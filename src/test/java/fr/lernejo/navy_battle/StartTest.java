package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

public class StartTest {
    @Test
    void gameStart() {
        try {
            final HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt("8765")), 0);

            ClientTools serverClient = new ClientTools();
            new ServerTools(serverClient).startServer(server);

            serverClient.adversaryURL.add("http://localhost:9876/ping");
            Assertions.assertTrue(serverClient.startGame("8765", "http://localhost:9876/ping"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
