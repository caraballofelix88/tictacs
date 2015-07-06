package tictacs;

import java.util.ArrayList;
import java.awt.Point;


public abstract class Checkable {
  protected enum Check{Row, Column, Diagonal, ReverseDiagonal};
  protected Symbol value;
  protected boolean complete;

  public Checkable() {
    value = Symbol.Empty;
    complete = false;
  }

  //public abstract boolean isWon

  public boolean isValid() {
    return !(value == Symbol.Player1 || value == Symbol.Player2 || value == Symbol.Tied);
  }

  public boolean isComplete() {
    return complete;
  }

  public Symbol getValue() { //getWinner isnt really accurate semantically
    return value;
  }

  public abstract boolean makeMove(ArrayList<Point> coordinates, Symbol move);
}
