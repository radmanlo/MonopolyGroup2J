package gamePresenter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import models.*;
import models.location.*;
import settingsPresenter.LocalDataManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GameManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5272580467727107668L;

	private static GameManager gameManager = null;

	//private BoardManager boardMngr;
	private Dice dice = new Dice();
	//private PlayerManager playerMngr;
	//private BoardManager boardMngr;
	//private SoundManager soundMngr;
	//private BankManager bankMngr;

	private GameManager() {
		BoardManager.getInstance();
	}
	
	public static GameManager getInstance() {
		if( gameManager == null ) {
			gameManager = new GameManager();
		}
		return gameManager;
	}
	
	public void initializeNewGame(ArrayList<PotentialPlayer> pL, Document doc) {
		for(int i = 0; i< pL.size(); i++) {
			String name = pL.get(i).getName();
			Token token = pL.get(i).getToken();
			PlayerColor color = pL.get(i).getColor();
			int playerId = i;
			Player newPlayer = new Player(name, token, color, playerId);
			PlayerManager.getInstance().addPlayer(newPlayer);
			BankManager.getInstance().openAccount(newPlayer);
		}

		LocalDataManager.getInstance().initialize(doc);

//		PlayerManager.getInstance().setInitialCurrentPlayer();
		BoardManager.getInstance();
//		LocationManager.getInstance();
//		CardManager.getInstance();
		TradeManager.getInstance();
	}

	public void saveGame(String name) {
		LocalDataManager.getInstance().saveGame(name);
	}
	
	public static void rollDice() {
		// Get current player
		// roll the dice
		// move player's token
		// Activate the new Location
	}

	public static void tradeRequest(Property property, int value) {
		
	}


	public void handleNewTurn() { // Initializing a new turn
		// get the current player
		// view the player
		// view player's offers
	}

	public void getOfferInfo(){
		// return the offer's information
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

