package project.logic;

public class Piece {
    private  Colour colour;

    public Piece(Colour colour) {
        this.colour = colour;
    }


    enum Colour {
        WHITE,BLACK,EMPTY
    }


    public Colour getColour() {
        return colour;
    }
}
