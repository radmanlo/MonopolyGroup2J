package userInterface.menus;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import gamePresenter.BankManager;
import gamePresenter.GameManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;
import gamePresenter.TradeManager;
import models.Token;
import models.PlayerColor;
import org.w3c.dom.Document;
import settingsPresenter.LocalDataManager;
import models.PotentialPlayer;
import userInterface.components.RoundedButton;
import userInterface.components.RoundedPanel;
import utilities.Utils;

public class NewGameMenu extends Menu{


	private RoundedButton initializeGameBtn;
	private RoundedButton addNewPlayerBtn;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> newPlayerAddingScreens;
	private ArrayList<PotentialPlayer> potentialPlayers;
	private JPanel addPlayerPanel;
	final int SPACE_BETWEEN_FIELDS = 30;
	final int FIRST_FIELD_Y = 100;
	final int FIELDS_X = 700;
	final int ADD_PLAYER_PANEL_WIDTH = 1080;
	final int ADD_PLAYER_ITEM_HEIGHT = 85;
	final int ADD_PLAYER_PANEL_HEIGHT = 750;

	public NewGameMenu() {
//		super("./resources/new-game-menu-bg.png");
		super("./resources/baseBackground.png");
		setBackground(Utils.getBgColor());

		// Create a separate panel for adding new players
		drawAddPlayerPanel();


		choosableTokens = new ArrayList<Token>();
		choosableTokens.add(new Token("./resources/piece1.png"));
		choosableTokens.add(new Token("./resources/piece2.png"));
		choosableTokens.add(new Token("./resources/piece3.png"));
		choosableTokens.add(new Token("./resources/piece4.png"));
		choosableTokens.add(new Token("./resources/piece5.png"));
		choosableTokens.add(new Token("./resources/piece6.png"));
		choosableTokens.add(new Token("./resources/piece7.png"));
		choosableTokens.add(new Token("./resources/piece8.png"));

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
		setBounds(100, 100, getWidth(), getHeight());

		backBtn.setLabel("Go Back");
		backBtn.setBounds(150, 190, 500, 70);

		// Initialize the first two players
		NewPlayerAddingScreen s1 = new NewPlayerAddingScreen(this, 1);
		newPlayerAddingScreens.add(s1);

		NewPlayerAddingScreen s2 = new NewPlayerAddingScreen(this, 2);
		newPlayerAddingScreens.add(s2);

		addNewPlayerBtn = new RoundedButton("Add Player");
		addNewPlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPotentialPlayer();
			}
		});

		addNewPlayerBtn.setBounds(FIELDS_X, ADD_PLAYER_PANEL_HEIGHT + 130, 300, 70);
		add(addNewPlayerBtn);

		initializeGameBtn = new RoundedButton("Start Game");
		initializeGameBtn.setBounds(150, 100, 500, 70);
		initializeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeNewGame();
			}
		});
		add(initializeGameBtn);

		drawInitPlayers();
		redrawPotentialPlayers();
	}

	/**
	 * Draws the addPlayer panel, adds two initial players and add new player button
	 */
	public void drawInitPlayers() {
		for (int i = 0; i < newPlayerAddingScreens.size(); ++i) {
			NewPlayerAddingScreen np = newPlayerAddingScreens.get(i);
			np.setBounds(20, i * ADD_PLAYER_ITEM_HEIGHT + SPACE_BETWEEN_FIELDS, ADD_PLAYER_PANEL_WIDTH - 40, ADD_PLAYER_ITEM_HEIGHT);
			addPlayerPanel.add(np);
		}
		addPlayerPanel.revalidate();
		addPlayerPanel.repaint();
	}

	/***
	 * Initializes addNewPlayer panel and its components
	 */
	public void drawAddPlayerPanel() {

		addPlayerPanel = new RoundedPanel(new Dimension(100, 100));
		addPlayerPanel.setBounds(FIELDS_X, 100, ADD_PLAYER_PANEL_WIDTH, ADD_PLAYER_PANEL_HEIGHT);
		addPlayerPanel.setLayout(null);
		addPlayerPanel.setBackground(new Color(230, 112, 112, 38));

		JLabel name  = createLabel("Name");
		JLabel color = createLabel("Color");
		JLabel token = createLabel("Token");

		// 290 is calculated from the width of namefield
		name.setBounds(290, 5, 60, 30);
		name.setForeground(Color.WHITE);
		addPlayerPanel.add(name);

		// 660 is calculated from the width of color field
		color.setBounds(660, 5, 60, 30);
		color.setForeground(Color.WHITE);
		addPlayerPanel.add(color);

		// 810 is calculated from the width of color field
		token.setBounds(810, 5, 60, 30);
		token.setForeground(Color.WHITE);
		addPlayerPanel.add(token);

		add(addPlayerPanel);
	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont((new Font("Tahoma", Font.PLAIN, 20)));
		return label;
	}



	public ArrayList<PotentialPlayer> getPotentialPlayers() {
		return potentialPlayers;
	}


	public void initializeNewGame() {
		if(areAllPlayersUnique() == false) {
			showMessageDialog(null, "Tokens, names or colors can not be same! \nName can not be empty");
		}else {
			PlayerManager.getInstance().readyForInitialize();
			LocationManager.getInstance().readyForInitialize();
			TradeManager.getInstance().readyForInitialize();
			BankManager.getInstance().readyForInitialize();
			potentialPlayers = new ArrayList<PotentialPlayer>();
			// Ask the local data manager to get the default values' document
			LocalDataManager ldm = LocalDataManager.getInstance();
			Document doc = ldm.getDefaultValues();
			doc.getDocumentElement().normalize();

			for(int i = 0; i < newPlayerAddingScreens.size(); i++) {
				Token plyrToken = tokenMatch(i);
				PlayerColor plyrClr = colorMatch(i);
				String plyrName = newPlayerAddingScreens.get(i).getChosenName();
				PotentialPlayer player = new PotentialPlayer(plyrToken, plyrClr, plyrName);
				potentialPlayers.add(player);
			}

			// Pass the document to the game manager alongside the potential players array
			GameManager.getInstance().initializeNewGame(potentialPlayers, doc);
			MenuManager.getInstance().openMenu(6);

			return;
		}
	}
	
	public Token tokenMatch(int index) {
		
		Token token;
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 0)
		{
			token = new Token("./resources/piece1.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 1)
		{
			token = new Token("./resources/piece2.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 2)
		{
			token = new Token("./resources/piece3.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 3)
		{
			token = new Token("./resources/piece4.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 4)
		{
			token = new Token("./resources/piece5.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 5)
		{
			token = new Token("./resources/piece6.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 6)
		{
			token = new Token("./resources/piece7.png");
			return token;
		}
		if(newPlayerAddingScreens.get(index).getChosenTokenIndex() == 7)
		{
			token = new Token("./resources/piece8.png");
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

			NewPlayerAddingScreen sTemp = new NewPlayerAddingScreen(this, newPlayerAddingScreens.size() + 1);
			newPlayerAddingScreens.add(sTemp);
			addPlayerPanel.add(sTemp);

			redrawPotentialPlayers();

			if(newPlayerAddingScreens.size() < 8) {
				addNewPlayerBtn.setEnabled(true);
			}
			else {
				addNewPlayerBtn.setEnabled(false);
			}

		}

		addPlayerPanel.revalidate();
		addPlayerPanel.repaint();
	}

	public void removePotentialPlayer(NewPlayerAddingScreen toRemove) {
		addPlayerPanel.remove(toRemove);
		newPlayerAddingScreens.remove(toRemove);
		redrawPotentialPlayers();
	}

	public void redrawPotentialPlayers() {
		for( int i = 0; i < newPlayerAddingScreens.size(); ++i) {
			// (i * 100 + SPACE_BETWEEN_FIELDS) to calcualte the size of a tile plus space
			// (ADD_PLAYER_ITEM_WIDTH - 40) to get horizontal padding
			newPlayerAddingScreens.get(i).setBounds(20, i * ADD_PLAYER_ITEM_HEIGHT + SPACE_BETWEEN_FIELDS, ADD_PLAYER_PANEL_WIDTH - 40, ADD_PLAYER_ITEM_HEIGHT);
		}

		if(newPlayerAddingScreens.size() < 8 )
			addNewPlayerBtn.setVisible(true);
		else
			addNewPlayerBtn.setVisible(false);


		addPlayerPanel.revalidate();
		addPlayerPanel.repaint();
	}

	public ArrayList<Token> getChoosableTokens() {
		return choosableTokens;
	}

	public ArrayList<PlayerColor> getChoosableColors() {
		return choosableColors;
	}

}
