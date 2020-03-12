public class Model
{
	private TestView view;
	private Board board;
	private Player player1;
	private Player player2;

	public Model(Board b, Player p1, Player p2) {
		board = b;
		player1 = p1;
		player2 = p2;
	}

	public void setView(TestView v)
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
		player1.placeBead(board, 1, 1);
		updateView();
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public void updateView()
	{
		view.updateAll();
	}
}