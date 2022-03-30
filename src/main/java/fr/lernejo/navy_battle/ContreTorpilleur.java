package fr.lernejo.navy_battle;

public class ContreTorpilleur implements Ship {
    private final Orientation orientation;
    private final String position;

    public ContreTorpilleur(String position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }
    @Override
    public Type getType() {
        return Type.CONTRETORPILLEUR;
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
        return 3;
    }
}
