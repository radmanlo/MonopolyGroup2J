package gamePresenter;

import java.util.ArrayList;

import models.Dice;
import models.Player;
import models.PlayerColor;
import models.PotentialPlayer;
import models.Token;

public class GameManager {
	private static GameManager gameManager = null;
	
	//private PlayerManager playerMngr;
	//private BoardManager boardMngr;
	private Dice dice;
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
	
	public void initializeNewGame(ArrayList<PotentialPlayer> pL) {
		for(int i = 0; i< pL.size(); i++) {
			String name = pL.get(i).getName();
			Token token = pL.get(i).getToken();
			PlayerColor color = pL.get(i).getColor();
			int playerId = i;
			Player newPlayer = new Player(name, token, color, playerId);
			PlayerManager.getInstance().addPlayer(newPlayer);
			BankManager.getInstance().openAccount(newPlayer);
		}
		PlayerManager.getInstance().setInitialCurrentPlayer();
		BoardManager.getInstance();
		LocationManager.getInstance();
		CardManager.getInstance();
	}
	
	public static void executePurchase() {
		
	}
	
	public static void rollDice() {
		
	}
	
	
	public void handleNewTurn() {
		
	}
	
	private boolean payRent() {
		return false;
	}
	
	private void getAllTaxesFromMayfest() {
		
	}
	
	
}

