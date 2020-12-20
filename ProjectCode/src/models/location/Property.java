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
        super(locationId, name, point, playersHere, groupColor, owner, price, currentRentValue, mortgageValue, breakMortgageValue, underMortgage, rentValues, LOCATION_TYPES.PROPERTY);
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
    public boolean degrade() {
    	
    	if(ownsStarbucks == false){
    		if(vendingMachinesNo > 0) {
    			vendingMachinesNo--;
    			return true;
    		}else {  		
    		return false;
    		}
    	}
    	ownsStarbucks = false; 
    	vendingMachinesNo = 2;
    	return true;
    
    }
    public int getUpgradeCost() {
        return upgradeCost;
    }
    
    @Override
    public int getRentValue() {
    	int level = 0;
        Player groupOwner = LocationManager.getInstance().groupHasSameOwner(this.getGroupColor());
        if(groupOwner != null && groupOwner.getName().equals(this.getOwner().getName())) {
            System.out.println("there is an owner of grpup");
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

    @Override
    public void resetToDefault() {
        super.resetToDefault();
        this.vendingMachinesNo = 0;
        this.ownsStarbucks = false;
    }
}
