package gamePresenter;

import java.util.ArrayList;

import models.Player;

public class PlayerManager {
	private Player currentPlayer;
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	private static PlayerManager playerManager = null;

	public PlayerManager() {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = 0;
	}
	
	public static PlayerManager getInstance() {
		if( playerManager == null ) {
			playerManager = new PlayerManager();
		}
		return playerManager;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void changeCurrentPlayer() {
		int nOfPlayers = players.size();
//If last player in the list is played and needs change
		if(currentPlayerIndex == nOfPlayers) {
			currentPlayerIndex = 0;
		}else {
// If current player was not last player 
			currentPlayerIndex++;
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
// Returns Player By Search ID
	public Player getPlayerById(int id) {
		for(int i = 0; i < players.size(); i++) {
			if(id == players.get(i).getId()) {
				return players.get(i);
			}
		}
		return null;
	}
	// After bankrupt we just remove player from the list
	public void bankruptPlayer(Player plyr) {
		removePlayer(plyr);
	}
	
	public void removePlayer(Player plyr) {
		for(int i = 0; i< players.size(); i++) {
			if( plyr.getName() == players.get(i).getName()){
				players.remove(i);
			}
		}
	}
}