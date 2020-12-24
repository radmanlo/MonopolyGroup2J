package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ChanceTile extends Location{
	/*
	 * Constructor of chance tile
	 */
    public ChanceTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.CHANCE);
    }
	/*
	 * Copy Constructor of chance tile
	 */
    public ChanceTile(ChanceTile copy) {
    	super(copy);
    }
    @Override
    public void activate() {
        super.activate();
    }
}
