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
	
	
}

