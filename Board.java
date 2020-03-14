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

	public void clearBoard()
	{
		Peg[][] pegs = grid.getAllPegs();

		for (Peg[] row: pegs) {
			for (Peg peg: row) {
				int numBeads = peg.getNumBeads();
				for (int i = 0; i < numBeads; i++)
				{
					peg.removeBead();
				}
			}
		}
	}
}