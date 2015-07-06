
package tictacs;


import java.util.ArrayList;
import java.awt.Point;

public class Grid extends Checkable {
  private Checkable[][] spaces;
  private int dimension;
  private int currentGridX;
  private int currentGridY;
  private boolean freeGridMove;

  public Grid(int dim) {
    super();

    spaces = new Checkable[3][3];

    if(dim > 1) {
      for(int y=0; y < 3; y++) {
        for(int x=0; x < 3; x++) {
          spaces[y][x] = new Grid(dim-1);
        }
      }
    } else {
      for(int y=0; y < 3; y++) {
        for(int x=0; x < 3; x++) {
          spaces[y][x] = new Space();
        }
      }
    }

    dimension = dim;
    complete = false;
    freeGridMove = true;
    value = Symbol.Empty;
  }

  public Symbol getIthSpace(int i, int var, Check check) {
    switch(check) {
      case Row:
        return spaces[i][var].getValue();
      case Column:
        return spaces[var][i].getValue();
      case Diagonal:
        return spaces[var][var].getValue();
      case ReverseDiagonal:
        return spaces[var][2-var].getValue();
      default:
        return Symbol.Empty;
    }
  }

  private Symbol inARow(int i, Check check) {
    Symbol out = getIthSpace(i, 0, check);
    for(int x = 1; x < 3; x++) {
      if(!getIthSpace(i, x, check).equals(out)) {
        out = Symbol.Empty;
      }
    }
    return out;
  }

  public boolean isWon(Symbol move) {
    for(int i = 0; i < 3; i++) {
      if(inARow(i, Check.Row).equals(move)) { return true; }
      if(inARow(i, Check.Column).equals(move)) { return true; }
    }
    if(inARow(0, Check.Diagonal).equals(move)) { return true; }
    if(inARow(0, Check.ReverseDiagonal).equals(move)) { return true; }

    return false;
  }

  public boolean isFull() {
    for(int y=0;y<3;y++) {
      for(int x=0;x<3;x++) {
        if(isValid(x,y)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isValid(int x, int y) {
    if(spaces[y][x].isValid()) {
      return true;
    }
    return false;
  }


  //coordinates composed of series of (x,y) points, each index corresponding to grid depth
  //TODO: replace ArrayList with just an array
  //Actually this coordinate list approach may not even be necessary
  public boolean makeMove(ArrayList<Point> coordinates, Symbol move) {
    //////////////////////////////////
    int x = coordinates.get(0).x;
    int y = coordinates.get(0).y;
    int nextX;
    int nextY;
    if(freeGridMove) {

      if(isValid(x,y)) {

      } else {
        System.out.println("that spot is full!");
        return false;
      }
    } else {
      if(currentGridX == x && currentGridY == y) {

      } else {
        System.out.println("Invalid grid choice.");
        return false;
      }
    }


    coordinates.remove(0);
    if(!coordinates.isEmpty()) {
      nextX = coordinates.get(0).x;
      nextY = coordinates.get(0).y;
      if(isValid(nextX,nextY)) {
        currentGridX = nextX;
        currentGridY = nextY;
        freeGridMove = false;
      } else {
        freeGridMove = true;
      }
    }


    if(spaces[y][x].makeMove(coordinates, move)) {
      if(isWon(move)) {
        complete = true;
        value = move;
      } else if(isFull()) {
        complete = true;
        value = Symbol.Tied;
      } else {
        value = Symbol.Incomplete;
      }
      return true;
    } else {
      return false;
    }
    /////////////////////////////
  }





  public boolean isFree() {
    return freeGridMove;
  }

}
