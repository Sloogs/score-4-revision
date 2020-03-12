public interface IModel
{
	public Peg getPeg(int x, int y);
	public Peg[][] getAllPegs();
	public Bead getBead(int x, int y, int z);
	public void addBead();
	public int getNumBeads();
	public void setView(IView v);
}