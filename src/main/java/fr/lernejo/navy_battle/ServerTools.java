package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.handler.FireRequestHandler;
import fr.lernejo.handler.PingHandler;
import fr.lernejo.handler.PostHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTools {
    private final ClientTools client;

    public ServerTools(ClientTools client) {
        this.client = client;
    }

    public void startServer(HttpServer server) {
        server.createContext("/ping", new PingHandler());
        server.createContext("/api/game/start", new PostHandler(client));
        server.createContext("/api/game/fire", new FireRequestHandler(client));

        final ExecutorService pool = Executors.newFixedThreadPool(1);
        server.setExecutor(pool);

        server.start();
    }
}
