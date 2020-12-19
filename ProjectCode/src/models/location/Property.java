package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Property extends BuyableLocation{
    private int vendingMachinesNo;
    private boolean ownsStarbucks;

    public Property(int locationId, String name, Point2D point, ArrayList<Player> playersHere, GroupColor groupColor, Player owner, int price, int currentRentValue, int mortgageValue, int breakMortgageValue, boolean underMortgage, ArrayList<Integer> rentValues, int vendingMachinesNo, boolean ownsStarbucks) {
        super(locationId, name, point, playersHere, groupColor, owner, price, currentRentValue, mortgageValue, breakMortgageValue, underMortgage, rentValues);
        this.vendingMachinesNo = vendingMachinesNo;
        this.ownsStarbucks = ownsStarbucks;
    }

    @Override
    public void activate() {
        super.activate();
    }

    public boolean upgrade(){
        return false; // TODO not always false
    }

    public boolean degrade(){
        return false; // TODO not always false
    }

    public int getVendingMachinesNo() {
        return vendingMachinesNo;
    }

    public boolean hasStarbucks() {
        return ownsStarbucks;
    }
}
