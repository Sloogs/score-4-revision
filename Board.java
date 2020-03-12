public class Board
{
	private Grid grid;

	public Board()
	{
		Debug.log("Creating Board");
		grid = new Grid();
	}

	public Grid getGrid()
	{
		return grid;
	}
}