package project.logic;

import java.util.ArrayList;

public class Board {
    private static ArrayList<Piece> firstColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> secondColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> thirdColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> fourthColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> fifthColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> sixthColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> seventhColumn = new ArrayList<Piece>();

    /**
     * checks if a piece can be inserted on a column
     * @param columnName the name of the column
     * @return true or false depending if the column is full or not.
     */
    public static boolean canInsertInto(String columnName) {
        return getColumn(columnName).size() <= 7;
    }

    /**
     * Inserts a piece into a column and returns the position where it was inserted.
     * @param columnName the name of the column
     * @param piece the piece to be inserted
     * @return the position where the piece was inserted.
     */
    public static int insertInto(String columnName, Piece piece) {
        getColumn(columnName).add(piece);
        return getColumn(columnName).size() - 1;
    }

    /**
     * checks if the last piece insertion made a 4 in a row formation.
     * @param columnName the column name where the piece was inserted.
     * @param position the position where the piece was inserted.
     * @return true or false depending if there was a 4 in a row formation or not.
     */
    public boolean isAVictory(String columnName,int position){
        Piece newPiece = getColumn(columnName).get(position);
        Piece.Colour colour = newPiece.getColour();
        //TODO
        //1. checkHorizontalWin O-O-O-O
        //2. checkVerticalWin O
        //                    O
        //                    O
        //                    O
        //3. checkDiagonalWin  4 diag
        return false;
    }

    /**
     * checks if the given piece in the given column is part of a horizontal 4 in a row formation
     * @param columnName the name of the given column
     * @param position the given position
     * @return true or false depending if there is a horizontal 4 in a row formation or not
     */
    private boolean checkHorizontalWIn(String columnName,int position){
        return false;
    }
    /**
     * checks if the given piece in the given column is part of a vertical 4 in a row formation
     * @param columnName the name of the given column
     * @param position the given position
     * @return true or false depending if there is a vertical 4 in a row formation or not
     */
    private boolean checkVerticalWin(String columnName,int position){
        if (position < 3) return false;
        ArrayList<Piece> column = getColumn(columnName);
        int piecesInARow = 1;
        Piece.Colour colour = column.get(position).getColour();
        for (int i = position - 1; i>= 0; i--) {
            if (column.get(i).getColour() == colour) piecesInARow++;
            if (piecesInARow == 4) return true;
        }
        return false;
    }
    /**
     * checks if the given piece in the given column is part of a horizontal 4 in a row formation
     * @param columnName the name of the given column
     * @param position the given position
     * @return true or false depending if there is a diagonal 4 in a row formation or not
     */
    private boolean checkDiagonalWIn(String columnName,int position){
        return false;
    }

    /**
     * given a column name, returns the column.
     * @param columnName the given column name
     * @return the column
     */
    private static ArrayList<Piece> getColumn(String columnName){
        switch(columnName){
            case "firstColumn ":
                return firstColumn;
            case "secondColumn":
                return secondColumn;
            case "thirdColumn ":
                return thirdColumn;
            case "fourthColumn":
                return fourthColumn;
            case "fifthColumn ":
                return fifthColumn;
            case "sixthColumn ":
                return sixthColumn;
            case "seventhColumn ":
                return seventhColumn;
        }
        throw new IllegalArgumentException("Column name is invalid");
    }
}
