package models;
import java.util.ArrayList;
import java.util.Objects;

import models.location.BuyableLocation;


public class Player {

	private String name;
	Token token;
	private PlayerColor colorId;
	private ArrayList<BuyableLocation> ownedLocations;
	private ArrayList<Card> cards;
	private int usableMoney;
	private BankAccount bankAccount;
	private int location; // TODO: Change type to Location
	private int inJailCount;
	private boolean isInJail;
	private int id;

	public Player(String name, Token token, PlayerColor colorId, ArrayList<BuyableLocation> ownedLocations,
			ArrayList<Card> cards, int usableMoney, BankAccount bankAccount, int location, int inJailCount,
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

	public void removeCard(Card card) {
		this.cards.remove(card);
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

	public int getLocation() {
		return this.location;
	}

	public void setLocation(int location) {
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
				+ getUsableMoney() + "'" + ", bankAccount='" + getBankAccount() + "'" + ", location='" + getLocation()
				+ "'" + ", inJailCount='" + getInJailCount() + "'" + ", isInJail='" + "'" + "}";
	}

}