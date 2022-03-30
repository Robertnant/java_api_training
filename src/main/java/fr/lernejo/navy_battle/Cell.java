package fr.lernejo.navy_battle;

public class Cell {

    // Different elements that can have a cell.
    enum CellType {
        SEA,
        SHIP
    }

    private final CellType type;

    public CellType getType() {
        return type;
    }

    // Initialize cell by giving it its type.
    public Cell(CellType type) {
        this.type = type;
    }
}
