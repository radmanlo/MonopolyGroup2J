package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import gamePresenter.LocationManager;

public class BusStop extends BuyableLocation{
    public BusStop(int locationId, String name, Point2D point, ArrayList<Player> playersHere, GroupColor groupColor, Player owner, int price, int currentRentValue, int mortgageValue, int breakMortgageValue, boolean underMortgage, ArrayList<Integer> rentValues) {
        super(locationId, name, point, playersHere, groupColor, owner, price, currentRentValue, mortgageValue, breakMortgageValue, underMortgage, rentValues, LOCATION_TYPES.BUS);
    }
    
    public BusStop(BusStop copy) {
    	super(copy);
    }
    @Override
    public void activate() {
        super.activate();
    }
    @Override
    public int getRentValue() {
		int i = LocationManager.getInstance().noOfOwnedByPlayerInGroup(this.getOwner(),this.getGroupColor());
		int money = this.getAllRentValues().get(i-1);
		this.setCurrentRentValue(money);
		return money;
    }

}
