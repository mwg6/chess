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
            case "ROOK":
                return rookRules(board, piece);

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
    public Tile[][] rookRules(Tile[][] board, Piece piece){

        return straightColsContact(board, piece);
    }


    private Tile[][] straightColsContact(Tile[][] board, Piece piece){
        int col = piece.getCol();
        int row = piece.getRow();

        for(int i =col; i<board[row].length; i++){
            if(board[i][col].getPiece()!=null&&board[i][col].getPiece().getSide()!=piece.getSide()){
                board[i][col].setBackground(Color.RED);
                board[i][col].setSelected(true, piece);
                break;
            }
            else if (board[i][col].getPiece()==null){
                board[i][col].setBackground(Color.YELLOW);
                board[i][col].setSelected(true, piece);
            }
            else if(board[i][col].getPiece()!=null&&board[i][col].getPiece().getSide()==piece.getSide()){
                break;
            }
        }
        for(int i =col; i>=0; i--){
            if(board[i][col].getPiece()!=null&&board[i][col].getPiece().getSide()!=piece.getSide()){
                board[i][col].setBackground(Color.RED);
                board[i][col].setSelected(true, piece);
                break;
            }
            else if (board[i][col].getPiece()==null){
                board[i][col].setBackground(Color.YELLOW);
                board[i][col].setSelected(true, piece);
            }
            else if(board[i][col].getPiece()!=null&&board[i][col].getPiece().getSide()==piece.getSide()){
                break;
            }
        }


        return board;
    }

    private Tile[][] sideColsContact(Tile[][] board, Piece piece){
        //int col = piece.getCol();
        int row = piece.getRow();

        for(int i = row; i<board.length; i++){
            if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()!=piece.getSide()){
                board[row][i].setBackground(Color.RED);
                board[row][i].setSelected(true, piece);
                break;
            }
            else if (board[row][i].getPiece()==null){
                board[row][i].setBackground(Color.YELLOW);
                board[row][i].setSelected(true, piece);
            }
            else if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()==piece.getSide()){
                break;
            }
        }
        for(int i =row; i>=0; i--){
            if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()!=piece.getSide()){
                board[row][i].setBackground(Color.RED);
                board[row][i].setSelected(true, piece);
                break;
            }
            else if (board[row][i].getPiece()==null){
                board[row][i].setBackground(Color.YELLOW);
                board[row][i].setSelected(true, piece);
            }
            else if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()==piece.getSide()){
                break;
            }
        }


        return board;
    }
}
