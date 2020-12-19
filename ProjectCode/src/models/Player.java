package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import gamePresenter.LocationManager;
import models.location.BuyableLocation;
import models.location.Location;


public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6142234477945059588L;
	private String name;
	Token token;
	private PlayerColor colorId;
	private ArrayList<BuyableLocation> ownedLocations;
	private ArrayList<Card> cards;
	private int usableMoney;
	private BankAccount bankAccount;
	private Location location; // TODO: Change type to Location
	private int inJailCount;
	private boolean isInJail;
	private int id;

	public Player(String name, Token token, PlayerColor colorId, int playerId) {
		this.name = name;
		this.token = token;
		this.colorId = colorId;
		this.ownedLocations = new ArrayList<BuyableLocation>();
		this.cards = new ArrayList<Card>();
		this.usableMoney = 200; // DEFAULT INITIAL MONEY
		this.bankAccount = new BankAccount(playerId, 0, 0);
		this.location = LocationManager.getInstance().getLocationList().get(0);
		this.inJailCount = 0; // Default
		this.isInJail = false;
		this.id = playerId;
	}
	public Player(String name, Token token, PlayerColor colorId, ArrayList<BuyableLocation> ownedLocations,
			ArrayList<Card> cards, int usableMoney, BankAccount bankAccount, Location location, int inJailCount,
			boolean isInJail, int id) {
		this.name = name;
		this.token = token;
		this.colorId = colorId;
		this.ownedLocations = ownedLocations;
		this.cards = cards;
		this.usableMoney = usableMoney;
		this.bankAccount = bankAccount;
		this.location = location;
		this.inJailCount = inJailCount;
		this.isInJail = isInJail;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public Token getToken() {
		return this.token;
	}

	public PlayerColor getColorId() {
		return this.colorId;
	}


	public ArrayList<BuyableLocation> getOwnedLocations() {
		return this.ownedLocations;
	}

	public void addOwnedLocation(BuyableLocation property) { // TODO: Change to Buyable
		this.ownedLocations.add(property);
	}

	public void removeOwnedLocation(BuyableLocation property) { // TODO: Change to Buyable
		this.ownedLocations.remove(property);
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public int getUsableMoney() {
		return this.usableMoney;
	}

	public void setUsableMoney(int usableMoney) {
		this.usableMoney = usableMoney;
	}

	public BankAccount getBankAccount() {
		return this.bankAccount;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getInJailCount() {
		return this.inJailCount;
	}

	public void setInJailCount(int inJailCount) {
		this.inJailCount = inJailCount;
	}

	public boolean getIsInJail() {
		return this.isInJail;
	}

	public void setIsInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}
	
	public void removeCard(Card card) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getCardId() == card.getCardId())
				cards.remove(i);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Player)) {
			return false;
		}
		Player player = (Player) o;
		return Objects.equals(name, player.name) && Objects.equals(token, player.token)
				&& Objects.equals(colorId, player.colorId) && Objects.equals(ownedLocations, player.ownedLocations)
				&& Objects.equals(cards, player.cards) && usableMoney == player.usableMoney
				&& Objects.equals(bankAccount, player.bankAccount) && location == player.location
				&& inJailCount == player.inJailCount && isInJail == player.isInJail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, token, colorId, ownedLocations, cards, usableMoney, bankAccount, location,
				inJailCount, isInJail);
	}

	@Override
	public String toString() {
		return "{" + " name='" + getName() + "'" + ", token='" + getToken() + "'" + ", colorId='" + getColorId() + "'"
				+ ", ownedLocations='" + getOwnedLocations() + "'" + ", cards='" + getCards() + "'" + ", usableMoney='"
				+ getUsableMoney() + "'" + ", bankAccount='" + getBankAccount() + "'" + ", location name='" + getLocation().getName()
				+ "'" + ", inJailCount='" + getInJailCount() + "'" + ", isInJail='" + "'" + "}";
	}

}