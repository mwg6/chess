package Pieces;

import java.awt.*;

public class PieceFactory {

    public Piece PieceFactory(String type, Color color, int row, int col){

        if("PAWN".equalsIgnoreCase(type)){
            return new Pawn(color, row, col);
        }
        else if("ROOK".equalsIgnoreCase(type)){
            return new Rook(color, row, col);
        }
        else if("KNIGHT".equalsIgnoreCase(type)){
            return new Knight(color, row, col);
        }
        else if("BISHOP".equalsIgnoreCase(type)){
            return new Bishop(color, row, col);
        }
        else if("QUEEN".equalsIgnoreCase(type)){
            return new Queen(color, row, col);
        }
        else if("KING".equalsIgnoreCase(type)){
            return new King(color, row, col);
        }
        else{
            return null;
        }
    }
}
