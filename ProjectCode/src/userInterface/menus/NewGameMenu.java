package userInterface.menus;

import java.util.ArrayList;
import javax.swing.JButton;
import models.Token;
import models.PlayerColor;

public class NewGameMenu extends Menu{

	private JButton initializeGameButton;
	private JButton addNewPlayerButton;
	private ArrayList<Token> choosableTokens;
	private ArrayList<PlayerColor> choosableColors;
	private ArrayList<NewPlayerAddingScreen> potentialNewPlayers;
	
	public NewGameMenu() {
		
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