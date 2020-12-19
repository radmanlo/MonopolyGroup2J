package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;

import models.Player;

public class PlayerManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4520295908155611891L;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	private static PlayerManager playerManager = null;

	public PlayerManager() {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = 0;
	}
	
	public PlayerManager(PlayerManager mngr) {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = mngr.currentPlayerIndex;
		// TODO Auto-generated constructor stub
		for(int i = 0; i < mngr.players.size(); i++) {
			Player plyr = new Player(mngr.players.get(i));
			this.players.add(plyr);
		}
		currentPlayer = players.get(currentPlayerIndex);
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

		currentPlayerIndex = (currentPlayerIndex + 1) % nOfPlayers;
		currentPlayer = players.get(currentPlayerIndex); // update the current player
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
	public void addPlayer(Player plyr) {
		players.add(plyr);
	}
	
	public void setInitialCurrentPlayer() {
		currentPlayerIndex = 0;
		currentPlayer = players.get(currentPlayerIndex);
	}

	public void create(PlayerManager mngr) {
		// TODO Auto-generated method stub
		playerManager = new PlayerManager(mngr);
	}
}
