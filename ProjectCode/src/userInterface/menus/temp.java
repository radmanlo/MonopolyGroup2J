package userInterface.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.PlayerColor;
import models.Token;

//This class exists for design purposes , ignore -G
public class temp extends JPanel{
	
	private JButton initializeGameButton;
	private JButton addNewPlayerButton;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> potentialNewPlayers;
	
	
	public temp() {
		///Menu Start
	setLayout(null);
	setBounds(0, 0, 1500, 900);
	
	JButton backBtn = new JButton("Back");
	backBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	backBtn.setBounds(10, 10, 89, 34);
	add(backBtn);
		///Menu Finish
		
		
		
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
