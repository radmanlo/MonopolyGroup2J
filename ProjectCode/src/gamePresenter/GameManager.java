package gamePresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import models.*;
import models.location.*;
import settingsPresenter.LocalDataManager;
import org.w3c.dom.Document;

import userInterface.menus.MenuManager;
import userInterface.scene.InteractionArea;

import javax.swing.*;

public class GameManager implements Serializable {

	// Properties
	private static final long serialVersionUID = -5272580467727107668L;
	private static GameManager gameManager = null;
	private Dice dice = new Dice();
	private Timer diceAnimationTimer;

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

	//Initializes new game with given potential players and doc field 
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

	//Calls save game 
	public void saveGame(String name) {
		LocalDataManager.getInstance().saveGame(name);
	}

	int total = 0;

	// Game Logic Methods
	/**
	 * When player press RollDice this gets executed
	 * it moves the token and activates the new location
	 */
	public void rollDice() {
		/*	for(int i = 0; i < CardManager.getInstance().getCardDeck().size(); i++)
			System.out.println(CardManager.getInstance().getCardDeck().get(i).getDescription());
		System.out.println("------------**********- Card sieze : "+ CardManager.getInstance().getCardDeck().size());
		 */
		Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int moveDistance = 0;
		if(PlayerManager.getInstance().getCurrentPlayer().getIsInJail() == true || PlayerManager.getInstance().getCurrentPlayer().getInJailCount() > 0) {
			PlayerManager.getInstance().getCurrentPlayer().setInJailCount(PlayerManager.getInstance().getCurrentPlayer().getInJailCount() - 1);
			if(PlayerManager.getInstance().getCurrentPlayer().getInJailCount() <= 0) {
				PlayerManager.getInstance().getCurrentPlayer().setIsInJail(false);
			}
			this.dice.rollDices();
			if(this.dice.isDoubleDice()) {
				PlayerManager.getInstance().getCurrentPlayer().setIsInJail(false);
				PlayerManager.getInstance().getCurrentPlayer().setInJailCount(0);
				JFrame f =new JFrame();
				JOptionPane.showMessageDialog(f,"You got double dice! " +dice.getFirstDiceResult() + ":"+ dice.getSecondDiceResult() + " and escaped from Jail !!! Reroll now! ");
				BoardManager.getInstance().enableDice();
				BoardManager.getInstance().setEndTurnButton(false);
				return;
			}
			JFrame f =new JFrame();
			JOptionPane.showMessageDialog(f,"You couln't get double dice! Wait for next turn :( Dice Results: " +dice.getFirstDiceResult() + ":"+ dice.getSecondDiceResult());
			BoardManager.getInstance().disableDice();
			BoardManager.getInstance().setEndTurnButton(true);
			return;
		}
		// Let the player to roll the dice if its double
		// Before it was done automatically

		do {
			if(diceAnimationTimer != null && diceAnimationTimer.isRunning()) continue;
			SoundManager.getInstance().playDiceSound();
			this.dice.rollDices();
			total += this.dice.getTotalResult();
			System.out.println(" DİCE RESULT : "+dice.getFirstDiceResult() + ":"+ dice.getSecondDiceResult());
			BoardManager.getInstance().animateDies(this.dice.getFirstDiceResult(), this.dice.getSecondDiceResult());
			if(this.dice.isDoubleDice()) {
				JFrame f =new JFrame();
				JOptionPane.showMessageDialog(f,"You got double dice! " +dice.getFirstDiceResult() + ":"+ dice.getSecondDiceResult() + " Automatically Rerolled ");
			}
		} while (dice.isDoubleDice());

		diceAnimationTimer = new Timer(400, new ActionListener() {
			// For counting the delay and stopping timer
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				// Stop timer after the delay
				if(count >= 5) {
					// Update the map after animation
					diceAnimationTimer.stop();
					BoardManager.getInstance().setEndTurnButton(true); // Opening EndTurn Button after getting dice results
					BoardManager.getInstance().updateMap();
					BoardManager.getInstance().updateInteractionArea();
					movePlayer(currentPlayer, total);
					total = 0;
					/*for(int i = 0; i< LocationManager.getInstance().getLocationList().size(); i++) {
						System.out.println( LocationManager.getInstance().getLocationList().size());
						System.out.println(LocationManager.getInstance().getLocationList().get(i).toString());
						System.out.println("");
						LocationManager.getInstance().getLocationList().get(i).printPlayersHere();

					}
					for(int i = 0; i < PlayerManager.getInstance().getPlayers().size(); i++) {
						System.out.println(PlayerManager.getInstance().getPlayers().get(i).getName() + " - cur loc : " + PlayerManager.getInstance().getPlayers().get(i).getLocation().getName());
					}*/
				}
			}
		});

