package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ChanceTile extends Location{
    public ChanceTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.CHANCE);
    }

    @Override
    public void activate() {
        super.activate();
    }
}
