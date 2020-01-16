import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Directions extends JPanel implements ActionListener {

	private int xLocation = 0;
	private int yLocation = 0;
	int goldTreasure = 0;
	int silverTreasure = 0;

	@SuppressWarnings("unused")
	private final MazeBoard board;

	public Directions(MazeBoard mazeBoard) {
		this.board = mazeBoard;
		xLocation = 1;
		yLocation = 1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	public void moveLeft(MazeBoard mazeBoard) {
		if ((mazeBoard.get(xLocation - 1, yLocation) != '*') && (mazeBoard.get(xLocation - 1, yLocation) != '-')) {
			mazeBoard.set(xLocation, yLocation, 'O');
			if (mazeBoard.get(xLocation -= 1, yLocation) == 'G') {
				gameWon();
			} else if (mazeBoard.get(xLocation, yLocation) == '£') {
				goldTreasure++;
			} else if (mazeBoard.get(xLocation, yLocation) == '$') {
				silverTreasure++;
			} else {
				mazeBoard.set(xLocation, yLocation, 'X');
			}
		}
	}

	public void moveRight(MazeBoard mazeBoard) {
		if ((mazeBoard.get(xLocation + 1, yLocation) != '*') && (mazeBoard.get(xLocation + 1, yLocation) != '-')) {
			mazeBoard.set(xLocation, yLocation, 'O');
			if (mazeBoard.get(xLocation += 1, yLocation) == 'G') {
				gameWon();
			} else if (mazeBoard.get(xLocation, yLocation) == '£') {
				goldTreasure++;
			} else if (mazeBoard.get(xLocation, yLocation) == '$') {
				silverTreasure++;
			} else {
				mazeBoard.set(xLocation, yLocation, 'X');
			}
		}
	}

	public void moveUp(MazeBoard mazeBoard) {
		if ((mazeBoard.get(xLocation, yLocation - 1) != '*') && (mazeBoard.get(xLocation, yLocation - 1) != '-')) {
			mazeBoard.set(xLocation, yLocation, 'O');
			if (mazeBoard.get(xLocation, yLocation -= 1) == 'G') {
				gameWon();
			} else if (mazeBoard.get(xLocation, yLocation) == '£') {
				goldTreasure++;
			} else if (mazeBoard.get(xLocation, yLocation) == '$') {
				silverTreasure++;
			} else {
				mazeBoard.set(xLocation, yLocation, 'X');
			}
		}
	}

	public void moveDown(MazeBoard mazeBoard) {
		if ((mazeBoard.get(xLocation, yLocation + 1) != '*') && (mazeBoard.get(xLocation, yLocation + 1) != '-')) {
			mazeBoard.set(xLocation, yLocation, 'O');
			if (mazeBoard.get(xLocation, yLocation += 1) == 'G') {
				gameWon();
			} else if (mazeBoard.get(xLocation, yLocation) == '£') {
				goldTreasure++;
			} else if (mazeBoard.get(xLocation, yLocation) == '$') {
				silverTreasure++;
			} else {
			}
			mazeBoard.set(xLocation, yLocation, 'X');
		}
	}

	public void gameWon() {
		new MazeGame(10, goldTreasure, silverTreasure);
	}

}