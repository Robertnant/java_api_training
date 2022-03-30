package fr.lernejo.navy_battle;

public class PorteAvion implements Ship {
    private final Orientation orientation;
    private final String position;

    public PorteAvion(String position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }
    @Override
    public Type getType() {
        return Type.PORTEAVION;
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
        return 5;
    }
}
