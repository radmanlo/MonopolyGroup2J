package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.*;
import models.location.BuyableLocation;
import models.location.Location;
import userInterface.menus.MenuManager;
import userInterface.scene.EndTurnPrompt;
import userInterface.scene.InteractionArea;
import userInterface.scene.InventoryScreen;
import userInterface.scene.Map;
import userInterface.scene.RentChoicePrompt;
import userInterface.scene.TradeScreen;

public class BoardManager extends JPanel implements Serializable{    

/**
	 * 
	 */
	private static final long serialVersionUID = -5498787588296943523L;
	private static BoardManager boardManager = null;
	
	private Map map;
	private InteractionArea interactionArea;
	private RentChoicePrompt rentChoicePrompt;
	private EndTurnPrompt endturnPrompt;
	private TradeScreen tradeScreen;
	private InventoryScreen inventoryScreen;
	
	
	private BoardManager() {
		setBounds(0, 0, 1900, 1000);
		setLayout(null);
		map = new Map();
		add(map);
		interactionArea = new InteractionArea();
		add(interactionArea);
	}
	
	public static BoardManager getInstance() {
		if( boardManager == null ) {
			boardManager = new BoardManager();
		}
		return boardManager;
	}
	
	public void updateMap() {
		ArrayList<Location> locationsList;
		locationsList = LocationManager.getInstance().getLocationList();
		map.update(locationsList);
	}
	
	public void updateInteractionArea() {
		interactionArea.setCurrentPlayerMoneyLbl(PlayerManager.getInstance().getCurrentPlayer().getUsableMoney());
		interactionArea.setDiceRollResultLbl(GameManager.getInstance().totalDiceResultForUtility());
		decideEnabledorNotForBuyBtn();
		interactionArea.update();
		interactionArea.revalidate();
		interactionArea.repaint();
	}
	
	
	private void pauseGame() {  //should be public I think -G
		
	}
	
	public void displayRentChoicePrompt() {
		
	}
	
	public void hideRentChoicePrompt() {
		
	}
	
	public void displayEndTurnPrompt() {
		
	}
	
	public void hideEndTurnPrompt() {
		
	}

	public void openInventoryScreen(Player player) {
		inventoryScreen = new InventoryScreen(player);
		add(inventoryScreen, 0);
		revalidate();
		repaint();
	}
	public void disableBuyButton() {
		interactionArea.getBuyButton().setEnabled(false);
	}
	
	public void enableBuyButton() {
		interactionArea.getBuyButton().setEnabled(true);
	}
	
	public void enableDice() {
		interactionArea.getRollDiceButton().setEnabled(true);
	}

	public void disableDice() {
		interactionArea.getRollDiceButton().setEnabled(false);
	}
	
	public void decideEnabledorNotForBuyBtn() {
		
		//System.out.println(LocationManager.getInstance().isPlaceBuyable() + "");
		
		if(LocationManager.getInstance().isPlaceBuyable())
			interactionArea.buyBtn.setEnabled(true);
		else
			interactionArea.buyBtn.setEnabled(false);
	}
}
