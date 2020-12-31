package project.logic;

import java.util.ArrayList;

public class Board {
    private static ArrayList<Piece> firstColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> secondColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> thirdColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> fourthColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> fifthColumn = new ArrayList<Piece>();
    private static ArrayList<Piece> sixthColumn = new ArrayList<Piece>();

    public static boolean canInsertInto(String columnName) {
        switch(columnName){
            case "firstColumn ":
                return firstColumn.size() <= 7;
            case "secondColumn":
                return secondColumn.size() <= 7;
            case "thirdColumn ":
                return thirdColumn.size() <= 7;
            case "fourthColumn":
                return fourthColumn.size() <= 7;
            case "fifthColumn ":
                return fifthColumn.size() <= 7;
            case "sixthColumn ":
                return sixthColumn.size() <= 7;
        }
        return false;
    }
    public static int insertInto(String columnName, Piece piece) {
        switch(columnName){
            case "firstColumn":
                firstColumn.add(piece);
                return firstColumn.size() - 1;
            case "secondColumn":
                secondColumn.add(piece);
                return secondColumn.size() - 1;
            case "thirdColumn ":
                thirdColumn.add(piece);
                return thirdColumn.size() - 1;
            case "fourthColumn":
                fourthColumn.add(piece);
                return fourthColumn.size() - 1;
            case "fifthColumn":
                fifthColumn.add(piece);
                return fifthColumn.size() - 1;
            case "sixthColumn":
                sixthColumn.add(piece);
                return sixthColumn.size() - 1;
        }
        throw new IllegalArgumentException("Column name is invalid");
    }
    public boolean isAVictory(String columnName, Piece piece,int position){
        //TODO
        //1. check O-O-O-O left and right
        //2. check O
        //         O
        //         O
        //         O
        //3. check  4 diag
        return false;
    }
}
