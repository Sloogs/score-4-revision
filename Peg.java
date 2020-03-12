import java.util.ArrayList;

public class Peg
{
	private int xCoord = 0;
	private int yCoord = 0;
	private int numBeads = 0;
	private Bead[] beads = { null, null, null, null };

	public Peg(int x, int y)
	{
		Debug.log("Creating Peg at: (x: " + x + ", y: " + y +")");
		xCoord = x;
		yCoord = y;
	}

	public boolean addBead(Colour colour)
	{
		boolean successful = false;
		if (numBeads < 4)
		{
			beads[numBeads] = new Bead(colour, xCoord, yCoord, numBeads);
			numBeads++;
			successful = true;
		}
		else
		{
			successful = false;
		}

		return successful;
	}

	public boolean removeBead(Bead bead)
	{
		boolean successful = false;
		if (numBeads > 0) { 
			int x = bead.getXCoord();
			int y = bead.getYCoord();
			int z = bead.getZCoord();
			Debug.log("Removing bead at: (x: " + x + ", y: " + y + ", z: " + z);
			beads[numBeads - 1] = null;
			numBeads--;
			successful = true;
		}
		else
		{
			successful = false;
		}

		return successful;
	}

	public Bead getBead(int n)
	{
		Bead bead = beads[n - 1];
		int x = bead.getXCoord();
		int y = bead.getYCoord();
		int z = bead.getZCoord();
		Debug.log("Getting bead at: (x: " + x + ", " + y + ")");
		return bead;
	}

	public Bead[] getAllBeads()
	{
		Debug.log("Getting all beads from peg: (x: " + xCoord + ", y: " + yCoord + ")");
		return beads;
	}

	public int getXCoord() {
		return xCoord;
	}

	public int getYCoord() {
		return yCoord;
	}

	public int getNumBeads() {
		return numBeads;
	}

	public void emptyBeads(){
		for (int i = 0; i < beads.length; i++)
			beads[i] = null;
		
		numBeads = 0;
	}
}