package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import models.location.BusStop;
import models.location.BuyableLocation;
import models.location.Location;
import models.location.Property;
import models.location.Utility;

public class TradeDeal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4290603540863062321L;
	private Player offerer;
	private Player receiver;
	private ArrayList<BuyableLocation> offeredBuyables; 
	private ArrayList<Card> offeredCards; 
	private int offeredMoney;
	private ArrayList<BuyableLocation> requestedBuyables; 
	private ArrayList<Card> requestedCards; 
	private int requestedMoney;

	public TradeDeal(Player offerer, Player receiver, ArrayList<BuyableLocation> offeredBuyables, ArrayList<Card> offeredCards, int offeredMoney, ArrayList<BuyableLocation> requestedBuyables, ArrayList<Card> requestedCards, int requestedMoney) {
		this.offerer = offerer;
		this.receiver = receiver;
		this.offeredBuyables = offeredBuyables;
		this.offeredCards = offeredCards;
		this.offeredMoney = offeredMoney;
		this.requestedBuyables = requestedBuyables;
		this.requestedCards = requestedCards;
		this.requestedMoney = requestedMoney;
	}

	public TradeDeal(TradeDeal copy) {
		this.offeredMoney = copy.offeredMoney;
		this.requestedMoney = copy.requestedMoney;
		this.offerer = new Player(copy.offerer);
		this.receiver = new Player(copy.receiver);
		for(int i = 0; i < copy.offeredCards.size(); i++) {
			Card car = new Card(copy.offeredCards.get(i));
			offeredCards.add(car);
		}
		for(int i = 0; i < copy.requestedCards.size(); i++) {
			Card car = new Card(copy.requestedCards.get(i));
			requestedCards.add(car);
		}
		for(int i = 0; i < copy.offeredBuyables.size();i++) {
			//this.ownedLocations.add(copy.ownedLocations.get(i));
			Location loc = copy.offeredBuyables.get(i);
			switch(loc.getClass().toString()) {
			case "class models.location.Property":
				Property pr = new Property((Property)copy.offeredBuyables.get(i));
				this.offeredBuyables.add(pr);
				break;
			case "class models.location.Utility":
				Utility ut = new Utility((Utility)copy.offeredBuyables.get(i));
				this.offeredBuyables.add(ut);
				break;
			case "class models.location.BusStop":
				BusStop bs = new BusStop((BusStop)copy.offeredBuyables.get(i));
				this.offeredBuyables.add(bs);
				break;	
			default:
			}
		}
		for(int i = 0; i < copy.requestedBuyables.size();i++) {
			//this.ownedLocations.add(copy.ownedLocations.get(i));
			Location loc = copy.requestedBuyables.get(i);
			switch(loc.getClass().toString()) {
			case "class models.location.Property":
				Property pr = new Property((Property)copy.requestedBuyables.get(i));
				this.requestedBuyables.add(pr);
				break;
			case "class models.location.Utility":
				Utility ut = new Utility((Utility)copy.requestedBuyables.get(i));
				this.requestedBuyables.add(ut);
				break;
			case "class models.location.BusStop":
				BusStop bs = new BusStop((BusStop)copy.requestedBuyables.get(i));
				this.requestedBuyables.add(bs);
				break;	
			default:
			}
		}
	}

	public void execute() {
		transferMoney();
		transferCards();
		transferBuyables();
	}
	public void transferMoney() {
		int money = offeredMoney- requestedMoney;
		offerer.setUsableMoney(offerer.getUsableMoney() - money);
		receiver.setUsableMoney(receiver.getUsableMoney() + money);
	}
	public void transferCards() {
		if(offeredCards != null) {
			for(int i = 0; i < offeredCards.size(); i++) {
				offerer.removeCard(offeredCards.get(i));
				receiver.addCard(offeredCards.get(i));
			}
			for(int i = 0; i < requestedCards.size(); i++) {
				receiver.removeCard(offeredCards.get(i));
				offerer.addCard(offeredCards.get(i));
			}
		}
	}
	public void transferBuyables() {
		for(int i = 0; i < offeredBuyables.size(); i++) {
			offerer.removeOwnedLocation(offeredBuyables.get(i));
			receiver.addOwnedLocation(offeredBuyables.get(i));
		}
		for(int i = 0; i < requestedBuyables.size(); i++) {
			receiver.removeOwnedLocation(offeredBuyables.get(i));
			offerer.addOwnedLocation(offeredBuyables.get(i));
		}
	}


	public void addBuyableToOffered(BuyableLocation item) {
		offeredBuyables.add(item);
	}
	public void removeBuyableFromOffered(BuyableLocation item) { 
		for(int i = 0; i < offeredBuyables.size(); i++) {
			if(offeredBuyables.get(i).getName() == item.getName()) {
				offeredBuyables.remove(i);
			}
		}
	}
	public void addCardToOffered(Card card) { 
		offeredCards.add(card);
	}
	public void removeCardFromOffered(Card card) { 
		for(int i = 0; i < offeredCards.size(); i++) {
			if(offeredCards.get(i).getCardId() == card.getCardId()) {
				offeredBuyables.remove(i);
			}
		}
	}
	public void addBuyableToRequested(BuyableLocation item) { 
		requestedBuyables.add(item);
	}
	public void removeBuyableFromRequested(BuyableLocation item) { 
		for(int i = 0; i < requestedBuyables.size(); i++) {
			if(requestedBuyables.get(i).getName() == item.getName()) {
				requestedBuyables.remove(i);
			}
		}
	}
	public void addCardToRequested(Card card) { 
		requestedCards.add(card);
	}
	public void removeCardFromRequested(Card card) { 
		for(int i = 0; i < requestedCards.size(); i++) {
			if(requestedCards.get(i).getCardId() == card.getCardId()) {
				requestedCards.remove(i);
			}
		}
	}

	public Player getOfferer() {
		return this.offerer;
	}

	public void setOfferer(Player offerer) {
		this.offerer = offerer;
	}

	public Player getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}

	public ArrayList<BuyableLocation> getOfferedBuyables() {
		return this.offeredBuyables;
	}

	public void setOfferedBuyables(ArrayList<BuyableLocation> offeredBuyables) {
		this.offeredBuyables = offeredBuyables;
	}

	public ArrayList<Card> getOfferedCards() {
		return this.offeredCards;
	}

	public void setOfferedCards(ArrayList<Card> offeredCards) {
		this.offeredCards = offeredCards;
	}

	public int getOfferedMoney() {
		return this.offeredMoney;
	}

	public void setOfferedMoney(int offeredMoney) {
		this.offeredMoney = offeredMoney;
	}

	public ArrayList<BuyableLocation> getRequestedBuyables() {
		return this.requestedBuyables;
	}

	public void setRequestedBuyables(ArrayList<BuyableLocation> requestedBuyables) {
		this.requestedBuyables = requestedBuyables;
	}

	public ArrayList<Card> getRequestedCards() {
		return this.requestedCards;
	}

	public void setRequestedCards(ArrayList<Card> requestedCards) {
		this.requestedCards = requestedCards;
	}

	public int getRequestedMoney() {
		return this.requestedMoney;
	}

	public void setRequestedMoney(int requestedMoney) {
		this.requestedMoney = requestedMoney;
	}

	public TradeDeal offerer(Player offerer) {
		this.offerer = offerer;
		return this;
	}

	public TradeDeal receiver(Player receiver) {
		this.receiver = receiver;
		return this;
	}

	public TradeDeal offeredBuyables(ArrayList<BuyableLocation> offeredBuyables) {
		this.offeredBuyables = offeredBuyables;
		return this;
	}

	public TradeDeal offeredCards(ArrayList<Card> offeredCards) {
		this.offeredCards = offeredCards;
		return this;
	}

	public TradeDeal offeredMoney(int offeredMoney) {
		this.offeredMoney = offeredMoney;
		return this;
	}

	public TradeDeal requestedBuyables(ArrayList<BuyableLocation> requestedBuyables) {
		this.requestedBuyables = requestedBuyables;
		return this;
	}

	public TradeDeal requestedCards(ArrayList<Card> requestedCards) {
		this.requestedCards = requestedCards;
		return this;
	}

	public TradeDeal requestedMoney(int requestedMoney) {
		this.requestedMoney = requestedMoney;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof TradeDeal)) {
			return false;
		}
		TradeDeal tradeDeal = (TradeDeal) o;
		return Objects.equals(offerer, tradeDeal.offerer) && Objects.equals(receiver, tradeDeal.receiver) && Objects.equals(offeredBuyables, tradeDeal.offeredBuyables) && Objects.equals(offeredCards, tradeDeal.offeredCards) && offeredMoney == tradeDeal.offeredMoney && Objects.equals(requestedBuyables, tradeDeal.requestedBuyables) && Objects.equals(requestedCards, tradeDeal.requestedCards) && requestedMoney == tradeDeal.requestedMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(offerer, receiver, offeredBuyables, offeredCards, offeredMoney, requestedBuyables, requestedCards, requestedMoney);
	}

	@Override
	public String toString() {
		return "{" +
				" offerer='" + getOfferer() + "'" +
				", receiver='" + getReceiver() + "'" +
				", offeredBuyables='" + getOfferedBuyables() + "'" +
				", offeredCards='" + getOfferedCards() + "'" +
				", offeredMoney='" + getOfferedMoney() + "'" +
				", requestedBuyables='" + getRequestedBuyables() + "'" +
				", requestedCards='" + getRequestedCards() + "'" +
				", requestedMoney='" + getRequestedMoney() + "'" +
				"}";
	}

	public String toStringForPrompt() {
		return 
				"Offerer= " + getOfferer() + "\n" +
				"OfferedBuyables= " + getOfferedBuyables() + "\n" +
				"OfferedMoney= " + getOfferedMoney() + "\n" +
				"requestedBuyables= " + getRequestedBuyables() + "\n" +
				"requestedMoney= " + getRequestedMoney() + "\n";
	}

}
