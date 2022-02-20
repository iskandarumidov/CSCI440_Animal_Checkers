package com.wolfe.boardbuilding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Brandon Wolfe
 * @author Brandon O'Donnel ->accredited member functions bear his name
 * @class MainActivity.java
 * @brief This class comprises the game board setup, and play (both AI and
 *        human)
 * @pre game play type should be selected (P1 Vs. P2, AI Vs. P2, or P1 Vs. AI)
 * @post A message will be output to screen indicating a win
 * 
 *       Last modified Dec-8-2015
 */
public class MainActivity extends Activity {

	// used in minimax
	private AIboard current_board = new AIboard();
	private static int chosen_from;
	private static int chosen_to;

	// for board movement
	private int fromId;
	private int toId;

	// used to display current player's turn
	private TextView p1, p2;

	// keeps track of total piece count for a type of win
	private int player1pawnCount;
	private int player2pawnCount;

	// for game mechanics
	private boolean winner;
	private boolean clicked;

	// for multiplayer setup (p1 v p2, ai v p2, p1 v ai)
	private boolean myturn;// true = white pieces, false = black pieces
	private boolean P1vAI;
	private boolean AIvP2;
	private boolean AIturnOne;

	// buttons corresponding to grid spaces 7col x 9row grid
	// row1
	ImageButton a1, a2, a3, a4, a5, a6, a7;
	// row2
	ImageButton b1, b2, b3, b4, b5, b6, b7;
	// row3
	ImageButton c1, c2, c3, c4, c5, c6, c7;
	// row4
	ImageButton d1, d2, d3, d4, d5, d6, d7;
	// row5
	ImageButton e1, e2, e3, e4, e5, e6, e7;
	// row6
	ImageButton f1, f2, f3, f4, f5, f6, f7;
	// row7
	ImageButton g1, g2, g3, g4, g5, g6, g7;
	// row8
	ImageButton h1, h2, h3, h4, h5, h6, h7;
	// row9
	ImageButton i1, i2, i3, i4, i5, i6, i7;

	// Maintains the ids of the buttons in a one-to-one relationship with an index
	// value
	ImageButton imagebuttons[] = { a1, a2, a3, a4, a5, a6, a7,
			b1, b2, b3, b4, b5, b6, b7,
			c1, c2, c3, c4, c5, c6, c7,
			d1, d2, d3, d4, d5, d6, d7,
			e1, e2, e3, e4, e5, e6, e7,
			f1, f2, f3, f4, f5, f6, f7,
			g1, g2, g3, g4, g5, g6, g7,
			h1, h2, h3, h4, h5, h6, h7,
			i1, i2, i3, i4, i5, i6, i7 };

	// button ids
	int ids[] = { R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7,
			R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7,
			R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7,
			R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7,
			R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7,
			R.id.f1, R.id.f2, R.id.f3, R.id.f4, R.id.f5, R.id.f6, R.id.f7,
			R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6, R.id.g7,
			R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7,
			R.id.i1, R.id.i2, R.id.i3, R.id.i4, R.id.i5, R.id.i6, R.id.i7 };

	// image ids
	int drawableIds[] = { R.drawable.grasstile, R.drawable.whitemouse, R.drawable.grasstile, R.drawable.den,
			R.drawable.grasstile, R.drawable.whiteelephant, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.whitetiger, R.drawable.grasstile,
			R.drawable.whitewolf, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.grasstile, R.drawable.blackwolf, R.drawable.grasstile,
			R.drawable.blacktiger, R.drawable.grasstile, R.drawable.grasstile,
			R.drawable.grasstile, R.drawable.blackelephant, R.drawable.grasstile, R.drawable.den, R.drawable.grasstile,
			R.drawable.blackmouse, R.drawable.grasstile, };

	// player 1- board representation
	int player1pieces[] = { 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 2, 0, 3, 0, 0,
			0, 4, 0, 0, 0, 1, 0 };

	// player 2- board representation
	int player2pieces[] = { 0, 1, 0, 0, 0, 4, 0,
			0, 0, 3, 0, 2, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0 };

