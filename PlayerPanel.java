/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *makes a panel of buttons
 * @author blybr
 */
public class PlayerPanel extends JPanel
{
	private JButton[][] button = new JButton[4][4];
	private ActionListener[][] clicks = new ClickListener[4][4];
	private BoardComponent graphicsBoard;
 
	/**
	 * makes buttons in appropriate order to match grid
	 * @param b 
	 */
	public PlayerPanel(BoardComponent b)
	{
		this.setLayout(new GridLayout(4,4));

		for (int i = 4; i > 0; i--)
		{
			for(int j = 1; j < 5; j++)
			{
				String name= Integer.toString(j) + "-" + Integer.toString(i);
				button[j - 1][i - 1] = new JButton(name);
				add(button[j - 1][i - 1]);
			}
		}

 		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{ 
				clicks[i][j] = new ClickListener((i + 1) * 75, 400 - (j * 75));
				button[i][j].addActionListener(clicks[i][j]);
			}
		}
		
		graphicsBoard = b;
	}

	/**
	 * Listeners to cause circles to appear on frame
	 */
	public class ClickListener implements ActionListener {
		private int n=1;
		private final int xCoord;
		private final int yCoord;
		private boolean colour;

		public ClickListener(int x, int y)
		{
			xCoord=x;
			yCoord=y;
	 	}
	 
		@Override
		// makes beads with coordinates
		public void actionPerformed(ActionEvent e)
		{ 
			graphicsBoard.getPeg(xCoord, yCoord).addBead(); // allows bead to be shown on peg
			graphicsBoard.paintAgain(); // repaints beads that have been added
		}
	}
}
