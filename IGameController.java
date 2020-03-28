public interface IGameController
{
  public void addBead(int x, int y);
  public void playerSetup();
  public void getMove();
  public void checkWin(); 
  public void nextTurn();
}