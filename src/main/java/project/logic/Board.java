package project.logic;

import java.util.ArrayList;

public class Board {
    /*private static ArrayList<Piece> firstColumn = new ArrayList<>();
    private static ArrayList<Piece> secondColumn = new ArrayList<>();
    private static ArrayList<Piece> thirdColumn = new ArrayList<>();
    private static ArrayList<Piece> fourthColumn = new ArrayList<>();
    private static ArrayList<Piece> fifthColumn = new ArrayList<>();
    private static ArrayList<Piece> sixthColumn = new ArrayList<>();
    private static ArrayList<Piece> seventhColumn = new ArrayList<>();
    */
    private static ArrayList<ArrayList<Piece>> board = new ArrayList<>();
    /**
     * checks if a piece can be inserted on a column
     * @param column the column number
     * @return true or false depending if the column is full or not.
     */
    public static boolean canInsertInto(int column) {
        return getColumn(column).size() <= 7;
    }

    /**
     * Inserts a piece into a column and returns the position where it was inserted.
     * @param column the column number
     * @param piece the piece to be inserted
     * @return the position where the piece was inserted.
     */
    public static int insertInto(int column, Piece piece) {
        getColumn(column).add(piece);
        return getColumn(column).size() - 1;
    }

    /**
     * checks if the last piece insertion made a 4 in a row formation.
     * @param column the column number where the piece was inserted.
     * @param position the position where the piece was inserted.
     * @return true or false depending if there was a 4 in a row formation or not.
     */
    public boolean isAVictory(int column,int position){
        Piece newPiece = getColumn(column).get(position);
        Piece.Colour colour = newPiece.getColour();
        //TODO
        //1. checkHorizontalWin O-O-O-O
        if (checkHorizontalWIn(column,position)) return true;
        //2. checkVerticalWin O
        //                    O
        //                    O
        //                    O
        if (checkVerticalWin(column,position)) return true;
        //3. checkDiagonalWin  4 diag
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
        ArrayList<Piece> row = new ArrayList<>();
        for (int i = 0; i < board.size(); i++){
            if (getColumn(i).size() > position) {
                row.add(getColumn(i).get(position));
            } else {
                row.add(new Piece(Piece.Colour.EMPTY));
            }
        }
        // column.size() > position

        return false;
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
            if (thisColumn.get(i).getColour() == colour) piecesInARow++;
            if (piecesInARow == 4) return true;
        }
        return false;
    }
    /**
     * checks if the given piece in the given column is part of a horizontal 4 in a row formation
     * @param column the column number
     * @param position the given position
     * @return true or false depending if there is a diagonal 4 in a row formation or not
     */
    private boolean checkDiagonalWIn(int column,int position){
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
}
