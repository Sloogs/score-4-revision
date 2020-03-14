import java.util.ArrayList;

public class Peg
{
	private final int MAX_BEADS = 4;
	private int xCoord = 0;
	private int yCoord = 0;
	private int numBeads = 0;
	private Beadlike[] beads = new Beadlike[4];

	public Peg(int x, int y)
	{
		Debug.log("Creating Peg at: (x: " + x + ", y: " + y +")");
		xCoord = x;
		yCoord = y;
		for (int i = 0; i < MAX_BEADS; i++)
		{
			beads[i] = new EmptyBead();
		}
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

	public boolean removeBead()
	{
		Debug.log("Attempting to remove bead.");
		boolean successful = false;
		if (numBeads > 0) {
			beads[numBeads - 1] = new EmptyBead();
			numBeads--;
			successful = true;
			Debug.log("Successfully removed bead.");
		}
		else
		{
			successful = false;
		}

		return successful;
	}

	public Beadlike getBead(int n)
	{
		Beadlike bead = beads[n];
		int x = bead.getXCoord();
		int y = bead.getYCoord();
		int z = bead.getZCoord();
		Debug.log("Getting bead at: (x: " + x + ", " + y + ")");
		return bead;
	}

	public Beadlike[] getAllBeads()
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