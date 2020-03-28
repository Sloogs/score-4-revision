public class EmptyBead implements Beadlike
{
  private boolean isEmpty;
  private PlayerColour colour;
  private int beadId;
  private int xCoord;
  private int yCoord;
  private int zCoord;

  /**
   * Creates an empty bead. The reason this was done was to have something that we could easily 
   * identify as an empty space where there is no bead, and to prevent Null Pointer Exceptions.
   * @param x X value where bead will be placed.
   * @param y Y value where bead will be placed.
   * @param z Z value where bead will be placed.
   */
  public EmptyBead(int x, int y, int z) {
    isEmpty = true;
    colour = PlayerColour.EMPTY;
    beadId = -1;
    xCoord = x;
    yCoord = y;
    zCoord = z;
  }

  /**
   * Gets the Colour of the EmptyBead, which should be PlayerColour.EMPTY.
   * @return Returns a PLayerColour, which should be PlayerColour.EMPTY.
   */
  public PlayerColour getColour()
  {
    return colour;
  }

  /**
   * Gets bead ID.
   * @return Returns the BeadId of the bead.
   */
  public int getId()
  {
    return beadId;
  }

  /**
   * Gets x coordinate (row).
   * @return Returns an integer of the x coordinate of the bead.
   */
  public int getXCoord()
  {
    return xCoord;
  }

  /**
   * Gets y coordinate (column).
   * @return Returns an integer of the y coordinate of the bead.
   */
  public int getYCoord()
  {
    return yCoord;
  }

  /**
   * Gets z coordnate (height).
   * @return Returns an integer of the z coordinate of the bead.
   */
  public int getZCoord()
  {
    return zCoord;
  }

  /**
   * Gets whether the bead is empty or not.
   * Only the EmptyBead class will have this true.
   * @return Returns whether the bead in this position is empty or not.
   */
  public boolean isEmpty()
  {
    return isEmpty;
  }
}