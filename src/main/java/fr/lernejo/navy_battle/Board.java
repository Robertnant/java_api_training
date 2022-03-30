package fr.lernejo.navy_battle;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final BoardTools boardTools = new BoardTools();

    // Map containing different ships on board as key and their positions as value.
    private final Map<Ship, ArrayList<String>> ships = new HashMap<>();
    private final Cell[][] board;

    // Is used to mark moves made by player. Value 1 means played else 0 means available.
    public final int[][] adversaryMarkBoard;

    public Board() {
        board = new Cell[10][10];
        boardTools.positionShips(ships, board);

        adversaryMarkBoard = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                adversaryMarkBoard[i][j] = 0;
            }
        }
    }

    public Cell getCell(String cell) {
        final int row = cell.charAt(1) % '0';
        final int col = cell.charAt(0) % 'A';

        return board[row][col];
    }

    public boolean isCellValid(String cell) {
        if (cell.length() == 2) {
            boolean valid = cell.charAt(0) >= 'A' && cell.charAt(0) <= 'J';
            valid &= cell.charAt(1) >= '0' && cell.charAt(1) <= '9';

            return valid;
        }

        return false;
    }

    private void createResponse(Map.Entry<Ship, ArrayList<String>> entry, JSONObject response) {
        if (entry.getValue().isEmpty())
            response.put("consequence", "sunk");
        else
            response.put("consequence", "hit");
        if (ships.entrySet().stream().filter(ship -> ship.getValue().isEmpty()).collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).size() == 5)
            response.put("shipLeft", false);
        else
            response.put("shipLeft", true);
    }
    // Updates board using (valid) cell position given as argument.
    public JSONObject updateBoard(String cell) {
        final JSONObject response = new JSONObject();
        // Check if cell contains boat.
        if (getCell(cell).getType() == Cell.CellType.SHIP) {
            for (var entry : ships.entrySet()) {
                if (entry.getValue().contains(cell)) {
                    entry.getValue().remove(cell);
                    createResponse(entry, response);
                    return response;
                }
            }
        }

        return response.put("consequence", "miss").put("shipLeft", true);
    }
}
