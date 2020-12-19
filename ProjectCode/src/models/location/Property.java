package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import gamePresenter.LocationManager;

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
    
    public boolean upgrade() {
    	
    	if(ownsStarbucks == false){
    		if(vendingMachinesNo < 2) {
    			vendingMachinesNo++;
    			return true;
    		}else {
    		vendingMachinesNo = 0;
    		ownsStarbucks = true;
    		return true;
    		}
    	}
    	return false;
    
    }
    public int getUpgradeCost() {
        return upgradeCost;
    }
    
    @Override
    public int getRentValue() {
    	int level = 0;
    	if(LocationManager.getInstance().groupHasSameOwner(this.getGroupColor()).getName().equals(this.getOwner().getName())) {
    		if(this.vendingMachinesNo == 0) {
    			if(this.ownsStarbucks == true) {
    				level = 4;
    			}
    			level = 1;
    		}else if(this.vendingMachinesNo == 1) {
    			level = 2;
    		}else if(this.vendingMachinesNo == 2) {
    			level = 3;
    		}
    	}
    	int rentVal = this.getRentValues().get(level);
    	this.setCurrentRentValue(rentVal);
    	return rentVal;
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
