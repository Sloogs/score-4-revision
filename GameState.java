public class GameState
{
  private Player whitePlayer;
  private Player blackPlayer;
  private Move currentMove;
  private Board board = new Board();
  private Player currentPlayer;
  private Stage stage;

  public GameState()
  {
    whitePlayer = new Player(PlayerColour.WHITE);
    blackPlayer = new Player(PlayerColour.BLACK);
    currentPlayer = whitePlayer;
    stage = Stage.GAME_START;
  }

  public Stage getStage()
  {
    return stage;
  }

  public void setStage(Stage s)
  {
    stage = s;
  }

  public Board getBoard()
  {
    return board;
  }

  public void setWhitePlayer(Player p)
  {
    whitePlayer = p;
  }

  public void setBlackPlayer(Player p)
  {
    blackPlayer = p;
  }

  public Player getWhitePlayer()
  {
    return whitePlayer;
  }

    public Player getBlackPlayer()
  {
    return blackPlayer;
  }

  public void setCurrentPlayer(Player p)
  {
    currentPlayer = p;
  }

  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }

  public void setCurrentMove(Move m)
  {
    currentMove = m;
  }

  public Move getCurrentMove()
  {
    return currentMove;
  }
}