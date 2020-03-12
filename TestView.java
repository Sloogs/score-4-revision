import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestView
{
	private Model model;
	private TestController controller;
	private JFrame frame;
	private JLabel label = new JLabel();

	public TestView(Model model)
	{
		this.model = model;
		this.controller = new TestController(model);

		frame = new JFrame();
		label.setText(Integer.toString(model.getNumBeads()));
		JPanel panel = new JPanel();
		JButton button = new JButton("Test");
		button.addActionListener(
			(ActionEvent ae)->{
				controller.addBead();
			});
		panel.add(button);
		panel.add(label);
		frame.add(panel);

		int width = 1000;
		int height = 1000;
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void updateAll()
	{
		System.out.println("Updating view");
		label.setText(Integer.toString(model.getNumBeads()));
		frame.repaint();
	}
}