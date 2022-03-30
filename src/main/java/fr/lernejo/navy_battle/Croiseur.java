package fr.lernejo.navy_battle;

public class Croiseur implements Ship {
    private final Orientation orientation;
    private final String position;

    public Croiseur(String position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }
    @Override
    public Type getType() {
        return Type.CROISEUR;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public int getLength() {
        return 4;
    }
}
