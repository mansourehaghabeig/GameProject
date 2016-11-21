package client;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * Game class is responsible for:
 * <li> Generating the graphical view of mini-game </li>
 * <li> Sending / getting information to client (client socket) class </li>
 * @author Mansoureh Aghabeig
 *
 */
public class Game extends JFrame {
	private static final String INITIAL_TEXT = "Nothing Pressed";
	private JLabel positionLabel; // label for showing current situation of game.
	private JButton resetButton; 
	private static int gridSize = 3;
	private int numberOfBoxes; // number of Boxes in the game
	private Icon icon;  // icon of each Boxes
	private Client client; //instance of Client class
	JPanel buttonPanel;  // panel for keeping all giftboxes

	/** 
	 * 
	 * The main constructor which:
	 * Setting the main windows property,
	 * Generating an instance of Client class
	 * @throws Exception 
	 * 	 
	 */
	public Game(Client client) throws Exception{
		this.icon = new ImageIcon("src\\main\\resources\\gift.png");
		this.setTitle( "mini-game" );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		this.setResizable( false );
		this.client = client;
		this.numberOfBoxes = client.getBoxes();
	}

	/**
	 * Generates the main window of Game
	 */
	public void createAndDisplayGUI()
	{       
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		positionLabel = new JLabel("Start Game", JLabel.CENTER);
		JPanel buttonLeftPanel = new JPanel();
		resetButton = new JButton("Start");

		//Add Action listener to Start Button
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				setComponentPanelEnabled(buttonPanel,true);
				positionLabel.setText("Start Game");
				try {
					client.startGame();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		labelPanel.add(positionLabel);
		buttonLeftPanel.add(resetButton);
		leftPanel.add(labelPanel);
		leftPanel.add(buttonLeftPanel);

		contentPane.add(leftPanel);
		contentPane.setPreferredSize(new Dimension (500,350));
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(gridSize, gridSize, 20, 20));

		for (int i = 0; i < numberOfBoxes; i++)
		{
			final JButton button = new JButton(this.icon);
			button.setEnabled(false);
			//Add Action listener to Box Gift Button
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae) {
					button.setEnabled(false);
					try {
						setSituationOfGame(client.calculateProfit());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			buttonPanel.add(button);
		}

		contentPane.add(buttonPanel);
		setContentPane(contentPane);
		pack();
		setLocationByPlatform(true);
		setVisible(true);
	}

	/**
	 * Set Enabling properties of all element in the specified panel.
	 * @param panel
	 * @param isEnabled which can be set to either true or false.
	 */
	void setComponentPanelEnabled(JPanel panel, Boolean isEnabled) {
		panel.setEnabled(isEnabled);
		Component[] components = panel.getComponents();
		for(int i = 0; i < components.length; i++) {
			components[i].setEnabled(isEnabled);
		}
	}

	/**
	 * Set current situation of game which can be:
	 * 1- game over 2- second chance  3- current obtained profit
	 *
	 * @param situation
	 */

	void setSituationOfGame (String situation){
		if (situation.contains("more")) {
			situation = situation.replace("more", "");
			positionLabel.setText("Second chance,the profit you get till now: " + situation);
			setComponentPanelEnabled(buttonPanel,true);
			return;
		}

		if (situation.contains("over")) {
			situation = situation.replace("over", "");
			positionLabel.setText("The game is over, you get: " + situation);
			setComponentPanelEnabled(buttonPanel,false);
			return;
		}

		positionLabel.setText("You get till now: " + situation);
		return;
	}

}