package models.location;

import models.Player;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Disciplinary extends Location{
    public Disciplinary(int locationId, String name, Point2D point, ArrayList<Player> playersHere) {
        super(locationId, name, point, playersHere);
    }

    @Override
    public void activate() {
        super.activate();
    }

}
