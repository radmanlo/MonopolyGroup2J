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
import userInterface.scene.Map;
import userInterface.scene.RentChoicePrompt;

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
		System.out.println("\ncurrent:" + LocationManager.getInstance().getPlayerLocation(PlayerManager.getInstance().getCurrentPlayer()));
		map.update(locationsList);
	}
	
	public void updateInteractionArea(PlayerManager playerMngr) {
		
	}
	
	public void updateDiceResults(Dice dice) {
		
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
}
