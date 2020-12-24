package models.location;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import gamePresenter.BoardManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;
import models.Player;

public class GoToDisciplinaryTile extends Location{

	/**
	 * Constructor of Go to disciplinary location
	 * @param locationId
	 * @param name
	 * @param point
	 * @param playersHere
	 */
    public GoToDisciplinaryTile(int locationId, String name, Point2D point, ArrayList<Player> playersHere) {
        super(locationId, name, point, playersHere, LOCATION_TYPES.GO_TO_DISCIPLINARY);
    }

    /**
     * Copy constructor of Goto disciplinary tile
     * @param copy
     */
    public GoToDisciplinaryTile(GoToDisciplinaryTile copy) {
    	super(copy);
    }
    @Override
    public void activate() {
        super.activate();
//        PlayerManager.getInstance().getCurrentPlayer().setInJailCount(3);
//        PlayerManager.getInstance().getCurrentPlayer().setIsInJail(true);
//        LocationManager.getInstance().movePlayer(PlayerManager.getInstance().getCurrentPlayer(),10);
//        BoardManager.getInstance().updateMap();
    }



}
