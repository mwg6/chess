package Board;

import Logic.Highlighting;
import Logic.Mother;
import Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class GameBoard {
    //use the JPanel as it's good for collecting components. Return and add to a JFrame for display
    public JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private Tile[][] chessboardTiles = new Tile[8][8];
    private Mother mother;
    private Highlighting highlighter = new Highlighting();
    //fields
    /*
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    */
    public static final String[] STARTING_ROW = {
            "ROOK", "KNIGHT", "BISHOP", "KING", "QUEEN", "BISHOP", "KNIGHT", "ROOK", "PAWN"
    };
    public static final int BLACK = 0, WHITE = 1;

    public static final String[] SIDES = {"WHITE ", "BLACK "};

    public GameBoard(){
        initializeBoard();
        mother = new Mother();
    }

    public void initializeBoard(){

        //createImages();
        gui.setBorder(new EmptyBorder(5,5,5,5));

        //create and toolbar
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        gui.setMinimumSize(new Dimension(200,200));

        //adding toolbar compenents
        tools.add(new JButton("To DO"));


        chessBoard = new JPanel(new GridLayout(0,8)){
            //overriding dimension so squares are always centered
            @Override
            public final Dimension getPreferredSize(){
                //gets preferred size of the non overidden grids
                Dimension dim = super.getPreferredSize();
                Dimension prefSize = null;
                //gets the component (and therefore dimensions) of the parent container
                Component c = getParent();

                if(c==null){
                    //case where the parent isn't created?
                    prefSize = new Dimension((int) dim.getWidth(), (int)dim.getHeight());
                }
                else if(c!=null &&
                        c.getWidth()> dim.getWidth() &&
                        c.getHeight()>dim.getHeight()){
                    //case where the parent container dwarfs chess board
                    prefSize = c.getSize();
                }
                else{
                    //case where the parent does exist and is smaller than the original wanted size
                    prefSize = dim;
                }

                int width = (int) prefSize.getWidth();
                int height = (int) prefSize.getHeight();
                //determine smallest size and base boxes of of that
                int size = Math.min(width, height);
                return new Dimension (size, size);
            }
        };

        //create chessBoard Borders
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                ));
        Color background = Color.LIGHT_GRAY;
        chessBoard.setBackground(background);

        //Resizing only works with the gridbag layout.
        //Hence add a grid bag layout panel to the gui containing our arranged grid
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(background);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);


        Insets buttonMargin = new Insets(0,0,0,0);
        for(int i = 0; i<chessboardTiles.length; i++){
            for(int j = 0; j<chessboardTiles[i].length; j++){


                Tile b = new Tile(null, i, j);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64,64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);

                if(i%2==j%2){
                    b.setBackground(Color.WHITE);
                }
                else{
                    b.setBackground(Color.GRAY);
                }

                b.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(b.isSelected()){
                            //move case
                            System.out.println("Selected Square");
                            chessboardTiles = move(b.selectedBy(), b.getRow(), b.getCol(), getChessboardTiles());
                            clearAnnotations(chessboardTiles);
                            setChessboardTiles(chessboardTiles);
                        }
                        else{
                            if(null!=b.getPiece()){
                                System.out.println("Non null piece square");
                                clearAnnotations(chessboardTiles);
                                chessboardTiles = markSquares(b.getPiece(), getChessboardTiles());
                                setChessboardTiles(chessboardTiles);

                            }
                            else{
                                System.out.println("Null piece square");

                                clearAnnotations(chessboardTiles);
                            }

                        }
                    }
                });

                chessboardTiles[i][j] = b;

            }
        }

        setChessboardTiles(chessboardTiles);
    }

    public void clearAnnotations(Tile[][] chessboardTiles){
        for(int i = 0; i<chessboardTiles.length; i++){
            for(int j = 0; j<chessboardTiles[i].length; j++){
                if(i%2==j%2){
                    chessboardTiles[i][j].setBackground(Color.WHITE);
                }
                else{
                    chessboardTiles[i][j].setBackground(Color.GRAY);

                }
                chessboardTiles[i][j].setSelected(false, null);

            }
        }
    }

    public void initializeGame(){
        for(int i =0; i<STARTING_ROW.length-1; i++){
            if(i==0||i==7){
                chessboardTiles[0][i].setPiece(new Rook(Color.BLACK,0, i));

            }
            else if(i==1||i==6){

                chessboardTiles[0][i].setPiece(new Knight(Color.BLACK,0, i));

            }
            else if(i==2||i==5){

                chessboardTiles[0][i].setPiece(new Bishop(Color.BLACK,0, i));

            }
            else if(i==3){

                chessboardTiles[0][i].setPiece(new Queen(Color.BLACK,0, i));
                
            }
            else{
                
                chessboardTiles[0][i].setPiece(new King(Color.BLACK,0, i));

            }
            
            chessboardTiles[1][i].setPiece(new Pawn(Color.BLACK,1, i));
        }
        for(int i =0; i<STARTING_ROW.length-1; i++){
            if(i==0||i==7){
                chessboardTiles[7][i].setPiece(new Rook(Color.WHITE,7, i));

            }
            else if(i==1||i==6){

                chessboardTiles[7][i].setPiece(new Knight(Color.WHITE,7, i));

            }
            else if(i==2||i==5){

                chessboardTiles[7][i].setPiece(new Bishop(Color.WHITE,7, i));

            }
            else if(i==3){

                chessboardTiles[7][i].setPiece(new Queen(Color.WHITE,7, i));

            }
            else{

                chessboardTiles[7][i].setPiece(new King(Color.WHITE,7, i));

            }
            chessboardTiles[6][i].setPiece(new Pawn(Color.WHITE,6, i));
        }
    }

    public JPanel returnBoard(){
        return gui;
    }
    public Tile[][] getChessboardTiles(){
        return chessboardTiles;
    }

    public void setChessboardTiles(Tile[][] chessboardTiles) {

        for(int i = 0; i<chessboardTiles.length; i++){
            for(int j = 0; j<chessboardTiles[i].length; j++){
                chessBoard.add(chessboardTiles[i][j]);

            }
        }
    }

    public Tile[][] markSquares(Piece piece, Tile[][] tiles){

        String type = piece.getType();
        Tile tile = tiles[piece.getRow()][piece.getCol()];
        System.out.println("Processing move");

        /*if("PAWN".equals(type)){

            if(Color.WHITE.equals(piece.getSide())){

                *//*tiles[piece.getRow()-1][piece.getCol()].setBackground(Color.YELLOW);
                tiles[piece.getRow()-1][piece.getCol()].setSelected(true, piece);

                if(6==piece.getRow()){

                    tiles[piece.getRow()-2][piece.getCol()].setBackground(Color.YELLOW);
                    tiles[piece.getRow()-2][piece.getCol()].setSelected(true, piece);

                }*//*
            }
            else{

            }
        }*/

        return highlighter.finder(tiles, piece);

    }

    public Tile[][] move(Piece attack, int row, int col, Tile[][] tiles){
        int oRow = attack.getRow();
        int oCol = attack.getCol();

        tiles[oRow][oCol].setPiece(null);

        attack.setRow(row);
        attack.setCol(col);

        tiles[row][col].setPiece(new Pawn(attack.getSide(), attack.getRow(), attack.getCol()));
        tiles[row][col].setSelected(false, null);

        return tiles;

    }
}