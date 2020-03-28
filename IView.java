import javax.swing.JLabel;

public interface IView
{  
  public JLabel getStatusLabel();
  public void setStatusLabel(String s);
  public void update();
}