public class TestController implements IGameController
{
	private IModel model;
	private IView view;

	public TestController(IModel m)
	{
		this.model = m;
	}

	public void addBead()
	{
		model.addBead();
	}

	public void getBead() {}
	
	public int getNumBeads()
	{
		int numBeads = model.getNumBeads();
		return numBeads;
	}

	public void playerSetup() {}
	public void getMove() {}
	public void checkWin() {}
	public void nextTurn() {}

}