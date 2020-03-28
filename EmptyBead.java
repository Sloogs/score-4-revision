public class EmptyBead implements Beadlike
{
  private boolean isEmpty;
  private PlayerColour colour;
  private int beadId;
  private int xCoord;
  private int yCoord;
  private int zCoord;

  public EmptyBead(int x, int y, int z) {
    isEmpty = true;
    colour = PlayerColour.EMPTY;
    beadId = -1;
    xCoord = x;
    yCoord = y;
    zCoord = z;
  }

  public PlayerColour getColour()
  {
    return colour;
  }

  public int getId()
  {
    return beadId;
  }

  public int getXCoord()
  {
    return xCoord;
  }

  public int getYCoord()
  {
    return yCoord;
  }

  public int getZCoord()
  {
    return zCoord;
  }

  public boolean isEmpty()
  {
    return isEmpty;
  }
}