import java.util.ArrayList;

public class BoardTestViewer {

    Board board;

    public BoardTestViewer(Board b)
    {
        board = b;
    }

    public void showBoard()
    {
        Peg[][] pegs = board.getGrid().getAllPegs();
        for(Peg[] row: pegs)
        {
            for (Peg peg: row) {
            	Bead[] beads = peg.getAllBeads();
            	int maxNumber = beads.length;
                String[] XChange = {"A","B","C","D"};
                System.out.print(XChange[peg.getXCoord()] + peg.getYCoord() + ": ");
                for (Bead bead: beads)
                {   
                        if(bead.getColour()==Colour.WHITE)
                            System.out.print("W");
                        else
                            System.out.print("B");         
                }
                System.out.println("");
            }
        }
    }

}
