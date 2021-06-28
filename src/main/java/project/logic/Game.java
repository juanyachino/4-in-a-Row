package project.logic;


import java.util.Queue;

public class Game {

    private static Queue<Player> players;
    private static Board board;
    public Game(String player1, String player2){
        board = new Board();
        addPlayers(player1,player2);
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

    /**
     * inserts a piece in the given column chosen by the given player.
     * @param column the given column number
     * @param player the given player
     * @throws Exception if the turn isn't correct or the column is full.
     * @return the position of the new inserted piece.
     */
    public static int insertPiece(int column, Player player) throws Exception {
        //check if the turn is correct
        if(!(player == players.peek())){
            throw new Exception("this isn't your turn!");
        }
        // call board's method insert
        if(!board.canInsertInto(column)){
            throw new Exception("the column is full!");
        }
        Piece thePiece = new Piece(player.getColour());
        return board.insertInto(column,thePiece);
    }
    public static Player getActualPlayer(){
        return players.peek();
    }

    /**
     * getter method for the board
     * @return
     */
    public Board getBoard(){
        return this.board;
    }
}
