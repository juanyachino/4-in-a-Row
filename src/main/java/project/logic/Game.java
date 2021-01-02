package project.logic;


import java.util.Queue;

public class Game {

    private static Queue<Player> players;
    private static Board board;
    public Game(){
        board = new Board();
    }
    public static void addPlayers(String displayName1, String displayName2) {
        players.add(new Player(Piece.Colour.BLACK,displayName1));
        players.add(new Player(Piece.Colour.WHITE,displayName2));
    }

    /**
     * changes the current player's turn.
     */
    public static void changeTurn(){
        Player nextPlayer = players.poll();
        players.add(nextPlayer);
    }


}
