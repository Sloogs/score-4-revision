import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JFrame;

public class Main
{
	private static Scanner input = new Scanner(System.in);
	private static String inputString;
	private static Board board = new Board();
	private static Player whitePlayer = new Player(Colour.WHITE);
	private static Player blackPlayer = new Player(Colour.BLACK);
	private static Player currentPlayer = whitePlayer;
	private static boolean testingMode = false;
	private static boolean running = true;
	private static boolean hasWon = false; 
	private static Move currentMove;

	public static void main(String[] args)
	{
		// Debug.enable(); // This enables debugging messages.
		System.out.println("Welcome to Score 4!");
		Debug.log("Initalizing game and creating elements.");

		while (running) {
			// GAME
			playerSetup();
			while (!hasWon) {
				boolean success = getMove();
				if (success)
				{
					playMove(currentMove);
				}
				showBoard();
				nextTurn();
				board.getGrid().checkWin();
			}

			// TESTING MODE
			if (testingMode)
			{
				if (inputString.startsWith("add")) {
					String pattern = "add (black|white) bead to ([A-D][1-4]).";
					Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(inputString);
					m.matches();

					System.out.println(m.group(1));
					System.out.println(m.group(2));
				}
				else if (inputString.startsWith("remove")) {
					String pattern = "remove (black|white) bead from ([A-D][1-4]).";
					Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(inputString);
					m.matches();

					System.out.println(m.group(1));
					System.out.println(m.group(2));
				}
				else if (inputString.contains("show board"))
				{
					// boardTestViewer.showBoard();
				}
				else if (inputString.contains("quit"))
				{
					running = false;
				}
			}
		}
	}

	public static void clearBoard(Board board,int rows, int cols){
		for(int i = 0; i < rows; i++){
			for(int j=0; j < cols; j++){
				board.getGrid().getPeg(i,j).emptyBeads();
			}
		}
	}

	public static void playerSetup()
	{
		System.out.println("First player will be: AI or Human?:");
		inputString = input.nextLine();
		if (inputString.equals("Human"))
		{
			whitePlayer = new Player(Colour.WHITE);
		}

		System.out.println("Second player will be: AI or Human?");
		inputString = input.nextLine();
		if (inputString.equals("Human"))
		{
			blackPlayer = new Player(Colour.BLACK);
		}
	}

	public static boolean getMove()
	{
		boolean result = false;
		int xCoord = 0;
		int yCoord = 0;
		System.out.println("Current turn is " + currentPlayer.getColour() + "'s turn.");
		System.out.println("Type a coordinate that you would like to place a bead at "
				+ "(.e.g. A1, B2; letters A-D and numbers 1-4 are valid.).");
		System.out.println("Enter " + currentPlayer.getColour() + "'s move:");
		inputString = input.nextLine();
		String pattern = "([A-D])([1-4])";
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(inputString);
		if (m.matches())
		{
			xCoord = Helper.translateABCDToNumber(m.group(1));
			yCoord = Integer.parseInt(m.group(2)) - 1;
			Debug.log("getMove() xCoord: " + xCoord);
			Debug.log("getMove() yCoord: " + yCoord);
			Move move = new Move(currentPlayer, xCoord, yCoord);
			currentMove = move;
			result = true;
		}
		else
		{
			System.out.println("That's not a valid coordinate, please try again");
		}

		Debug.log("Move coordinates: " + xCoord + ", " + yCoord);

		return result;
	}

	public static void playMove(Move m)
	{
		Player player = m.getPlayer();
		int xCoord = m.getXCoord();
		int yCoord = m.getYCoord();
		Debug.log("playMove() xCoord: " + xCoord);
		Debug.log("playMove() yCoord: " + yCoord);
		player.placeBead(board, xCoord, yCoord);
	}

	public static void showBoard()
	{
		Peg[][] pegs = board.getGrid().getAllPegs();
		String fourthRow = "";
		String thirdRow = "";
		String secondRow = "";
		String firstRow = "";
		String fourthRowBead = "";
		String thirdRowBead = "";
		String secondRowBead = "";
		String firstRowBead = "";

		for (Peg[] row: pegs)
		{
			// For every ROW, we need to have our string builders reset.

			fourthRow = "";
			thirdRow = "";
			secondRow = "";
			firstRow = "";

			// The following loop builds up a peg as such.
			//
			//     |
			//     B
			//     W
			//     B
			//
			// I had considered an easier method but unfortunately System.out.println builds
			// something top-down but we need it built bottom-up like so:
			//     
			//     B
			//     W
			//     B
			//     |
			//
			// Hopefully that makes sense!

			for (Peg peg: row)
			{

				// For every PEG, we need to have our string builders reset.

				fourthRowBead = "";
				thirdRowBead = "";
				secondRowBead = "";
				firstRowBead = "";
				int numBeads = peg.getNumBeads();

				if (numBeads == 4)
				{
					fourthRowBead = (peg.getBead(4).getColour() == Colour.WHITE) ? "W" : "B";
					thirdRowBead = (peg.getBead(3).getColour() == Colour.WHITE) ? "W" : "B";
					secondRowBead = (peg.getBead(2).getColour() == Colour.WHITE) ? "W" : "B";
					firstRowBead = (peg.getBead(1).getColour() == Colour.WHITE) ? "W" : "B";
				}
				else if (numBeads == 3)
				{
					fourthRowBead = "|";
					thirdRowBead = (peg.getBead(3).getColour() == Colour.WHITE) ? "W" : "B";
					secondRowBead = (peg.getBead(2).getColour() == Colour.WHITE) ? "W" : "B";
					firstRowBead = (peg.getBead(1).getColour() == Colour.WHITE) ? "W" : "B";
				}
				else if (numBeads == 2)
				{
					fourthRowBead = "|";
					thirdRowBead = "|";
					secondRowBead = (peg.getBead(2).getColour() == Colour.WHITE) ? "W" : "B";
					firstRowBead = (peg.getBead(1).getColour() == Colour.WHITE) ? "W" : "B";
				}
				else if (numBeads == 1)
				{
					fourthRowBead += "|";
					thirdRowBead += "|";
					secondRowBead += "|";
					firstRowBead += (peg.getBead(1).getColour() == Colour.WHITE) ? "W" : "B";
				}
				else if (numBeads == 0)
				{
					fourthRowBead += "|";
					thirdRowBead += "|";
					secondRowBead += "|";
					firstRowBead += "|";
				}

				fourthRow += fourthRowBead + "   ";
				thirdRow += thirdRowBead + "   ";
				secondRow += secondRowBead + "   ";
				firstRow += firstRowBead + "   ";
			}

			// Prints out a row of 1x4 pegs.
			//
			//     |   |   |   |
			//     |   |   |   |
			//     |   |   W   |
			//     |   B   B   |

			System.out.println("");
			System.out.println(fourthRow);
			System.out.println(thirdRow);
			System.out.println(secondRow);
			System.out.println(firstRow);
		}
	}

	public static void nextTurn()
	{
		if (currentPlayer.equals(whitePlayer))
		{
			currentPlayer = blackPlayer;
		}
		else if (currentPlayer.equals(blackPlayer))
		{
			currentPlayer = whitePlayer;
		}
	}
}