package project.logic;


import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Queue;

public class Game {

    private static Queue<Player> players;
    private static Board board;
    private static Player player1;
    private static Player player2;
    private static Player winner;
    private static boolean victory = false;
    public Game(String player1, String player2){
        board = new Board();
        players = new LinkedList<>();
        addPlayers(player1,player2);
    }

    /**
     * add the players to the game
     * @param displayName1 the player one 's display name
     * @param displayName2 the player tow 's display name.
     */
    public static void addPlayers(String displayName1, String displayName2) {
        player1 =new Player(Piece.Colour.BLACK,displayName1);
        player2 =new Player(Piece.Colour.WHITE,displayName2);
        players.add(player1);
        players.add(player2);
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
        int position = board.insertInto(column,thePiece);
        if (board.isAVictory(column,position)){
            victory = true;
            winner = player;
        }
        changeTurn();
        return position;
    }

    /**
     * get the actual player , aka which player's turn to play
     * @return the actual player
     */
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
    /**
     * creates a JSON object with all the information of a game state
     */
    public JSONObject createGameJSON(){
        JSONObject gameObject = new JSONObject();
        // players info
        JSONObject playerData = new JSONObject();
        playerData.put("actual_player",getActualPlayer().getDisplayName());
        playerData.put("player1",player1.getDisplayName());
        playerData.put("player2",player2.getDisplayName());
        gameObject.put("player_info", playerData);
        if (victory) gameObject.put("winner!",winner);
        // board info
        gameObject.put("board_info", this.board.createBoardJSON());
        return gameObject;
    }
}
