package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

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

            /**
             * Create server context. Since any user is at the same time a server (uses handlers to listen to requests)
             * and a client, a board is directly associated to the server.
             * The server's adversary url will be the one of the other client which is retrieved by PostHandler when
             * handler triggered for first time.
             */
            ClientTools serverClient = new ClientTools();
            ServerTools serverTools = new ServerTools(serverClient.getBoard());
            serverTools.createContext(server);

            // Create an executor service.
            final ExecutorService pool = Executors.newFixedThreadPool(poolSize);
            server.setExecutor(pool);

            // Start server.
            server.start();

            // TODO: should the server launch its own client? YES at it will have its own board.

            // Launch client.
            if (args.length > 1) {
                new ClientTools().startGame(args[0], args[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