		diceAnimationTimer.restart();
		BoardManager.getInstance().disableDice();


	}
	//Calls dice Total result for reuse in utility
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

	//Disable buy button if they have same owner
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
	public boolean handleEndTurn(){
		Player prevPlayer = PlayerManager.getInstance().getCurrentPlayer();

		// Handle player bankruptcy situation
		if (this.isBankrupt(prevPlayer)){
			// TODO Give warning and ask whether to end turn and go bankrupt or continue and sell some property
			
			JDialog.setDefaultLookAndFeelDecorated(true);
			
			boolean warningAns = false;// true to continue and try to sell stuff (probably to bank)
			
			String promptMessage = "You are going to be bankrupt.\nDo you want to try to save yourself by trading/selling?\n";
			int response = JOptionPane.showConfirmDialog(null, promptMessage, "Bankruptcy",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.NO_OPTION) {
				warningAns = false;
			} else if (response == JOptionPane.YES_OPTION) {
				warningAns = true;
			} else if (response == JOptionPane.CLOSED_OPTION) {
				warningAns = false;
			}

			if (warningAns){
				return false;
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
		checkForWin();
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
		return true;
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

	//Declares someone bankrupt
	public void declarePlayerBankrupt(Player player){
		LocationManager.getInstance().freeAllLocationsOf(player);
		LocationManager.getInstance().removePlayerFromMap(player);
		PlayerManager.getInstance().playerBankrupt(player);
	}

	//Returns true if current player has deal

	public boolean currentPlayerHasDeal() {
		ArrayList<TradeDeal> playerDeals = TradeManager.getInstance().getTradeDeals(PlayerManager.getInstance().getCurrentPlayer());
		if(playerDeals.size() == 0)
			return false;
		return true;
	}
	//Returns list of deals of current player

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
		//gameManager = new GameManager(mngr);
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
	//Updates UI

	public void updateUI(){
		BoardManager.getInstance().updateMap();
		BoardManager.getInstance().updateInteractionArea();
	}

	//Executing purchase of a buyable
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
			//	System.out.println("Im in execute purchase");
			//	System.out.println("Im have disabled buy b utton");
			this.disableBuyIfSameOwner();
		}

		// Play sound effect
		SoundManager.getInstance().playBuyingPropertySound();

		// Update UI
		BoardManager.getInstance().updateInteractionArea();

		BoardManager.getInstance().updateMap();
	}
	//Sells property to bank
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
		JDialog.setDefaultLookAndFeelDecorated(true);
		boolean payWithDice = false;
		String promptMessage = "Do you want double dice instead of paying rent?\n";
		int response = JOptionPane.showConfirmDialog(null, promptMessage, "Reroll Dice",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			payWithDice=false;
		} else if (response == JOptionPane.YES_OPTION) {
			payWithDice = true;
		} else if (response == JOptionPane.CLOSED_OPTION) {
			System.out.println("JOptionPane closed");
		}
		// TODO get use input (false is temporary)

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
		BoardManager.getInstance().animateDies(dice.getFirstDiceResult(), dice.getSecondDiceResult());
		diceAnimationTimer = new Timer(400, new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;

				if (count >= 5) {
					diceAnimationTimer.stop();
					if (!dice.isDoubleDice()){
						// Pay double rent
						JFrame f =new JFrame();
						JOptionPane.showMessageDialog(f, "You couldn't get the double and escaped form payment ");
						LocationManager.getInstance().deductRentValue(locationOwner, curPlayer, curLocation.getRentValue()*2);
					}else {
						JFrame f =new JFrame();
						JOptionPane.showMessageDialog(f, "Congrats!, You get the double and escaped form payment ");
					}
					// Update UI
					BoardManager.getInstance().updateMap();
					BoardManager.getInstance().updateInteractionArea();
				}
			}
		});

		diceAnimationTimer.start();


	}

	//Paying rent method
	public void payRent(){
		// Variables
		JDialog.setDefaultLookAndFeelDecorated(true);
		boolean payWithDice = false;
		String promptMessage = "Do you want double dice instead of paying rent?\n";
		int response = JOptionPane.showConfirmDialog(null, promptMessage, "Reroll Dice",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			payWithDice =false;
		} else if (response == JOptionPane.YES_OPTION) {
			payWithDice = true;
			payRentWithDice();
		} else if (response == JOptionPane.CLOSED_OPTION) {
			System.out.println("JOptionPane closed");
		}
		if(payWithDice == false) {
			Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
			BuyableLocation curLocation = (BuyableLocation) LocationManager.getInstance().getPlayerLocation(curPlayer);
			Player locationOwner = curLocation.getOwner();

			// Process
			LocationManager.getInstance().deductRentValue(locationOwner, curPlayer, curLocation.getRentValue());
			JFrame f =new JFrame();  
			JOptionPane.showMessageDialog(f, "Your came to "+locationOwner.getName() + "'s location. You have to pay: " + curLocation.getRentValue() + " TL");  
			// Update UI
			BoardManager.getInstance().updateMap();
			BoardManager.getInstance().updateInteractionArea();
		}
	}

	//Controlling whether player passed starttile or not
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
			if (aProperty.upgrade()){
				curPlayer.setUsableMoney(curPlayer.getUsableMoney() - upgradeCost);
			}
		} else {
			// give warning when you cant update
			JFrame f =new JFrame();
			JOptionPane.showMessageDialog(f,"Unfortunately, you don't have enough money for the upgrade. Money needed: " + upgradeCost);
		}

		this.updateUI();
	}

	/*
	 * gets the name of the property to be upgraded and degrades it 
	 */
	public void degradeProperty(String nameOfProperty) {
		Property aProperty = (Property) LocationManager.getInstance().getLocationByName(nameOfProperty);
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int degradeCost = aProperty.getUpgradeCost() / 2;

		if (aProperty.degrade()){
			curPlayer.setUsableMoney(curPlayer.getUsableMoney() + degradeCost);
		}

		this.updateUI();
	}

	/*
	 * gets the name of the property and sells it 
	 */
	public void sellProperty(String nameOfProperty) {
		Property aProperty = (Property) LocationManager.getInstance().getLocationByName(nameOfProperty);
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int price = aProperty.getPrice();
		price = (int)(0.8 * price);

		if (aProperty.getOwner() != null && aProperty.getOwner().getName() == curPlayer.getName()){ // If player owns the property
			if (aProperty.getVendingMachinesNo() == 0 && !aProperty.hasStarbucks()){
				aProperty.resetToDefault();
				curPlayer.setUsableMoney(curPlayer.getUsableMoney() + price);
				JFrame f =new JFrame();  
				JOptionPane.showMessageDialog(f, "You sold "+aProperty.getName() + " and earned " + price+" TL");  
			}
		}
	}

	public void sellUtilityOrStation(String nameOfLocation) {
		BuyableLocation loc = (BuyableLocation) LocationManager.getInstance().getLocationByName(nameOfLocation);
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		int price = loc.getPrice();
		price = (int)(0.8 * price);

		if (loc.getOwner() != null && loc.getOwner().getName() == curPlayer.getName()){ // If player owns the location
			loc.resetToDefault();
			curPlayer.setUsableMoney(curPlayer.getUsableMoney() + price);
			JFrame f =new JFrame();  
			JOptionPane.showMessageDialog(f, "You sold "+ loc.getName() + " and earned " + price+" TL");  
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

	public void checkForWin() {
		if(PlayerManager.getInstance().getPlayers().size() == 1) {
			JDialog.setDefaultLookAndFeelDecorated(true);
			
			JFrame f =new JFrame();  
			JOptionPane.showMessageDialog(f, PlayerManager.getInstance().getCurrentPlayer().getName() + " won!\n"); 
			MenuManager.getInstance().openMenu(5);
		}
	}

	/*
	 * Make ready game manager for upload data
	 */
	public void readyForInitialize() {
		// TODO Auto-generated method stub
		PlayerManager.getInstance().readyForInitialize();
		LocationManager.getInstance().readyForInitialize();
		TradeManager.getInstance().readyForInitialize();
		BankManager.getInstance().readyForInitialize();
		BoardManager.getInstance().readyForInitialize();
		CardManager.getInstance().readyForInitialize();
	}

}

