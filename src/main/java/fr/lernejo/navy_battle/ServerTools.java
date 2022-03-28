package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.handler.FireRequestHandler;
import fr.lernejo.handler.PingHandler;
import fr.lernejo.handler.PostHandler;

public class ServerTools {
    // Create a new Board class (with game board and functions needed to update board) and set it
    // as game board for FireRequestHandler.
    private final Board board;

    public ServerTools(Board board) {
        this.board = board;
    }

    public void createContext(HttpServer server) {
        server.createContext("/ping").setHandler(new PingHandler());
        server.createContext("/api/game/start").setHandler(new PostHandler());
        server.createContext("/api/game/fire").setHandler(new FireRequestHandler(board));
    }
}
