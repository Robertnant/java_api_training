package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.handler.PingHandler;
import fr.lernejo.handler.PostHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    private static void createContext(HttpServer server) {
        server.createContext("/ping").setHandler(new PingHandler());
        server.createContext("/api/game/start").setHandler(new PostHandler());
    }
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No port was given as argument. Please add one");
        }
        final InetSocketAddress socketAddress = new InetSocketAddress(Integer.parseInt(args[0]));
        final int poolSize = 1;

        try {
            // Create HTTP server.
            final HttpServer server = HttpServer.create(socketAddress, 0);

            // Create server context.
            createContext(server);

            // Create an executor service.
            final ExecutorService pool = Executors.newFixedThreadPool(poolSize);
            server.setExecutor(pool);

            // Start server.
            server.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
