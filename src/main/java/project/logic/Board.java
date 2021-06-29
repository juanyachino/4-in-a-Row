package project.logic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Board {

    private static final ArrayList<ArrayList<Piece>> board = new ArrayList<>();
    private static final int MAX_COLUMNS = 6;
    private static final int MAX_PIECES_PER_COLUMN = 7;

    /**
     * class constructor, initializes the board by creating the column's arrays
     */
    public Board() {
        for (int i = 1; i <= MAX_COLUMNS; i++) {
            board.add(new ArrayList<Piece>());
        }
    }
    /**
     * checks if a piece can be inserted on a column
     * @param column the column number
     * @return true or false depending if the column is full or not.
     */
    public static boolean canInsertInto(int column) {
        return getColumn(column).size() <= MAX_PIECES_PER_COLUMN;
    }

    /**
     * Inserts a piece into a column and returns the position where it was inserted.
     * @param column the column number
     * @param piece the piece to be inserted
     * @return the position where the piece was inserted.
     */
    public static int insertInto(int column, Piece piece) {
        ArrayList<Piece> theColumn = getColumn(column);
        theColumn.add(piece);
        setColumn(column,theColumn);
        return getColumn(column).size() - 1;
    }

    /**
     * checks if the last piece insertion made a 4 in a row formation.
     * @param column the column number where the piece was inserted.
     * @param position the position where the piece was inserted.
     * @return true or false depending if there was a 4 in a row formation or not.
     */
    public boolean isAVictory(int column,int position){
        if (checkHorizontalWIn(column,position)) return true;
        if (checkVerticalWin(column,position)) return true;
        //TODO
        if (checkDiagonalWIn(column,position)) return true;
        return false;
    }

    /**
     * checks if the given piece in the given column is part of a horizontal 4 in a row formation
     * @param column the column number
     * @param position the given position
     * @return true or false depending if there is a horizontal 4 in a row formation or not
     */
    private boolean checkHorizontalWIn(int column,int position){
        ArrayList<Piece> thisRow = new ArrayList<>();
        for (int i = 0; i < board.size(); i++){
            if (getColumn(i).size() > position) {
                thisRow.add(getColumn(i).get(position));
            } else {
                thisRow.add(new Piece(Piece.Colour.EMPTY));
            }
        }
        Piece.Colour pieceColour = thisRow.get(column).getColour();
        return checkRow(thisRow,pieceColour);
    }
    /**
     * checks if the given piece in the given column is part of a vertical 4 in a row formation
     * @param column the column number
     * @param position the given position
     * @return true or false depending if there is a vertical 4 in a row formation or not
     */
    private boolean checkVerticalWin(int column,int position){
        if (position < 3) return false;
        ArrayList<Piece> thisColumn = getColumn(column);
        int piecesInARow = 1;
        Piece.Colour colour = thisColumn.get(position).getColour();
        for (int i = position - 1; i>= 0; i--) {
            if (thisColumn.get(i).getColour() == colour){
                piecesInARow++;
            } else {
                piecesInARow = 0;
            }
            if (piecesInARow == 4) return true;
        }
        return false;
    }
    /**
     * checks if the given piece in the given column is part of a diagonal 4 in a row formation
     * @param column the column number
     * @param position the given position
     * @return true or false depending if there is a diagonal 4 in a row formation or not
     */
    private boolean checkDiagonalWIn(int column,int position){
        //TODO
        ArrayList<Piece> diagonalRow = new ArrayList<>();
        int horizontalIndex  = 0;
        int verticalIndex = column;
        for (; verticalIndex < board.size() && verticalIndex >= 0 && horizontalIndex <= position; ){
            if (getColumn(verticalIndex).size() > position - horizontalIndex) {
                diagonalRow.add(getColumn(verticalIndex).get(position));
            } else {
                diagonalRow.add(new Piece(Piece.Colour.EMPTY));
            }
            verticalIndex--;
            horizontalIndex++;
        }
        Piece.Colour pieceColour = getColumn(column).get(position).getColour();
        return checkRow(diagonalRow,pieceColour);
    }

    /**
     * checks if the given row has 4 consecutive pieces of the same colour.
     * @param thisRow the given row
     * @param pieceColour the given piece colour
     * @return true or false depending if there is a row of consecutive pieces of the same colour or not.
     */
    private boolean checkRow(ArrayList<Piece> thisRow, Piece.Colour pieceColour) {
        int piecesInARow= 0;
        for (Piece p : thisRow) {
            if (p.getColour() == pieceColour){
                piecesInARow++;
            } else {
                piecesInARow = 0;
            }
            if (piecesInARow == 4) return true;
        }
        return false;
    }
    /**
     * given a column number, returns the column.
     * @param column the given column number
     * @return the column
     */
    private static ArrayList<Piece> getColumn(int column){
        return board.get(column);
    }
    private static void setColumn(int columnNumber,ArrayList<Piece> column) {
        board.set(columnNumber,column);
    }
    /**
     * creates a JSON object of the board information
     */
    public JSONObject createBoardJSON () {
        JSONObject boardData = new JSONObject();
        for (int i = 1; i <= MAX_COLUMNS; i++) {
            JSONArray columnData = new JSONArray();
            ArrayList<Piece> column =getColumn(i);
            for (Piece p : column) {
                columnData.put(p.getColour().toString());
            }
            boardData.put("column " + i, columnData);
        }
        return boardData;
    }
}
