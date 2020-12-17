package userInterface.menus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import models.Token;
import models.PlayerColor;

public class NewGameMenu extends Menu{

	private JButton initializeGameBtn;
	private JButton addNewPlayerBtn;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> potentialNewPlayers;
	
	final int SPACE_BETWEEN_FIELDS = 90;
	final int FIRST_FIELD_Y = 100;
	final int FIELDS_X = 400;

	public NewGameMenu() {

		choosableTokens = new ArrayList<Token>();
		choosableTokens.add(new Token("./resources/Token1.jpg"));
		choosableTokens.add(new Token("./resources/Token2.jpg"));
		choosableTokens.add(new Token("./resources/Token3.jpg"));
		
		choosableColors = new ArrayList<PlayerColor>();
		choosableColors.add(PlayerColor.RED);
		choosableColors.add(PlayerColor.GREEN);
		choosableColors.add(PlayerColor.BLUE);
		choosableColors.add(PlayerColor.YELLOW);
		choosableColors.add(PlayerColor.PURPLE);
		choosableColors.add(PlayerColor.WHITE);
		choosableColors.add(PlayerColor.BLACK);
		
		potentialNewPlayers = new ArrayList<NewPlayerAddingScreen>();

		setLayout(null);
		setBounds(0, 0, 1500, 900);

		backBtn.setText("Go Back");
		backBtn.setBounds(33, 379, 289, 91);


		NewPlayerAddingScreen s1 = new NewPlayerAddingScreen(this);
		potentialNewPlayers.add(s1);
		add(s1);

		NewPlayerAddingScreen s2 = new NewPlayerAddingScreen(this);
		potentialNewPlayers.add(s2);
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
		add(initializeGameBtn);
		
		redrawPotentialPlayers();
	}


	public void initializeNewGame() {

	}

	public void addPotentialPlayer() {
		if( potentialNewPlayers.size() < 8 ) {
			
			NewPlayerAddingScreen sTemp = new NewPlayerAddingScreen(this);
			potentialNewPlayers.add(sTemp);
			add(sTemp);
			
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
	
	public void updateComboBoxesForPotentialPlayers() {
		for( int i = 0; i < potentialNewPlayers.size(); ++i) {
			potentialNewPlayers.get(i).updateComboBoxes();
		}
	}

	public ArrayList<Token> getChoosableTokens() {
		return choosableTokens;
	}

	public void addChoosableToken(Token toAdd) {
		choosableTokens.add(toAdd);
	}

	public void removeChoosableToken(Token toRemove) {
		choosableTokens.remove(toRemove);
	}

	public ArrayList<PlayerColor> getChoosableColors() {
		return choosableColors;
	}

	public void addChoosableColor(PlayerColor toAdd) {
		choosableColors.add(toAdd);
	}

	public void removeChoosableColor(PlayerColor toRemove) {
		choosableColors.remove(toRemove);
	}

}
