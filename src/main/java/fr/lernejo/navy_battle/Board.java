package fr.lernejo.navy_battle;

public class Board {

    private final Cell[][] board;

    public Board() {
        board = new Cell[10][10];
    }

    private Cell getCell(String cell) {
        int row = cell.charAt(1) % '0';
        int col = cell.charAt(0) % 'A';

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

    // Updates board using (valid) cell position given as argument.
    public String updateBoard(String cell) {
        String response = null;
        // Check if cell contains boat.
        if (getCell(cell).getType() == Cell.CellType.SHIP) {
            // Find full ship associated to cell, mark the given cell position of ship as hit
            // and update status depending on if all cells of ship were hit.
        }

        return response;
    }
}
