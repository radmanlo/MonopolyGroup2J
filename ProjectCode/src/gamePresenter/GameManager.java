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
		disableDice();
	}

	public int totalDiceResultForUtility() {
		return this.dice.getTotalResult();
	}
	
	
	//this is a method for testing only - we'll delete it before releasing -G
	public void rollDiceForTesting() {
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

		movePlayer(currentPlayer, 1);
	}
	
	public void disableBuyIfSameOwner() {
		//BoardManager.getInstance()
		BoardManager.getInstance().disableBuyButton();
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
			boolean warningAns = false; // true to continue and try to sell stuff (probably to bank)
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
		BoardManager.getInstance().enableDice();
	}

	/**
	 *
	 */
	public void disableDice(){
		BoardManager.getInstance().disableDice();
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
		// Move player
		Location newLocation = LocationManager.getInstance().movePlayer(player, distance);

		// Activate the new Location
		LocationManager.getInstance().activateLocation(newLocation);

		// Update UI
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public void updateUI(){
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public void executePurchase(){
		// Variables
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
		int locationPrice = curLocation.getPrice();

		// Process
		if (curPlayer.getUsableMoney() >= locationPrice){
			LocationManager.getInstance().setLocationOwner(curLocation, curPlayer);
			PlayerManager.getInstance().getCurrentPlayer().addOwnedLocation((BuyableLocation)LocationManager.getInstance().getPlayerLocation(PlayerManager.getInstance().getCurrentPlayer()));
			PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, locationPrice);
			System.out.println("Im in execute purchase");
			System.out.println("Im have disabled buy b utton");
			this.disableBuyIfSameOwner();
		}


		// Update UI
		BoardManager.getInstance().updateInteractionArea();

		BoardManager.getInstance().updateMap();
	}
	public void sellProperty(BuyableLocation loc) {
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		if(loc.getCurrentRentValue() == loc.getAllRentValues().get(0)) {
			loc.setOwner(null);
			curPlayer.removeOwnedLocation(loc);
			curPlayer.setUsableMoney(curPlayer.getUsableMoney()+ loc.getPrice() - 20);
		}else {
			return;
		}

	}
	/**
	 * Asks the user for their preference
	 * called from LocationManager buyables activation methods
	 */
	public void askPlayerPaymentChoice(){
		boolean payWithDice = false; // TODO get use input (false is temporary)

		if (payWithDice)
			payRentWithDice();
		else // normal payment
			payRent();

		// Update UI
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public void payRentWithDice(){
		// Variables
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
		Player locationOwner = curLocation.getOwner();

		// Process
		this.dice.rollDices();
		if (!this.dice.isDoubleDice()){
			// Pay double rent
			LocationManager.getInstance().deductRentValue(locationOwner, curPlayer, curLocation.getRentValue()*2);
		}

		// Update UI
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	public void payRent(){
		// Variables
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
		Player locationOwner = curLocation.getOwner();

		// Process
		LocationManager.getInstance().deductRentValue(locationOwner, curPlayer, curLocation.getRentValue());

		// Update UI
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

	/*
	 * gets the name of the property to be upgraded and upgrades it 
	 */
	public void upgradeProperty(String nameOfProperty) { 
		Property aProperty = (Property) LocationManager.getInstance().getLocationByName(nameOfProperty);
		boolean propertyUpgradable = LocationManager.getInstance().isPropertyUpgradeable(aProperty);
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int upgradeCost = aProperty.getUpgradeCost();

		if (propertyUpgradable && curPlayer.getUsableMoney() > upgradeCost){
			aProperty.upgrade();
			curPlayer.setUsableMoney(curPlayer.getUsableMoney() - upgradeCost);
		}
	}
	
	/*
	 * gets the name of the property to be upgraded and degrades it 
	 */
	public void degradeProperty(String nameOfProperty) {
		Property aProperty = (Property) LocationManager.getInstance().getLocationByName(nameOfProperty);
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int upgradeCost = aProperty.getUpgradeCost();

		aProperty.degrade();
		curPlayer.setUsableMoney(curPlayer.getUsableMoney() + upgradeCost);
	}
	
	/*
	 * gets the name of the property to be upgraded and sells it 
	 */
	public void sellProperty(String nameOfProperty) {
		Property aProperty = (Property) LocationManager.getInstance().getLocationByName(nameOfProperty);
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int price = aProperty.getPrice();
		price = (int)(0.8 * price);

		if (aProperty.getVendingMachinesNo() == 0 && !aProperty.hasStarbucks()){
			aProperty.resetToDefault();
			curPlayer.setUsableMoney(curPlayer.getUsableMoney() + price);
		}
	}
	public void useCardByName(String cardName) {
		ArrayList<Card> deck = CardManager.getInstance().getCardDeck();
		for(int i = 0; i < deck.size(); i++) {
			if(deck.get(i).getCardName().equals(cardName)) {
				CardManager.getInstance().executeCardAction(CardManager.getInstance().getCardDeck().get(i));
			}
		}
	}
	
	/*
	 * gets the name of the card and executes it
	 */
	public void useCard(Card card) {
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		CardManager.getInstance().executeCardAction(card);
		curPlayer.removeCard(card);
	}

//	public static boolean upgradeProperty(Property property) { // Let's have it in location's activate() method
//		return false;
//	}
	
//	public static LocationManager getInventoryManager() { // What does it do?
//		return null;
//	}
}

