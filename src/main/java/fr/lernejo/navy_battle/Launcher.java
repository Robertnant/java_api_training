package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("No port was given as argument. Please add one");
        try {
            final HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(args[0])), 0);

            ClientTools serverClient = new ClientTools();
            new ServerTools(serverClient).startServer(server);

            if (args.length > 1) {
                serverClient.adversaryURL.add(args[1]);
                serverClient.startGame(args[0], args[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
