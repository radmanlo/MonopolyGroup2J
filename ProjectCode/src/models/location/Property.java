package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Property extends BuyableLocation{
    private int vendingMachinesNo;
    private boolean ownsStarbucks;
    private int upgradeCost;

    public Property(int locationId, String name, Point2D point, ArrayList<Player> playersHere, GroupColor groupColor, Player owner, int price, int currentRentValue, int mortgageValue, int breakMortgageValue, boolean underMortgage, ArrayList<Integer> rentValues, int vendingMachinesNo, boolean ownsStarbucks, int upgradeCost) {
        super(locationId, name, point, playersHere, groupColor, owner, price, currentRentValue, mortgageValue, breakMortgageValue, underMortgage, rentValues);
        this.vendingMachinesNo = vendingMachinesNo;
        this.ownsStarbucks = ownsStarbucks;
        this.upgradeCost = upgradeCost;
    }

    public Property(Property copy) {
        super(copy);
        this.vendingMachinesNo = copy.vendingMachinesNo;
        this.ownsStarbucks = copy.ownsStarbucks;
        this.upgradeCost = copy.upgradeCost;
		// TODO Auto-generated constructor stub
	}

    public Property clone() {
        return new Property(this);
    }
	@Override
    public void activate() {
        super.activate();
    }

    public int getVendingMachinesNo() {
        return vendingMachinesNo;
    }

    public boolean hasStarbucks() {
        return ownsStarbucks;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public static int noOfPropertyPerColor(GroupColor color){
        switch (color){
            case GREEN:
            case LIGHT_BLUE:
            case PINK:
            case ORANGE:
            case RED:
            case YELLOW: return 3;
            case DARK_BLUE:
            case BROWN: return 2;
            default: return -1;
        }
    }
}