	/**
	 * onCreate(Bundle)
	 * 
	 * @brief Upon instantiation of MainActivity.java call onCreate
	 * @post variables are initialized, game type is selected, board is setup.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// for game play mechanics
		winner = false;
		clicked = false;

		// for type of game selection
		P1vAI = false;
		AIvP2 = false;
		AIturnOne = false;

		// four pieces on the board initially
		player1pawnCount = 4;
		player2pawnCount = 4;

		// check which game play option was selected (p1Vp2 aiVp2 p1Vai)
		Bundle extras = getIntent().getExtras();
		String user_choice = extras.getString("option");

		int myNum = Integer.parseInt(user_choice);

		if (user_choice != null) {
			if (myNum == 3) {// p1vai
				P1vAI = true;
				p1 = (TextView) findViewById(R.id.p1txt);
				p2 = (TextView) findViewById(R.id.p2txt);
				p1.setText("P1 V. AI-P2");
				p2.setVisibility(View.GONE);
			} else if (myNum == 2) {// aivp2
				AIvP2 = true;
				AIturnOne = true;
				p1 = (TextView) findViewById(R.id.p1txt);
				p2 = (TextView) findViewById(R.id.p2txt);
				p1.setText("AI-P1 V. P2");
				p2.setVisibility(View.GONE);
			} else {// p1vp2
				P1vAI = false;
				AIvP2 = false;
			}
		}

		if (AIvP2)// AI makes opening move
			AIOpeningBook();

		// draw the board and set the buttons to listen for a click
		setupBoard();

		if (AIvP2) {
			myturn = false;// human plays as white pieces(player 2)
			enableButtons(player2pieces);
		} else {
			myturn = true;
			enableButtons(player1pieces);
		}

	}

	/**
	 * AIOpeningBook()
	 * 
	 * @brief The AI to perform the games opening move. Allows for
	 *        extensibility in terms of not needin to tie the opening move to
	 *        minimax output.
	 * @pre AIvP2 is the selected game type
	 * @post The board will display a single move
	 * 
	 */
	public void AIOpeningBook() {

		int temp;

		// pass the board to AI_board for consistency
		current_board.update(player1pieces, player2pieces, AIvP2);

		// call minimax
		minimax(current_board, 0, 3, -9999, 9999, AIvP2);

		// minimax has set chosen_to and chosen_from globally
		toId = chosen_to;
		fromId = chosen_from;

		// keep continuity of the board
		temp = drawableIds[toId];
		drawableIds[toId] = drawableIds[fromId];
		drawableIds[fromId] = temp;

		// perform piece swap in index array
		player1pieces[toId] = player1pieces[fromId];
		player1pieces[fromId] = 0;
	}

