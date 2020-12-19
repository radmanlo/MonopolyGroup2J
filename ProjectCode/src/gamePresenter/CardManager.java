package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import models.Card;

public class CardManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5617304288178342343L;
	private ArrayList<Card> cardDeck;
	private int takenCardCount;
	private static CardManager cardMngr = null;

	
	public CardManager() {
		cardDeck = new ArrayList<Card>();
		takenCardCount = 0;
	}
	
	public CardManager(CardManager mngr) {
		// TODO Auto-generated constructor stub
		this.cardDeck = new ArrayList<Card>();
		this.takenCardCount = mngr.takenCardCount;
		for(int i = 0; i < cardDeck.size(); i++) {
			this.cardDeck.add(mngr.cardDeck.get(i));
		}		
	}

	public static CardManager getInstance() {
		if( cardMngr == null ) {
			cardMngr = new CardManager();
		}
		return cardMngr;
	}

	public int getTakenCardCount() {
		return takenCardCount;
	}
	
	public Card getTopCard() {
		takenCardCount++;
		return cardDeck.get(takenCardCount-1);
	}
	
	public void shuffle() {
        Collections.shuffle(cardDeck);
	}
	//TODO CARD INSTRUCTIONS
	public void executeCardAction(int cardId) {
		if(cardId == 0) {
			getOutOfJail();
		}
	}
	
	//TODO GET OUT OF JAIL IMPLEMENTATION
	public void getOutOfJail() {
		System.out.println("Getting out of Jail Dummy");
	}
	
	public void addCard(Card card) {
		cardDeck.add(card);
	}

	@Override
	public String toString() {
		return "CardManager{" +
				"cardDeck=" + cardDeck.toString() +
				", takenCardCount=" + takenCardCount +
				'}';
	}

	public void create(CardManager mngr) {
		// TODO Auto-generated method stub
		cardMngr = new CardManager(mngr);
	}
}
