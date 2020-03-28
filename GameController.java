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

  public void setView(GameView v)
  {
    view = v;
  }

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

  public void getBlackMove()
  {
    GameState gamestate = model.getGameState();
    Player blackPlayer = gamestate.getBlackPlayer();
    Move move;

    move = blackPlayer.getMove(gamestate);
    model.addBead(move.getXCoord(), move.getYCoord());
    advanceRound();
  }

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

  public void showResetButton()
  {
    view.getResetButton().setVisible(true);
  }

  public void hideResetButton()
  {
    view.getResetButton().setVisible(false);
  }

  public void resetGame()
  {
    GameState gamestate = model.getGameState();
    gamestate.getBoard().clearBoard();
    startGame();
    view.update();
  }

  public void nextTurn() {}
}