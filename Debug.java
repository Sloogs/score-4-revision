public class Debug
{
  static private boolean debugMode = false;
  
  static public void log(String message)
  {
    if (debugMode) {
      System.out.println("DEBUG: " + message);
    }
  }

  static public void enable()
  {
    debugMode = true;
  }
}