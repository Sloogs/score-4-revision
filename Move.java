public class Move
  {
    private Player player;
    private int xCoord;
    private int yCoord;

    /**
     * Constructor for a Move.
     * @param p The Player whose move this is.
     * @param x Represents the x value of the Peg the player wants to place their bead on.
     * @param y Represents the y value of the Peg the player wants to place their bead on.
     */
    public Move(Player p, int x, int y)
    {
      player = p;
      xCoord = x;
      yCoord = y;
    }

    /**
     * Get the x coordinate for this Move.
     */
    public int getXCoord() {
      return xCoord;
    }

    /**
     * Get the y coordinate for this Move.
     */
    public int getYCoord() {
      return yCoord;
    }

    /**
     * Set the x coordinate for this Move.
     */
    public void setXCoord(int x) {
      xCoord = x;
    }

    /**
     * Set the x coordinate for this Move.
     */
    public void setYCoord(int y) {
      yCoord = y;
    }

    /**
     * Gets the Player this Move is associated with. 
     * @return Returns the Player the Move is associated with.
     */
    public Player getPlayer()
    {
      return player;
    }

    /**
     * Changes the Player this Move is associated with. 
     * @param p The player who we wish to set the Move to.
     */
    public void setPlayer(Player p)
    {
      player = p;
    }
  }