package userInterface.menus;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import models.Token;
import models.PlayerColor;
import models.PotentialPlayer;

public class NewGameMenu extends Menu{

	private JButton initializeGameBtn;
	private JButton addNewPlayerBtn;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> newPlayerAddingScreens;
	private ArrayList<PotentialPlayer> potentialPlayers;
	final int SPACE_BETWEEN_FIELDS = 90;
	final int FIRST_FIELD_Y = 100;
	final int FIELDS_X = 600;

	public NewGameMenu() {

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

		newPlayerAddingScreens = new ArrayList<NewPlayerAddingScreen>();
		potentialPlayers = new ArrayList<PotentialPlayer>();
		setLayout(null);
		setBounds(100, 100, 1900, 1000);

		backBtn.setText("Go Back");
		backBtn.setBounds(33, 379, 289, 91);


		NewPlayerAddingScreen s1 = new NewPlayerAddingScreen(this);
		newPlayerAddingScreens.add(s1);
		add(s1);

		NewPlayerAddingScreen s2 = new NewPlayerAddingScreen(this);
		newPlayerAddingScreens.add(s2);
		add(s2);	

		addNewPlayerBtn = new JButton("+");
		addNewPlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPotentialPlayer();
			}
		});
		addNewPlayerBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(addNewPlayerBtn);

		initializeGameBtn = new JButton("Start Game");
		initializeGameBtn.setBounds(33, 247, 289, 106);
		initializeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeNewGame();
			}
		});
		add(initializeGameBtn);

		redrawPotentialPlayers();
	}


	public ArrayList<PotentialPlayer> getPotentialPlayers() {
		return potentialPlayers;
	}


	public void initializeNewGame() {
		if(areAllPlayersUnique() == false) {	
			showMessageDialog(null, "//TODO Write correct Definition. Im in New Game Menu.");
		}else {
			for(int i = 0; i < newPlayerAddingScreens.size(); i++) {
				Token plyrToken = tokenMatch(i);
				PlayerColor plyrClr = colorMatch(i);
				String plyrName = newPlayerAddingScreens.get(i).getChosenName();
				PotentialPlayer player = new PotentialPlayer(plyrToken, plyrClr, plyrName);
				potentialPlayers.add(player);
			}
			return;
		}
	}
	
	public Token tokenMatch(int index) {
		
		Token token;
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 0)
		{
			token = new Token("./resources/Token1.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 1)
		{
			token = new Token("./resources/Token2.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 2)
		{
			token = new Token("./resources/Token3.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 3)
		{
			token = new Token("./resources/Token4.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 4)
		{
			token = new Token("./resources/Token5.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 5)
		{
			token = new Token("./resources/Token6.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 6)
		{
			token = new Token("./resources/Token7.jpg");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 7)
		{
			token = new Token("./resources/Token8.jpg");
			return token;
		}
		return null;
	}

	public PlayerColor colorMatch(int index) {
		//DEFAULT
		PlayerColor plyrColor = PlayerColor.WHITE;
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 0) {
			plyrColor = PlayerColor.WHITE;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 1) {
			plyrColor = PlayerColor.RED;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 2) {
			plyrColor = PlayerColor.GREEN;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 3) {
			plyrColor = PlayerColor.BLUE;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 4) {
			plyrColor = PlayerColor.YELLOW;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 5) {
			plyrColor = PlayerColor.PURPLE;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 6) {
			plyrColor = PlayerColor.ORANGE;
			return plyrColor;
		}
		if(newPlayerAddingScreens.get(index).getChosenColorIndex() == 7) {
			plyrColor = PlayerColor.BLACK;
			return plyrColor;
		}

		return null;
	}
	private boolean areAllPlayersUnique() {

		for( int i = 0; i < newPlayerAddingScreens.size(); i++) {
			newPlayerAddingScreens.get(i).updateChosens();
		}
		
		for( int i = 0; i < newPlayerAddingScreens.size(); ++i) {
			for( int j = i+1; j < newPlayerAddingScreens.size(); j++) {
				if( newPlayerAddingScreens.get(i).getChosenName().equalsIgnoreCase( newPlayerAddingScreens.get(j).getChosenName())) {
					return false;
				}
			}
		}
		
		for( int i = 0; i < newPlayerAddingScreens.size(); i++) {
			for( int j = i+1; j < newPlayerAddingScreens.size(); j++) {
				if( newPlayerAddingScreens.get(i).getChosenTokenIndex() == newPlayerAddingScreens.get(j).getChosenTokenIndex()){
					return false;
				}
			}
		}
		
		for( int i = 0; i < newPlayerAddingScreens.size(); i++) {
			for( int j = i+1; j < newPlayerAddingScreens.size(); j++) {
				if( newPlayerAddingScreens.get(i).getChosenColorIndex() == newPlayerAddingScreens.get(j).getChosenColorIndex()){
					return false;
				}
			}
		}
		return true;
	}

	public void addPotentialPlayer() {
		if( newPlayerAddingScreens.size() < 8 ) {

			NewPlayerAddingScreen sTemp = new NewPlayerAddingScreen(this);
			newPlayerAddingScreens.add(sTemp);
			add(sTemp);

			redrawPotentialPlayers();

			if(newPlayerAddingScreens.size() != 8  )
				addNewPlayerBtn.setBounds(830, FIRST_FIELD_Y + SPACE_BETWEEN_FIELDS * newPlayerAddingScreens.size(), 51, 42);
			else
				addNewPlayerBtn.setVisible(false);

		}

		revalidate();
		repaint();

	}

	public void removePotentialPlayer(NewPlayerAddingScreen toRemove) {
		remove(toRemove);
		newPlayerAddingScreens.remove(toRemove);
		redrawPotentialPlayers();
	}

	public void redrawPotentialPlayers() {
		for( int i = 0; i < newPlayerAddingScreens.size(); ++i) {
			newPlayerAddingScreens.get(i).setBounds(FIELDS_X, FIRST_FIELD_Y + SPACE_BETWEEN_FIELDS * i, 1040, 80);
		}

		addNewPlayerBtn.setBounds(830, FIRST_FIELD_Y + SPACE_BETWEEN_FIELDS * newPlayerAddingScreens.size(), 51, 42);

		if(newPlayerAddingScreens.size() != 8  )
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
