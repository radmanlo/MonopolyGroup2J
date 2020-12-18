package gamePresenter;

import java.util.ArrayList;
import java.util.Collections;

import models.Card;

public class CardManager {
	private ArrayList<Card> cardDeck;
	private int takenCardCount;
	private static CardManager cardMngr = null;

	
	public CardManager() {
		cardDeck = new ArrayList<Card>();
		takenCardCount = 0;
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
}
