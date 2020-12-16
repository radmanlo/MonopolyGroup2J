package models.location;


import models.Player;

public class BuyableLocation extends Location{
    public enum GroupColor { BROWN, LIGHT_BLUE, DARK_BLUE, PINK, ORANGE, RED, YELLOW, GREEN, STATION, UTILITY}
    private GroupColor groupId;
    private Player owner;
    private int price;
    private int currentRentValue;
    private final int mortgageValue = 80; // TODO to be changed later
    private final int breakMortgageValue = 50; //TODO to be changed later
    private boolean underMortgage;

    BuyableLocation(){

    }

    public Player getOwner() {
        return owner;
    }

    public GroupColor getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupColor groupId) {
        this.groupId = groupId;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCurrentRentValue() {
        return currentRentValue;
    }

    public void setCurrentRentValue(int currentRentValue) {
        this.currentRentValue = currentRentValue;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public int getBreakMortgageValue() {
        return breakMortgageValue;
    }

    public boolean isUnderMortgage() {
        return underMortgage;
    }

    public void setUnderMortgage(boolean underMortgage) {
        this.underMortgage = underMortgage;
    }
}