	/**
	 * setupBoard()
	 * 
	 * @brief Used once, upon startup. Draws a new board to screen and handles the
	 *        logic behind button clicks.
	 *        First button selected represents piece moving from, second button in
	 *        the sequence represents selected piece move to spot.
	 * @pre imagebutton ids, drawable ids and player pieces must be defined first
	 * @post 7x9 grid of image buttons is displayed to screen. Game is ready to
	 *       play.
	 */
	public void setupBoard() {
		for (int i = 0; i < ids.length; i++) {

			imagebuttons[i] = (ImageButton) findViewById(ids[i]);
			imagebuttons[i].setImageResource(drawableIds[i]);
			imagebuttons[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					if (clicked) {// second button pressed in the sequence (to spot)
						toId = v.getId();
						if (toId == fromId) {// de-select piece
							clicked = false;

							if (myturn)
								enableButtons(player1pieces);
							else
								enableButtons(player2pieces);

							unhighlightPiece();
						} else if (isValid(fromId, toId)) {
							unhighlightPiece();
							makeMove();
						}

					} else {// first button pressed in the sequence (from spot)
						clicked = true;
						fromId = v.getId();
						highlightPiece();
						reEnableallforMove();
					}
				}
			});

		}
	}

	public void unhighlightPiece() {

		if (myturn) {

			switch (player1pieces[indexLookup(fromId)]) {
				case (1):
					drawableIds[indexLookup(fromId)] = R.drawable.blackmouse;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.blackmouse);
					break;
				case (2):
					drawableIds[indexLookup(fromId)] = R.drawable.blackwolf;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.blackwolf);
					break;
				case (3):
					drawableIds[indexLookup(fromId)] = R.drawable.blacktiger;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.blacktiger);
					break;
				case (4):
					drawableIds[indexLookup(fromId)] = R.drawable.blackelephant;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.blackelephant);
					break;
			}

		}

		else {
			switch (player2pieces[indexLookup(fromId)]) {
				case (1):
					drawableIds[indexLookup(fromId)] = R.drawable.whitemouse;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.whitemouse);
					break;
				case (2):
					drawableIds[indexLookup(fromId)] = R.drawable.whitewolf;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.whitewolf);
					break;
				case (3):
					drawableIds[indexLookup(fromId)] = R.drawable.whitetiger;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.whitetiger);
					break;
				case (4):
					drawableIds[indexLookup(fromId)] = R.drawable.whiteelephant;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.whiteelephant);
					break;
			}

		}

	}

	public void highlightPiece() {

		if (myturn) {

			switch (player1pieces[indexLookup(fromId)]) {
				case (1):
					drawableIds[indexLookup(fromId)] = R.drawable.hblackmouse;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hblackmouse);
					break;
				case (2):
					drawableIds[indexLookup(fromId)] = R.drawable.hblackwolf;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hblackwolf);
					break;
				case (3):
					drawableIds[indexLookup(fromId)] = R.drawable.hblacktiger;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hblacktiger);
					break;
				case (4):
					drawableIds[indexLookup(fromId)] = R.drawable.hblackelephant;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hblackelephant);
					break;
			}

		}

		else {
			switch (player2pieces[indexLookup(fromId)]) {
				case (1):
					drawableIds[indexLookup(fromId)] = R.drawable.hwhitemouse;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hwhitemouse);
					break;
				case (2):
					drawableIds[indexLookup(fromId)] = R.drawable.hwhitewolf;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hwhitewolf);
					break;
				case (3):
					drawableIds[indexLookup(fromId)] = R.drawable.hwhitetiger;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hwhitetiger);
					break;
				case (4):
					drawableIds[indexLookup(fromId)] = R.drawable.hwhiteelephant;
					imagebuttons[indexLookup(fromId)].setImageResource(R.drawable.hwhiteelephant);
					break;
			}

		}

	}

	/**
	 * makeMove()
	 * 
	 * @brief Performs the array swaps of both piece ids and drawable ids,
	 *        corrsponding to a single move.
	 * @post Human players move is updated. If playing against AI, there move is
	 *       updated as well. (2ply)
	 * @pre SetupBoard must be called.
	 */
	public void makeMove() {

		// prevent accidental clicks by human player
		disableButtons();

		// for to/from double button click
		clicked = false;

		// for variable swap
		int temp, oldSpot, newSpot;

		// lookup corresponding from and to indexes
		oldSpot = indexLookup(fromId);
		newSpot = indexLookup(toId);

		// redraw the button images /visual board updating
		ImageButton from = (ImageButton) findViewById(fromId);
		from.setImageResource(drawableIds[newSpot]);

		ImageButton to = (ImageButton) findViewById(toId);
		to.setImageResource(drawableIds[oldSpot]);

		temp = drawableIds[newSpot];
		drawableIds[newSpot] = drawableIds[oldSpot];
		drawableIds[oldSpot] = temp;

		// switch turns
		if (myturn) {// player 1

			// set next turn to player2
			myturn = false;

			// perform this turns piece movement
			temp = player1pieces[newSpot];
			player1pieces[newSpot] = player1pieces[oldSpot];
			player1pieces[oldSpot] = temp;

			// check if I win the game by taking an opponents empty den
			if (newSpot == 3)// permanent den index
				winner = true;

			// if moving to my den
			if (newSpot == 59)
				drawDenTo(oldSpot);

			// if moving from my den
			if (oldSpot == 59)
				drawDenFrom(oldSpot);

			// checking to see if player 2 is set as AI
			if (P1vAI) {

				// AI makes a move
				AIturn();

				// my turn again, unlock p1 pieces
				enableButtons(player1pieces);
				myturn = true;

			} else {// for human only mode

				// display current players turn
				p1 = (TextView) findViewById(R.id.p1txt);
				p2 = (TextView) findViewById(R.id.p2txt);
				p1.setVisibility(View.GONE);
				p2.setVisibility(View.VISIBLE);

				// unlock p2 pieces
				enableButtons(player2pieces);
			}
		} else {// if player 2
			myturn = true;

			temp = player2pieces[newSpot];
			player2pieces[newSpot] = player2pieces[oldSpot];
			player2pieces[oldSpot] = temp;

			// check for win game by taking an opponents empty den
			if (newSpot == 59)// permanent den index
				winner = true;

			// if moving to my den
			if (newSpot == 3)
				drawDenTo(oldSpot);

			// if moving from my den
			if (oldSpot == 3)
				drawDenFrom(oldSpot);

			// checking to see if player 1 is set as AI or not
			if (AIvP2) {
				AIturn();// AI makes a move

				// setup for my turn again
				enableButtons(player2pieces);
				myturn = false;
			} else {// for human v human play only

				// display current players turn
				p1 = (TextView) findViewById(R.id.p1txt);
				p2 = (TextView) findViewById(R.id.p2txt);
				p2.setVisibility(View.GONE);
				p1.setVisibility(View.VISIBLE);

				// unlock p1 pieces
				enableButtons(player1pieces);

			}
		}

		// check for a winner at end of each turn
		if (winner) {
			// output message to screen
			Toast toast = Toast.makeText(getApplicationContext(), "WINNER WINNER CHICKEN DINNER", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	/**
	 * drawDenTo(int)
	 * 
	 * @brief updates display for pawn on its own den
	 * @param from, the grid spot the pawn is moving from
	 * @post the den disappears, the animal icon takes its grid location
	 */
	public void drawDenTo(int from) {
		drawableIds[from] = R.drawable.grasstile;
		imagebuttons[from].setImageResource(R.drawable.grasstile);

	}

	/**
	 * drawDenFrom(int)
	 * 
	 * @brief updates display for pawn off its own den
	 * @param from, the grid spot the pawn is moving from
	 * @post the den reappears, the animal icon takes a new grid location
	 */
	public void drawDenFrom(int from) {
		drawableIds[from] = R.drawable.den;
		imagebuttons[from].setImageResource(R.drawable.den);

	}

	/**
	 * isValid(int, int)
	 * 
	 * @brief Identifies valid board moves for the human players.
	 *        Considers the four edges, the four corners, and all other grid spaces.
	 * @param fromID, selected pieces spot
	 * @param toID,   spot to move piece to.
	 * @return boolean, whether or not the desired move is indeed valid
	 */
	public boolean isValid(int fromID, int toID) {

		boolean retVal = false;
		int coming_from = indexLookup(fromID);
		int going_to = indexLookup(toID);

		switch (coming_from) {
			case (R.id.a2):// top edge space
			case (R.id.a3):
			case (R.id.a4):
			case (R.id.a5):
			case (R.id.a6): {
				if ((going_to == (coming_from + 1)) || (going_to == (coming_from + 7))
						|| (going_to == (coming_from - 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			case (R.id.i2):// bottom edge space
			case (R.id.i3):
			case (R.id.i4):
			case (R.id.i5):
			case (R.id.i6): {
				if ((going_to == (coming_from + 1)) || (going_to == (coming_from - 7))
						|| (going_to == (coming_from - 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;

					else
						retVal = false;
				}
				break;
			}
			case (R.id.b1):// left edge space
			case (R.id.c1):
			case (R.id.d1):
			case (R.id.e1):
			case (R.id.f1):
			case (R.id.g1):
			case (R.id.h1): {
				if ((going_to == (coming_from + 1)) || (going_to == (coming_from + 7))
						|| (going_to == (coming_from - 7))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			case (R.id.b7):// right edge space
			case (R.id.c7):
			case (R.id.d7):
			case (R.id.e7):
			case (R.id.f7):
			case (R.id.g7):
			case (R.id.h7): {
				if ((going_to == (coming_from - 7)) || (going_to == (coming_from + 7))
						|| (going_to == (coming_from - 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			case (R.id.a7):// top right corner
			{
				if ((going_to == (coming_from + 7)) || (going_to == (coming_from - 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			case (R.id.a1):// top left corner
			{
				if ((going_to == (coming_from + 7)) || (going_to == (coming_from + 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			case (R.id.i7):// bottom right corner
			{
				if ((going_to == (coming_from - 7)) || (going_to == (coming_from - 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			case (R.id.i1):// top right corner
			{
				if ((going_to == (coming_from - 7)) || (going_to == (coming_from + 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
			default:// any square not on the edge
			{
				if ((going_to == (coming_from - 1)) || (going_to == (coming_from - 7))
						|| (going_to == (coming_from + 7)) || (going_to == (coming_from + 1))) {
					if (!isOccupied(going_to, coming_from))
						retVal = true;
					else
						retVal = false;
				}
				break;
			}
		}

		return retVal;
	}

	/**
	 * takePawn(int)
	 * 
	 * @brief Remove an opponents pawn
	 * @param to, grid location where pawn is
	 * @pre validity of move must be checked
	 * @post Pawn to be taken will be removed and in its place a grass tile appears
	 */
	public void takePawn(int to) {

		// turns "to" location containing valid "take-able" opponent pawn into a grass
		// tile
		drawableIds[to] = R.drawable.grasstile;
		imagebuttons[to].setImageResource(R.drawable.grasstile);

		if (myturn) {// player one's the current player

			// deletes the piece from the array and decrements the opposing players total
			// pawn count
			player2pieces[to] = 0;
			player2pawnCount--;

			// check to see it the opposing players pawn count is zero. if so signal a win.
			if (player2pawnCount == 0)
				winner = true;

			// check if win by taking an opponent camping on the den
			if (to == 3)// permanent den index
				winner = true;

		} else {// player two

			// delete the piece in the opposing players array
			player1pieces[to] = 0;
			player1pawnCount--;

			// check to see it the opposing players pawn count is zero. if so signal a win.
			if (player1pawnCount == 0)
				winner = true;

			// check if win by taking an opponent camping on the den
			if (to == 59)// permanent den index
				winner = true;
		}
	}

	/**
	 * isOccupied(int, int)
	 * 
	 * @brief checks to see if the desired spot has a piece already.
	 *        If so, it will determine what is allowed to be done to that piece.
	 * @param to,   pawns desired location
	 * @param from, pawns current location
	 * @return true if the move is no good
	 */
	public boolean isOccupied(int to, int from) {

		boolean occupied = false;
		boolean occupiedByHim = false;

		int currPlayer[], opposPlayer[];

		if (myturn) {// player one's the current player
			currPlayer = player1pieces;
			opposPlayer = player2pieces;

		} else {
			currPlayer = player2pieces;
			opposPlayer = player1pieces;
		}

		// if it is occupied by my piece, then it is not a valid move
		if (currPlayer[to] > 0) {
			occupied = true;
		}

		// if his piece is there, check for attack validation
		if (opposPlayer[to] > 0) {
			occupiedByHim = attackValidity(to, from);

			// if a valid attack, remove his piece
			if (occupiedByHim)
				takePawn(to);

			occupied = !occupiedByHim;
		}

		// return true if the move is no good
		return occupied;
	}

	/**
	 * attackValididty(int,int)
	 * 
	 * @brief Not all pawns can take one other,
	 *        this confirms if the attack is a valid one.
	 * @param to,   pawns desired location in grid
	 * @param from, pawns current location in grid
	 * @pre board must be setup, a move must me made.
	 * @return true if the attack is valid
	 * 
	 */
	public boolean attackValidity(int to, int from) {
		boolean valid = false;
		int currPlayer[], opposPlayer[];

		if (myturn) {// player ones the current player
			currPlayer = player1pieces;
			opposPlayer = player2pieces;
		} else {
			currPlayer = player2pieces;
			opposPlayer = player1pieces;
		}

		// compare the current pawn at "from" with the opponents pawn at "to"
		switch (currPlayer[from]) {
			case (1):// we are the mouse
			{
				switch (opposPlayer[to]) {
					case (1):// they are also the mouse
					case (4): // they are the elephant
						return true;

					case (2):// opponent is the wolf
					case (3):// opponent is the tiger
						return false;

					default:
						return false;

				}
			}

			case (2):// we are the wolf
			{
				switch (opposPlayer[to]) {
					case (1):// opponent is mouse
					case (2):// opponent is also the wolf
					{
						return true;
					}
					case (3):// opponent is the tiger
					case (4):// opponent is the elephant
					{
						return false;
					}

					default:
						return false;
				}

			}
			case (3):// we are the tiger
			{
				switch (opposPlayer[to]) {
					case (1):// opponent is the mouse
					case (2):// opponent is the wolf
					case (3):// opponent is also the tiger
					{
						return true;
					}
					case (4):// opponent is the elephant
					{
						return false;
					}

					default:
						return false;
				}

			}
			case (4):// we are the elephant
			{
				switch (opposPlayer[to]) {
					case (1):// opponent is the mouse
					{
						return false;
					}
					case (2):// opponent is the wolf
					case (3):// opponent is also the tiger
					case (4):// opponent is the elephant
					{

						return true;

					}
					default:
						return false;
				}

			}

		}

		// if this is a valid attack return true, else return false
		return valid;
	}

	/**
	 * reEnableallforMove()
	 * 
	 * @brief unlocks all buttons, allows clicking
	 * @post all buttons are now clickable
	 */
	public void reEnableallforMove() {

		for (int i = 0; i < imagebuttons.length; i++) {
			imagebuttons[i].setClickable(true);
		}
	}

	/**
	 * disableButtons()
	 * 
	 * @brief lock all buttons
	 * @post player's buttons are now unclickable
	 */
	public void disableButtons() {

		for (int i = 0; i < imagebuttons.length; i++) {
			imagebuttons[i].setClickable(false);
		}
	}

	/**
	 * enableButtons(int)
	 * 
	 * @brief unlock the buttons belonging to a specific player
	 * @param plyrSpots, the players pieces
	 * @post player's buttons are now clickable
	 */
	public void enableButtons(int plyrSpots[]) {

		for (int i = 0; i < plyrSpots.length; i++) {
			if (plyrSpots[i] >= 1)// there is a piece belonging to the player here
				imagebuttons[i].setClickable(true);
			else
				imagebuttons[i].setClickable(false);
		}
	}

	/**
	 * indexLookup(int)
	 * 
	 * @brief takes an android id R.id.## and return the index
	 * @param Rid
	 * @return integer index corresponding to the id
	 */
	public int indexLookup(int Rid) {
		int returnIndex = -1;

		for (int i = 0; i < ids.length; i++)
			if (Rid == ids[i])
				returnIndex = i;

		return returnIndex;
	}

	/**
	 * AIturn()
	 * 
	 * @brief Performs the AI's turn.
	 *        Interface between the two code chunks.
	 *        Connects the board with minimax and SEF.
	 * @post The board display will be updated with a single turn.
	 */
	public void AIturn() {

		int temp;

		// pass the board to AI_board for update
		current_board.update(player1pieces, player2pieces, AIvP2);

		// call minimax
		minimax(current_board, 0, 7, -9999, 9999, AIvP2);

		// set minimax values globally for makeMove
		toId = chosen_to;
		fromId = chosen_from;

		if (myturn) {// if AI is player 1

			// check if we are taking opponents piece
			if (player2pieces[toId] > 0)
				takePawn(toId);

			// if the "to" spot index is the opponents den, trigger a win
			if (toId == 3)// permanent den index player 2
				winner = true;

			// redraw the board
			ImageButton from = (ImageButton) findViewById(ids[fromId]);
			from.setImageResource(drawableIds[toId]);

			ImageButton to = (ImageButton) findViewById(ids[toId]);
			to.setImageResource(drawableIds[fromId]);

			// keep global continuity of the board
			temp = drawableIds[toId];
			drawableIds[toId] = drawableIds[fromId];
			drawableIds[fromId] = temp;

			// if moving to my den
			if (toId == 59)// 59 is permanent den id for P1
				drawDenTo(fromId);

			// if moving from my den
			if (fromId == 59)
				drawDenFrom(fromId);

			// perform piece swap in index array
			player1pieces[toId] = player1pieces[fromId];
			player1pieces[fromId] = 0;

		} else {// AI is player2

			// check if we are taking opponents piece
			if (player1pieces[toId] > 0)
				takePawn(toId);

			// redraw the board
			ImageButton from = (ImageButton) findViewById(ids[fromId]);
			from.setImageResource(drawableIds[toId]);

			ImageButton to = (ImageButton) findViewById(ids[toId]);
			to.setImageResource(drawableIds[fromId]);

			// keep global continuity of the board
			temp = drawableIds[toId];
			drawableIds[toId] = drawableIds[fromId];
			drawableIds[fromId] = temp;

			// if the to spot index is the opponents den, trigger a win
			if (toId == 59)// permanent den index
				winner = true;

			// if moving to my den
			if (toId == 3)
				drawDenTo(fromId);

			// if moving from my den
			if (fromId == 3)
				drawDenFrom(fromId);

			// perform piece swap in index array
			player2pieces[toId] = player2pieces[fromId];
			player2pieces[fromId] = 0;
		}
	}

	/**
	 * minimax(AI_Board, int, int, int, int, boolean)
	 * 
	 * @author Brandon O'donnel
	 * @brief Recursive Minimax tree with AlphaBeta pruning
	 * @param current_board, AI's view of the board
	 * @param level,         current level (starts at 0 always)
	 * @param depth,         desired number of ply (8 is the upper limit on Android
	 *                       device)
	 * @param alpha,         for pruning max nodes
	 * @param beta,          for pruning min nodes
	 * @param AI_p1,         flag to be passed to SEF indicating play direction
	 * @pre board must be set
	 * @return used in recursion, indicated best choice at level
	 */
	public int minimax(AIboard currentBoard, int level, int depth, int alpha, int beta, boolean AI_P1) {

		int bestVal;
		int currBest;
		int moveTo = 0;
		int inPlayP1[] = new int[4];
		int inPlayP2[] = new int[4];
		boolean AIturn;

		// at depth run SEF
		if (level == depth) {
			return SEF(currentBoard, AI_P1);
		}
		// min node
		if ((level % 2) == 1) {
			AIturn = false;
			bestVal = 99999;

			// find valid moves
			for (int x = 0; x < 4; ++x) // for each piece
			{
				if (currentBoard.player2InPlay[x] != -1) // if the piece is in play
				{
					int moveFrom = currentBoard.player2InPlay[x];

					for (int d = 0; d < 5; ++d) // for each direction
					{
						switch (d) {
							case (0):
								moveTo = moveFrom + 7; // move down
								break;
							case (1):
								moveTo = moveFrom - 7; // move up
								break;
							case (2):
								moveTo = moveFrom + 1; // move right
								break;
							case (3):
								moveTo = moveFrom - 1; // move left
								break;
						}

						if (AIvalidMove(currentBoard, moveFrom, moveTo, AIturn)) {

							// save the values for in_play of this state
							for (int y = 0; y < 4; ++y) {
								inPlayP1[y] = currentBoard.player1InPlay[y];
								inPlayP2[y] = currentBoard.player2InPlay[y];
							}

							// make the move
							currentBoard.makeMove(moveFrom, moveTo);

							currBest = minimax(currentBoard, level + 1, depth, alpha, beta, AI_P1);

							// reveres the move
							currentBoard.setBoard(inPlayP1, inPlayP2);

							if (currBest < bestVal) {
								bestVal = currBest;

								// alpha beta pruning
								if (bestVal < beta)
									beta = bestVal;
								if (beta <= alpha)
									return bestVal;

							}

						}

					}

				}
			}
		} else { // max node

			AIturn = true;
			bestVal = -99999;

			// find valid moves
			for (int x = 0; x < 4; ++x) // for each piece
			{
				if (currentBoard.player1InPlay[x] != -1) // if the piece is in play
				{
					int moveFrom = currentBoard.player1InPlay[x];

					for (int d = 0; d < 4; ++d) // for each direction
					{
						switch (d) {
							case (0):
								moveTo = moveFrom + 7; // move down
								break;
							case (1):
								moveTo = moveFrom - 7; // move up
								break;
							case (2):
								moveTo = moveFrom + 1; // move right
								break;
							case (3):
								moveTo = moveFrom - 1; // move left
								break;
						}

						if (AIvalidMove(currentBoard, moveFrom, moveTo, AIturn)) {
							// save the values for in_play of this state
							for (int y = 0; y < 4; ++y) {
								inPlayP1[y] = currentBoard.player1InPlay[y];
								inPlayP2[y] = currentBoard.player2InPlay[y];
							}

							// make the move
							currentBoard.makeMove(moveFrom, moveTo);

							currBest = minimax(currentBoard, level + 1, depth, alpha, beta, AI_P1);

							// reveres the move
							currentBoard.setBoard(inPlayP1, inPlayP2);

							if (currBest > bestVal) {
								bestVal = currBest;

								// Set the choosen return values
								if (level == 0) {
									chosen_from = moveFrom;
									chosen_to = moveTo;
								}

								// alpha beta prunning
								if (bestVal > alpha)
									alpha = bestVal;
								if (beta <= alpha)
									return bestVal;

							}

						}

					}

				}
			}
		}

		return bestVal;
	}

	/**
	 * AIvalidMove(AI_board, int, int, boolean)
	 * 
	 * @author Brandon O'donnel
	 * @brief AI checks to see if it's desired move is valid.
	 * @param the_board, AI's version of the board
	 * @param from,      AI pawn's current position in grid
	 * @param to,        AI pawn's desired position in grid
	 * @param max_turn,  flag indicating AI turn (max nodes)
	 *                   @return, returns true if move is valid
	 */
	public boolean AIvalidMove(AIboard theBoard, int from, int to, boolean maxNodeTurn) {
		int enemy, friendly;

		if (to < 0 || to > 62) // off the board up or down
			return false;

		if ((from % 6) == 1 & (to % 5) == 1) // left to right move
			return false;

		if ((from % 5) == 1 & (to % 6) == 1) // right to left
			return false;

		if (maxNodeTurn) // if it is the AI's turn
		{
			// check for friendly pieces
			if (theBoard.AI_player1Pieces[to] > 0)
				return false;
			// check enemy pieces
			else {
				enemy = theBoard.AI_player2Pieces[to];
				friendly = theBoard.AI_player1Pieces[from];

				if (enemy == 0)
					return true;

				switch (friendly) {
					case (1): // mouse
						switch (enemy) {
							case (1): // mouse
								return true;
							case (2): // wolf
								return false;
							case (3): // tiger
								return false;
							case (4): // elephant
								return true;
						}
					case (2): // wolf
						switch (enemy) {
							case (1): // mouse
								return true;
							case (2): // wolf
								return true;
							case (3): // tiger
								return false;
							case (4): // elephant
								return false;
						}
					case (3): // tiger
						switch (enemy) {
							case (1): // mouse
								return true;
							case (2): // wolf
								return true;
							case (3): // tiger
								return true;
							case (4): // elephant
								return false;
						}

					case (4): // elephant
						switch (enemy) {
							case (1): // mouse
								return false;
							case (2): // wolf
								return true;
							case (3): // tiger
								return true;
							case (4): // elephant
								return true;
						}

				}
			}

		}

		else// if it is NOT the AI's turn
		{
			// check for friendly pieces
			if (theBoard.AI_player2Pieces[to] > 0)
				return false;
			// check enemy pieces
			else {
				enemy = theBoard.AI_player1Pieces[to];
				friendly = theBoard.AI_player2Pieces[from];

				if (enemy == 0)
					return true;

				switch (friendly) {
					case (1): // mouse
						switch (enemy) {
							case (1): // mouse
								return true;
							case (2): // wolf
								return false;
							case (3): // tiger
								return false;
							case (4): // elephant
								return true;
						}
					case (2): // wolf
						switch (enemy) {
							case (1): // mouse
								return true;
							case (2): // wolf
								return true;
							case (3): // tiger
								return false;
							case (4): // elephant
								return false;
						}
					case (3): // tiger
						switch (enemy) {
							case (1): // mouse
								return true;
							case (2): // wolf
								return true;
							case (3): // tiger
								return true;
							case (4): // elephant
								return false;
						}

					case (4): // elephant
						switch (enemy) {
							case (1): // mouse
								return false;
							case (2): // wolf
								return true;
							case (3): // tiger
								return true;
							case (4): // elephant
								return true;
						}

				}
			}

		}
		return false;
	}

	/**
	 * SEF(AI_board, boolean)
	 * 
	 * @brief Static Evaluation Function, evaluates a potential move
	 * @param game_board, copy of the current global game board
	 * @param AI_p1,      flag indicating if AI is player 1
	 * @return a measurement of the board indicating fitness of a single move.
	 *         Higher the better, 1000 max.
	 */
	public int SEF(AIboard gameBoard, boolean AI_p1) {
		int eval = 0;
		int temp[];

		// point value corresponding to grid position for player 1
		int p1ValGrid[] = { 1, 2, 3, 4, 3, 2, 1,
				2, 3, 4, 5, 4, 3, 2,
				3, 4, 5, 6, 5, 4, 3,
				4, 5, 6, 7, 6, 5, 4,
				5, 6, 7, 8, 7, 6, 5,
				6, 7, 8, 9, 8, 7, 6,
				7, 8, 9, 10, 9, 8, 7,
				8, 9, 10, 11, 10, 9, 8,
				9, 10, 11, 1000, 11, 10, 9 };

		// point value corresponding to grid position for player 2
		int p2ValGrid[] = { 9, 10, 11, 1000, 11, 10, 9,
				8, 9, 10, 11, 10, 9, 8,
				7, 8, 9, 10, 9, 8, 7,
				6, 7, 8, 9, 8, 7, 6,
				5, 6, 7, 8, 7, 6, 5,
				4, 5, 6, 7, 6, 5, 4,
				3, 4, 5, 6, 5, 4, 3,
				2, 3, 4, 5, 4, 3, 2,
				1, 2, 3, 4, 3, 2, 1 };

		// choose the appropriate evaluation depending on which player you are
		if (AI_p1) {
			temp = p1ValGrid;
			p1ValGrid = p2ValGrid;
			p2ValGrid = temp;
		}

		// evaluate the integer of each piece in play still (i.e. not -1)
		for (int x = 0; x < 4; ++x) {
			// for AI (max node calculation)
			// if there is a valid piece to take at a valid spot then this is likely
			// preferable to forward marching
			if (gameBoard.player1InPlay[x] != -1)
				eval = eval + p1ValGrid[gameBoard.player1InPlay[x]];

			// For player 2 (min node calculation)
			if (gameBoard.player2InPlay[x] != -1)
				eval = eval - p2ValGrid[gameBoard.player2InPlay[x]];
		}

		return eval;
	}
}
