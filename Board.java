public class Board
{
  private Grid grid;

  /**
   * Constructor for a Board.
   */
  public Board()
  {
    Debug.log("Creating Board");
    grid = new Grid();
  }

  /**
   * Gets the inner grid layout of the board, which contains Pegs and Lines.
   */
  public Grid getGrid()
  {
    return grid;
  }

  /**
   * Clears each Peg of Beads on the Board.
   */
  public void clearBoard()
  {
    Peg[][] pegs = grid.getAllPegs();

    for (Peg[] row: pegs) {
      for (Peg peg: row) {
        int numBeads = peg.getNumBeads();
        for (int i = 0; i < numBeads; i++)
        {
          peg.removeBead();
        }
      }
    }
  }
}