import java.util.ArrayList;

public class Player
{
  PlayerColour colour;
  boolean isAI;

  public Player (PlayerColour c)
  {
    colour = c;
    isAI = false;
  }

  public void placeBead(Board board, int x, int y)
  {
    boolean successful = board.getGrid().getPeg(x, y).addBead(colour);
    if (successful)
    {
      Debug.log("Successfully created bead.");
    }
    else
    {
      Debug.log("Bead could not be added.");
    }
  }

  public PlayerColour getColour()
  {
    return colour;
  }

  /**
   * Gets a move from the player's AI.
   * @param gs Requires to have the GameState passed in.
   * @return Returns a move to play.
   */
  public Move getMove(GameState gs)
  {
    ArrayList<Line> lines = gs.getBoard().getGrid().getLines();
    ArrayList<Integer> coordinate;
    ArrayList<ArrayList<Integer>> emptyPositions = new ArrayList<ArrayList<Integer>>();

    for (Line line: lines) {
      Beadlike[] beads = line.getBeads();
      for (Beadlike bead: beads) {
        if (bead.isEmpty()) {
          coordinate = new ArrayList<Integer>();
          int x = bead.getXCoord();
          int y = bead.getYCoord();
          int z = bead.getZCoord();

          coordinate.add(x);
          coordinate.add(y);
          coordinate.add(z);
          emptyPositions.add(coordinate);
        }
      }
    }

    int randomPosition;
    int numberOfEmptyPositions = emptyPositions.size();
    randomPosition = Helper.generateRandomNumber(numberOfEmptyPositions);

    ArrayList<Integer> moveCoords = emptyPositions.get(randomPosition);
    int xMove = moveCoords.get(0);
    int yMove = moveCoords.get(1);

    return new Move(this, xMove, yMove);
  }
}