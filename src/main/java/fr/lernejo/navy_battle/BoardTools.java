package fr.lernejo.navy_battle;

import java.util.ArrayList;
import java.util.Map;

public class BoardTools {

    private void setArrangement(Map<Ship, ArrayList<String>> ships) {
        final Ship[] arrangement = { new PorteAvion("A1", Ship.Orientation.HORIZONTAL),
            new Croiseur("G1", Ship.Orientation.HORIZONTAL),
            new ContreTorpilleur("A3", Ship.Orientation.VERTICAL),
            new ContreTorpilleur("C3", Ship.Orientation.VERTICAL),
            new Torpilleur("E9", Ship.Orientation.HORIZONTAL)};
        for (Ship ship : arrangement) {
            ships.put(ship, shipPositions(ship));
        }
    }

    private boolean isShipCoordinate(Map<Ship, ArrayList<String>> ships, String pos) {
        for (var entry : ships.entrySet()) {
            if (entry.getValue().contains(pos)) {
                return true;
            }
        }
        return false;
    }
    public void positionShips(Map<Ship, ArrayList<String>> ships, Cell[][] board) {
        // Set position of ships.
        setArrangement(ships);

        // Initialize board cells.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Coordinates in string form.
                String pos = new StringBuilder().append((char) (j + 'A')).append((char) (i + '0')).toString();
                if (isShipCoordinate(ships, pos))
                    board[i][j] = new Cell(Cell.CellType.SHIP);
                else
                    board[i][j] = new Cell(Cell.CellType.SEA);
            }
        }
    }
    // Gives position of all cells used by given ship.
    public ArrayList<String> shipPositions(Ship ship) {
        ArrayList<String> res = new ArrayList<>();
        final int row = ship.getPosition().charAt(1) % '0';
        final int col = ship.getPosition().charAt(0) % 'A';

        if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            for (int i = 0; i < ship.getLength(); i++) {
                res.add(new StringBuilder().append((char) (col + i + 'A')).append((char) (row + '0')).toString());
            }
        } else {
            for (int i = 0; i < ship.getLength(); i++) {
                res.add(new StringBuilder().append((char) (col + 'A')).append((char) (row + i + '0')).toString());
            }
        }

        return res;
    }
}
