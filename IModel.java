public interface IModel
{
  public GameState getGameState();
  public Peg getPeg(int x, int y);
  public Peg[][] getAllPegs();
  public Beadlike getBead(int x, int y, int z);
  public void addBead(int x, int y);
  public int getNumBeads();
  public void setView(IView v);
  public Board getBoard();
  public Player getBlackPlayer();
  public Player getWhitePlayer();
}