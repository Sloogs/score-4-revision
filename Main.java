import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JFrame;

public class Main
{
  private static Scanner input = new Scanner(System.in);
  private static String inputString;
  private static boolean testingMode = false;
  private static boolean running = true;
  private static boolean hasWon = false;

  public static void main(String[] args)
  {
    // Check to see if testing mode is enabled.
    if (args.length > 0)
    {
      if (args[0].equals("--test"))
      {
        testingMode = true;
      }
    }

    GameState gameState = new GameState();

    // Check if the testing mode has been engaged.
    // If not, play the game as normal.
    // Otherwise, enter testing mode.

    if (!testingMode) {
      initGUI(gameState);
    }
    else
    {
      System.out.println("Entering TEST MODE");
      while (running) {
        String inputString = input.nextLine();
        if (inputString.startsWith("add")) {
          String pattern = "add (black|white) bead to ([A-D])([1-4]).";
          Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(inputString);
          int row = 0;
          int column = 0;

          if (m.matches()) {
            Debug.log("Match succeeded: " + m.group(2) + m.group(3));
            row = Helper.translateABCDToNumber(m.group(2));
            column = Integer.parseInt(m.group(3)) - 1;

            if (m.group(1).toLowerCase().equals("white")) {
              gameState.getWhitePlayer().placeBead(
                  gameState.getBoard(), row, column
              );
            }
            else if (m.group(1).toLowerCase().equals("black"))
            {
              gameState.getBlackPlayer().placeBead(
                  gameState.getBoard(), row, column
              );
            }
            else
            {
              Debug.log("Hmm...");
            }
          }
        }
        else if (inputString.startsWith("remove")) {
          String pattern = "remove bead from ([A-D])([1-4]).";
          Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(inputString);
          int row = 0;
          int column = 0;

          if (m.matches())
          {
            row = Helper.translateABCDToNumber(m.group(1));
            column = Integer.parseInt(m.group(2)) - 1;
            gameState.getBoard().getGrid().
                getPeg(row, column).removeBead();
          }
        }
        else if (inputString.contains("get white move."))
        {
          Move move = gameState.getWhitePlayer().getMove(gameState);
          String moveString = Helper.translateNumberToABCD(move.getXCoord(), move.getYCoord());
          System.out.println(moveString);
        }
        else if (inputString.contains("get black move."))
        {
          Move move = gameState.getBlackPlayer().getMove(gameState);
          String moveString = Helper.translateNumberToABCD(move.getXCoord(), move.getYCoord());
          System.out.println(moveString);
        }
        else if (inputString.contains("show board."))
        {
          showBoard(gameState);
        }
        else if (inputString.contains("draw board."))
        {
          drawBoard(gameState);
        }
        else if (inputString.contains("clear board."))
        {
          gameState.getBoard().clearBoard();
        }
        else if (inputString.contains("go gui.") || inputString.contains("go interactive."))
        {
          initGUI(gameState);
        }
        else if (inputString.contains("quit.") || inputString.contains("exit."))
        {
          running = false;
        }
      }
    }
  }

  public static void initGUI(GameState gs)
  {
      GameModel model = new GameModel(gs);
      GameView view = new GameView(model);
      model.setView(view);
      GameController controller = new GameController(model);
      view.setController(controller);
      controller.setView(view);
      controller.startGame();
  }

  public static void playerSetup(GameState gs)
  {
    boolean firstPlayerSelected = false;
    boolean secondPlayerSelected = false;

    while (!firstPlayerSelected) {
      System.out.println("First player will be: AI or Human?:");
      inputString = input.nextLine();
      if (inputString.toLowerCase().equals("human"))
      {
        gs.setWhitePlayer(new Player(PlayerColour.WHITE));
        gs.setCurrentPlayer(gs.getWhitePlayer());
        firstPlayerSelected = true;
      }
    }
  
    while (!secondPlayerSelected) {
      System.out.println("Second player will be: AI or Human?");
      inputString = input.nextLine();
      if (inputString.toLowerCase().equals("human"))
      {
        gs.setBlackPlayer(new Player(PlayerColour.BLACK));
        secondPlayerSelected = true;
      }
    }
  
  }

  public static boolean getMove(GameState gs)
  {
    boolean result = false;
    int xCoord = 0;
    int yCoord = 0;
    System.out.println("Current turn is " + gs.getCurrentPlayer().getColour() + "'s turn.");
    System.out.println("Type a coordinate that you would like to place a bead at "
        + "(.e.g. A1, B2; letters A-D and numbers 1-4 are valid.).");
    System.out.println("Enter " + gs.getCurrentPlayer().getColour() + "'s move:");
    inputString = input.nextLine();
    String pattern = "([A-D])([1-4])";
    Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(inputString);
    if (m.matches())
    {
      xCoord = Helper.translateABCDToNumber(m.group(1));
      yCoord = Integer.parseInt(m.group(2)) - 1;
      Debug.log("getMove() xCoord: " + xCoord);
      Debug.log("getMove() yCoord: " + yCoord);
      Move move = new Move(gs.getCurrentPlayer(), xCoord, yCoord);
      gs.setCurrentMove(move);
      result = true;
    }
    else
    {
      System.out.println("That's not a valid coordinate, please try again");
    }

    Debug.log("Move coordinates: " + xCoord + ", " + yCoord);

    return result;
  }

