package tictacs;


import tictacs.Game;


public class GameDriver {
  public static void main(String[] args) {
    Game g = new Game();
    System.out.println(g.getGameID());
    g.start();
  }
}
