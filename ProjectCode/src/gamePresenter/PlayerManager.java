package gamePresenter;

import java.util.ArrayList;

import models.Player;

public class PlayerManager {
	private Player currentPlayer;
	private ArrayList<Player> players;
	private int currentPlayerIndex;
	
	public PlayerManager() {
		
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void changeCurrentPlayer() {
		
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}
