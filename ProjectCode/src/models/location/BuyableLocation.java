package models.location;


import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;
import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Locale;

public class BuyableLocation extends Location{
    public enum GroupColor { BROWN, LIGHT_BLUE, DARK_BLUE, PINK, ORANGE, RED, YELLOW, GREEN, PURPLE, BUS, UTILITY}
    private GroupColor groupColor;
    private Player owner;
    private int price;
    private int currentRentValue;
    private int currentRentIndex;
    private int mortgageValue = 80; // TODO to be changed later
    private int breakMortgageValue = 50; //TODO to be changed later
    private boolean underMortgage;
    private ArrayList<Integer> rentValues;

    public static BuyableLocation.GroupColor parseGroupColor(String groupColorStr){
        System.out.println("parsing group color: " + groupColorStr);
        groupColorStr = groupColorStr.toLowerCase();

        switch (groupColorStr){
            case "green":
                System.out.println("green");
                return GroupColor.GREEN;
            case "red":
                System.out.println("red");
                return GroupColor.RED;
            case "lightblue":
                System.out.println("loghtblue");
                return GroupColor.LIGHT_BLUE;
            case "darkblue":
                System.out.println("darkblue");
                return GroupColor.DARK_BLUE;
            case "pink":
                System.out.println("pink");
                return GroupColor.PINK;
            case "brown":
                System.out.println("brown");
                return GroupColor.BROWN;
            case "orange":
                System.out.println("orange");
                return GroupColor.ORANGE;
            case "yellow":
                System.out.println("yellow");
                return GroupColor.YELLOW;
            case "bus":
                System.out.println("bus");
                return GroupColor.BUS;
            case "utility":
                System.out.println("utility");
                return GroupColor.UTILITY;
            case "purple":
                System.out.println("purple");
                return GroupColor.PURPLE;
            default:
                return null;
        }
    }

    public BuyableLocation(int locationId, String name, Point2D point, ArrayList<Player> playersHere, GroupColor groupColor, Player owner, int price, int currentRentValue, int mortgageValue, int breakMortgageValue, boolean underMortgage, ArrayList<Integer> rentValues, LOCATION_TYPES type) {
        super(locationId, name, point, playersHere, type);
        this.groupColor = groupColor;
        this.owner = owner;
        this.price = price;
        this.currentRentValue = currentRentValue;
        this.currentRentIndex = 0;
        this.mortgageValue = mortgageValue;
        this.breakMortgageValue = breakMortgageValue;
        this.underMortgage = underMortgage;
        this.rentValues = rentValues;
    }

    public BuyableLocation(BuyableLocation copy) {
    	super(copy);
        this.groupColor = copy.groupColor;
        this.owner = copy.owner;
        this.price = copy.price;
        this.currentRentValue = copy.currentRentValue;
        this.currentRentIndex = copy.currentRentIndex;
        this.mortgageValue = copy.mortgageValue;
        this.breakMortgageValue = copy.breakMortgageValue;
        this.underMortgage = copy.underMortgage;
        this.rentValues = copy.rentValues;
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

    public ArrayList<Integer> getAllRentValues(){
        return this.rentValues;
    }

    public int getRentValue() {
		return this.currentRentValue;
    }

    public boolean upgrade(){
        if (this.currentRentIndex < this.rentValues.size() - 1){
            this.currentRentIndex++;
            this.currentRentValue = this.rentValues.get(this.currentRentIndex);
            return true;
        }

        return false;
    }

    public boolean degrade(){
        if (this.currentRentIndex > 0){
            this.currentRentIndex--;
            this.currentRentValue = this.rentValues.get(this.currentRentIndex);
            return true;
        }

        return false;
    }

    public void resetToDefault(){
        this.owner = null;
        this.currentRentIndex = 0;
        this.currentRentValue = this.rentValues.get(0);
    }

    public static int noOfBuyablePerColor(GroupColor color){
        if (color == null){
            return -1;
        }

        switch (color){
            case GREEN:
            case LIGHT_BLUE:
            case PURPLE:
            case ORANGE:
            case RED:
            case YELLOW: return 3;
            case DARK_BLUE:
            case BROWN:
            case UTILITY: return 2;
            case BUS: return 4;
            default: return -1;
        }
    }

    @Override
    public void activate() {
        super.activate();
    }
}
