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

  /**
   * Sets the view assoicated with this GameModel.
   * @param v The view to be associated with this GameModel.
   */
  public void setView(GameView v)
  {
    this.view = v;
  }

  /**
   * Returns the GameState.
   * @return Returns the GameState.
   */
  public GameState getGameState() {
    return gameState;
  }

  /**
   * Returns the current Stage.
   * @return Returns the current Stage.
   */
  public Stage getStage()
  {
    return gameState.getStage();
  }

  public void setStage(Stage s)
  {
    gameState.setStage(s);
    view.update();
  }

  /**
   * Returns the specific Bead.
   * @param x X coordinate of the Bead to be found. Should be an integer value between 0-3.
   * @param y Y coordinate of the Bead to be found. Should be an integer value between 0-3.
   * @param z Z coordinate of the Bead to be found. Should be an integer value between 0-3.
   * @return Returns a Beadlike indicating either a Bead or EmptyBead.
   */
  public Beadlike getBead(int x, int y, int z)
  {
    Beadlike bead = board.getGrid().getPeg(x,y).getBead(z);
    return bead;
  }

  /**
   * Adds a bead to the specified location by the current player.
   * @param x X coordinate of the Bead to be added. Should be an integer value between 0-3.
   * @param y Y coordinate of the Bead to be added. Should be an integer value between 0-3.
   */
  public void addBead(int x, int y)
  {
    currentPlayer.placeBead(board, x, y);
    view.update();
  }

  /**
   * Gets a Peg from the specified coordinates.
   * @param x X coordinate of the Peg to be found.
   * @param y Y coordinate of the Peg to be found.
   * @return The Peg at the specified coordinates.
   */
  public Peg getPeg(int x, int y)
  {
    Peg peg = board.getGrid().getPeg(x, y);
    return peg;
  }

  /**
   * Gets a two-dimensional array with the Pegs on the Board/Grid.
   * @return A two-dimensional Peg array.
   */
  public Peg[][] getAllPegs()
  {
    Peg[][] pegArray = board.getGrid().getAllPegs();
    return pegArray;
  }

  /**
   * Gets the white Player.
   * @return The white Player.
   */
  public Player getWhitePlayer()
  {
    return whitePlayer;
  }

  /**
   * Gets the black Player.
   * @return The black Player.
   */
  public Player getBlackPlayer()
  {
    return blackPlayer;
  }

  /**
   * Gets the current Player.
   * @return The current Player.
   */
  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }

  /**
   * Sets the current Player.
   * @param p The Player to be set as current.
   */
  public void setCurrentPlayer(Player p)
  {
    currentPlayer = p;
    gameState.setCurrentPlayer(p);
    view.update();
  }

  /**
   * Checks for a win.
   * @return A boolean indicating whether the game has been won.
   */
  public boolean checkWin()
  {
    return board.getGrid().getWinState();
  }
}