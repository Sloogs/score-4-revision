class GameAI
{
	class Move 
{ 
    int row, col,bead; 
}; 
static char player = 'o', opponent = 'x'; 
	
	boolean isMovesLeft(char board[][][]) 
{ 
    for (int i = 0; i < 4; i++)
    { 
        for (int j = 0; j < 4; j++) 
        {
			for(int k=0;k<4;k++)
			{
				if (board[i][j][k] == '_'){ 
					//dis(board);
					return true; }
			}
		}
	}				
    return false; 
} 
  

int evaluate(char[][][] board)
	{
		
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(board[i][j][0]!= '_' && board[i][j][0]==board[i][j][1] && board[i][j][1]==board[i][j][2] && board[i][j][2]==board[i][j][3] )
				{
					//System.out.println(board[i][j][0]);
					if(board[i][j][0] == player)
					{
						return 10;
					}
					else if(board[i][j][0] == opponent)
					{
						return -10;
					}
				}
				
				
				else if(board[i][0][j]!= '_' && board[i][0][j]==board[i][1][j] && board[i][1][j]==board[i][2][j] && board[i][2][j]==board[i][3][j])
				{
					
					if(board[i][0][j] == player)
					{
						return 10;
					}
					else if(board[i][0][j] == opponent)
					{
						return -10;
					}
				}
				
				else if(board[0][i][j]!= '_' && board[0][i][j]==board[1][i][j] && board[1][i][j]==board[2][i][j] && board[2][i][j]==board[3][i][j])
				{
					
					if(board[0][i][j] == player)
					{
						return 10;
					}
					else if(board[0][i][j] == opponent)
					{
						return -10;
					}
				}
			} 
		}
		
		
		
		for(int i=0;i<4;i++)
		{
			
				if(board[i][0][0]!= '_' && board[i][0][0]==board[i][1][1] && board[i][1][1]==board[i][2][2] && board[i][2][2]==board[i][3][3])
				{
					//System.out.println(board[i][0][0]);
					//dis(board);
					
					if(board[i][0][0] == player)
					{
						return 10;
					}
					else if(board[i][0][0] == opponent)
					{
						return -10;
					}
				}
				
				else if(board[i][0][3] != '_' && board[i][0][3]==board[i][1][2] && board[i][1][2]==board[i][2][1] && board[i][2][1]==board[i][3][0])
				{
					
					if(board[i][0][3] == player)
					{
						return 10;
					}
					else if(board[i][0][3] == opponent)
					{
						return -10;
					}
				}
				
				
				
				else if(board[0][i][0]!= '_' && board[0][i][0]==board[1][i][1] && board[1][i][1]==board[2][i][2] && board[2][i][2]==board[3][i][3])
				{
					//System.out.println(board[0][i][0]);
					if(board[0][i][0] == player)
					{
						return 10;
					}
					else if(board[0][i][0] == opponent)
					{
						return -10;
					}
				}
				
				else if(board[0][i][3]!= '_' && board[0][i][3]==board[1][i][2] && board[1][i][2]==board[2][i][1] && board[2][i][1]==board[3][i][0])
				{
					
					if(board[0][i][3] == player)
					{
						return 10;
					}
					else if(board[0][i][3] == opponent)
					{
						return -10;
					}
				}

			
		}
		
		
		return 0;
	}

  
// This is the minimax function. It considers all 
// the possible ways the game can go and returns 
// the value of the board 



int minimax(char board[][][],int depth, boolean isMax) 
{ 
    int score = evaluate(board); 
    //dis(board);
	//System.out.println(score);
    // If Maximizer has won the game  
    // return his/her evaluated score 
    if (score == 10){ 
		//System.out.println(score);
        return score;}
         
  
    // If Minimizer has won the game  
    // return his/her evaluated score 
    else if (score == -10) {
        return score; }
  
    // If there are no more moves and  
    // no winner then it is a tie 
    else if (isMovesLeft(board) == false) 
        {return 0; }
  
    // If this maximizer's move 
    else if (isMax) 
    { 
        int best = -1000; 
  
        // Traverse all cells 
        for (int i = 0; i < 4; i++) 
        { 
            for (int j = 0; j < 4; j++) 
            { 
				for(int k=0;k<4;k++)
				{
                // Check if cell is empty 
                if (board[i][j][k]=='_') 
                { 
                    // Make the move 
                    board[i][j][k] = player; 
  
                    // Call minimax recursively and choose 
                    // the maximum value 
                    best = Math.max(best, minimax(board,  
                                    depth + 1, !isMax)); 
                    //System.out.println(best);
  
                    // Undo the move 
                    board[i][j][k] = '_'; 
                }
			} 
            } 
        } 
        return best; 
    } 
  
    // If this minimizer's move 
    else
    { 
        int best = 1000; 
  
        // Traverse all cells 
        for (int i = 0; i < 4; i++) 
        { 
            for (int j = 0; j < 4; j++) 
            { 
				for(int k=0;k<4;k++)
                // Check if cell is empty 
                if (board[i][j][k] == '_') 
                { 
                    // Make the move 
                    board[i][j][k] = opponent; 
  
                    // Call minimax recursively and choose 
                    // the minimum value 
                    best = Math.min(best, minimax(board,  
                                    depth + 1, !isMax)); 
  
                    // Undo the move 
                    board[i][j][k] = '_'; 
                } 
            } 
        } 
        return best; 
    } 
} 
  
// This will return the best possible 
// move for the player 
Move findBestMove(char board[][][]) 
{ 
	
    int bestVal = -1000; 
    Move bestMove = new Move(); 
    bestMove.row = -1; 
    bestMove.col = -1; 
    bestMove.bead = -1; 
    
  
    // Traverse all cells, evaluate minimax function  
    // for all empty cells. And return the cell  
    // with optimal value. 
    for (int i = 0; i < 4; i++) 
    { 
        for (int j = 0; j < 4; j++) 
        {  
			for(int k=0;k<4;k++)
			{
            // Check if cell is empty 
				if (board[i][j][k] == '_') 
				{ 
					// Make the move 
					board[i][j][k] = player; 
					dis(board);
					// compute evaluation function for this 
					// move. 
					int moveVal = minimax(board, 0, false); 
					System.out.println(moveVal);
	  
					// Undo the move 
					board[i][j][k] = '_'; 
	  
					// If the value of the current move is 
					// more than the best value, then update 
					// best/ 
					if (moveVal > bestVal) 
					{ 
						bestMove.row = i; 
						bestMove.col = j; 
						bestMove.bead = k; 
						
						bestVal = moveVal; 
					} 
				} 
			}
        } 
    } 
  
    System.out.printf("The value of the best Move " +  
                             "is : %d-%d-%d",bestMove.row,bestMove.col,bestMove.bead); 
  
    return bestMove; 
} 
	
	void dis(char board[][][])
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				for(int k=0;k<4;k++)
				{
					System.out.print(board[i][j][k]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	
}




public class AI
{

	public static void main(String arg[])
	{
		GameAI ai = new GameAI();
	 
	char[][][] board = new char[4][4][4];
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				for(int k=0;k<4;k++)
				{
					board[i][j][k] ='_';
					
				}
			}
		}
		
	
		board[0][3][3]='x';
		board[1][3][3]='x';
		board[2][3][3]='x';
		//board[3][3][3]='x';
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				for(int k=0;k<4;k++)
				{
					System.out.print(board[i][j][k]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		
	
		System.out.println(ai.findBestMove(board));
	
	}
	
}