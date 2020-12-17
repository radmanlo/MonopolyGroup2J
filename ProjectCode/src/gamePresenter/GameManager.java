package gamePresenter;

import models.Dice;
import models.Player;
import models.Property;

public class GameManager {
	private static GameManager gameManager = null;
	
	private PlayerManager playerMngr = PlayerManager.getInstance();
	private BoardManager boardMngr;
	private Dice dice = new Dice();
	private SoundManager soundMngr = SoundManager.getInstance();
	private CardDeck cardDeck = new CardDeck();
	private LocationManager locationManager = LocationManager.getInstance();

	private GameManager() {
		boardMngr = new BoardManager();
	}

	public void initGame(){

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
	
	public static void tradeRequest(Property property, int value) {
		
	}
	
	public static boolean upgradeProperty(Property property) {
		return false;
	}
	
	public static LocationManager getInventoryManager() {
		return null;
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

