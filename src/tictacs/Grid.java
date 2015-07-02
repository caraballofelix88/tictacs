package tictacs;

public class Grid {
  private String[][] spaces;
  private String winner;
  private boolean complete;
  private String[] symbols;
  private enum Check {Row, Column, Diagonal, ReverseDiagonal};


  public Grid() {
    spaces = new String[3][3];
    for(int y=0; y < 3;y++) {
      for(int x=0; x < 3;x++) {
        spaces[y][x] = new String(" ");
      }
    }

    winner = " ";
    complete = false;
  }

  public boolean isValid(int x, int y) {
    if(spaces[y][x].equals(" ")) {
      return true;
    }
    return false;
  }

//TODO: implement game condition check functions in a seperate abstract class?
//      add to interface?
  public String getIthSpace(int i, int var, Check check) {
    switch(check) {
      case Row:
        return spaces[i][var];
      case Column:
        return spaces[var][i];
      case Diagonal:
        return spaces[var][var];
      case ReverseDiagonal:
        return spaces[var][2-var];
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

  public String getWinner() {
    return winner;
  }

  public boolean makeMove(int x, int y, String move) {
    if(isValid(x,y)) {
      spaces[y][x] = move;

      if(isWon(move)) {
        complete = true;
        winner = move;
      }

      if(isFull()) {
        complete = true;
        winner = "*";
      }

      return true;
    }
    System.out.println("Space is already filled!");
    return false;
  }

  public String getSpace(int x, int y) {
    return spaces[y][x];
  }

  public String toString() {
    String out = new String();
    for(int y = 0; y < 3;y++) {
      out+= spaces[y][0] + "|" + spaces[y][1] + "|" + spaces[y][2] + "\n";
    }
    return out;
  }
}
