public class TestController
{
	Model model;
	TestView view;

	public TestController(Model model)
	{
		this.model = model;
	}

	public void addBead()
	{
		model.addBead();
	}

	public void getNumBeads()
	{
		model.getNumBeads();
	}
}