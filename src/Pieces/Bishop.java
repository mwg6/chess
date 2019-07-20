package Pieces;

import javax.swing.*;
import java.awt.*;

public class Bishop implements Piece{
    private final Color color;
    private final String type;
    private final ImageIcon icon;
    private int row;
    private int col;


    public Bishop(Color color, int row, int col){
        this.color = color;
        this.row=row;
        this.col=col;

        type = "Bishop";

        if(Color.BLACK.equals(color)){

            icon = new ImageIcon("/home/max/Repos/chess/resources/bBishop.png");

        }
        else{

            icon = new ImageIcon("/home/max/Repos/chess/resources/wBishop.png");

        }
    }

    public Color getSide() {
        return color;
    }

    public String getType() {
        return type;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
