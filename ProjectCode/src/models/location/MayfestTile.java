package models.location;
import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MayfestTile extends Location{
    private int collectedTax;

    public MayfestTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int collectedTax) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.MAYFEST);
        this.collectedTax = collectedTax;
    }
    public MayfestTile(MayfestTile copy) {
    	super(copy);
    	this.collectedTax = copy.collectedTax;
    }

    public int getCollectedTax(){
        return this.collectedTax;
    }

    @Override
    public void activate() {
        super.activate();
    }

    public void addTax(int tax){
        this.collectedTax += tax;
    }

    public void giveTaxMoney(Player player){ // TODO Should not get player as param
        player.setUsableMoney(player.getUsableMoney()+this.collectedTax);
        this.collectedTax = 0;
    }
}
