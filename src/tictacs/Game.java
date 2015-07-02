package tictacs;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Scanner;
import tictacs.State;



public class Game {
  private static AtomicLong GAME_ID = new AtomicLong();
  private final long gameId = Game.generateID();
  private State gameState;
  private String[] players;

  public Game() {
    gameState = new State();
    players = new String[2];
    players[0] = "Xavier";
    players[1] = "Oliver";
  }

  public int start() {
    Scanner scan = new Scanner(System.in);
    String in = new String();
    int moveVal = 0;
    int gridVal = 0;
    int gridY = 0;
    int gridX = 0;
    int moveY = 0;
    int moveX = 0;

    while(!gameState.isComplete()) {
      if(gameState.isFree()) {
        System.out.println("enter a number 1-9 to select grid.");
        in = scan.nextLine();
        try {
          gridVal = Integer.parseInt(in);
        } catch(NumberFormatException e) {
          System.out.println("Invalid input");
          continue;
        }

        gridVal--;
        if(gridVal < 0 || gridVal > 8) {
          System.out.println("Invalid number.");
          continue;
        }

        gridY = gridVal/3;
        gridX = gridVal%3;
      }



      System.out.println("enter a number 1-9 to select move.");
      in = scan.nextLine();
      try {
        moveVal = Integer.parseInt(in);
      } catch(NumberFormatException e) {
        System.out.println("Invalid input");
        continue;
      }

      moveVal--;
      if(moveVal < 0 || moveVal > 8) {
        System.out.println("Invalid number.");
        continue;
      }

      moveY = moveVal/3;
      moveX = moveVal%3;

      if(gameState.isFree()) {
        gameState.makeMove(gridX, gridY, moveX, moveY);
      } else {
        gameState.makeMove(moveX, moveY);
      }
      System.out.println(gameState);
    }

    if(gameState.getWinner() == -1) {
      System.out.println("its a tie");
    } else {
      System.out.println("Game " + gameId + " complete, " + players[gameState.getWinner()] + " wins!");
    }
    return 0;
  }





  public long getGameID() {
    return gameId;
  }

  public State getState() {
    return gameState;
  }



  private static long generateID() {
    return GAME_ID.incrementAndGet();
  }
}
