package Logic;

import Board.Tile;

import java.util.PriorityQueue;
import java.util.Stack;

public class UndoStack {
    private Stack<Tile[][]> moves;

    public UndoStack(){
        moves = new Stack<>();
    }

    public Tile[][] undoMove(){
        if(moves.size()>0){
            return moves.pop();
        }

        return null;
    }

    public void addBoard(Tile[][] lastMove){
        moves.push(lastMove);
    }
}
