package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.BankAccount;
import models.Card;
import models.Player;
import models.location.Location;
import models.location.Property;

public class CardManager implements Serializable{
	// Property
	private static final long serialVersionUID = 5617304288178342343L;
	private ArrayList<Card> cardDeck;
	private int takenCardCount;
	private static CardManager cardMngr = null;

	// Constructor
	public CardManager() {
		cardDeck = new ArrayList<Card>();
		takenCardCount = 0;
	}

	// Copy Constructor
	public CardManager(CardManager mngr) {
		// TODO Auto-generated constructor stub
		this.cardDeck = new ArrayList<Card>();
		this.takenCardCount = mngr.takenCardCount;
		for(int i = 0; i < cardDeck.size(); i++) {
			Card cr = new Card(mngr.cardDeck.get(i));
			this.cardDeck.add(cr);
		}		
	}

	// Operational Methods
	public static CardManager getInstance() {
		if( cardMngr == null ) {
			cardMngr = new CardManager();
		}
		return cardMngr;
	}

	public void create(CardManager mngr) {
		// TODO Auto-generated method stub
		this.cardDeck = new ArrayList<Card>();
		this.takenCardCount = mngr.takenCardCount;
		for(int i = 0; i < mngr.cardDeck.size(); i++) {
			Card cr = new Card(mngr.cardDeck.get(i).getCardId(), mngr.cardDeck.get(i).getCardName(), mngr.cardDeck.get(i).getDescription(), mngr.cardDeck.get(i).isStorable());
			this.cardDeck.add(cr);
		}
	}

	public void addCard(Card card) {
		cardDeck.add(card);
	}

	// Game Logic Methods
	public int getTakenCardCount() {
		return takenCardCount;
	}

	/**
	 * Shuffle and return the top card (since cards are infinite)
	 * @return
	 */
	public Card getTopCard() {
		this.shuffle();
		return cardDeck.get(0);
	}

	public void shuffle() {
        Collections.shuffle(cardDeck);
	}

	public void executeCardAction(Card card) {
		switch (card.getCardId()){
			case 111: this.getOutOfJail(card);
			break;
			case 112: this.upgradeAProperty();
			break;
			case 113: this.pladiarismPunishment();
			break;
			case 114: this.goWc();
			break;
			case 115: this.aInCs319();
			break;
			case 116: this.schoolTaxes();
			break;
			case 117: this.accidentFee();
			break;
			case 118: this.moneyBorrowed();
			break;
			case 119: this.fellAsleep();
			break;
			case 120: this.friendHw();
			break;
		}
	}

	/**
	 * if Player is in jail free him/her
	 * @param outOfJailCard
	 */
	public void getOutOfJail(Card outOfJailCard) {
		System.out.println("OutOfJail card activated");
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		if (curPlayer.getIsInJail()){
			curPlayer.setIsInJail(false);
			GameManager.getInstance().enableDice();
			curPlayer.removeCard(outOfJailCard);
		}
	}

	public void upgradeAProperty(){
		System.out.println("Upgrade property card activated");
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		ArrayList<Property> properties = LocationManager.getInstance().getPropertiesOfPlayer(curPlayer);
		ArrayList<Property> upgradableProps = new ArrayList<Property>(0);

		// get the upgradeable properties owned by the user
		for (Property prop : properties){
			if (LocationManager.getInstance().isPropertyUpgradeable(prop)){
				upgradableProps.add(prop);
			}
		}

		if (upgradableProps.size() > 0){
			Collections.shuffle(upgradableProps);
			upgradableProps.get(0).upgrade();
		}

		GameManager.getInstance().updateUI();
	}

	public void pladiarismPunishment(){
		System.out.println("Plagiarism -500 card activated");

		final int PLAGIARISM_FINE = 500;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, PLAGIARISM_FINE);
	}

	public void goWc(){
		final int WC_ID = 28;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		Location curLocation = LocationManager.getInstance().getPlayerLocation(curPlayer);
		int curLocationId = curLocation.getLocationId();
		int distance = 0;

		if (curLocationId < WC_ID){
			distance = WC_ID - curLocationId + 1;
		} else {
			distance = WC_ID + (40 - curLocationId) + 1;
		}

		System.out.println("Go to Wc card activated " + distance);

		LocationManager.getInstance().movePlayer(curPlayer, distance);
	}

	public void aInCs319(){
		System.out.println("a in cs319 +500 card activated");

		final int PRIZE = 500;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		PlayerManager.getInstance().addMoneyToPlayer(curPlayer, PRIZE);
	}

	public void schoolTaxes(){
		System.out.println("school taxes -1000 card activated");


		final int SCHOOL_TAX = 350;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, SCHOOL_TAX);
	}

	public void accidentFee(){
		System.out.println("accident -500card activated");

		final int ACCIDENT_FEE = 180;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, ACCIDENT_FEE);
	}

	public void moneyBorrowed(){
		System.out.println("money borrowed 1500 to other player card activated");

		final int AMOUNT_BORROWED = 400;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		Player nextPlayer = PlayerManager.getInstance().getNextPlayer();

		PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, AMOUNT_BORROWED);
		PlayerManager.getInstance().addMoneyToPlayer(nextPlayer, AMOUNT_BORROWED);
	}

	public void fellAsleep(){
		GameManager.getInstance().handleEndTurn();
	}

	public void friendHw(){
		System.out.println("friend hw -500 card activated");

		final int HW_FEE = 75;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, HW_FEE);
	}
	public ArrayList<Card> getCardDeck(){
		return cardDeck;
	}

	@Override
	public String toString() {
		return "CardManager{" +
				"cardDeck=" + cardDeck.toString() +
				", takenCardCount=" + takenCardCount +
				'}';
	}
	public void readyForInitialize() {
		this.takenCardCount = 0;
		cardDeck = new ArrayList<Card>();
	}
}
