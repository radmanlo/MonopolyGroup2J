package models.location;
import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MayfestTile extends Location{
    private int collectedTax;

    /**
     * Constructor with given parameters
     * @param locationId
     * @param name
     * @param point
     * @param playersHere
     * @param collectedTax
     */
    public MayfestTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int collectedTax) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.MAYFEST);
        this.collectedTax = collectedTax;
    }
    /**
     * Copy construcotr
     * @param copy
     */
    public MayfestTile(MayfestTile copy) {
    	super(copy);
    	this.collectedTax = copy.collectedTax;
    }

    /**
     * Returns collected tax
     * @return
     */
    public int getCollectedTax(){
        return this.collectedTax;
    }

    @Override
    public void activate() {
        super.activate();
    }

    /**
     * Adds tax inside
     * @param tax
     */
    public void addTax(int tax){
        this.collectedTax += tax;
    }

    /**
     * Give a tax to money method
     * @param player
     */
    public void giveTaxMoney(Player player){ // TODO Should not get player as param
        player.setUsableMoney(player.getUsableMoney()+this.collectedTax);
        this.collectedTax = 0;
    }
}
