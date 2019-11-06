package Logic;

import Board.Tile;
import Pieces.Piece;

import java.awt.*;

public class Highlighting {

    public Highlighting(){

    }

    public Tile[][] finder(Tile[][] board, Piece piece){

        switch(piece.getType()){
            case "PAWN":
                if(piece.getSide()== Color.WHITE){
                    return wPawnRules(board, piece);
                }
                else{
                    return bPawnRules(board, piece);
                }

        }


        return board;
    }

    public Tile[][] wPawnRules(Tile[][] board, Piece piece){

        board[piece.getRow()-1][piece.getCol()].setBackground(Color.YELLOW);
        board[piece.getRow()-1][piece.getCol()].setSelected(true, piece);

        if(6==piece.getRow()){

            board[piece.getRow()-2][piece.getCol()].setBackground(Color.YELLOW);
            board[piece.getRow()-2][piece.getCol()].setSelected(true, piece);

        }
        return board;
    }
    public Tile[][] bPawnRules(Tile[][] board, Piece piece){

        board[piece.getRow()+1][piece.getCol()].setBackground(Color.YELLOW);
        board[piece.getRow()+1][piece.getCol()].setSelected(true, piece);

        if(1==piece.getRow()){

            board[piece.getRow()+2][piece.getCol()].setBackground(Color.YELLOW);
            board[piece.getRow()+2][piece.getCol()].setSelected(true, piece);

        }

        return board;
    }
}
