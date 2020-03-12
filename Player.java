public class Player
{
	Colour colour;

	public Player (Colour c)
	{
		colour = c;
	}

	public void placeBead(Board board, int x, int y)
	{
		boolean successful = board.getGrid().getPeg(x, y).addBead(colour);
		if (successful)
		{
			Debug.log("Successfully created bead.");
		}
		else
		{
			Debug.log("Bead could not be added.");
		}
	}

	public Colour getColour()
	{
		return colour;
	}
}