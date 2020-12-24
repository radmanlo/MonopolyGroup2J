package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import gamePresenter.BoardManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;

public class IncomeTaxTile extends Location{
    private int taxValue;
/**
 * Construcotr of Income tax tile
 * @param locationId
 * @param name
 * @param point
 * @param playersHere
 * @param taxValue
 */
    public IncomeTaxTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int taxValue) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.INCOME_TAX);
        this.taxValue = taxValue;
    }

    /**
     * Copy constructor of incometaxtile
     * @param copy
     */
    public IncomeTaxTile( IncomeTaxTile copy) {
    	super(copy);
    	this.taxValue = copy.taxValue;
    }
    /**
     * retuns tax value of tax tile
     * @return
     */
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
     *Deduct player money with tax
     * @param player
     */
    public void deductTax(Player player){
        player.setUsableMoney(player.getUsableMoney() - this.taxValue);
    }
}
