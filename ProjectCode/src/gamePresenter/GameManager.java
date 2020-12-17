package gamePresenter;

import models.Dice;

public class GameManager {
	private static GameManager gameManager = null;
	
	private PlayerManager playerMngr;
	private BoardManager boardMngr;
	private Dice dice;
	private SoundManager soundMngr;

	private GameManager() {
		boardMngr = BoardManager.getInstance();
	}
	
	public static GameManager getInstance() {
		if( gameManager == null ) {
			gameManager = new GameManager();
		}
		return gameManager;
	}
	
	public static void executePurchase() {
		
	}
	
	public static void rollDice() {
		
	}
	
	
	public void handleNewTurn() {
		
	}
	
	private boolean payRent() {
		return false;
	}
	
	private void getAllTaxesFromMayfest() {
		
	}

	public BoardManager getBoardMngr() {
		return boardMngr;
	}
	
	
}

