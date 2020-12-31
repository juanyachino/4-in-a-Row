package project.logic;


public class Game {
    private static Board board;
    private static Player player1;
    private static Player player2;
    public Game(){
        board = new Board();
    }
    public static void addPlayers(String displayName1, String displayName2) {
        player1 = new Player(Piece.Colour.BLACK,displayName1);
        player1 = new Player(Piece.Colour.WHITE,displayName2);
    }
}
