public class Move
  {
    private Player player;
    private int xCoord;
    private int yCoord;

    public Move(Player p, int x, int y)
    {
      player = p;
      xCoord = x;
      yCoord = y;
    }

    public int getXCoord() {
      return xCoord;
    }

    public int getYCoord() {
      return yCoord;
    }

    public void setXCoord(int x) {
      xCoord = x;
    }

    public void setYCoord(int y) {
      yCoord = y;
    }

    public Player getPlayer()
    {
      return player;
    }

    public void setPlayer(Player p)
    {
      player = p;
    }
  }