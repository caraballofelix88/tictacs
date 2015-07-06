package tictacs;

import java.util.ArrayList;
import java.awt.Point;

public class State {
  private enum Check {Row, Column, Diagonal, ReverseDiagonal};
  private Grid board;
  private int turns;
  private int winner;
  private int dimension;
  private boolean complete;

  public State(int dim) {
    board = new Grid(dim);
    dimension = dimension;
    turns = 0;
    complete = false;
    winner = -1;
  }

  public boolean makeMove(ArrayList<Point> coords) {
    Symbol current = (turns%2==0) ? Symbol.Player1 : Symbol.Player2;
    if(board.makeMove(coords, current)) {
      turns++;
      return true;
    }
    return false;
  }

  public boolean isComplete() {
    return board.isComplete();
  }

  public int getWinner() {
    if(board.getValue() == Symbol.Player1) { return 0; }
    else if(board.getValue() == Symbol.Player2) { return 1; }
    else return -1;
  }

  public boolean isFree() {
    return board.isFree();
  }


}
