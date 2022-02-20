package com.wolfe.boardbuilding;

/**
 * 
 * @author Brandon O'Donnel
 * @class AIboard.java
 * @brief This is the AI's data structure which represents 
 * the current game board.
 * @pre Game board should be built and running
 * @post The AI's array will be identical to the game boards 
 * 
 * Last modified Dec-8-2015
 */
public class AIboard {
    public int AI_player1Pieces[]={0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,};
	
	public int AI_player2Pieces[]={0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,
			    0,0,0,0,0,0,0,};
		
	
	//shows the location of each piece.  -1 means the piece is out of play
	public int player1InPlay[];

	public int player2InPlay[]; 
	
	
				

	/**
	 * AIboard()
	 * @brief constructor, initializes empty boards
	 * 
	 */
	public AIboard()
	{
	
		//set player ones pieces
		AI_player1Pieces[1] = 1;
		AI_player1Pieces[5] = 4;
		AI_player1Pieces[9] = 3;
		AI_player1Pieces[11] = 2;
		
		//set player twos pieces
		AI_player2Pieces[51] = 2;
		AI_player2Pieces[53] = 3;
		AI_player2Pieces[57] = 4;
		AI_player2Pieces[61] = 1;
		
		//set in play pieces
		player1InPlay = new int[]{1,11,9,5};
		player2InPlay = new int[]{61,51,53,57};
		
	}
	/**
	 * setBoard(int[],int[])
	 * @brief set the pieces of the AI's board
	 *
	 */
	public void setBoard(int[] p1, int[] p2)
	{
		for(int x = 0; x < 4; ++x)
		{
			if(player1InPlay[x] != -1)
			{
				AI_player1Pieces[player1InPlay[x]] = 0;
			}
			
			player1InPlay[x] = p1[x];
			
			if(player2InPlay[x] != -1)
			{
				AI_player2Pieces[player2InPlay[x]] = 0;
			}
			
			player2InPlay[x] = p2[x];
		}
		
		for(int x = 0; x < 4; ++x)
		{
			if(player1InPlay[x] != -1)
			{
				AI_player1Pieces[player1InPlay[x]] = x + 1;
			}
			
			if(player2InPlay[x] != -1)
			{
				AI_player2Pieces[player2InPlay[x]] = x + 1;
			}
			
		}
	}

	/**
	 * update(int, int, boolean)
	 * @brief Maintains consistency between AI's board and the Main Board
	 * @post AI has updated its player boards
	 * @param p1r1[] players pieces
	 * @param p2r2[]
	 * @param isAIp1r1 check AI's player spot
	 */
	public void update(int plr1[], int plr2[], boolean isAIplr1){
	
	int plyr1Pawns[] = new int[]{-1,-1,-1,-1};
	int plyr2Pawns[] = new int[]{-1,-1,-1,-1};
	
	//if the game is AI v Player2
	if(isAIplr1)
	{
		for(int x = 0; x < AI_player1Pieces.length; ++x)
		{
			switch (plr1[x])
			{
			case(0):
				break;
			case(1):
				plyr1Pawns[0] = x;
				break; 
			case(2):
				plyr1Pawns[1] = x;
				break;
			case(3):
				plyr1Pawns[2] = x;
				break;
			case(4):
				plyr1Pawns[3] = x;
				break;
			}
			
		}
		
		for(int x = 0; x < AI_player2Pieces.length; ++x)
		{
			switch (plr2[x])
			{
			case(0):
				break;
			case(1):
				plyr2Pawns[0] = x;
				break; 
			case(2):
				plyr2Pawns[1] = x;
				break;
			case(3):
				plyr2Pawns[2] = x;
				break;
			case(4):
				plyr2Pawns[3] = x;
				break;
			}
			
		}
		
	}
	
	else		//if the game is Player1 VS AI
	{
		for(int x = 0; x < AI_player1Pieces.length; ++x)
		{
			switch (plr2[x])
			{
			case(0):
				break;
			case(1):
				plyr1Pawns[0] = x;
				break; 
			case(2):
				plyr1Pawns[1] = x;
				break;
			case(3):
				plyr1Pawns[2] = x;
				break;
			case(4):
				plyr1Pawns[3] = x;
				break;
			}
			
		}
		
		for(int x = 0; x < AI_player2Pieces.length; ++x)
		{
			switch (plr1[x])
			{
			case(0):
				break;
			case(1):
				plyr2Pawns[0] = x;
				break; 
			case(2):
				plyr2Pawns[1] = x;
				break;
			case(3):
				plyr2Pawns[2] = x;
				break;
			case(4):
				plyr2Pawns[3] = x;
				break;
			}
			
		}
	}
	
	this.setBoard(plyr1Pawns, plyr2Pawns);
	
	}

	
	/**
	 * AIboard(AIboard)
	 * @brief overloaded constructor used by SEF and minimax
	 * @param AIboard 
	 */
	public AIboard(AIboard oldBoard)
	{
	//copy the pieces one at a time
	for(int x = 0; x < 63; ++x)
	{
	this.AI_player1Pieces[x] = oldBoard.AI_player1Pieces[x];
	this.AI_player2Pieces[x] = oldBoard.AI_player2Pieces[x];
	}
	
	//copy pieces in play
	for(int x = 0; x < 4; ++x)
	{
	this.player1InPlay[x] = oldBoard.player1InPlay[x];
	this.player1InPlay[x] = oldBoard.player1InPlay[x];
	}
	}
	
	
	/**
	 * makeMove(int, int)
	 * @brief Makes a move on the AI's board, for minimax
	 * @param from
	 * @param to
	 */
	public void makeMove(int from, int to)
	{
		int hold;
		
		//it is the AI that is moving  //player1 validity
		if(AI_player1Pieces[from] > 0)
		{
			hold = AI_player1Pieces[from] - 1;
			player1InPlay[hold] = to;
			
			AI_player1Pieces[to] = AI_player1Pieces[from];
			AI_player1Pieces[from] = 0;
			
			switch(AI_player2Pieces[to])
			{
				case(0):	//there is no enemy piece there
					break;
				case(1):	//enemey piece is removed
					AI_player2Pieces[to] = 0;
					player2InPlay[0] = -1;
					break;
				case(2):	//enemey piece is removed
					AI_player2Pieces[to] = 0;
					player2InPlay[1] = -1;
					break;
				case(3):	//enemey piece is removed
					AI_player2Pieces[to] = 0;
					player2InPlay[2] = -1;
					break;
				case(4):	//enemey piece is removed
					AI_player2Pieces[to] = 0;
					player2InPlay[3] = -1;
					break;
			}
			
			
		}
		
		//it is the player that is moving  //player two validity
		else
		{
			
			hold = AI_player2Pieces[from] - 1;
			player2InPlay[hold] = to;
			
			AI_player2Pieces[to] = AI_player2Pieces[from];
			AI_player2Pieces[from] = 0;
			
			switch(AI_player1Pieces[to])
			{
				case(0):	//there is no enemy piece there
					break;
				case(1):	//enemey piece is removed
					AI_player1Pieces[to] = 0;
					player1InPlay[0] = -1;
					break;
				case(2):	//enemey piece is removed
					AI_player1Pieces[to] = 0;
					player1InPlay[1] = -1;
					break;
				case(3):	//enemey piece is removed
					AI_player1Pieces[to] = 0;
					player1InPlay[2] = -1;
					break;
				case(4):	//enemey piece is removed
					AI_player1Pieces[to] = 0;
					player1InPlay[3] = -1;
					break;
			}
			
		}
	}
	
}
