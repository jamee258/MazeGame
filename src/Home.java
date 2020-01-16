import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Home implements ActionListener {
	private ImageIcon image1;
	private JLabel imageLabel;
	Color blue = Color.decode("#009ce2");
	Color darkerBlue = Color.decode("#0077e0");
	Color electricLime = Color.decode("#ccff00");

	public Home() {

		// Check user working directory of project
		System.out.println("Maze Project Working Directory:" + System.getProperty("user.dir"));

		JFrame box = new JFrame("Olde Worlde Phunne's Maze Game™");

		// Add Maze Logo to Home Page
		JPanel imagePanel = new JPanel();
		imagePanel.setPreferredSize(new Dimension(600, 230));
		image1 = new ImageIcon("C:\\Users\\ISLAMM\\eclipse-workspace\\MainMazeGame\\Pictures\\MazeGameLogo4.jpg");
		Image img = image1.getImage();
		Image newimg = img.getScaledInstance(340, 200, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);

		imageLabel = new JLabel(newIcon);
		// imageLabel.setPreferredSize(new Dimension(340, 200));
		imagePanel.add(imageLabel);
		imagePanel.setVisible(true);
		box.pack();

		JButton playButton = new JButton("-PLAY-");
		Border buttonBorder = new LineBorder(blue, 2);
		playButton.setForeground(darkerBlue);
		playButton.setFont(new Font("Calibri", Font.BOLD, 18));
		playButton.setFont(playButton.getFont().deriveFont((Font.BOLD)));
		playButton.setBorder(buttonBorder);
		playButton.setBounds(300, 300, 200, 180);
		playButton.setPreferredSize(new Dimension(110, 50));
		playButton.setBackground(electricLime);
		playButton.setFocusPainted(false);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setSize(450, 450);
		buttonsPanel.setBorder(BorderFactory.createLineBorder(blue, 12));
		buttonsPanel.add(playButton);

		box.add(imagePanel, BorderLayout.SOUTH);

		box.setSize(460, 480);
		box.setResizable(false);
		box.setLocationRelativeTo(null);
		box.add(buttonsPanel);
		box.setLayout(null);
		box.setVisible(true);
		box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEv) {
				box.dispose();
				// Start New Maze Game. Parameter specifies width & height of maze
				new MazeGame(15);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("Name Saved");
	}

}