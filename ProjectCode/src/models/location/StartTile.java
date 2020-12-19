package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class StartTile extends Location{
    private int prizeValue;

    public StartTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere, int prizeValue) {
        super(locationId, name, point, playersHere);
        this.prizeValue = prizeValue;
    }

    @Override
    public void activate() { // TODO to be implemented
        super.activate();
    }

    public int getPrize(){
        return this.prizeValue;
    }
}
