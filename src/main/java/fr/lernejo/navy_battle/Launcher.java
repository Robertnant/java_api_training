package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.Handler.PingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No port was given as argument. Please add one");
        }
        final InetSocketAddress socketAddress = new InetSocketAddress(Integer.parseInt(args[0]));
        final int poolSize = 1;

        try {
            // Create HTTP server.
            final HttpServer server = HttpServer.create(socketAddress, 0);

            // Create an executor service.
            final ExecutorService pool = Executors.newFixedThreadPool(poolSize);
            server.createContext("/ping").setHandler(new PingHandler());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
