import java.util.ArrayList;
import java.util.Objects;

public class TradeDeal {
    
    private Player offerer;
    private Player receiver;
    private ArrayList<Property> offeredBuyables; // TODO: Change Property to Buyable when Buyable is added    
    private ArrayList<Card> offeredCards; 
    private int offeredMoney;
    private ArrayList<Property> requestedBuyables; // TODO: Change Property to Buyable when Buyable is added    
    private ArrayList<Card> requestedCards; 
    private int requestedMoney;


    public TradeDeal() {
    }

    public TradeDeal(Player offerer, Player receiver, ArrayList<Property> offeredBuyables, ArrayList<Card> offeredCards, int offeredMoney, ArrayList<Property> requestedBuyables, ArrayList<Card> requestedCards, int requestedMoney) {
        this.offerer = offerer;
        this.receiver = receiver;
        this.offeredBuyables = offeredBuyables;
        this.offeredCards = offeredCards;
        this.offeredMoney = offeredMoney;
        this.requestedBuyables = requestedBuyables;
        this.requestedCards = requestedCards;
        this.requestedMoney = requestedMoney;
    }

    public void execute() {
        
    }
    public void transferMoney() {

    }
    public void transferCards() {

    }
    public void transferBuyables() {

    }

    public void addBuyableToOffered(Property item) { // TODO: Change to Buyable

    }
    public void removeBuyableFromOffered(Property item) { // TODO: Change to Buyable

    }
    public void addCardToOffered(Card card) { // TODO: Change to Buyable

    }
    public void removeCardFromOffered(Card card) { // TODO: Change to Buyable

    }

    public void addBuyableToRequested(Property item) { // TODO: Change to Buyable

    }
    public void removeBuyableFromRequested(Property item) { // TODO: Change to Buyable

    }
    public void addCardToRequested(Card card) { // TODO: Change to Buyable

    }
    public void removeCardFromRequested(Card card) { // TODO: Change to Buyable

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

    public ArrayList<Property> getOfferedBuyables() {
        return this.offeredBuyables;
    }

    public void setOfferedBuyables(ArrayList<Property> offeredBuyables) {
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

    public ArrayList<Property> getRequestedBuyables() {
        return this.requestedBuyables;
    }

    public void setRequestedBuyables(ArrayList<Property> requestedBuyables) {
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

    public TradeDeal offeredBuyables(ArrayList<Property> offeredBuyables) {
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

    public TradeDeal requestedBuyables(ArrayList<Property> requestedBuyables) {
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


}
