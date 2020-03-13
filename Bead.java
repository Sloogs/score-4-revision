public class Bead implements Beadlike
{
	private static int numWhite;
	private static int numBlack;
	private Colour colour;
	private int beadId;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private boolean isEmpty;

	public Bead(Colour c, int x, int y, int z)
	{
		Debug.log("Creating bead at: (x: " + x + ", y: " + y +", z: " + z + ")");
		if (c == Colour.WHITE)
		{
			colour = Colour.WHITE;
			beadId = numWhite + 1;
			isEmpty = false;
			numWhite++;
		}
		else
		{
			colour = Colour.BLACK;
			beadId = numBlack + 1;
			isEmpty = false;
			numBlack++;
		}

		Debug.log("Number of white beads: " + numWhite);
		Debug.log("Number of black beads: " + numBlack);
		Debug.log("Colour: " + colour);
		Debug.log("Bead Id: " + beadId);
	}

	public Colour getColour()
	{
		return colour;
	}

	public int getId()
	{
		return beadId;
	}

	public int getXCoord()
	{
		return xCoord;
	}

	public int getYCoord()
	{
		return yCoord;
	}

	public int getZCoord()
	{
		return zCoord;
	}

	public boolean isEmpty()
	{
		return isEmpty;
	}
}