package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import models.*;
import models.location.*;
import settingsPresenter.LocalDataManager;
import org.w3c.dom.Document;

public class GameManager implements Serializable {

	// Properties
	private static final long serialVersionUID = -5272580467727107668L;
	private static GameManager gameManager = null;
	private Dice dice = new Dice();

	// Constructor
	private GameManager() {
		BoardManager.getInstance();
	}

	public GameManager(GameManager mngr) {
		// TODO Auto-generated constructor stub
		// TODO ADD MAYBE NEEDED
	
	}

	// Operational Methods
	public static GameManager getInstance() {
		if( gameManager == null ) {
			gameManager = new GameManager();
		}
		return gameManager;
	}
	
	
	
	public void initializeNewGame(ArrayList<PotentialPlayer> pL, Document doc) {
		LocalDataManager.getInstance().initialize(doc);
		
		for(int i = 0; i< pL.size(); i++) {
			String name = pL.get(i).getName();
			Token token = pL.get(i).getToken();
			PlayerColor color = pL.get(i).getColor();
			int playerId = i;
			Player newPlayer = new Player(name, token, color, playerId);
			PlayerManager.getInstance().addPlayer(newPlayer);
			BankManager.getInstance().openAccount(newPlayer);
			LocationManager.getInstance().getLocationList().get(36).addPlayerHere(newPlayer);
		}

		

		PlayerManager.getInstance().setInitialCurrentPlayer();
		BoardManager.getInstance();
		LocationManager.getInstance();
		CardManager.getInstance();
		TradeManager.getInstance();
	}

	public void saveGame(String name) {
		LocalDataManager.getInstance().saveGame(name);
	}

	// Game Logic Methods
	/**
	 * When player press RollDice this gets executed
	 * it moves the token and activates the new location
	 */
	public void rollDice() {
		// Get current player
		Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
		Location newLocation = null;

		// roll the dice
		int moveDistance = 0;

		do {
			this.dice.rollDices();
			moveDistance += this.dice.getTotalResult();
		}while(this.dice.isDoubleDice());

		// move player's token
		newLocation = LocationManager.getInstance().movePlayer(currentPlayer, moveDistance);

		// todo Update the view

		// Activate the new Location
		newLocation.activate();
		BoardManager.getInstance().updateMap();
	}
	
	public int totalDiceResultForUtility() {
		return this.dice.getTotalResult();
	}

	/**
	 * Gets called when the game just started or when player presses EndTurn
	 * Gets all the information about the current player and passes them to the view
	 */
	public void handleNewTurn() { // Initializing a new turn Basically view players info on the screen
		// get the current player
		Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
		// view the player
		System.out.println(currentPlayer);
		// view player's offers
		ArrayList<TradeDeal> playerDeals = TradeManager.getInstance().getTradeDeals(currentPlayer);
	}

	/**
	 * Handles the EndTurn button, change the turn and handles new turn
	 */
	public void handleEndTurn(){
		PlayerManager.getInstance().changeCurrentPlayer();
		this.handleNewTurn();
	}

	/**
	 * Create a trade request
	 * @param property
	 * @param value
	 */
	public void tradeRequest(Property property, int value) {

	}

	/**
	 * Gets the information about a particular trade
	 */
	public void getOfferInfo(){
		// return the offer's information
	}

	public void create(GameManager mngr) {
		// TODO Auto-generated method stub
		gameManager = new GameManager(mngr);
	}


//	public static void executePurchase() { // Let's have it in location's activate() method
//
//	}
	
//	public static boolean upgradeProperty(Property property) { // Let's have it in location's activate() method
//		return false;
//	}
	
//	public static LocationManager getInventoryManager() { // What does it do?
//		return null;
//	}
	
//	private boolean payRent() { // Let's have it in location's activate() method
//		return false;
//	}
	
//	private void getAllTaxesFromMayfest() { // Let's have it in location's activate() method
//
//	}
	
	
}

