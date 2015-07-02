package tictacs;

import tictacs.Grid;


public class State {
    private enum Check {Row, Column, Diagonal, ReverseDiagonal};
  private Grid[][] grids;
  private int currentGridX;
  private int currentGridY;
  private int turns;
  private int winner;
  private boolean complete;
  private boolean freeGridMove;
  private String[] symbols;

  public State() {
    grids = new Grid[3][3];
    for(int y=0; y < 3;y++) {
      for(int x=0; x < 3;x++) {
        grids[x][y] = new Grid();
      }
    }
    turns = 0;
    symbols = new String[2];
    symbols[0] = "X";
    symbols[1] = "O";
    complete = false;
    freeGridMove = true;
    winner = -1;
  }

  public String getIthSpace(int i, int var, Check check) {
    switch(check) {
      case Row:
        return grids[i][var].getWinner();
      case Column:
        return grids[var][i].getWinner();
      case Diagonal:
        return grids[var][var].getWinner();
      case ReverseDiagonal:
        return grids[var][2-var].getWinner();
      default:
        return " ";
    }
  }

  private String inARow(int i, Check check) {
    String out = getIthSpace(i, 0, check);
    for(int x = 1; x < 3; x++) {
      if(!getIthSpace(i, x, check).equals(out)) {
        out = " ";
      }
    }

    return out;
  }

  public boolean isWon(String move) {
    for(int i = 0; i < 3; i++) {
      if(inARow(i, Check.Row).equals(move)) { return true; }
      if(inARow(i, Check.Column).equals(move)) { return true; }
    }
    if(inARow(0, Check.Diagonal).equals(move)) { return true; }
    if(inARow(0, Check.ReverseDiagonal).equals(move)) { return true; }

    return false;
  }

  public boolean isValid(int x, int y) {
    if(grids[y][x].getWinner().equals(" ")) {
      return true;
    }
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

  public boolean isComplete() {
    return complete;
  }

  public boolean isFree() {
    return freeGridMove;
  }

  public int getWinner() {
    return winner;
  }

  //to be called when grid choice is free
  public boolean makeMove(int gX, int gY, int x, int y) {
      if(isValid(gX,gY)) { //while the grid they choose is incomplete...
        currentGridX = gX;
        currentGridY = gY;
        freeGridMove = false;
        return makeMove(x,y);
      } else {
        System.out.println("That grid is complete!");
        return false;
      }
  }


  //restricted grid
  public boolean makeMove(int x, int y) {
    String move = symbols[turns % 2];
    if(grids[currentGridY][currentGridX].makeMove(x,y,move)) { //if they make a valid move...
      //check for wins or completion
      if(isWon(move)) {
        complete = true;
        winner = turns % 2;
      } else if(isFull()) {
        complete = true;
        winner = -1;
      }

      //check if next grid is viable
      if(isValid(x,y)) {
        currentGridX = x;
        currentGridY = y;
      } else {
        freeGridMove = true;
      }

      turns++;
      return true;
    } else {
      return false;
    }
  }


  public String toString() {
    String out = new String();
    for(int v = 0; v < 3;v++) {
      for(int z = 0;z <3;z++) {
      for(int y = 0; y < 3;y++) {
        for(int x = 0; x < 3;x++) {
          out+= grids[v][y].getSpace(x,z) + " ";
        }
        out+= "   ";
      }
      out += "\n";
    }
      out += "\n\n";
  }
    return out;
  }


}
