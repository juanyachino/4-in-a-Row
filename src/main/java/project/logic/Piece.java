package project.logic;

public class Piece {
    enum Colour {
        WHITE,BLACK
    }

    private  Colour colour;
    public void Piece(Colour colour) {
        this.colour = colour;
    }
    public Colour getColour() {
        return colour;
    }
}
