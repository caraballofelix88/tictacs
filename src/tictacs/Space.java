package tictacs;


import java.util.ArrayList;
import java.awt.Point;

public class Space extends Checkable {

  public Space() {
    super();
  }



  public boolean makeMove(ArrayList<Point> coordinates, Symbol move) {
    if(coordinates.size() == 0) {
      value = move;
      return true;
    } else {
      System.out.println("Wrong number of points?");
      return false;
    }
  }

}
