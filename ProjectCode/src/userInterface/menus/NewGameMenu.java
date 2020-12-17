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
		initializeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeNewGame();
			}
		});
		add(initializeGameBtn);

		redrawPotentialPlayers();
	}


	public void initializeNewGame() {

		if(areAllPlayersUnique() == false) {
			
			showMessageDialog(null, "Herkes farklı değil!");


		}else {
			System.out.println("True Döndüm Areallplayersunieque ");

		}
	}
	
	private boolean areAllPlayersUnique() {

		for( int i = 0; i < potentialNewPlayers.size(); i++) {
			potentialNewPlayers.get(i).updateChosens();
		}
		
		for( int i = 0; i < potentialNewPlayers.size(); ++i) {
			for( int j = i+1; j < potentialNewPlayers.size(); j++) {
				if( potentialNewPlayers.get(i).getChosenName().equalsIgnoreCase( potentialNewPlayers.get(j).getChosenName())) {
					return false;
				}
			}
		}
		
		for( int i = 0; i < potentialNewPlayers.size(); i++) {
			for( int j = i+1; j < potentialNewPlayers.size(); j++) {
				if( potentialNewPlayers.get(i).getChosenToken().getImage().equals( potentialNewPlayers.get(j).getChosenToken().getImage()) ){
					//return false;
					System.out.println("Equal Comp. Of Images Doesnt Work On New Game Menu Class");

				}
			}
		}
		
		for( int i = 0; i < potentialNewPlayers.size(); i++) {
			for( int j = 0; j < potentialNewPlayers.size(); j++) {
				//if( i != j && potentialNewPlayers.get(i).getChosenColor() ==  potentialNewPlayers.get(j).getChosenColor())
				//	return false;
			}
		}
		return true;
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

	public ArrayList<Token> getChoosableTokens() {
		return choosableTokens;
	}

	public ArrayList<PlayerColor> getChoosableColors() {
		return choosableColors;
	}

}
