public class EmptyBead implements Beadlike
{
	private boolean isEmpty;
	private Colour colour;
	private int beadId;
	private int xCoord;
	private int yCoord;
	private int zCoord;

	public EmptyBead() {
		isEmpty = true;
		colour = Colour.EMPTY;
		beadId = -1;
		xCoord = -1;
		yCoord = -1;
		zCoord = -1;
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