import java.util.ArrayList;
import java.security.SecureRandom;

public class GameController
{
  private GameModel model;
  private GameView view;

  public GameController(GameModel m)
  {
    this.model = m;
  }

  /**
   * Sets the GameView that this GameController is associated with. 
   * @param v The view to attach to this GameController.
   */
  public void setView(GameView v)
  {
    view = v;
  }

  /**
   * Initializes the game.
   */
  public void startGame() {
    GameState gamestate = model.getGameState();
    Stage stage = gamestate.getStage();
    Player whitePlayer = gamestate.getWhitePlayer();
    Player blackPlayer = gamestate.getBlackPlayer();
    gamestate.setCurrentPlayer(whitePlayer);
    gamestate.setStage(Stage.GAME_START);
    view.getStatusLabel1().setText("Welcome to Score 4!");
    view.getStatusLabel2().setText("You will play against an AI opponent.");
    view.getStatusLabel3().setText("Please select a peg.");
    advanceRound();
  }

  /**
   * Advances the round to the next stage.
   */
  public void advanceRound()
  {
    GameState gamestate = model.getGameState();
    Stage stage = gamestate.getStage();
    Player whitePlayer = gamestate.getWhitePlayer();
    Player blackPlayer = gamestate.getBlackPlayer();

    if (stage == Stage.GAME_START) {
      gamestate.setStage(Stage.GET_WHITE_MOVE);
      gamestate.setCurrentPlayer(whitePlayer);
    } else if (!model.checkWin() && stage == Stage.GET_WHITE_MOVE) {
      gamestate.setStage(Stage.GET_BLACK_MOVE);
      model.setCurrentPlayer(blackPlayer);
      getBlackMove();
    } else if (!model.checkWin() && stage == Stage.GET_BLACK_MOVE) {
      gamestate.setStage(Stage.GET_WHITE_MOVE);
      model.setCurrentPlayer(whitePlayer);
    }

    if (model.checkWin()) {
      showWinMessage();
      showResetButton();
    }
  }

  /**
   * Gets the move that the white (human) player wishes to play.
   * @param x The x coordinate that the white player wishes to play.
   * @param y The y coordinate that the white player wishes to play.
   */
  public void getWhiteMove(int x, int y)
  {
    GameState gamestate = model.getGameState();
    Stage stage = model.getGameState().getStage();
    if (stage == Stage.GET_WHITE_MOVE) {
      model.addBead(x, y);
      advanceRound();
      // view.getStatusLabel3().setText("Your last move is: (" + x + ", " + y + ")");
    }
  }

  /**
   * Gets the move that the white (AI) player wishes to play.
   * @param x The x coordinate that the white player wishes to play.
   * @param y The y coordinate that the white player wishes to play.
   */
  public void getBlackMove()
  {
    GameState gamestate = model.getGameState();
    Player blackPlayer = gamestate.getBlackPlayer();
    Move move;

    move = blackPlayer.getMove(gamestate);
    model.addBead(move.getXCoord(), move.getYCoord());
    advanceRound();
  }

  /**
   * Displays a win message in the status area in the View.
   */
  public void showWinMessage()
  {
    GameState gamestate = model.getGameState();
    Player whitePlayer = gamestate.getWhitePlayer();
    Player blackPlayer = gamestate.getBlackPlayer();

    if (model.getCurrentPlayer().equals(whitePlayer)) {
      view.getStatusLabel1().setText("CONGRULATIONS!");
      view.getStatusLabel2().setText("WHITE WINS");
      view.getStatusLabel3().setText("");
      view.getStatusLabel4().setText("");
    } else if (model.getCurrentPlayer().equals(blackPlayer)) {
      view.getStatusLabel1().setText("SORRY");
      view.getStatusLabel2().setText("BLACK WINS");
      view.getStatusLabel3().setText("");
      view.getStatusLabel4().setText("");
    }
  }

  /**
   * Displays the reset button in the status area in the View.
   */
  public void showResetButton()
  {
    view.getResetButton().setVisible(true);
  }

  /**
   * Hides the reset button in the status area in the View.
   */
  public void hideResetButton()
  {
    view.getResetButton().setVisible(false);
  }

  /**
   * Resets the game.
   */
  public void resetGame()
  {
    GameState gamestate = model.getGameState();
    gamestate.getBoard().clearBoard();
    startGame();
    view.update();
  }
}