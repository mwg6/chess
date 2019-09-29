package Logic;

import Board.GameBoard;
import Board.Tile;
import Pieces.Pawn;
import Pieces.Piece;

import java.awt.*;

public class Mother {
    //the tiniest fucking change possible
    public boolean isWhiteTurn;

    public Mother(){
        isWhiteTurn = true;
    }

    public Tile[][] processMove(Piece piece, Tile[][] tiles){

        String type = piece.getType();
        Tile tile = tiles[piece.getRow()][piece.getCol()];
        System.out.println(type);

        if("PAWN".equals(type)){

            if(Color.WHITE.equals(piece.getSide())){

                tiles[piece.getRow()-1][piece.getCol()].setBackground(Color.YELLOW);
                tiles[piece.getRow()-1][piece.getCol()].setSelected(true, piece);

                if(6==piece.getRow()){

                    tiles[piece.getRow()-2][piece.getCol()].setBackground(Color.YELLOW);
                    tiles[piece.getRow()-2][piece.getCol()].setSelected(true, piece);

                }
            }
            else{
                tiles[piece.getRow()+1][piece.getCol()].setBackground(Color.YELLOW);
                tiles[piece.getRow()+1][piece.getCol()].setSelected(true, piece);

                if(1==piece.getRow()){

                    tiles[piece.getRow()+2][piece.getCol()].setBackground(Color.YELLOW);
                    tiles[piece.getRow()+2][piece.getCol()].setSelected(true, piece);

                }
            }
        }

        return tiles;

    }
    public Tile[][] move(Piece attack, int row, int col, Tile[][] tiles){
        int oRow = attack.getRow();
        int oCol = attack.getCol();

        tiles[oRow][oCol].setPiece(null);

        attack.setRow(row);
        attack.setCol(col);

        tiles[row][col].setPiece(new Pawn(attack.getSide(), attack.getRow(), attack.getCol()));

        return tiles;

    }
}
