import Board.GameBoard;

import javax.swing.*;
import java.awt.*;

public class main {

    public static void main(String... args){

        GameBoard board = new GameBoard();
        JFrame frame = new JFrame("Chess");
        board.initializeGame();
        JPanel gui = board.returnBoard();



        frame.add(gui);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setMinimumSize(new Dimension(100,100));
        gui.setPreferredSize(new Dimension(100,100));

        frame.setPreferredSize(new Dimension(100,100));

        frame.setLocation(50, 50);

        frame.pack();
        frame.setVisible(true);


    }
}
