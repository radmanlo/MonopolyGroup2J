package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Utility extends BuyableLocation {

    public Utility(int locationId, String name, Point2D point, ArrayList<Player> playersHere, GroupColor groupColor, Player owner, int price, int currentRentValue, int mortgageValue, int breakMortgageValue, boolean underMortgage, ArrayList<Integer> rentValues) {
        super(locationId, name, point, playersHere, groupColor, owner, price, currentRentValue, mortgageValue, breakMortgageValue, underMortgage, rentValues);
    }
    public Utility(Utility copy) {
    	super(copy);
    }
    @Override
    public void activate() {
        super.activate();
    }
}
