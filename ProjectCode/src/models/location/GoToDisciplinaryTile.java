package models.location;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import models.Player;

public class GoToDisciplinaryTile extends Location{

    public GoToDisciplinaryTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere) {
        super(locationId, name, point, playersHere);
    }

    @Override
    public void activate() {
        super.activate();
    }



}
