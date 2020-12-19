package models.location;


import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BuyableLocation extends Location{
    public enum GroupColor { BROWN, LIGHT_BLUE, DARK_BLUE, PINK, ORANGE, RED, YELLOW, GREEN, PURPLE, BUS, UTILITY}
    private GroupColor groupColor;
    private Player owner;
    private int price;
    private int currentRentValue;
    private int mortgageValue = 80; // TODO to be changed later
    private int breakMortgageValue = 50; //TODO to be changed later
    private boolean underMortgage;
    private ArrayList<Integer> rentValues;

    public static BuyableLocation.GroupColor parseGroupColor(String groupColorStr){
        switch (groupColorStr){
            case "green":
                return GroupColor.GREEN;
            case "red":
                return GroupColor.RED;
            case "lightBlue":
                return GroupColor.LIGHT_BLUE;
            case "darkBlue":
                return GroupColor.DARK_BLUE;
            case "pink":
                return GroupColor.PINK;
            case "brown":
                return GroupColor.BROWN;
            case "orange":
                return GroupColor.ORANGE;
            case "yellow":
                return GroupColor.YELLOW;
            case "bus":
                return GroupColor.BUS;
            case "utility":
                return GroupColor.UTILITY;
            case "purple":
                return GroupColor.PURPLE;
            default:
                return null;
        }
    }

    public BuyableLocation(int locationId, String name, Point2D point, ArrayList<Player> playersHere, GroupColor groupColor, Player owner, int price, int currentRentValue, int mortgageValue, int breakMortgageValue, boolean underMortgage, ArrayList<Integer> rentValues) {
        super(locationId, name, point, playersHere);
        this.groupColor = groupColor;
        this.owner = owner;
        this.price = price;
        this.currentRentValue = currentRentValue;
        this.mortgageValue = mortgageValue;
        this.breakMortgageValue = breakMortgageValue;
        this.underMortgage = underMortgage;
        this.rentValues = rentValues;
    }

    public Player getOwner() {
        return owner;
    }

    public GroupColor getGroupColor() {
        return groupColor;
    }

    public void setGroupColor(GroupColor groupColor) {
        this.groupColor = groupColor;
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

    public ArrayList<Integer> getRentValues() {
        return rentValues;
    }
}
