package Board;

import Pieces.*;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private Piece piece;
    private Piece selPiece;
    private int row;
    private int col;
    private boolean selected;

    public Tile(Piece piece, int row, int col){

        this.piece = piece;
        this.row=row;
        this.col=col;
        selected = false;

    }

    public Tile(Tile tile){
        Piece oldP = tile.piece;
        Piece newP = null;
        if(oldP!=null){
            int row = oldP.getRow();
            int col = oldP.getCol();
            Color side = oldP.getSide();
            switch(oldP.getType()){
                case "PAWN":
                    newP = new Pawn(side, row, col);
                case "ROOK":
                    newP = new Rook(side, row, col);
                    //TODO preserve castling
                   /* if(((Rook)oldP).hasMoved()){
                        ((Rook) newP).setMoved();
                    }*/
                case "KNIGHT":
                    newP = new Knight(side, row, col);
                case "BISHOP":
                    newP = new Bishop(side, row, col);
                case "QUEEN":
                    newP = new Queen(side, row, col);
                case "KING":
                    newP = new King(side, row, col);
                /*    if(((King)oldP).hasMoved()){
                        ((King) newP).setMoved();
                    }*/
            }
        }

        this.piece=newP;
        this.row = tile.getRow();
        this.col = tile.getCol();
        selected = tile.selected;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        setIcon(piece==null?null:piece.getIcon());
    }
    
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public boolean isSelected() {
        return selected;
    }

    public Piece selectedBy(){
        return selPiece;
    }

    public void setSelected(boolean val, Piece selPiece){
        selected = val;
        this.selPiece = selPiece;
    }
}