import java.util.ArrayList;

public class Grid
{
	private final int ROWS = 4;
	private final int COLUMNS = 4;
	private Peg[][] pegs = new Peg[ROWS][COLUMNS];
	private Bead[] line = new Bead[4];

	public Grid()
	{
		Debug.log("Creating Grid.");
		for (int x = 0; x < ROWS; x++)
		{
			for (int y = 0; y < COLUMNS; y++) {
				pegs[x][y] = new Peg(x, y);
			}
		}
	}

	public Peg getPeg(int x, int y)
	{
		return pegs[x][y];
	}

	public Peg[][] getAllPegs()
	{
		return pegs;
	}

	public void checkWin()
	{
		// Check all rows.
		Bead[] line = new Bead[4];
		ArrayList<Bead[]> lineList = new ArrayList<Bead[]>();
		line[0] = getPeg(0, 0).getBead(1);
		line[1] = getPeg(0, 0).getBead(2);
		line[2] = getPeg(0, 0).getBead(3);
		line[3] = getPeg(0, 0).getBead(4);

		lineList.add(line);
		for (Bead[] lineListLine: lineList)
		{
			for (Bead bead: lineListLine)
			{
				Debug.log("" + bead.getColour());
			}
		}
	}
}