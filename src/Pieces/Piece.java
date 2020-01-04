package Pieces;

import javax.swing.*;
import java.awt.*;

public interface Piece{

    public Color getSide();

    public String getType();

    public ImageIcon getIcon();

    public int getRow();

    public int getCol();

    public void setCol(int col);

    public void setRow(int row);


}
