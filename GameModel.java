public class GameModel
{
  private GameView view;
  private GameState gameState;
  private Board board;
  private Player whitePlayer;
  private Player blackPlayer;
  private Player currentPlayer;

  public GameModel(GameState g) {
    gameState = g;
    board = gameState.getBoard();
    whitePlayer = gameState.getWhitePlayer();
    blackPlayer = gameState.getBlackPlayer();
    currentPlayer = gameState.getCurrentPlayer();
  }

  public GameState getGameState() {
    return gameState;
  }

  public Stage getStage()
  {
    return gameState.getStage();
  }

  public void setStage(Stage s)
  {
    gameState.setStage(s);
    view.update();
  }

  public void setView(GameView v)
  {
    this.view = v;
  }

  public void addBead(int x, int y)
  {
    currentPlayer.placeBead(board, x, y);

    // EVERY MUTATOR METHOD SHOULD HAVE VIEW.UPDATE()
    view.update();
  }

  public Beadlike getBead(int x, int y, int z)
  {
    Beadlike bead = board.getGrid().getPeg(x,y).getBead(z);
    return bead;
  }

  public Peg getPeg(int x, int y)
  {
    Peg peg = board.getGrid().getPeg(x, y);
    return peg;
  }

  public Peg[][] getAllPegs()
  {
    Peg[][] pegArray = board.getGrid().getAllPegs();
    return pegArray;
  }

  public Player getWhitePlayer()
  {
    return whitePlayer;
  }

  public Player getBlackPlayer()
  {
    return blackPlayer;
  }

  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player p)
  {
    currentPlayer = p;
    gameState.setCurrentPlayer(p);
    view.update();
  }

  public boolean checkWin()
  {
    return board.getGrid().getWinState();
  }
}