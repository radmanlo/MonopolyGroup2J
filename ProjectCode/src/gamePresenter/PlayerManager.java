package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;

import models.BankAccount;
import models.Player;

public class PlayerManager implements Serializable{
	// Property
	private static final long serialVersionUID = -4520295908155611891L;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	private static PlayerManager playerManager = null;

	// Constructor
	public PlayerManager() {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = 0;
	}

	// Copy constructor
	public PlayerManager(PlayerManager mngr) {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = mngr.currentPlayerIndex;
		// TODO Auto-generated constructor stub
		for(int i = 0; i < mngr.players.size(); i++) {
			Player plyr = new Player(mngr.players.get(i));
			this.players.add(plyr);
		}
		currentPlayer = new Player(mngr.getCurrentPlayer());
	}

	// Operational methods
	public static PlayerManager getInstance() {
		if( playerManager == null ) {
			playerManager = new PlayerManager();
		}
		return playerManager;
	}

	// Game Logic methods
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	//Giving next Player
	public Player getNextPlayer(){
		int nOfPlayers = players.size();
		int nextPlayerIndex = (currentPlayerIndex + 1) % nOfPlayers;

		return players.get(nextPlayerIndex);
	}

	//Next player will be current player
	public void changeCurrentPlayer() {
		int nOfPlayers = players.size();

		currentPlayerIndex = (currentPlayerIndex + 1) % nOfPlayers;
		currentPlayer = players.get(currentPlayerIndex); // update the current player
	}
	
	//Returns all players
	public ArrayList<Player> getPlayers(){
		return players;
	}

	/**
	 * Returns Player By Searching for the ID
	 * @param id
	 * @return
	 */
	public Player getPlayerById(int id) {
		for(int i = 0; i < players.size(); i++) {
			if(id == players.get(i).getId()) {
				return players.get(i);
			}
		}
		return null;
	}

	/**
	 * After bankrupt we just remove player from the list
	 * @param plyr
	 */
	public void playerBankrupt(Player plyr) {
		removePlayer(plyr);
		currentPlayerIndex --;
	}

	//Remove Player from list
	public void removePlayer(Player plyr) {
		for(int i = 0; i< players.size(); i++) {
			if( plyr.getName() == players.get(i).getName()){
				players.remove(i);
			}
		}
	}

	//Add Player from list
	public void addPlayer(Player plyr) {
		players.add(plyr);
	}

	//Arranging initial values of current player
	public void setInitialCurrentPlayer() {
		currentPlayerIndex = 0;
		currentPlayer = players.get(currentPlayerIndex);
	}

	//Changing our Manager adding new datas
	public void create(PlayerManager mngr) {
		// TODO Auto-generated method stub
		set(mngr);
	}

	//Changing our Manager adding new datas
	public void set(PlayerManager mngr) {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = mngr.currentPlayerIndex;
		// TODO Auto-generated constructor stub
		for(int i = 0; i < mngr.players.size(); i++) {
			Player plyr = new Player(mngr.players.get(i));
			this.players.add(plyr);
		}
		currentPlayer = new Player(mngr.getCurrentPlayer());
	}
	
	//Remove amount of money from the given player
	public void deductMoneyFromPlayer(Player player, int amount){
		player.setUsableMoney(player.getUsableMoney() - amount);
	}

	//Add amount of money from the given player
	public void addMoneyToPlayer(Player player, int amount){
		player.setUsableMoney(player.getUsableMoney() + amount);
	}
	
	/*
	 * takes the name of the player and returns it
	 */
	public Player getPlayerByName(String name) {
		
		for(int i = 0 ; i< players.size(); i++) {
			if(players.get(i).getName().equals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	//Design Manager for Ready Load Data 
	public void readyForInitialize() {
		players = new ArrayList<Player>();
		currentPlayer = null;
		currentPlayerIndex = 0;
	}
}
