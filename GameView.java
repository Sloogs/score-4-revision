import javax.swing.border.*;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView
{
  final int width = 800;
  final int height = 400;
  final int PEG_ROWS = 4;
  final int PEG_COLUMNS = 4;
  private GameModel model;
  private GameController controller;
  private JFrame frame;
  JPanel leftPanel = new JPanel();
  JPanel rightPanel = new JPanel();
  JPanel upperRightPanel = new JPanel();
  JPanel lowerRightPanel = new JPanel();
  JPanel[][] pegPanel = new JPanel[PEG_ROWS][PEG_COLUMNS];
  JLabel statusLabel1 = new JLabel("");
  JLabel statusLabel2 = new JLabel("");
  JLabel statusLabel3 = new JLabel("");
  JLabel statusLabel4 = new JLabel("");
  JButton resetButton = new JButton("Reset");

  /**
   * Constructor for the GameView. We are using an MVC pattern so this will be attached to a
   * GameController and will get change updates from the GameModel.
   * @param m The GameModel to be associated with this GameView.
   */
  public GameView(GameModel m)
  {
    frame = new JFrame();
    this.model = m;
    frame.setLayout(new GridLayout(1, 2));

    // Using a 1x1 Grid Layout here because it stretches inner components conveniently.
    leftPanel.setLayout(new GridLayout(4, 4));
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    upperRightPanel.setLayout(new BoxLayout(upperRightPanel, BoxLayout.Y_AXIS));

    // Grid of Pegs

    pegPanel[0][0] = new JPanel();
    pegPanel[0][1] = new JPanel();
    pegPanel[0][2] = new JPanel();
    pegPanel[0][3] = new JPanel();

    pegPanel[1][0] = new JPanel();
    pegPanel[1][1] = new JPanel();
    pegPanel[1][2] = new JPanel();
    pegPanel[1][3] = new JPanel();

    pegPanel[2][0] = new JPanel();
    pegPanel[2][1] = new JPanel();
    pegPanel[2][2] = new JPanel();
    pegPanel[2][3] = new JPanel();

    pegPanel[3][0] = new JPanel();
    pegPanel[3][1] = new JPanel();
    pegPanel[3][2] = new JPanel();
    pegPanel[3][3] = new JPanel();

    // Left Side of Window (The Board)

    leftPanel.add(pegPanel[0][0]);
    leftPanel.add(pegPanel[0][1]);
    leftPanel.add(pegPanel[0][2]);
    leftPanel.add(pegPanel[0][3]);

    leftPanel.add(pegPanel[1][0]);
    leftPanel.add(pegPanel[1][1]);
    leftPanel.add(pegPanel[1][2]);
    leftPanel.add(pegPanel[1][3]);

    leftPanel.add(pegPanel[2][0]);
    leftPanel.add(pegPanel[2][1]);
    leftPanel.add(pegPanel[2][2]);
    leftPanel.add(pegPanel[2][3]);

    leftPanel.add(pegPanel[3][0]);
    leftPanel.add(pegPanel[3][1]);
    leftPanel.add(pegPanel[3][2]);
    leftPanel.add(pegPanel[3][3]);


    // Create the actual graphics inside the panels.
    for (int x = 0; x < PEG_ROWS; x++)
    {
      for (int y = 0; y < PEG_COLUMNS; y++) {
        // Using a 1x1 Grid Layout here because it stretches inner components conveniently.
        pegPanel[x][y].setLayout(new GridLayout(1, 1));
        pegPanel[x][y].add(new PegPanel(model.getPeg(x, y)));

        // Need to make these final because Java is extra cautious and wouldn't let me use the
        // regular x and y in the MouseEvent without making them final.
        final int x2 = x;
        final int y2 = y;
        pegPanel[x][y].addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              controller.getWhiteMove(x2, y2);
            }
          }
        );
      }
    }

    upperRightPanel.add(statusLabel1);
    upperRightPanel.add(statusLabel2);
    upperRightPanel.add(statusLabel3);
    upperRightPanel.add(statusLabel4);

    lowerRightPanel.add(resetButton);
    resetButton.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          controller.resetGame();
        }
      });
    resetButton.setVisible(false);

    rightPanel.add(upperRightPanel);
    rightPanel.add(lowerRightPanel);
    statusLabel1.setHorizontalAlignment(JLabel.CENTER);

    frame.add(leftPanel);
    frame.add(rightPanel);

    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Sets the GameController that this GameView will be associated with.
   * @param c The controller to attach to the GameView.
   */
  public void setController(GameController c)
  {
    controller = c;
  }

  /**
   * Repaints the view. This is mainly only called from the GameModel to tell this GameView that the
   * model has been updated.
   */
  public void update()
  {
    frame.repaint();
  }

  /**
   * Gets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public JLabel getStatusLabel1()
  {
    return statusLabel1;
  }

  /**
   * Sets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public void setStatusLabel1(String s)
  {
    statusLabel1.setText(s);
  }

  /**
   * Gets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public JLabel getStatusLabel2()
  {
    return statusLabel2;
  }

  /**
   * Sets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public void setStatusLabel2(String s)
  {
    statusLabel2.setText(s);
  }

  /**
   * Gets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public JLabel getStatusLabel3()
  {
    return statusLabel3;
  }

  /**
   * Sets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public void setStatusLabel3(String s)
  {
    statusLabel3.setText(s);
  }

  /**
   * Gets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public JLabel getStatusLabel4()
  {
    return statusLabel4;
  }

  /**
   * Sets a status label so that it can be manipulated by the controller.
   * @return The status label  we wish to manipulate.
   */
  public void setStatusLabel4(String s)
  {
    statusLabel4.setText(s);
  }

  /**
   * Gets the reset button so that it can be manipulated by the controller.
   * @return The reset button which we wish to manipulate.
   */
  public JButton getResetButton()
  {
    return resetButton;
  }

  /**
   * This class is used internally to draw our pegs based on the state of our Board, Grid, Pegs,
   * and Beads.
   */
  private class PegPanel extends JPanel {
    private final int PEG_WIDTH = 5;
    private final int PEG_HEIGHT = 60;
    private final double BEAD_DIAMETER = 15.0;
    private Rectangle pegRect;
    private Ellipse2D.Double[] beadGraphic = new Ellipse2D.Double[4];
    private int numBeads;
    private Peg pegModel;

    /**
     * Constructs a PegPamel which controls the grapgics logic has our pegs and beads.
     * @param peg The Peg to which this panel will be attached.
     */
    public PegPanel(Peg peg)
    {
      pegModel = peg;

      pegRect = new Rectangle(PEG_WIDTH, PEG_HEIGHT);

      beadGraphic[0] = new Ellipse2D.Double(
          -PEG_WIDTH,
          PEG_HEIGHT - BEAD_DIAMETER,
          BEAD_DIAMETER,
          BEAD_DIAMETER);
      beadGraphic[1] = new Ellipse2D.Double(
          -PEG_WIDTH,
          PEG_HEIGHT - BEAD_DIAMETER * 2,
          BEAD_DIAMETER,
          BEAD_DIAMETER);
      beadGraphic[2] = new Ellipse2D.Double(
          -PEG_WIDTH,
          PEG_HEIGHT - BEAD_DIAMETER * 3,
          BEAD_DIAMETER,
          BEAD_DIAMETER);
      beadGraphic[3] = new Ellipse2D.Double(
          -PEG_WIDTH,
          PEG_HEIGHT - BEAD_DIAMETER * 4,
          BEAD_DIAMETER,
          BEAD_DIAMETER);
    }

    /**
     * Paints our component. Required by the Java AWT/Swing libraries to draw graphics.
     * @param g A AWT/Swing Graphics object.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
      Dimension parentSize = this.getParent().getSize();
      Double height = parentSize.getHeight();
      Double width = parentSize.getWidth();
      Double center = width / 2;

      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLACK);
      g2.translate(center - (PEG_WIDTH / 2), 0);

      g2.draw(pegRect);

      Beadlike[] beads = pegModel.getAllBeads();
      int i = 0;
      for (Beadlike bead: beads) {
        if (!bead.isEmpty())
        {
          Color colour = (bead.getColour() == PlayerColour.WHITE) ? Color.WHITE : Color.BLACK;
          g2.setColor(Color.BLACK);
          g2.draw(beadGraphic[i]);
          g2.setColor(colour);
          g2.fill(beadGraphic[i]);
          i++;
        }
      }
    }
  }
}