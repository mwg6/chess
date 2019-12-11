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
            case "KNIGHT":
                return knightRules(board, piece);
            case "BISHOP":
                return bishopRules(board, piece);
        }


        return board;
    }

    //TODO add pawn take rules

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

        return sideColsContact(straightColsContact(board, piece),piece);
    }


    private Tile[][] straightColsContact(Tile[][] board, Piece piece){
        int col = piece.getCol();
        int row = piece.getRow();

        for(int i =row+1; i<board.length; i++){
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
        for(int i =row-1; i>=0; i--){
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
        int col = piece.getCol();
        int row = piece.getRow();

        for(int i = col+1; i<board.length; i++){
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
        for(int i =col-1; i>=0; i--){
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
    
    private Tile[][] knightRules(Tile[][] board, Piece piece){
        int col = piece.getCol();
        int row = piece.getRow();
        Color side = piece.getSide();
        
        if(row+2<board.length){
            if(col-1>=0){
                if(board[row+2][col-1].getPiece()==null){
                    board[row+2][col-1].setBackground(Color.YELLOW);
                    board[row+2][col-1].setSelected(true, piece);
                }
                else if(board[row+2][col-1].getPiece().getSide()!=side){
                    board[row+2][col-1].setBackground(Color.RED);
                    board[row+2][col-1].setSelected(true, piece);
                }
            }
            if(col+1<board[row+2].length){
                if(board[row+2][col+1].getPiece()==null){
                    board[row+2][col+1].setBackground(Color.YELLOW);
                    board[row+2][col+1].setSelected(true, piece);
                }
                else if(board[row+2][col+1].getPiece().getSide()!=side){
                    board[row+2][col+1].setBackground(Color.RED);
                    board[row+2][col+1].setSelected(true, piece);
                }
            }
        }

        if(row+1<board.length){
            if(col-2>=0){
                if(board[row+1][col-2].getPiece()==null){
                    board[row+1][col-2].setBackground(Color.YELLOW);
                    board[row+1][col-2].setSelected(true, piece);
                }
                else if(board[row+1][col-2].getPiece().getSide()!=side){
                    board[row+1][col-2].setBackground(Color.RED);
                    board[row+1][col-2].setSelected(true, piece);
                }
            }
            if(col+2<board[row+1].length){
                if(board[row+1][col+2].getPiece()==null){
                    board[row+1][col+2].setBackground(Color.YELLOW);
                    board[row+1][col+2].setSelected(true, piece);
                }
                else if(board[row+1][col+2].getPiece().getSide()!=side){
                    board[row+1][col+2].setBackground(Color.RED);
                    board[row+1][col+2].setSelected(true, piece);
                }
            }
        }
        if(row-1<board.length){
            if(col-2>=0){
                if(board[row-1][col-2].getPiece()==null){
                    board[row-1][col-2].setBackground(Color.YELLOW);
                    board[row-1][col-2].setSelected(true, piece);
                }
                else if(board[row-1][col-2].getPiece().getSide()!=side){
                    board[row-1][col-2].setBackground(Color.RED);
                    board[row-1][col-2].setSelected(true, piece);
                }
            }
            if(col+2<board[row-1].length){
                if(board[row-1][col+2].getPiece()==null){
                    board[row-1][col+2].setBackground(Color.YELLOW);
                    board[row-1][col+2].setSelected(true, piece);
                }
                else if(board[row-1][col+2].getPiece().getSide()!=side){
                    board[row-1][col+2].setBackground(Color.RED);
                    board[row-1][col+2].setSelected(true, piece);
                }
            }
        }
        if(row-2<board.length){
            if(col-1>=0){
                if(board[row-2][col-1].getPiece()==null){
                    board[row-2][col-1].setBackground(Color.YELLOW);
                    board[row-2][col-1].setSelected(true, piece);
                }
                else if(board[row-2][col-1].getPiece().getSide()!=side){
                    board[row-2][col-1].setBackground(Color.RED);
                    board[row-2][col-1].setSelected(true, piece);
                }
            }
            if(col+1<board[row-2].length){
                if(board[row-2][col+1].getPiece()==null){
                    board[row-2][col+1].setBackground(Color.YELLOW);
                    board[row-2][col+1].setSelected(true, piece);
                }
                else if(board[row-2][col+1].getPiece().getSide()!=side){
                    board[row-2][col+1].setBackground(Color.RED);
                    board[row-2][col+1].setSelected(true, piece);
                }
            }
        }

        return board;
    }

    private Tile[][] bishopRules(Tile[][] board, Piece piece){
        int row = piece.getRow();
        int col = piece.getCol();
        Color side = piece.getSide();

        //FIXME: cannot access col 0 for either row-i col-i or row+i col-i conditions

        for(int i = 1; i<Math.min(board.length-row, board[row].length-col); i++){
            if(board[row+i][col+i].getPiece()==null){
                board[row+i][col+i].setBackground(Color.YELLOW);
                board[row+i][col+i].setSelected(true, piece);
            }
            else if(board[row+i][col+i].getPiece().getSide()!=side){
                board[row+i][col+i].setBackground(Color.RED);
                board[row+i][col+i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }

        for(int i = 1; i<=Math.min(board.length-row, col); i++){
            System.out.println(col +" "+(board.length-row));

            if(board[row+i][col-i].getPiece()==null){
                board[row+i][col-i].setBackground(Color.YELLOW);
                board[row+i][col-i].setSelected(true, piece);
            }
            else if(board[row+i][col-i].getPiece().getSide()!=side){
                board[row+i][col-i].setBackground(Color.RED);
                board[row+i][col-i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }

        for(int i = 1; i<=Math.min(row, board[row].length-col); i++){
            if(board[row-i][col+i].getPiece()==null){
                board[row-i][col+i].setBackground(Color.YELLOW);
                board[row-i][col+i].setSelected(true, piece);
            }
            else if(board[row-i][col+i].getPiece().getSide()!=side){
                board[row-i][col+i].setBackground(Color.RED);
                board[row-i][col+i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }

        for(int i = 1; i<Math.min(row, col); i++){
            if(board[row-i][col-i].getPiece()==null){
                board[row-i][col-i].setBackground(Color.YELLOW);
                board[row-i][col-i].setSelected(true, piece);
            }
            else if(board[row-i][col-i].getPiece().getSide()!=side){
                board[row-i][col-i].setBackground(Color.RED);
                board[row-i][col-i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }



        return board;
    }
}
