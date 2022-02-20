import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class GameAnimal4 extends JFrame implements MouseListener {
	public int x = 0, y = 0, player1MoveCount = 0, player2MoveCount = 0, elephantclickCount = 0, wolfclickCount = 0,
			tigerclickCount = 0, mouseclickCount = 0, elephantclickCount1 = 0, wolfclickCount1 = 0,
			tigerclickCount1 = 0, mouseclickCount1 = 0;
	private int elephantMoveCount = 0, wolfMoveCount = 0, tigerMoveCount = 0, mouseMoveCount = 0,
			elephantMoveCount1 = 0, wolfMoveCount1 = 0, tigerMoveCount1 = 0, mouseMoveCount1 = 0;
	private JButton[][] jB1 = new JButton[8][10];
	private JButton exitButton = new JButton();
	private JTextArea player1MovesField = new JTextArea();
	private JTextArea player2MovesField = new JTextArea();
	private JTextArea p1elepMovesField = new JTextArea();
	private JTextArea p2elepMovesField = new JTextArea();
	private JTextArea p1wolfMovesField = new JTextArea();
	private JTextArea p2wolfMovesField = new JTextArea();
	private JTextArea p1tigMovesField = new JTextArea();
	private JTextArea p2tigMovesField = new JTextArea();
	private JTextArea p1mousMovesField = new JTextArea();
	private JTextArea p2mousMovesField = new JTextArea();
	private int[][] buttonClickCount = new int[8][10];
	private JTextArea commentBox = new JTextArea("Hi Welcome, Player1 has to make a move to start the game.");

	String names[] = { "a", "a", "b", "c", "d", "e", "f", "g", "h", "i" };

	public GameAnimal4() {
	}

	public void setImages() {
		try {
			Image elephantImage = ImageIO.read(getClass().getResource("images/Elephant.jpg"));
			jB1[2][9].setIcon(new ImageIcon(elephantImage));
			jB1[2][9].setName("elephantButton");
			Image wolfImage = ImageIO.read(getClass().getResource("images/Wolf.jpg"));
			jB1[3][8].setIcon(new ImageIcon(wolfImage));
			jB1[3][8].setName("wolfButton");
			Image tigerImage = ImageIO.read(getClass().getResource("images/Tiger.png"));
			jB1[5][8].setIcon(new ImageIcon(tigerImage));
			jB1[5][8].setName("tigerButton");
			Image mouseImage = ImageIO.read(getClass().getResource("images/Rat.jpeg"));
			jB1[6][9].setIcon(new ImageIcon(mouseImage));
			jB1[6][9].setName("mouseButton");
			Image denImage = ImageIO.read(getClass().getResource("images/Den.png"));
			jB1[4][9].setIcon(new ImageIcon(denImage));
			jB1[4][9].setName("denButton");
		} catch (IOException ex) {
		}
		try {
			Image elephantImage1 = ImageIO.read(getClass().getResource("images/BElephant.jpg"));
			jB1[6][1].setIcon(new ImageIcon(elephantImage1));
			jB1[6][1].setName("elephantButton1");
			Image wolfImage1 = ImageIO.read(getClass().getResource("images/BWolf.jpg"));
			jB1[5][2].setIcon(new ImageIcon(wolfImage1));
			jB1[5][2].setName("wolfButton1");
			Image tigerImage1 = ImageIO.read(getClass().getResource("images/BTiger.png"));
			jB1[3][2].setIcon(new ImageIcon(tigerImage1));
			jB1[3][2].setName("tigerButton1");
			Image mouseImage1 = ImageIO.read(getClass().getResource("images/BRat.jpeg"));
			jB1[2][1].setIcon(new ImageIcon(mouseImage1));
			jB1[2][1].setName("mouseButton1");
			Image denImage1 = ImageIO.read(getClass().getResource("images/BDen.png"));
			jB1[4][1].setIcon(new ImageIcon(denImage1));
			jB1[4][1].setName("denButton1");
		} catch (IOException ex) {
		}
	}

	public void moveElephant(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "mouseButton1" && player1MoveCount == player2MoveCount) {
			player1MoveCount++;
			System.out.println("elephant moved to " + newX + "," + newY);
			jB1[newX][newY].setName("elephantButton");
			try {
				Image elephantImage = ImageIO.read(getClass().getResource("images/Elephant.jpg"));
				jB1[newX][newY].setIcon(new ImageIcon(elephantImage));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			elephantMoveCount++;
		} else {
			commentBox.setText("It is player-2's turn.");
		}
		try {
			if (jB1[4][9].getName() != "elephantButton") {
				Image denImage = ImageIO.read(getClass().getResource("images/Den.png"));
				jB1[4][9].setIcon(new ImageIcon(denImage));
				jB1[4][9].setName("denButton");
			}
		} catch (IOException ex) {
		}
		p1elepMovesField.setText("Elephant moves:" + elephantMoveCount);
	}

	public void moveElephant1(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "mouseButton" && player1MoveCount > player2MoveCount) {
			player2MoveCount++;
			System.out.println("Black elephant moved to " + newX + "," + newY);
			jB1[newX][newY].setName("elephantButton1");
			try {
				Image elephantImage1 = ImageIO.read(getClass().getResource("images/BElephant.jpg"));
				jB1[newX][newY].setIcon(new ImageIcon(elephantImage1));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			elephantMoveCount1++;
		} else {
			commentBox.setText("It is player-1's turn.");
		}
		try {
			if (jB1[4][1].getName() != "elephantButton1") {
				Image denImage1 = ImageIO.read(getClass().getResource("images/BDen.png"));
				jB1[4][1].setIcon(new ImageIcon(denImage1));
				jB1[4][1].setName("denButton1");
			}
		} catch (IOException ex) {
		}
		p2elepMovesField.setText("Player2 elephant moves:" + elephantMoveCount1);
	}

	public void moveWolf(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "elephantButton1" && jB1[newX][newY].getName() != "tigerButton1"
				&& player1MoveCount == player2MoveCount) {
			player1MoveCount++;
			System.out.println("wolf moved to " + newX + "," + newY);
			jB1[newX][newY].setName("wolfButton");
			try {
				Image wolfImage = ImageIO.read(getClass().getResource("images/Wolf.jpg"));
				jB1[newX][newY].setIcon(new ImageIcon(wolfImage));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			wolfMoveCount++;
		} else {
			commentBox.setText("It is player-2's turn.");
		}
		try {
			if (jB1[4][9].getName() != "wolfButton") {
				Image denImage = ImageIO.read(getClass().getResource("images/Den.png"));
				jB1[4][9].setIcon(new ImageIcon(denImage));
				jB1[4][9].setName("denButton");
			}
		} catch (IOException ex) {
		}
	}

	public void moveWolf1(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "elephantButton" && jB1[newX][newY].getName() != "tigerButton"
				&& player1MoveCount > player2MoveCount) {
			player2MoveCount++;
			System.out.println("Black wolf moved to " + newX + "," + newY);
			jB1[newX][newY].setName("wolfButton1");
			try {
				Image wolfImage1 = ImageIO.read(getClass().getResource("images/BWolf.jpg"));
				jB1[newX][newY].setIcon(new ImageIcon(wolfImage1));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			wolfMoveCount1++;
		} else {
			commentBox.setText("It is player-1's turn.");
		}
		try {
			if (jB1[4][1].getName() != "wolfButton1") {
				Image denImage1 = ImageIO.read(getClass().getResource("images/BDen.png"));
				jB1[4][1].setIcon(new ImageIcon(denImage1));
				jB1[4][1].setName("denButton1");
			}
		} catch (IOException ex) {
		}
	}

	public void moveTiger(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "elephantButton1" && player1MoveCount == player2MoveCount) {
			player1MoveCount++;
			System.out.println("tiger moved to " + newX + "," + newY);
			jB1[newX][newY].setName("tigerButton");
			try {
				Image tigerImage = ImageIO.read(getClass().getResource("images/Tiger.png"));
				jB1[newX][newY].setIcon(new ImageIcon(tigerImage));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			tigerMoveCount++;
		} else {
			commentBox.setText("It is player-2's turn.");
		}
		try {
			if (jB1[4][9].getName() != "tigerButton") {
				Image denImage = ImageIO.read(getClass().getResource("images/Den.png"));
				jB1[4][9].setIcon(new ImageIcon(denImage));
				jB1[4][9].setName("denButton");
			}
		} catch (IOException ex) {
		}
	}

	public void moveTiger1(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "elephantButton" && player1MoveCount > player2MoveCount) {
			player2MoveCount++;
			System.out.println("Black tiger moved to " + newX + "," + newY);
			jB1[newX][newY].setName("tigerButton1");
			try {
				Image tigerImage1 = ImageIO.read(getClass().getResource("images/BTiger.png"));
				jB1[newX][newY].setIcon(new ImageIcon(tigerImage1));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			tigerMoveCount1++;
		} else {
			commentBox.setText("It is player-1's turn.");
		}
		try {
			if (jB1[4][1].getName() != "tigerButton1") {
				Image denImage1 = ImageIO.read(getClass().getResource("images/BDen.png"));
				jB1[4][1].setIcon(new ImageIcon(denImage1));
				jB1[4][1].setName("denButton1");
			}
		} catch (IOException ex) {
		}
	}

	public void moveMouse(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "wolfButton1" && jB1[newX][newY].getName() != "tigerButton1"
				&& player1MoveCount == player2MoveCount) {
			player1MoveCount++;
			System.out.println("mouse moved to " + newX + "," + newY);
			jB1[newX][newY].setName("mouseButton");
			try {
				Image mouseImage = ImageIO.read(getClass().getResource("images/Rat.jpeg"));
				jB1[newX][newY].setIcon(new ImageIcon(mouseImage));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			mouseMoveCount++;
		} else {
			commentBox.setText("It is player-2's turn.");
		}
		try {
			if (jB1[4][9].getName() != "mouseButton") {
				Image denImage = ImageIO.read(getClass().getResource("images/Den.png"));
				jB1[4][9].setIcon(new ImageIcon(denImage));
				jB1[4][9].setName("denButton");
			}
		} catch (IOException ex) {
		}
	}

	public void moveMouse1(int newX, int newY, int oldX, int oldY) {
		if (jB1[newX][newY].getName() != "wolfButton" && jB1[newX][newY].getName() != "tigerButton"
				&& player1MoveCount > player2MoveCount) {
			player2MoveCount++;
			System.out.println("Black mouse moved to " + newX + "," + newY);
			jB1[newX][newY].setName("mouseButton1");
			try {
				Image mouseImage1 = ImageIO.read(getClass().getResource("images/BRat.jpeg"));
				jB1[newX][newY].setIcon(new ImageIcon(mouseImage1));
			} catch (IOException ex) {
			}
			jB1[oldX][oldY].setName("Button" + oldX + "," + oldY);
			jB1[oldX][oldY].setIcon(null);
			System.out.println("Name Changed to " + jB1[oldX][oldY].getName());
			mouseMoveCount1++;
		} else {
			commentBox.setText("It is player-1's turn.");
		}
		try {
			if (jB1[4][1].getName() != "mouseButton1") {
				Image denImage1 = ImageIO.read(getClass().getResource("images/BDen.png"));
				jB1[4][1].setIcon(new ImageIcon(denImage1));
				jB1[4][1].setName("denButton1");
			}
		} catch (IOException ex) {
		}
	}

	private void implementGame(GameAnimal4 ga) {
		setTitle("Game Animal");
		setSize(1150, 900);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel jP1 = new JPanel();
		jP1.setLayout(null);
		jP1.setBounds(50, 50, 1400, 850);
		jP1.setVisible(true);
		jP1.setBackground(Color.GREEN);
		// Font font = new Font("Serif", Font.BOLD, 20);
		Border panelBorder = new LineBorder(Color.BLACK, 5, true);
		TitledBorder title = new TitledBorder(panelBorder, "Animals Checker", TitledBorder.CENTER, TitledBorder.TOP);
		title.setTitleJustification(TitledBorder.CENTER);
		jP1.setBorder(title);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				JButton jB2 = new JButton();
				jB2.setBounds(i * 70 + 70, j * 70 + 70, 70, 70);
				jB2.setBackground(Color.GREEN);
				jB2.setEnabled(true);
				jB2.setVisible(true);
				if (i == 0) {
					// jB2.setText(""+j);
					Border thickBorder = new LineBorder(Color.GREEN, 1);
					jB2.setBorder(thickBorder);
					if (j != 0) {
						jB2.setText(names[j]);
					}
				}
				if (j == 0) {
					Border thickBorder = new LineBorder(Color.GREEN, 1);
					jB2.setBorder(thickBorder);
					if (i != 0) {
						jB2.setText("" + i);
					}
				}
				jB1[i][j] = jB2;
				if (i == 2 && j == 9) {

				}
				jB1[i][j].addMouseListener(this);
				jP1.add(jB1[i][j]);
				jB1[i][j].setName("Button-" + i + "*" + j);
			}
		}
		jP1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 10; j++) {
						buttonClickCount[i][j] = 0;
					}
				}
				elephantclickCount = 0;
				wolfclickCount = 0;
				tigerclickCount = 0;
				mouseclickCount = 0;
				System.out.println("Button Count nullified");
				elephantclickCount1 = 0;
				wolfclickCount1 = 0;
				tigerclickCount1 = 0;
				mouseclickCount1 = 0;
				System.out.println("Button Count nullified");
			}

			public void mouseReleased(MouseEvent e) {

			}
		});
		commentBox.setBounds(750, 120, 300, 120);
		commentBox.setEditable(false);
		commentBox.setVisible(true);
		commentBox.setLineWrap(true);
		commentBox.setWrapStyleWord(true);
		Font commentfont = new Font("Arial", Font.BOLD, 20);
		commentBox.setBackground(Color.RED);
		commentBox.setFont(commentfont);
		Border commentBoxBorder = new LineBorder(Color.BLACK, 5, true);
		commentBox
				.setBorder(new TitledBorder(commentBoxBorder, "Comments", TitledBorder.CENTER, TitledBorder.TOP));
		p1elepMovesField.setBounds(750, 260, 300, 60);
		p1elepMovesField.setEditable(false);
		p1elepMovesField.setVisible(true);
		p1elepMovesField.setLineWrap(true);
		p1elepMovesField.setWrapStyleWord(true);
		p1elepMovesField.setBackground(Color.GREEN);
		p1elepMovesField.setFont(commentfont);
		p1wolfMovesField.setBounds(750, 320, 300, 60);
		p1wolfMovesField.setEditable(false);
		p1wolfMovesField.setVisible(true);
		p1wolfMovesField.setLineWrap(true);
		p1wolfMovesField.setBackground(Color.GREEN);
		p1wolfMovesField.setWrapStyleWord(true);
		p1tigMovesField.setBounds(750, 380, 300, 60);
		p1tigMovesField.setEditable(false);
		p1tigMovesField.setVisible(true);
		p1tigMovesField.setLineWrap(true);
		p1tigMovesField.setWrapStyleWord(true);
		p1tigMovesField.setBackground(Color.GREEN);
		p1mousMovesField.setBounds(750, 440, 300, 60);
		p1mousMovesField.setEditable(false);
		p1mousMovesField.setVisible(true);
		p1mousMovesField.setLineWrap(true);
		p1mousMovesField.setWrapStyleWord(true);
		p1mousMovesField.setBackground(Color.GREEN);
		p2elepMovesField.setBounds(750, 500, 300, 60);
		p2elepMovesField.setEditable(false);
		p2elepMovesField.setVisible(true);
		p2elepMovesField.setLineWrap(true);
		p2elepMovesField.setWrapStyleWord(true);
		p2elepMovesField.setBackground(Color.GREEN);
		p2elepMovesField.setFont(commentfont);
		p2wolfMovesField.setBounds(750, 560, 300, 60);
		p2wolfMovesField.setEditable(false);
		p2wolfMovesField.setVisible(true);
		p2wolfMovesField.setLineWrap(true);
		p2wolfMovesField.setWrapStyleWord(true);
		p2wolfMovesField.setBackground(Color.GREEN);
		p2tigMovesField.setBounds(750, 620, 300, 60);
		p2tigMovesField.setEditable(false);
		p2tigMovesField.setVisible(true);
		p2tigMovesField.setLineWrap(true);
		p2tigMovesField.setWrapStyleWord(true);
		p2tigMovesField.setBackground(Color.GREEN);
		p2mousMovesField.setBounds(750, 680, 300, 60);
		p2mousMovesField.setEditable(false);
		p2mousMovesField.setVisible(true);
		p2mousMovesField.setLineWrap(true);
		p2mousMovesField.setWrapStyleWord(true);
		p2mousMovesField.setBackground(Color.GREEN);
		exitButton.setBounds(400, 790, 100, 50);
		exitButton.setVisible(true);
		exitButton.setBackground(Color.BLUE);
		exitButton.setForeground(Color.BLACK);
		exitButton.setFont(new Font("Arial", Font.BOLD, 20));
		;
		exitButton.setText("Exit");
		exitButton.addMouseListener(this);
		jP1.add(exitButton);
		jP1.add(commentBox);
		jP1.add(p1elepMovesField);
		jP1.add(p2elepMovesField);
		jB1[1][9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Button-1*9 clicked");
			}
		});

		// System.out.println(jB1[5][2].getName());
		ga.add(jP1);
		// setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setExtendedState(JFrame.NORMAL);
	}

	public void mousePressed(MouseEvent e) {
		for (x = 1; x < 8; x++) {
			for (y = 1; y < 10; y++) {
				if ((e.getSource() == jB1[x][y] && jB1[x][y].getName() == "elephantButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "wolfButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "tigerButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "mouseButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "elephantButton1") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "wolfButton1") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "tigerButton1") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "mouseButton1")) {
					if (x < 7)
						jB1[x + 1][y].setBackground(Color.RED);
					if (x > 1)
						jB1[x - 1][y].setBackground(Color.RED);
					if (y < 9)
						jB1[x][y + 1].setBackground(Color.RED);
					if (y > 1)
						jB1[x][y - 1].setBackground(Color.RED);
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		for (x = 1; x < 8; x++) {
			for (y = 1; y < 10; y++) {
				if ((e.getSource() == jB1[x][y] && jB1[x][y].getName() == "elephantButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "wolfButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "tigerButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "mouseButton") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "elephantButton1") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "wolfButton1") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "tigerButton1") ||
						(e.getSource() == jB1[x][y] && jB1[x][y].getName() == "mouseButton1")) {
					if (x < 7)
						jB1[x + 1][y].setBackground(Color.BLUE);
					if (x > 1)
						jB1[x - 1][y].setBackground(Color.BLUE);
					if (y < 9)
						jB1[x][y + 1].setBackground(Color.BLUE);
					if (y > 1)
						jB1[x][y - 1].setBackground(Color.BLUE);
				}
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		for (x = 1; x < 8; x++) {
			for (y = 1; y < 10; y++) {
				if (e.getSource() == jB1[x][y]) {
					if (x < 7)
						jB1[x + 1][y].setBackground(Color.GREEN);
					if (x > 1)
						jB1[x - 1][y].setBackground(Color.GREEN);
					if (y < 9)
						jB1[x][y + 1].setBackground(Color.GREEN);
					if (y > 1)
						jB1[x][y - 1].setBackground(Color.GREEN);
				}
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitButton) {
			System.exit(0);
			System.out.println("Exit button clicked");
		}
		for (x = 1; x < 8; x++) {
			for (y = 1; y < 10; y++) {
				if (e.getSource() == jB1[x][y]) {
					if (jB1[x][y].getName() == "elephantButton") {
						elephantclickCount++;
					}
					if (jB1[x][y].getName() == "wolfButton") {
						wolfclickCount++;
					}
					if (jB1[x][y].getName() == "tigerButton") {
						tigerclickCount++;
					}
					if (jB1[x][y].getName() == "mouseButton") {
						mouseclickCount++;
					}
					if (jB1[x][y].getName() == "elephantButton1") {
						elephantclickCount1++;
					}
					if (jB1[x][y].getName() == "wolfButton1") {
						wolfclickCount1++;
					}
					if (jB1[x][y].getName() == "tigerButton1") {
						tigerclickCount1++;
					}
					if (jB1[x][y].getName() == "mouseButton1") {
						mouseclickCount1++;
					}
					// buttonClickCount[x][y]++;
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "elephantButton"
							&& jB1[x + 1][y].getName() != "elephantButton"
							&& jB1[x][y + 1].getName() != "elephantButton"
							&& jB1[x][y - 1].getName() != "elephantButton" && jB1[x][y].getName() != "elephantButton") {
						elephantclickCount = 0;
						System.out.println("Elephant click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "wolfButton"
							&& jB1[x + 1][y].getName() != "wolfButton" && jB1[x][y + 1].getName() != "wolfButton"
							&& jB1[x][y - 1].getName() != "wolfButton" && jB1[x][y].getName() != "wolfButton") {
						wolfclickCount = 0;
						System.out.println("Wolf click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "tigerButton"
							&& jB1[x + 1][y].getName() != "tigerButton" && jB1[x][y + 1].getName() != "tigerButton"
							&& jB1[x][y - 1].getName() != "tigerButton" && jB1[x][y].getName() != "tigerButton") {
						tigerclickCount = 0;
						System.out.println("tiger click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "mouseButton"
							&& jB1[x + 1][y].getName() != "mouseButton" && jB1[x][y + 1].getName() != "mouseButton"
							&& jB1[x][y - 1].getName() != "mouseButton" && jB1[x][y].getName() != "mouseButton") {
						mouseclickCount = 0;
						System.out.println("mouse click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "elephantButton1"
							&& jB1[x + 1][y].getName() != "elephantButton1"
							&& jB1[x][y + 1].getName() != "elephantButton1"
							&& jB1[x][y - 1].getName() != "elephantButton1"
							&& jB1[x][y].getName() != "elephantButton1") {
						elephantclickCount1 = 0;
						System.out.println("Black Elephant click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "wolfButton1"
							&& jB1[x + 1][y].getName() != "wolfButton1" && jB1[x][y + 1].getName() != "wolfButton1"
							&& jB1[x][y - 1].getName() != "wolfButton1" && jB1[x][y].getName() != "wolfButton1") {
						wolfclickCount1 = 0;
						System.out.println("Black Wolf click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "tigerButton1"
							&& jB1[x + 1][y].getName() != "tigerButton1" && jB1[x][y + 1].getName() != "tigerButton1"
							&& jB1[x][y - 1].getName() != "tigerButton1" && jB1[x][y].getName() != "tigerButton1") {
						tigerclickCount1 = 0;
						System.out.println("Black tiger click Count nullified");
					}
					if ((x < 7) && (y < 9) && jB1[x - 1][y].getName() != "mouseButton1"
							&& jB1[x + 1][y].getName() != "mouseButton1" && jB1[x][y + 1].getName() != "mouseButton1"
							&& jB1[x][y - 1].getName() != "mouseButton1" && jB1[x][y].getName() != "mouseButton1") {
						mouseclickCount1 = 0;
						System.out.println("Black mouse click Count nullified");
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("elephantButton")) && elephantclickCount > 0) {
						elephantclickCount = 0;
						if (jB1[x][y].getName() != "wolfButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveElephant(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("elephantButton")) && elephantclickCount > 0) {
						elephantclickCount = 0;
						if (jB1[x][y].getName() != "wolfButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveElephant(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("elephantButton")) && elephantclickCount > 0) {
						elephantclickCount = 0;
						if (jB1[x][y].getName() != "wolfButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveElephant(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("elephantButton")) && elephantclickCount > 0) {
						elephantclickCount = 0;
						if (jB1[x][y].getName() != "wolfButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveElephant(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("wolfButton")) && wolfclickCount > 0) {
						wolfclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveWolf(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("wolfButton")) && wolfclickCount > 0) {
						wolfclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveWolf(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("wolfButton")) && wolfclickCount > 0) {
						wolfclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveWolf(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("wolfButton")) && wolfclickCount > 0) {
						wolfclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "tigerButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveWolf(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("tigerButton")) && tigerclickCount > 0) {
						tigerclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveTiger(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("tigerButton")) && tigerclickCount > 0) {
						tigerclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveTiger(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("tigerButton")) && tigerclickCount > 0) {
						tigerclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveTiger(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("tigerButton")) && tigerclickCount > 0) {
						tigerclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "mouseButton") {
							moveTiger(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("mouseButton")) && mouseclickCount > 0) {
						mouseclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "tigerButton") {
							moveMouse(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("mouseButton")) && mouseclickCount > 0) {
						mouseclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "tigerButton") {
							moveMouse(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("mouseButton")) && mouseclickCount > 0) {
						mouseclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "tigerButton") {
							moveMouse(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("mouseButton")) && mouseclickCount > 0) {
						mouseclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton" && jB1[x][y].getName() != "wolfButton"
								&& jB1[x][y].getName() != "tigerButton") {
							moveMouse(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("elephantButton1")) && elephantclickCount1 > 0) {
						elephantclickCount1 = 0;
						if (jB1[x][y].getName() != "wolfButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveElephant1(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("elephantButton1")) && elephantclickCount1 > 0) {
						elephantclickCount1 = 0;
						if (jB1[x][y].getName() != "wolfButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveElephant1(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("elephantButton1")) && elephantclickCount1 > 0) {
						elephantclickCount1 = 0;
						if (jB1[x][y].getName() != "wolfButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveElephant1(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("elephantButton1")) && elephantclickCount1 > 0) {
						elephantclickCount1 = 0;
						if (jB1[x][y].getName() != "wolfButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveElephant1(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("wolfButton1")) && wolfclickCount1 > 0) {
						wolfclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveWolf1(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("wolfButton1")) && wolfclickCount1 > 0) {
						wolfclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveWolf1(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("wolfButton1")) && wolfclickCount1 > 0) {
						wolfclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveWolf1(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("wolfButton1")) && wolfclickCount1 > 0) {
						wolfclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "tigerButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveWolf1(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("tigerButton1")) && tigerclickCount1 > 0) {
						tigerclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveTiger1(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("tigerButton1")) && tigerclickCount1 > 0) {
						tigerclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveTiger1(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("tigerButton1")) && tigerclickCount1 > 0) {
						tigerclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveTiger1(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("tigerButton1")) && tigerclickCount1 > 0) {
						tigerclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "mouseButton1") {
							moveTiger1(x, y, x, (y - 1));
						}
					}
					if (x > 1 && (jB1[x - 1][y].getName().equals("mouseButton1")) && mouseclickCount1 > 0) {
						mouseclickCount = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "tigerButton1") {
							moveMouse1(x, y, (x - 1), y);
						}
					}
					if (x < 7 && (jB1[x + 1][y].getName().equals("mouseButton1")) && mouseclickCount1 > 0) {
						mouseclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "tigerButton1") {
							moveMouse1(x, y, (x + 1), y);
						}
					}
					if (y < 9 && (jB1[x][y + 1].getName().equals("mouseButton1")) && mouseclickCount1 > 0) {
						mouseclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "tigerButton1") {
							moveMouse1(x, y, x, (y + 1));
						}
					}
					if (y > 1 && (jB1[x][y - 1].getName().equals("mouseButton1")) && mouseclickCount1 > 0) {
						mouseclickCount1 = 0;
						if (jB1[x][y].getName() != "elephantButton1" && jB1[x][y].getName() != "wolfButton1"
								&& jB1[x][y].getName() != "tigerButton1") {
							moveMouse1(x, y, x, (y - 1));
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		GameAnimal4 game = new GameAnimal4();
		game.setVisible(true);
		game.implementGame(game);
		game.setImages();
		// System.out.println(game.jB1.);
	}
}