import java.util.ArrayList;

public class Player {
	private int turnCount;
	private int money;
	private int colorId;
	private int location;
	private String name;
	private ArrayList<Card> cards;
	private ArrayList<Property> properties;
	Token token;
	
	public Player() {
		
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public void increaseTurnCount() {
		turnCount++;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public int getMoney() {
		return money;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
	
	
}
