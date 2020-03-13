public class TestModel implements IModel
{
	private IView view;
	private Board board;
	private Player whitePlayer;
	private Player blackPlayer;
	private Player currentPlayer;

	public TestModel(Board b, Player p1, Player p2, Player cp) {
		board = b;
		whitePlayer = p1;
		blackPlayer = p2;
		currentPlayer = cp;
	}

	public void setView(IView v)
	{
		this.view = v;
	}

	public Board getBoard()
	{
		return board;
	}

	public int getNumBeads()
	{
		int numBeads = board.getGrid().getPeg(1,1).getNumBeads();
		return numBeads;
	}

	public void addBead()
	{
		currentPlayer.placeBead(board, 1, 1);

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
}