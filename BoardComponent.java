/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author blybr
 */
public class BoardComponent extends JComponent
{
	private PegGraphic[][] pegArray;

	/**
	 * makes a visual for the game board
	 */
	public BoardComponent()
	{
		pegArray= new PegGraphic[4][4]; 
		for (int i = 1; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				pegArray[i - 1][j] = new PegGraphic(i * 75, 400 - (j * 75));
			}
		}
	}

/**
 * paints current state of peg array
 * @param g 
 */
	@Override
	public void paintComponent(Graphics g)
	{  
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 1; i < 5; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				g2.setColor(Color.BLACK);
				pegArray[i - 1][j].draw(g2);
			}
		}
	}
  
  /**
   * gets the correct peg for each actionListener
   * @param x
   * @param y
   * @return 
   */
	public PegGraphic getPeg(int x, int y)
	{
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(x==pegArray[i][j].getX() && y==pegArray[i][j].getY())
				{
					return pegArray[i][j];
				}
			}
		}

		return null; // should never return null
	}

   /**
	* made to refresh only the BoardComponent
	*/
	public void paintAgain()
	{
		repaint();
	}
}


