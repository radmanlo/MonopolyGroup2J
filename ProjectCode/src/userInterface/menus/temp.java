package userInterface.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.PlayerColor;
import models.Token;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Color;

//This class exists for design purposes , ignore -G
public class temp extends JPanel{
	private JLabel cannotCreateWarningLabel;
	private JButton initializeGameBtn;
	private JButton addNewPlayerBtn;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> potentialNewPlayers;

	final int SPACE_BETWEEN_FIELDS = 90;
	final int FIRST_FIELD_Y = 100;
	final int FIELDS_X = 400;

	public temp() {
		setBorder(UIManager.getBorder("DesktopIcon.border"));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		backBtn.setBounds(10, 10, 89, 34);
		add(backBtn);
		
		choosableTokens = new ArrayList<Token>();
		choosableTokens.add(new Token("./resources/Token1.jpg"));
		choosableTokens.add(new Token("./resources/Token2.jpg"));
		choosableTokens.add(new Token("./resources/Token3.jpg"));
		choosableTokens.add(new Token("./resources/Token4.jpg"));
		choosableTokens.add(new Token("./resources/Token5.jpg"));
		choosableTokens.add(new Token("./resources/Token6.jpg"));
		choosableTokens.add(new Token("./resources/Token7.jpg"));
		choosableTokens.add(new Token("./resources/Token8.jpg"));

		choosableColors = new ArrayList<PlayerColor>();
		choosableColors.add(PlayerColor.WHITE);
		choosableColors.add(PlayerColor.RED);
		choosableColors.add(PlayerColor.GREEN);
		choosableColors.add(PlayerColor.BLUE);
		choosableColors.add(PlayerColor.YELLOW);
		choosableColors.add(PlayerColor.PURPLE);
		choosableColors.add(PlayerColor.ORANGE);
		choosableColors.add(PlayerColor.BLACK);

		potentialNewPlayers = new ArrayList<NewPlayerAddingScreen>();

		setLayout(null);
		setBounds(0, 0, 1500, 900);

		backBtn.setText("Go Back");
		backBtn.setBounds(33, 379, 289, 91);


		//NewPlayerAddingScreen s1 = new NewPlayerAddingScreen(this);
		//potentialNewPlayers.add(s1);
		//add(s1);

		//NewPlayerAddingScreen s2 = new NewPlayerAddingScreen(this);
		//potentialNewPlayers.add(s2);
		//add(s2);	

		addNewPlayerBtn = new JButton("+");
		addNewPlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPotentialPlayer();
			}
		});
		addNewPlayerBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(addNewPlayerBtn);

		initializeGameBtn = new JButton("Start Game");
		initializeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		initializeGameBtn.setBounds(33, 247, 289, 106);
		add(initializeGameBtn);
		
		JPanel warningPrompt = new JPanel();
		warningPrompt.setBackground(Color.LIGHT_GRAY);
		warningPrompt.setBorder(UIManager.getBorder("OptionPane.messageAreaBorder"));
		warningPrompt.setBounds(376, 322, 849, 493);
		add(warningPrompt);
		warningPrompt.setLayout(null);
		
		JLabel warningLbl = new JLabel("<html>The game cannot be initialized with the current selections!<br/> Please make sure that the same Name/Color/Token is not selected for different players!</html>", SwingConstants.CENTER);
		warningLbl.setBounds(149, 79, 550, 91);
		warningPrompt.add(warningLbl);
		warningLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		warningLbl.setHorizontalTextPosition(SwingConstants.LEFT);
		warningLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		warningLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton okBtn = new JButton("OK");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		okBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		okBtn.setBounds(323, 300, 177, 73);
		warningPrompt.add(okBtn);

		redrawPotentialPlayers();
	}


	public void initializeNewGame() {

	}

	public void addPotentialPlayer() {
		if( potentialNewPlayers.size() < 8 ) {

			//NewPlayerAddingScreen sTemp = new NewPlayerAddingScreen(this);
			//potentialNewPlayers.add(sTemp);
			//add(sTemp);

			redrawPotentialPlayers();

			if(potentialNewPlayers.size() != 8  )
				addNewPlayerBtn.setBounds(830, FIRST_FIELD_Y + SPACE_BETWEEN_FIELDS * potentialNewPlayers.size(), 51, 42);
			else
				addNewPlayerBtn.setVisible(false);

		}

		revalidate();
		repaint();

	}

	public void removePotentialPlayer(NewPlayerAddingScreen toRemove) {
		remove(toRemove);
		potentialNewPlayers.remove(toRemove);
		redrawPotentialPlayers();
	}

	public void redrawPotentialPlayers() {
		for( int i = 0; i < potentialNewPlayers.size(); ++i) {
			potentialNewPlayers.get(i).setBounds(FIELDS_X, FIRST_FIELD_Y + SPACE_BETWEEN_FIELDS * i, 1040, 80);
		}

		addNewPlayerBtn.setBounds(830, FIRST_FIELD_Y + SPACE_BETWEEN_FIELDS * potentialNewPlayers.size(), 51, 42);

		if(potentialNewPlayers.size() != 8  )
			addNewPlayerBtn.setVisible(true);
		else
			addNewPlayerBtn.setVisible(false);


		revalidate();
		repaint();	
	}

	public ArrayList<Token> getChoosableTokens() {
		return choosableTokens;
	}

	public ArrayList<PlayerColor> getChoosableColors() {
		return choosableColors;
	}
}
