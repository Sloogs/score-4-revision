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

	/**
	 * Gets colour.
	 * @return Returns the Colour of the bead.
	 */
	public Colour getColour()
	{
		return colour;
	}

	/**
	 * Gets bead ID.
	 * @return Returns the BeadId of the bead.
	 */
	public int getId()
	{
		return beadId;
	}

	/**
	 * Gets x coordinate (row).
	 * @return Returns an integer of the x coordinate of the bead.
	 */
	public int getXCoord()
	{
		return xCoord;
	}

	/**
	 * Gets y coordinate (column).
	 * @return Returns an integer of the y coordinate of the bead.
	 */
	public int getYCoord()
	{
		return yCoord;
	}

	/**
	 * Gets z coordnate (height).
	 * @return Returns an integer of the z coordinate of the bead.
	 */
	public int getZCoord()
	{
		return zCoord;
	}

	/**
	 * Gets whether the bead is empty or not.
	 * Only the EmptyBead class will have this true.
	 * @return Returns whether the bead in this position is empty or not.
	 */
	public boolean isEmpty()
	{
		return isEmpty;
	}
}