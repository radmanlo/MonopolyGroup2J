package models;

import java.io.Serializable;

public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3366285344187846757L;
	// Attributes
	private int cardId;
	private String cardName;
	private String description;
	private boolean storable;

	//Constructor
	public Card(int cardId, String cardName, String description, boolean storable) {
		this.cardId = cardId;
		this.cardName = cardName;
		this.description = description;
		this.storable = storable;
	}
	
	/*
	 * Copy Constructor
	 */
	public Card(Card id) {
		this.cardId = id.cardId;
		this.cardName = id.cardName;
		this.description = id.description;
		this.storable = id.storable;
	}
	//Methods

	public int getCardId() {
		return this.cardId;
	}

	/*
	 * Retuns description of card
	 */
	public String getDescription() { return this.description; }

	/*
	 * Returns a card is storable or not
	 */
	public boolean isStorable() {
		return this.storable;
	}

	/*
	 * Returns card name
	 */
	public String getCardName() { return  this.cardName; }

	@Override
	public String toString() {
		return "Card{" +
				"cardId=" + cardId +
				", cardName='" + cardName + '\'' +
				", description='" + description + '\'' +
				", storable=" + storable +
				'}';
	}
}
