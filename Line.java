public class Line
{
  private Beadlike[] beads;

  /**
   * Constructor for a Line.
   * @param Line An Line, which represents an array of beadlikes (either a real Bead or an
   *             EmptyBead).
   */
  public Line(Beadlike[] line)
  {
    beads = line;
  }

  /**
   * Gets the array of Beadlikes either a real Bead or an EmptyBead) which is held within this Line.
   * @return An array of Beadlikes either a real Bead or an EmptyBead).
   */
  public Beadlike[] getBeads()
  {
    return beads;
  }
}