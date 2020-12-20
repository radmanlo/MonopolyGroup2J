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
		BoardManager.getInstance().updateInteractionArea();
		
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
		Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int moveDistance = 0;
		if(PlayerManager.getInstance().getCurrentPlayer().getIsInJail() == true) {
			PlayerManager.getInstance().getCurrentPlayer().setInJailCount(PlayerManager.getInstance().getCurrentPlayer().getInJailCount() - 1);
			if(PlayerManager.getInstance().getCurrentPlayer().getInJailCount() <= 0) {
				PlayerManager.getInstance().getCurrentPlayer().setIsInJail(false);
				return;
			}
			return;
		}
		do {
			this.dice.rollDices();
			moveDistance += this.dice.getTotalResult();
		}while(this.dice.isDoubleDice());
        BoardManager.getInstance().updateMap();
        BoardManager.getInstance().updateInteractionArea();

		// move player's token
		movePlayer(currentPlayer, moveDistance);
	}

	public int totalDiceResultForUtility() {
		return this.dice.getTotalResult();
	}

	/**
	 * Gets called when the game just started or when player presses EndTurn
	 * Gets all the information about the current player and passes them to the view
	 */
	public void handleNewTurn() { // Initializing a new turn Basically view players info on the screen
		// TODO Disable dice if player is in Jail (canRollDice == false)
		// TODO when player uses outOfJail card rollDice is enabled
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		// TODO handle if only one player left
		if (this.gameEnded()){
			this.declareWinner(curPlayer);
			return;
		}

		// TODO get the current player
		Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
		// TODO view the player
		System.out.println(currentPlayer);
		// TODO view player's offers
		ArrayList<TradeDeal> playerDeals = TradeManager.getInstance().getTradeDeals(currentPlayer);
	}

	/**
	 * Handles the EndTurn button, change the turn and handles new turn
	 * checks if a player is in jail and update their jail status
	 */
	public void handleEndTurn(){
		Player prevPlayer = PlayerManager.getInstance().getCurrentPlayer();

		// Handle player bankruptcy situation
		if (this.isBankrupt(prevPlayer)){
			// TODO Give warning and ask whether to end turn and go bankrupt or continue and sell some property
			boolean warningAns = true; // true to continue and try to sell stuff (probably to bank)
			if (warningAns){
				return;
			} else {
				this.declarePlayerBankrupt(prevPlayer);
			}
		}

		// Move forward with the next player
		PlayerManager.getInstance().changeCurrentPlayer();
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		if (curPlayer.getIsInJail() && curPlayer.getInJailCount() < 3){
			curPlayer.setInJailCount(curPlayer.getInJailCount() + 1);
			this.disableDice();
			this.handleNewTurn();
		} else {
			curPlayer.setIsInJail(false);
			this.enableDice();
			this.handleNewTurn();
		}
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	/**
	 *
	 * @param player
	 */
	public boolean isBankrupt(Player player){
		if (player.getUsableMoney() <= 0){
			return true;
		}
		return false;
	}

	public void declarePlayerBankrupt(Player player){
		LocationManager.getInstance().freeAllLocationsOf(player);
		PlayerManager.getInstance().playerBankrupt(player);
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public boolean currentPlayerHasDeal() {
		ArrayList<TradeDeal> playerDeals = TradeManager.getInstance().getTradeDeals(PlayerManager.getInstance().getCurrentPlayer());
		if(playerDeals.size() == 0)
			return false;
		return true;
	}
	
	public ArrayList<TradeDeal> currentPlayerDeals(){
		ArrayList<TradeDeal> playerDeals = TradeManager.getInstance().getTradeDeals(PlayerManager.getInstance().getCurrentPlayer());
		return playerDeals;
	}
	
	public void declareWinner(Player winner){
		// TODO some UI stuff for winner
	}

	/**
	 *
	 */
	public void enableDice(){
		// TODO tell the UI to re-enable the dice
	}

	/**
	 *
	 */
	public void disableDice(){
		// TODO tell the UI to re-enable the dice
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

	/**
	 * Asks the Location manager to move the player and update the UI
	 * @param player
	 * @param distance
	 */
	public void movePlayer(Player player, int distance){

		Location oldLocation = LocationManager.getInstance().getPlayerLocation(player);
		Location newLocation = LocationManager.getInstance().movePlayer(player, distance);
		PlayerManager.getInstance().getCurrentPlayer().getLocation().activate();
		LocationManager.getInstance().getPlayerLocation(PlayerManager.getInstance().getCurrentPlayer()).activate();
		
		//int oldLocationId = oldLocation.getLocationId();
		//int newLocationId = newLocation.getLocationId();



		// Activate the new Location
		newLocation.activate();
		BoardManager.getInstance().updateMap();
	}

	public void executePurchase(){
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
		int locationPrice = curLocation.getPrice();

		if (curPlayer.getUsableMoney() >= locationPrice){
			LocationManager.getInstance().setLocationOwner(curLocation, curPlayer);
			PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, locationPrice);
		}
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	/**
	 * Asks the user for their preference
	 * called from LocationManager buyables activation methods
	 */
	public void askPlayerPaymentChoice(){
		// TODO prompt for payment choice
		// pay with dice
		payRentWithDice();

		// normal payment
		payRent();
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public void payRentWithDice(){
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
		Player locationOwner = curLocation.getOwner();

		this.dice.rollDices();
		if (!this.dice.isDoubleDice()){
			// Pay double rent
			LocationManager.getInstance().deductRentValue(locationOwner, curPlayer, curLocation.getRentValue()*2);
		}
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public void payRent(){
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
		Player locationOwner = curLocation.getOwner();
		LocationManager.getInstance().deductRentValue(locationOwner, curPlayer, curLocation.getRentValue());
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public boolean checkPassingStartReward(int oldLocationId, int newLocationId){
		
		// TODO probably location Manager is a better place to do this?
		boolean giveReward = false;
		int distance = Math.abs(oldLocationId - newLocationId); // Just in case the player turns around the bord and end
															// up in a location > old location (probably not possible)

		if (distance > 40 || newLocationId < oldLocationId){
			giveReward = true;
		}
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
		return giveReward;
	}

	public boolean gameEnded(){
		if (PlayerManager.getInstance().getPlayers().size() == 1){
			BoardManager.getInstance().updateMap();
			BoardManager.getInstance().updateInteractionArea();
			return true;
		}
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
		return false;
	}

//	public static boolean upgradeProperty(Property property) { // Let's have it in location's activate() method
//		return false;
//	}
	
//	public static LocationManager getInventoryManager() { // What does it do?
//		return null;
//	}
}

