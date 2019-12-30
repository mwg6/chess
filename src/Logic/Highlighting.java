package Logic;

import Board.Tile;
import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;

import java.awt.*;

public class Highlighting {

    private Color sideTurn;
    public Highlighting(){
        sideTurn=Color.WHITE;
    }

    public void switchSide(){
        if(sideTurn==Color.WHITE){
            sideTurn=Color.BLACK;
        }
        else{
            sideTurn=Color.WHITE;
        }
    }

    public Tile[][] finder(Tile[][] board, Piece piece){
        if(piece.getSide()==sideTurn){
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
                case "QUEEN":
                    return queenRules(board, piece);
                case "KING":
                    return kingRules(board, (King) piece);
            }
        }

        return board;
    }

    public Tile[][] wPawnRules(Tile[][] board, Piece piece){
        
        int row = piece.getRow();
        int col = piece.getCol();
        Color side = piece.getSide();
        
        if(board[row-1][col].getPiece()==null){
            board[row-1][col].setBackground(Color.YELLOW);
            board[row-1][col].setSelected(true, piece);

            if(6==row){
                board[row-2][col].setBackground(Color.YELLOW);
                board[row-2][col].setSelected(true, piece);

            }
        }

        if(board[row-1][col+1].getPiece()!=null&&board[row-1][col+1].getPiece().getSide()!=side){
            board[row-1][col+1].setBackground(Color.RED);
            board[row-1][col+1].setSelected(true, piece);
            
        }
        if(board[row-1][col-1].getPiece()!=null&&board[row-1][col-1].getPiece().getSide()!=side){
            board[row-1][col-1].setBackground(Color.RED);
            board[row-1][col-1].setSelected(true, piece);
        }

        
        return board;
    }
    public Tile[][] bPawnRules(Tile[][] board, Piece piece){
        int row = piece.getRow();
        int col = piece.getCol();
        Color side = piece.getSide();
        
        if(board[row+1][col].getPiece()==null){
            board[row+1][col].setBackground(Color.YELLOW);
            board[row+1][col].setSelected(true, piece);

            if(1==row){

                board[row+2][col].setBackground(Color.YELLOW);
                board[row+2][col].setSelected(true, piece);

            }
        }
        

        if(board[row+1][col+1].getPiece()!=null&&board[row+1][col+1].getPiece().getSide()!=side){
            board[row+1][col+1].setBackground(Color.RED);
            board[row+1][col+1].setSelected(true, piece);

        }
        if(board[row+1][col-1].getPiece()!=null&&board[row+1][col-1].getPiece().getSide()!=side){
            board[row+1][col-1].setBackground(Color.RED);
            board[row+1][col-1].setSelected(true, piece);
        }

        return board;
    }

    private Tile[][] kingRules(Tile[][] board, King piece){
        int row = piece.getRow();
        int col = piece.getCol();
        Color side = piece.getSide();

        for(int i = (col-1<0?0:col-1);i<(col+1>board[row].length?board[row].length:col+2); i++){
            if(row+1<board.length){
                if(board[row+1][i].getPiece()==null){
                    board[row+1][i].setBackground(Color.YELLOW);
                    board[row+1][i].setSelected(true, piece);
                }
                else if(board[row+1][i].getPiece().getSide()!=side){
                    board[row+1][i].setBackground(Color.RED);
                    board[row+1][i].setSelected(true, piece);
                }
            }

            if(row-1>=0){
                if(board[row-1][i].getPiece()==null){
                    board[row-1][i].setBackground(Color.YELLOW);
                    board[row-1][i].setSelected(true, piece);
                }
                else if(board[row-1][i].getPiece().getSide()!=side){
                    board[row-1][i].setBackground(Color.RED);
                    board[row-1][i].setSelected(true, piece);
                }
            }
            
            if(i!=col){
                if(board[row][i].getPiece()==null){
                    board[row][i].setBackground(Color.YELLOW);
                    board[row][i].setSelected(true, piece);
                }
                else if(board[row][i].getPiece().getSide()!=side){
                    board[row][i].setBackground(Color.RED);
                    board[row][i].setSelected(true, piece);
                }
            }
        }
        
        if(!piece.hasMoved()){
            if(board[row][0].getPiece().getType().equals("ROOK")){
                Rook rZ = (Rook) board[row][0].getPiece();
                if(!rZ.hasMoved()){
                    boolean canCastle = true;
                    for(int i = col-1; i>rZ.getCol(); i--){
                        if(board[row][i].getPiece()!=null){
                            canCastle=false;
                        }
                    }

                    if(canCastle){
                        board[row][col-1].setSelected(true, piece);
                        board[row][col-1].setBackground(Color.YELLOW);
                        board[row][col-2].setSelected(true, piece);
                        board[row][col-2].setBackground(Color.GREEN);
                    }
                }
            }

            int colL = board[row].length-1;
            if(board[row][colL].getPiece().getType().equals("ROOK")){
                Rook rZ = (Rook) board[row][colL].getPiece();
                if(!rZ.hasMoved()){
                    boolean canCastle = true;
                    for(int i = col+1; i<colL; i++){
                        if(board[row][i].getPiece()!=null){
                            canCastle=false;
                        }
                    }

                    if(canCastle){
                        board[row][col+1].setSelected(true, piece);
                        board[row][col+1].setBackground(Color.YELLOW);
                        board[row][col+2].setSelected(true, piece);
                        board[row][col+2].setBackground(Color.GREEN);
                    }
                }
            }
        }

        return board;
    }

    private Tile[][] queenRules(Tile[][] board, Piece piece){
        return rookRules(bishopRules(board,piece),piece);
    }

    private Tile[][] rookRules(Tile[][] board, Piece piece){

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
        Color side = piece.getSide();

        for(int i = col+1; i<board.length; i++){
            if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()!=side){
                board[row][i].setBackground(Color.RED);
                board[row][i].setSelected(true, piece);
                break;
            }
            else if (board[row][i].getPiece()==null){
                board[row][i].setBackground(Color.YELLOW);
                board[row][i].setSelected(true, piece);
            }
            else if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()==side){
                break;
            }
        }
        for(int i =col-1; i>=0; i--){
            if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()!=side){
                board[row][i].setBackground(Color.RED);
                board[row][i].setSelected(true, piece);
                break;
            }
            else if (board[row][i].getPiece()==null){
                board[row][i].setBackground(Color.YELLOW);
                board[row][i].setSelected(true, piece);
            }
            else if(board[row][i].getPiece()!=null&&board[row][i].getPiece().getSide()==side){
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

        for(int i = 1; i<Math.min(board.length-row, board[row].length-col); i++){
            if(row+i<board.length && col+i<board[row+i].length && board[row+i][col+i].getPiece()==null){
                board[row+i][col+i].setBackground(Color.YELLOW);
                board[row+i][col+i].setSelected(true, piece);
            }
            else if(row+i<board.length && col+i<board[row+i].length && board[row+i][col+i].getPiece().getSide()!=side){
                board[row+i][col+i].setBackground(Color.RED);
                board[row+i][col+i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }

        for(int i = 1; i<=Math.min(board.length-row, col); i++){

            if(row+i<board.length && col-i>=0 && board[row+i][col-i].getPiece()==null){
                board[row+i][col-i].setBackground(Color.YELLOW);
                board[row+i][col-i].setSelected(true, piece);
            }
            else if(row+i<board.length && col-i>=0 && board[row+i][col-i].getPiece().getSide()!=side){
                board[row+i][col-i].setBackground(Color.RED);
                board[row+i][col-i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }

        for(int i = 1; i<=Math.min(row, board[row].length-col); i++){
            if(row-i>=0 && col+i<board[row-i].length && board[row-i][col+i].getPiece()==null){
                board[row-i][col+i].setBackground(Color.YELLOW);
                board[row-i][col+i].setSelected(true, piece);
            }
            else if(row-i>=0 && col+i<board[row-i].length && board[row-i][col+i].getPiece().getSide()!=side){
                board[row-i][col+i].setBackground(Color.RED);
                board[row-i][col+i].setSelected(true, piece);
                break;
            }
            else{
                break;
            }
        }

        for(int i = 1; i<=Math.min(row, col); i++){

            if(row-i>=0 && col-i>=0 && board[row-i][col-i].getPiece()==null){
                board[row-i][col-i].setBackground(Color.YELLOW);
                board[row-i][col-i].setSelected(true, piece);
            }
            else if(row-i>=0 && col-i>=0 && board[row-i][col-i].getPiece().getSide()!=side){
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
