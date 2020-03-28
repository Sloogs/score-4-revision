import java.util.ArrayList;

public class Peg
{
  private final int MAX_BEADS = 4;
  private int xCoord = 0;
  private int yCoord = 0;
  private int numBeads = 0;
  private Beadlike[] beads = new Beadlike[4];

  /**
   * The constructor is created by the Grid and creates a peg at he given x
   * and y coordinate. There is no need for a programmer to create a peg
   * manually as it is already done by the Grid.
   */
  public Peg(int x, int y)
  {
    Debug.log("Creating Peg at: (x: " + x + ", y: " + y +")");
    xCoord = x;
    yCoord = y;
    for (int z = 0; z < MAX_BEADS; z++)
    {
      beads[z] = new EmptyBead(x, y, z);
    }
  }

  /**
   * Call this to add a Bead to this individual Peg. Although for actual
   * gameplay is is perhaps better to call PLayer.placeBead(...) instead.
   * @param colour The colour of the bead you wish to create. The colour can
   *               be either PlayerColour.WHITE or PlayerColour.BLACK.
   */
  public boolean addBead(PlayerColour colour)
  {
    boolean successful = false;
    if (numBeads < 4)
    {
      beads[numBeads] = new Bead(colour, xCoord, yCoord, numBeads);
      numBeads++;
      successful = true;
    }
    else
    {
      successful = false;
    }

    return successful;
  }

  /**
   * Removes a bead from this individual Peg.
   * @return
   */
  public boolean removeBead()
  {
    Debug.log("Attempting to remove bead.");
    boolean successful = false;
    if (numBeads > 0) {
      beads[numBeads - 1] = new EmptyBead(xCoord, yCoord, numBeads);
      numBeads--;
      successful = true;
      Debug.log("Successfully removed bead.");
    }
    else
    {
      successful = false;
    }

    return successful;
  }

  /**
   * Gets the nth Bead from this Peg.
   * @param n The nth Bead you want from the Peg, from 0-3.
   * @return The Bead you wish to get.
   */
  public Beadlike getBead(int n)
  {
    Beadlike bead = beads[n];
    int x = bead.getXCoord();
    int y = bead.getYCoord();
    int z = bead.getZCoord();
    Debug.log("Getting bead at: (x: " + x + ", " + y + ")");
    return bead;
  }

  /**
   * Gets all Beads from this Peg and returns a Beadlike array.
   * @return
   */
  public Beadlike[] getAllBeads()
  {
    Debug.log("Getting all beads from peg: (x: " + xCoord + ", y: " + yCoord + ")");
    return beads;
  }

  /**
   * Gets the x-coordinate (row) of the Peg.
   * @return Integer representing the x-coordinate.
   */
  public int getXCoord() {
    return xCoord;
  }

  /**
   * Gets the y-coordinate (column) of the Peg.
   * @return Integer representing the x-coordinate.
   */
  public int getYCoord() {
    return yCoord;
  }

  /**
   * Gets the number of Beads on the Peg.
   * @return An integer representing the number of beads on the peg.
   */
  public int getNumBeads() {
    return numBeads;
  }

  /**
   * Empties the beads on the Peg.
   */
  public void emptyBeads(){
    for (int i = 0; i < beads.length; i++)
      beads[i] = null;
    
    numBeads = 0;
  }
}