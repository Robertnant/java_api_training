package fr.lernejo.navy_battle;

public class Torpilleur implements Ship {
    private final Orientation orientation;
    private final String position;

    public Torpilleur(String position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }
    @Override
    public Type getType() {
        return Type.TORPILLEUR;
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
        return 2;
    }
}
