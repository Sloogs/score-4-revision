import java.util.ArrayList;

public class Grid
{
  private final int ROWS = 4;
  private final int COLUMNS = 4;
  private final int HEIGHT = 4;
  private Peg[][] pegs = new Peg[ROWS][COLUMNS];
  private ArrayList<Line> lines;

  public Grid()
  {
    Debug.log("Creating Pegs in Grid.");
    for (int x = 0; x < ROWS; x++)
    {
      for (int y = 0; y < COLUMNS; y++) {
        pegs[x][y] = new Peg(x, y);
      }
    }

    Debug.log("Creating Lines in Grid");
    findAndListBeadLines();
  }

  public Peg getPeg(int x, int y)
  {
    return pegs[x][y];
  }

  public Peg[][] getAllPegs()
  {
    return pegs;
  }

  /**
   * This method finds and creates a list of lines from the already existing
   * Peg grid and Beads that were created by the Grid.
   *
   * It is used to keep a list of Lines for the checkWin() method.
   * 
   * Note that the Pegs have to be created first before this method can find
   * any Lines.
   */
  public void findAndListBeadLines()
  {
    Line line;
    lines = new ArrayList<Line>();
    Beadlike[] lineBuilder = new Beadlike[4];

    // Check all ROWS.
    for (int x = 0; x < ROWS; x++) {
      for (int z = 0; z < HEIGHT; z++) {
        for (int y = 0; y < COLUMNS; y++) {
          lineBuilder[y] =  getPeg(x, y).getBead(z);
        }
        line = new Line(lineBuilder);
        lines.add(line);
        lineBuilder = new Beadlike[4];
      }
    }

    // Check all COLUMNS.
    for (int y = 0; y < COLUMNS; y++) {
      for (int z = 0; z < HEIGHT; z++) {
        for (int x = 0; x < ROWS; x++) {
          lineBuilder[x] =  getPeg(x, y).getBead(z);
        }
        line = new Line(lineBuilder);
        lines.add(line);
        lineBuilder = new Beadlike[4];
      }
    }

    // Check for HEIGHT.
    for (int x = 0; x < ROWS; x++) {
      for (int y = 0; y < COLUMNS; y++) {
        for (int z = 0; z < HEIGHT; z++) {
          lineBuilder[z] =  getPeg(x, y).getBead(z);
        }
        line = new Line(lineBuilder);
        lines.add(line);
        lineBuilder = new Beadlike[4];
      }
    }

    // Check for DIAGONAL ROWS, bottom up.
    for (int x = 0; x < ROWS; x++) {
      for (int y = 0; y < COLUMNS; y++) {
        lineBuilder[y] =  getPeg(x, y).getBead(y);
      }
      line = new Line(lineBuilder);
      lines.add(line);
      lineBuilder = new Beadlike[4];
    }

    // Check for DIAGONAL ROWS, top down.
    for (int x = 0; x < ROWS; x++) {
      for (int y = 3, index = 0; y >= 0; y--, index++) {
        lineBuilder[index] =  getPeg(x, y).getBead(y);
      }
      line = new Line(lineBuilder);
      lines.add(line);
      lineBuilder = new Beadlike[4];
    }

    // Check for DIAGONAL COLUMNS, bottom up.
    for (int y = 0; y < COLUMNS; y++) {
      for (int x = 0; x < ROWS; x++) {
        lineBuilder[x] =  getPeg(x, y).getBead(x);
      }
      line = new Line(lineBuilder);
      lines.add(line);
      lineBuilder = new Beadlike[4];
    }

    // Check for DIAGONAL COLUMNS, top down.
    for (int y = 0; y < COLUMNS; y++) {
      for (int x = 3, index = 0; x >= 0; x--, index++) {
        lineBuilder[index] =  getPeg(x, y).getBead(x);
      }
      line = new Line(lineBuilder);
      lines.add(line);
      lineBuilder = new Beadlike[4];
    }

    // Check for FLAT DIAGONALS, NW to SE.
    for (int z = 0; z < HEIGHT; z++) {
      for (int x = 0; x < ROWS; x++) {
        lineBuilder[x] =  getPeg(x, x).getBead(z);
      }
      line = new Line(lineBuilder);
      lines.add(line);
      lineBuilder = new Beadlike[4];
    }

    // Check for FLAT DIAGONALS, SW to NE.
    for (int z = 0; z < HEIGHT; z++) {
      for (int x = 3, y = 0; x >= 0; x--, y++) {
        lineBuilder[y] =  getPeg(x, y).getBead(z);
      }
      line = new Line(lineBuilder);
      lines.add(line);
      lineBuilder = new Beadlike[4];
    }

    // Check for DIAGONAL DIAGONALS, NW to SE, bottom up.
    for (int x = 0; x < ROWS; x++) {
      lineBuilder[x] = getPeg(x, x).getBead(x);  
    }
    line = new Line(lineBuilder);
    lines.add(line);
    lineBuilder = new Beadlike[4];

    // Check for DIAGONAL DIAGONALS, NW to SE, top down.
    for (int x = 0, z = 3; x < ROWS && z >= 0; x++, z--) {
      lineBuilder[x] =  getPeg(x, x).getBead(z);
    }
    line = new Line(lineBuilder);
    lines.add(line);
    lineBuilder = new Beadlike[4];

    // Check for DIAGONAL DIAGONALS, SW to NE, bottom up.
    for (int x = 3, y = 0, z = 0; x >= 0 && y < COLUMNS; x--, y++, z++) {
      lineBuilder[z] =  getPeg(x, y).getBead(z);
    }
    line = new Line(lineBuilder);
    lines.add(line);
    lineBuilder = new Beadlike[4];

    // Check for DIAGONAL DIAGONALS, SW to NE, bottom up.
    for (int x = 3, y = 0, z = 3; x >= 0 && y < COLUMNS; x--, y++, z--) {
      lineBuilder[y] =  getPeg(x, y).getBead(z);
    }
    line = new Line(lineBuilder);
    lines.add(line);
    lineBuilder = new Beadlike[4];
  }

  public boolean getWinState()
  {
    findAndListBeadLines();
    boolean result = false;

    for (Line line: lines)
    {
      Beadlike[] beads = line.getBeads();
      int numBlack = 0;
      int numWhite = 0;
      for (Beadlike bead: beads) {
        if (bead.getColour() == PlayerColour.WHITE) {
          numWhite++;
        }

        if (bead.getColour() == PlayerColour.BLACK) {
          numBlack++;
        }
      }

      if (numWhite == 4 || numBlack == 4) {
        result = true;
      }
    }

    return result;
  }

  public ArrayList<Line> getLines()
  {
    findAndListBeadLines();
    return lines;
  }

}