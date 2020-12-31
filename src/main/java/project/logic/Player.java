package project.logic;

public class Player {
    private Piece.Colour colour;
    private String displayName;

    public Player(Piece.Colour colour, String displayName){
        this.colour = colour;
        this.displayName = displayName;
    }
}
