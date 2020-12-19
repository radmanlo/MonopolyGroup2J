package models.location;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import models.Player;

public class Disciplinary extends Location{
    private ArrayList<Player> prisoners;

    public Disciplinary(int locationId, String name, Point2D point, ArrayList<Player> playersHere,ArrayList<Player> prisoners) {
		super(locationId, name, point, playersHere, LOCATION_TYPES.DISCIPLINARY);
        this.prisoners = prisoners;

		// TODO Auto-generated constructor stub
	}


    @Override
    public void activate() {
        super.activate();
    }

    public void freePlayer(Player player){
        for (int i = 0; i < prisoners.size(); i++){
            if (prisoners.get(i).equals(player)){
                prisoners.remove(i);
                return;
            }
        }
    }

    public void arrestPlayer(Player player){
        prisoners.add(player);
    }

}