public class GameState
{
	private Player whitePlayer = new Player(Colour.WHITE);
	private Player blackPlayer = new Player(Colour.BLACK);
	private Move currentMove;
	private Board board = new Board();
	private Player currentPlayer = whitePlayer;

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