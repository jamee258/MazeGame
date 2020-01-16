import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MazeGame {
	JFrame actualMaze = new JFrame("Olde Worlde Phunne's Maze Game™");
	Color blue = Color.decode("#009ce2");

	public MazeGame(int size) {

		JPanel jp1 = new JPanel(new BorderLayout());
		JPanel jp2 = new JPanel(new BorderLayout());
		JPanel instructions = new JPanel();
		JLabel instructionsLabel = new JLabel(
				"<html><div style='text-align: center;'><font color='white'>Use Arrows or 'WASD' keys to navigate the Blue square to the Green Square.<br>Collect as many coins as possible.<br>Be careful of the <font color='red'>red</font> enemies!</font></div></html>");

		Border instructionsBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);

		instructions.add(instructionsLabel);
		instructions.setBorder(instructionsBorder);
		instructions.setBackground(blue);

		final MazeBoard mb = new MazeBoard(size, size);
		final Directions directions = new Directions(mb);

		jp1.add(mb, BorderLayout.CENTER);
		jp1.setFocusable(true);

		JButton infoButton = new JButton("Guide");
		infoButton.setPreferredSize(new Dimension(93, 400));
		infoButton.setForeground(blue);
		infoButton.setFocusable(true);
		jp2.add(infoButton, BorderLayout.EAST);
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				JFrame infoDetails = new JFrame();
				JPanel jp5 = new JPanel();
				infoDetails.add(jp5);
				JLabel jp4 = new JLabel("Finish the Maze with as many coins as possible");
				jp5.add(jp4, BorderLayout.CENTER);
				jp4.setVisible(true);
				infoDetails.setVisible(true);
			}
		});

		actualMaze.add(jp1, BorderLayout.CENTER);
		actualMaze.add(jp2, BorderLayout.NORTH);
		actualMaze.add(instructions, BorderLayout.SOUTH);
		actualMaze.add(infoButton, BorderLayout.EAST);

		JButton mainMenu = new JButton("Main Menu");
		mainMenu.setForeground(blue);
		mainMenu.setFocusable(false);
		jp2.add(mainMenu, BorderLayout.CENTER);
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				actualMaze.dispose();
				new Home();
			}
		});

		JButton newGameButton = new JButton("New Maze");
		newGameButton.setForeground(blue);
		newGameButton.setFocusable(false);
		jp2.add(newGameButton, BorderLayout.EAST);
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				actualMaze.dispose();
				new MazeGame(15);
			}
		});

		jp1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent kE) {
				int key = kE.getKeyCode();
				if (kE.getKeyChar() == 'w' || key == KeyEvent.VK_UP) {
					directions.moveUp(mb);
				}
				if (kE.getKeyChar() == 's' || key == KeyEvent.VK_DOWN) {
					directions.moveDown(mb);
				}
				if (kE.getKeyChar() == 'd' || key == KeyEvent.VK_RIGHT) {
					directions.moveRight(mb);
				}
				if (kE.getKeyChar() == 'a' || key == KeyEvent.VK_LEFT) {
					directions.moveLeft(mb);
				}
			}
		});

		actualMaze.setSize(490, 520);
		actualMaze.setResizable(false);
		actualMaze.setLocationRelativeTo(null);
		actualMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		actualMaze.setVisible(true);
	}

	public MazeGame(int size, int goldTreasure, int silverTreasure) {
		int totalScore = (goldTreasure * 50) + (silverTreasure * 25);
		actualMaze.dispose();
		JFrame showWin = new JFrame();
		JPanel newOptions = new JPanel();
		JLabel winDetails = new JLabel(
				"<html><div style='text-align: center;'> -WELL DONE!-<br>You Have Completed The Game!<br>Coins Collected:<br>Gold: "
						+ goldTreasure + "<br>Silver: " + silverTreasure + "<br>Total Score = " + totalScore
						+ "</div></html>",
				JLabel.CENTER);
		winDetails.setFont(new Font("Calibri", Font.BOLD, 21));
		winDetails.setForeground(blue);
		winDetails.setBackground(Color.LIGHT_GRAY);

		newOptions.add(winDetails);
		showWin.add(newOptions, BorderLayout.CENTER);

		JButton winNewGame = new JButton("New Game");
		winNewGame.setForeground(blue);

		winNewGame.setFocusable(false);
		newOptions.add(winNewGame, BorderLayout.SOUTH);
		winNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				actualMaze.dispose();
				new MazeGame(15);
			}
		});

		showWin.setResizable(false);

		showWin.add(winNewGame, BorderLayout.SOUTH);
		showWin.setSize(450, 450);
		showWin.setLocationRelativeTo(null);
		showWin.setVisible(true);
		showWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}