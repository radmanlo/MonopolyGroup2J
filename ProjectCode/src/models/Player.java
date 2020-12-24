package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import gamePresenter.LocationManager;
import models.location.BusStop;
import models.location.BuyableLocation;
import models.location.ChanceTile;
import models.location.Disciplinary;
import models.location.GoToDisciplinaryTile;
import models.location.IncomeTaxTile;
import models.location.Location;
import models.location.MayfestTile;
import models.location.Property;
import models.location.StartTile;
import models.location.Utility;


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

	/*
	 * Default Construcotr
	 */
	public Player (Player copy) {

		//TODO LOCATION AND BUYABLELOCATION LIST HARD COPY
		this.name = copy.name;
		this.token = new Token(copy.token);
		this.colorId = copy.getColorId();
		this.ownedLocations = new ArrayList<BuyableLocation>();
		this.cards = new ArrayList<Card>();
		//copy.ownedLocations.get(0)
		
		for(int i = 0; i < copy.ownedLocations.size();i++) {
			//this.ownedLocations.add(copy.ownedLocations.get(i));
			Location loc = copy.ownedLocations.get(i);
			switch(loc.getClass().toString()) {
			case "class models.location.Property":
				Property pr = new Property((Property)copy.ownedLocations.get(i));
				this.ownedLocations.add(pr);
				break;
			case "class models.location.Utility":
				Utility ut = new Utility((Utility)copy.ownedLocations.get(i));
				this.ownedLocations.add(ut);
				break;
			case "class models.location.BusStop":
				BusStop bs = new BusStop((BusStop)copy.ownedLocations.get(i));
				this.ownedLocations.add(bs);
				break;	
			default:
			}
		}
			
		for(int k = 0; k < copy.cards.size(); k++ ) {
			this.cards.add(copy.cards.get(k));
		}
		
		this.usableMoney = copy.usableMoney;
		this.bankAccount = new BankAccount(copy.bankAccount);
		this.location = new Location(copy.location);
		this.inJailCount = copy.inJailCount;
		this.isInJail = copy.isInJail;
		this.id = copy.id;
	}
	/*
	 * Constructor with given name token colorid playerid
	 */
	public Player(String name, Token token, PlayerColor colorId, int playerId) {
		this.name = name;
		this.token = token;
		this.colorId = colorId;
		this.ownedLocations = new ArrayList<BuyableLocation>();
		this.cards = new ArrayList<Card>();
		this.usableMoney = 1200; // DEFAULT INITIAL MONEY
		this.bankAccount = new BankAccount(playerId, 0, 0);
		this.location = LocationManager.getInstance().getLocationList().get(0);
		this.inJailCount = 0; // Default
		this.isInJail = false;
		this.id = playerId;
	}
	/*
	 * Constructor with given all parameters
	 */
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
	
	/*
	 * Returns id of player
	 */
	public int getId() {
		return id;
	}

	/*
	 * Sets id of player
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * Returns name of player
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * Returns token of player
	 */
	public Token getToken() {
		return this.token;
	}

	/*
	 * Returns color id of player
	 */
	public PlayerColor getColorId() {
		return this.colorId;
	}

	/*
	 * Returns buyable locations of player
	 */
	public ArrayList<BuyableLocation> getOwnedLocations() {
		return this.ownedLocations;
	}

	/*
	 * adds a buyable to list of ownings 
	 */
	public void addOwnedLocation(BuyableLocation property) { // TODO: Change to Buyable
		this.ownedLocations.add(property);
		property.setOwner(this);
	}

	/*
	 * removes a buyable from the list of buyables
	 */
	public void removeOwnedLocation(BuyableLocation property) { // TODO: Change to Buyable
		this.ownedLocations.remove(property);
		property.setOwner(null);
	}

	/*
	 * Returns stored cards
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	/*
	 * adds a storable card
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}

	/*
	 * Get cash money
	 */
	public int getUsableMoney() {
		return this.usableMoney;
	}

	/*
	 * Set cash money
	 */
	public void setUsableMoney(int usableMoney) {
		this.usableMoney = usableMoney;
	}

	/*
	 * Get bank account of player
	 */
	public BankAccount getBankAccount() {
		return this.bankAccount;
	}

	/*
	 * Get current location of player
	 */
	public Location getLocation() {
		for(int i = 0 ; i < LocationManager.getInstance().getLocationList().size(); i++){
			if(LocationManager.getInstance().getLocationList().get(i).hasPlayer(this)) {
				return LocationManager.getInstance().getLocationList().get(i);
			}
		}
		return null;
	}

	/*
	 * Sets location of player
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/*
	 * Return in jail count stiuation of player
	 */
	public int getInJailCount() {
		return this.inJailCount;
	}

	/*
	 * sets jail count stiuation of player
	 */
	public void setInJailCount(int inJailCount) {
		this.inJailCount = inJailCount;
	}

	/*
	 * get jail stiuation of player
	 */
	public boolean getIsInJail() {
		return this.isInJail;
	}

	/*
	 * set jail stiuation of player
	 */
	public void setIsInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}
	
	/*
	 * remove a storable card from list
	 */
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
				+ getUsableMoney() + "'" + ", bankAccount='" + getBankAccount() + "'" 
				+ "'" + ", inJailCount='" + getInJailCount() + "'" + ", isInJail='" + "'" + "}";
	}

}