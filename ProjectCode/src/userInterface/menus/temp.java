package userInterface.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.PlayerColor;
import models.Token;
import java.awt.Font;

//This class exists for design purposes , ignore -G
public class temp extends JPanel{
	
	private JButton initializeGameBtn;
	private JButton addNewPlayerBtn;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> potentialNewPlayers;
	
	
	public temp() {
	setLayout(null);
	setBounds(0, 0, 1500, 900);
	
	choosableTokens = new ArrayList<Token>();
	choosableColors = new ArrayList<PlayerColor>();
	potentialNewPlayers = new ArrayList<NewPlayerAddingScreen>();
	
	///Menu Start
	JButton backBtn = new JButton("Go Back");
	backBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	backBtn.setBounds(33, 379, 289, 91);
	add(backBtn);
	
		///Menu Finish
	NewPlayerAddingScreen s1 = new NewPlayerAddingScreen();
	potentialNewPlayers.add(s1);
	s1.setBounds(400, 300, 1040, 70);
	
	add(s1);
	
	NewPlayerAddingScreen s2 = new NewPlayerAddingScreen();
	potentialNewPlayers.add(s2);
	s2.setBounds(400, 400, 1040, 70);
	
	add(s2);	
	
	addNewPlayerBtn = new JButton("+");
	addNewPlayerBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	addNewPlayerBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
	addNewPlayerBtn.setBounds(830, 480, 51, 42);
	add(addNewPlayerBtn);
	
	initializeGameBtn = new JButton("Start Game");
	initializeGameBtn.setBounds(33, 247, 289, 106);
	add(initializeGameBtn);
	}
	

	public void initializeNewGame() {
		
	}
	
	public void addPotentialPlayer() {
		
	}
	
	public void removePotentialPlayer(NewPlayerAddingScreen toRemove) {
		
	}
	
	public void updateComboBoxesForPotentialPlayers() {
		
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
