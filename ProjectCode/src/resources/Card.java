package resources;

public class Card {
	// Attributes
	private int cardId;
	private String description;
	private boolean storable;

	//Constructor
	public Card(int cardId, String description, boolean storable) {
		this.cardId = cardId;
		this.description = description;
		this.storable = storable;
	}
	
	//Methods

	public int getCardId() {
		return cardId;
	}

	public String getDescription() {
		return description;
	}

	public boolean isStorable() {
		return storable;
	}
	
}
