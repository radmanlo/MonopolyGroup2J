package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class StartTile extends Location{
    private int prizeValue;

    /**
     * Constuctor with given parameters
     * @param locationId
     * @param name
     * @param point
     * @param playersHere
     * @param prizeValue
     */
    public StartTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int prizeValue) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.START);
        this.prizeValue = prizeValue;
    }

    /**
     * Copy construcot
     * @param startTile
     */
    public StartTile(StartTile startTile) {
		// TODO Auto-generated constructor stub
    	super(startTile);
    	this.prizeValue = prizeValue;
	}

	@Override
    public void activate() { // TODO to be implemented
        super.activate();
    }

	/**
	 * Given prize
	 * @return
	 */
    public int getPrize(){
        return this.prizeValue;
    }
}
