package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class IncomeTaxTile extends Location{
    private int taxValue;

    public IncomeTaxTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int taxValue) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.INCOME_TAX);
        this.taxValue = taxValue;
    }

    public int getTaxValue(){
        return this.taxValue;
    }

    /**
     *
     */
    @Override
    public void activate() {
        super.activate();
    }

    /**
     *
     * @param player
     */
    public void deductTax(Player player){
        player.setUsableMoney(player.getUsableMoney() - this.taxValue);
    }
}
