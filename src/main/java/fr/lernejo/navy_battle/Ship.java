package fr.lernejo.navy_battle;

public interface Ship {

    enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    enum Type {
        PORTEAVION,
        CROISEUR,
        CONTRETORPILLEUR,
        TORPILLEUR
    }

    Type getType();
    Orientation getOrientation();
    String getPosition();
    int getLength();
}
