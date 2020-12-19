package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import models.Card;
import models.Player;

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
			this.cardDeck.add(mngr.cardDeck.get(i));
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
		cardMngr = new CardManager(mngr);
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
			case 119: this.fellAsleap();
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
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		if (curPlayer.getIsInJail()){
			curPlayer.setIsInJail(false);
			curPlayer.removeCard(outOfJailCard);
		}
	}

	public void upgradeAProperty(){
		// get the upgradeable properties owned by the user

		// prompt which one they want to upgrade

		// ask location manager to upgrade
	}

	public void pladiarismPunishment(){
		final int PLAGIARISM_FINE = 500;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

		PlayerManager.getInstance().deductMoneyFromPlayer(curPlayer, PLAGIARISM_FINE);
	}

	public void goWc(){
		final int WC_ID = 28;
		Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
		
		LocationManager.getInstance().movePlayer(curPlayer, WC_ID);
	}

	public void aInCs319(){

	}

	public void schoolTaxes(){

	}

	public void accidentFee(){

	}

	public void moneyBorrowed(){

	}

	public void fellAsleap(){

	}

	public void friendHw(){

	}

	@Override
	public String toString() {
		return "CardManager{" +
				"cardDeck=" + cardDeck.toString() +
				", takenCardCount=" + takenCardCount +
				'}';
	}
}
