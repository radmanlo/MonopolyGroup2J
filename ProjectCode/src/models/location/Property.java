package models.location;

import java.util.ArrayList;

public class Property extends BuyableLocation{
    private ArrayList<Integer> rentValues;
    private int vendingMachinesNo;
    private boolean ownsStarbucks;

    Property(){
        super();
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

    public ArrayList<Integer> getRentValues() {
        return rentValues;
    }

    public int getVendingMachinesNo() {
        return vendingMachinesNo;
    }
	
    public boolean hasStarbucks() {
        return ownsStarbucks;
    }
}
