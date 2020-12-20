package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import gamePresenter.BoardManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;

public class IncomeTaxTile extends Location{
    private int taxValue;

    public IncomeTaxTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int taxValue) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.INCOME_TAX);
        this.taxValue = taxValue;
    }

    public IncomeTaxTile( IncomeTaxTile copy) {
    	super(copy);
    	this.taxValue = copy.taxValue;
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
        PlayerManager.getInstance().getCurrentPlayer().setUsableMoney(PlayerManager.getInstance().getCurrentPlayer().getUsableMoney() - taxValue);
        BoardManager.getInstance().updateInteractionArea();
        BoardManager.getInstance().updateMap();
    }

    /**
     *
     * @param player
     */
    public void deductTax(Player player){
        player.setUsableMoney(player.getUsableMoney() - this.taxValue);
    }
}