  public static void playMove(GameState gs)
  {
    Move m = gs.getCurrentMove();
    Player player = m.getPlayer();
    int xCoord = m.getXCoord();
    int yCoord = m.getYCoord();
    Debug.log("playMove() xCoord: " + xCoord);
    Debug.log("playMove() yCoord: " + yCoord);
    player.placeBead(gs.getBoard(), xCoord, yCoord);
  }

  public static void showBoard(GameState gs)
  {
      Peg[][] pegs = gs.getBoard().getGrid().getAllPegs();
      for(Peg[] row: pegs)
      {
          for (Peg peg: row) {
            Beadlike[] beads = peg.getAllBeads();
            int maxNumber = beads.length;
            String[] XChange = {"A","B","C","D"};
            System.out.print(XChange[peg.getXCoord()] + (peg.getYCoord() + 1) + ": ");

            for (Beadlike bead: beads)
            {
              if (bead.getColour() == PlayerColour.WHITE)
                  System.out.print("W");
              else if (bead.getColour() == PlayerColour.BLACK)
                  System.out.print("B");         
            }
            System.out.println("");
          }
      }
  }

  public static void drawBoard(GameState gs)
  {
    Peg[][] pegs = gs.getBoard().getGrid().getAllPegs();
    String fourthRow = "";
    String thirdRow = "";
    String secondRow = "";
    String firstRow = "";
    String fourthRowBead = "";
    String thirdRowBead = "";
    String secondRowBead = "";
    String firstRowBead = "";

    for (Peg[] row: pegs)
    {
      // For every ROW, we need to have our string builders reset.

      fourthRow = "";
      thirdRow = "";
      secondRow = "";
      firstRow = "";

      // The following loop builds up a peg as such. E.g.
      //
      //     |
      //     B
      //     W
      //     B

      for (Peg peg: row)
      {

        // For every PEG, we need to have our string builders reset.

        fourthRowBead = "";
        thirdRowBead = "";
        secondRowBead = "";
        firstRowBead = "";
        int numBeads = peg.getNumBeads();

        if (numBeads == 4)
        {
          fourthRowBead = (peg.getBead(3).getColour() == PlayerColour.WHITE) ? "W" : "B";
          thirdRowBead = (peg.getBead(2).getColour() == PlayerColour.WHITE) ? "W" : "B";
          secondRowBead = (peg.getBead(1).getColour() == PlayerColour.WHITE) ? "W" : "B";
          firstRowBead = (peg.getBead(0).getColour() == PlayerColour.WHITE) ? "W" : "B";
        }
        else if (numBeads == 3)
        {
          fourthRowBead = "|";
          thirdRowBead = (peg.getBead(2).getColour() == PlayerColour.WHITE) ? "W" : "B";
          secondRowBead = (peg.getBead(1).getColour() == PlayerColour.WHITE) ? "W" : "B";
          firstRowBead = (peg.getBead(0).getColour() == PlayerColour.WHITE) ? "W" : "B";
        }
        else if (numBeads == 2)
        {
          fourthRowBead = "|";
          thirdRowBead = "|";
          secondRowBead = (peg.getBead(1).getColour() == PlayerColour.WHITE) ? "W" : "B";
          firstRowBead = (peg.getBead(0).getColour() == PlayerColour.WHITE) ? "W" : "B";
        }
        else if (numBeads == 1)
        {
          fourthRowBead += "|";
          thirdRowBead += "|";
          secondRowBead += "|";
          firstRowBead += (peg.getBead(0).getColour() == PlayerColour.WHITE) ? "W" : "B";
        }
        else if (numBeads == 0)
        {
          fourthRowBead += "|";
          thirdRowBead += "|";
          secondRowBead += "|";
          firstRowBead += "|";
        }

        fourthRow += fourthRowBead + "   ";
        thirdRow += thirdRowBead + "   ";
        secondRow += secondRowBead + "   ";
        firstRow += firstRowBead + "   ";
      }

      // Prints out a row of 1x4 pegs.
      //
      //     |   |   |   |
      //     |   |   |   |
      //     |   |   W   |
      //     |   B   B   |

      System.out.println("");
      System.out.println(fourthRow);
      System.out.println(thirdRow);
      System.out.println(secondRow);
      System.out.println(firstRow);
    }
  }

  public static void nextTurn(GameState gs)
  {
    Player currentPlayer = gs.getCurrentPlayer();
    Player whitePlayer = gs.getWhitePlayer();
    Player blackPlayer = gs.getBlackPlayer();

    System.out.println("Current player: " + currentPlayer);
    System.out.println("White player: " + whitePlayer);
    System.out.println("Black player: " + blackPlayer);

    System.out.println("Current player equals white: " + gs.getCurrentPlayer().equals(whitePlayer));
    System.out.println("Current player equals black: " + gs.getCurrentPlayer().equals(blackPlayer));
    if (gs.getCurrentPlayer().equals(whitePlayer))
    {
      gs.setCurrentPlayer(blackPlayer);
    }
    else if (gs.getCurrentPlayer().equals(blackPlayer))
    {
      gs.setCurrentPlayer(whitePlayer);
    }
  }


}