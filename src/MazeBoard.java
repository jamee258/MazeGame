import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MazeBoard extends JPanel {

	private char[][] mazeBoard;
	private int size;
	private int notVisited;
	private int scale = 1;
	LinkedList<Location> positionList = new LinkedList<Location>();

	public MazeBoard(int width, int height) {
		notVisited = width * height;
		width = width * 2;
		height = height * 2;
		width++;
		height++;
		scale = height;

		mazeBoard = new char[width][height];
		size = width;
		createBoard();
		addGoldTreasure(20);
		addSilverTreasure(20);
		addThreat();
	}

	public void createBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mazeBoard[i][j] = '¤';

			}
		}
		for (int i = 0; i < size; i += 2) {
			for (int j = 0; j < size; j++) {
				mazeBoard[i][j] = '-';
				mazeBoard[j][i] = '-';
			}
		}
		for (int i = 0; i < size; i++) {
			mazeBoard[i][0] = '*';
			mazeBoard[0][i] = '*';
			mazeBoard[size - 1][i] = '*';
			mazeBoard[i][size - 1] = '*';
		}
		create(1, 1);

	}

	public void paint(Graphics g) {
		super.paint(g);
		int n = 500 / (scale + 10);
		Color blue = Color.decode("#009ce2");
		Color green = Color.decode("#39ff1f");
		Color darkGrey = Color.decode("#282828");

		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				if ((mazeBoard[i][k] == '*')) {
					g.setColor(darkGrey);
					g.fillRect(i * n, k * n, n, n);
				} else if (mazeBoard[i][k] == '-') {
					g.setColor(darkGrey);
					g.fillRect(i * n, k * n, n, n);
				} else if (mazeBoard[i][k] == 'X') {
					g.setColor(blue);
					g.fill3DRect(i * n, k * n, n, n, true);
				} else if (mazeBoard[i][k] == 'G') {
					g.setColor(green);
					g.fill3DRect(i * n, k * n, n, n, true);
				} else if (mazeBoard[i][k] == '£') {
					g.setColor(Color.yellow);
					g.fillOval(i * n, k * n, n, n);
				} else if (mazeBoard[i][k] == '$') {
					g.setColor(Color.GRAY);
					g.fillOval(i * n, k * n, n, n);
				} else if (mazeBoard[i][k] == 'T') {
					g.setColor(Color.RED);
					g.fillArc(i * n, k * n, 15, 15, 300, 300);
				}
			}
		}
	}

	public char get(int x, int y) {
		return mazeBoard[x][y];

	}

	public void set(int x, int y, char value) {
		mazeBoard[x][y] = value;
		repaint();
	}

	public char[] updateDirection(Location pO) {
		char up = 0, down = 0, right = 0, left = 0;
		if (get(pO.getX(), pO.getY() + 1) != '*')
			right = get(pO.getX(), pO.getY() + 2);
		if (get(pO.getX(), pO.getY() - 1) != '*')
			left = get(pO.getX(), pO.getY() - 2);
		if (get(pO.getX() - 1, pO.getY()) != '*')
			up = get(pO.getX() - 2, pO.getY());
		if (get(pO.getX() + 1, pO.getY()) != '*')
			down = get(pO.getX() + 2, pO.getY());
		char direction[] = { left, right, down, up };
		return direction;
	}

	Location posList[] = new Location[(2 * (getX() / 2))];
	Location cC = new Location(5, 5);

	public void create(int xLocation, int yLocation) {
		cC = new Location(xLocation, yLocation);
		set(cC.getX(), cC.getY(), 'v');
		notVisited = notVisited - 1;

		char up = 0;
		char down = 0;
		char right = 0;
		char left = 0;

		char[] direction = { left, right, down, up };
		direction = updateDirection(cC);

		while (notVisited != 0) {
			int free = 0;
			if ((direction[0] == '¤') || (direction[1] == '¤') || (direction[2] == '¤') || (direction[3] == '¤')) {
				free = 1;
			}

			Random rndm = new Random();
			int randomNumber = rndm.nextInt(4);
			set(cC.getX(), cC.getY(), 'v');

			if ((randomNumber == 0) && (direction[0] == '¤')) {
				if (get(cC.getX(), cC.getY() - 1) != '*') {
					set(cC.getX(), cC.getY() - 1, 'v');
					cC = new Location(cC.getX(), cC.getY() - 2);
					positionList.push(cC);
					direction = updateDirection(cC);
					notVisited--;
				}
			} else if ((randomNumber == 1) && (direction[1] == '¤')) {
				if (get(cC.getX(), cC.getY() + 1) != '*') {
					set(cC.getX(), cC.getY() + 1, 'v');
					cC = new Location(cC.getX(), cC.getY() + 2);
					positionList.push(cC);
					direction = updateDirection(cC);
					notVisited--;
				}
			} else if ((randomNumber == 2) && (direction[2] == '¤')) {
				if (get(cC.getX() + 1, cC.getY()) != '*') {
					set(cC.getX() + 1, cC.getY(), 'v');
					cC = new Location(cC.getX() + 2, cC.getY());
					positionList.push(cC);
					direction = updateDirection(cC);
					notVisited--;
				}
			} else if ((randomNumber == 3) && (direction[3] == '¤')) {
				if (get(cC.getX() - 1, cC.getY()) != '*') {
					set(cC.getX() - 1, cC.getY(), 'v');
					cC = new Location(cC.getX() - 2, cC.getY());
					positionList.push(cC);
					direction = updateDirection(cC);
					notVisited--;
				}
			} else {
				if (free == 0 && positionList.size() != 0) {
					cC = positionList.get(positionList.size() - 1);
					positionList.remove(positionList.size() - 1);
					direction = updateDirection(cC);
				}
			}

		}
		set(cC.getX(), cC.getY(), 'G');
		set(1, 1, 'X');
	}

	// Add treasure to random locations on the map
	public void addGoldTreasure(int width) {

		for (int i = 0; i < 3; i++) {
			int xLocation = 0;
			int yLocation = 0;
			while ((xLocation == 0 || yLocation == 0)) {
				int xRan = 1 + (int) (Math.random() * ((width)));
				int yRan = 1 + (int) (Math.random() * ((width)));
				if (((xRan % 2) != 0) && (xRan != 0 || xRan != width))
					xLocation = xRan;
				if (((yRan % 2) != 0) && (yRan != 0 || yRan != width))
					yLocation = yRan;

				if ((get(xLocation, yLocation) == 'X') || get(xLocation, yLocation) == 'G') {
					xLocation = 0;
					yLocation = 0;
				}
			}

			set(xLocation, yLocation, '£');
		}
	}

	public void addSilverTreasure(int width) {

		for (int i = 0; i < 6; i++) {
			int xLocation = 0;
			int yLocation = 0;
			while ((xLocation == 0 || yLocation == 0)) {
				int xRan = 1 + (int) (Math.random() * ((width)));
				int yRan = 1 + (int) (Math.random() * ((width)));
				if (((xRan % 2) != 0) && (xRan != 0 || xRan != width))
					xLocation = xRan;
				if (((yRan % 2) != 0) && (yRan != 0 || yRan != width))
					yLocation = yRan;

				if ((get(xLocation, yLocation) == 'X') || get(xLocation, yLocation) == 'G') {
					xLocation = 0;
					yLocation = 0;
				}
			}

			set(xLocation, yLocation, '$');
		}
	}

	// Add threats to random locations on the Maze
	public void addThreat() {
		Random position = new Random();
		for (int i = 0; i < 2; i++) {
			int xLocation = 0;
			int yLocation = 0;
			while ((xLocation == 0 || yLocation == 0)) {
				int randX = position.nextInt(size);
				int randY = position.nextInt(size);
				if (((randX % 2) != 0) && (randX != 0 || randX != size))
					xLocation = randX;
				if (((randY % 2) != 0) && (randY != 0 || randY != size))
					yLocation = randY;

				if ((get(xLocation, yLocation) == 'X') || get(xLocation, yLocation) == 'G') {
					xLocation = 0;
					yLocation = 0;
				}
			}

			set(xLocation, yLocation, 'T');
		}
	}

	// Actions taken when player encounters a threat
	public void dealWithThreat(Location pO) {
		if (get(pO.getX(), pO.getY()) == 'T') {
			JFrame enemyFrame = new JFrame();
			JLabel jl = new JLabel("An enemy has approached you!");
			JButton dealButton = new JButton("Defeat Threat");
			enemyFrame.add(jl, BorderLayout.NORTH);
			enemyFrame.add(dealButton, BorderLayout.SOUTH);
			enemyFrame.setVisible(true);
			enemyFrame.setResizable(false);
			dealButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actEv) {
					enemyFrame.dispose();
				}
			});
		}
	}
}