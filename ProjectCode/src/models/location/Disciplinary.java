package models.location;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import models.Player;

public class Disciplinary extends Location{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> prisoners;

	/*
	 * Constructor of Disciplinary Location
	 */
    public Disciplinary(int locationId, String name, Point2D point, ArrayList<Player> playersHere,ArrayList<Player> prisoners) {
		super(locationId, name, point, playersHere, LOCATION_TYPES.DISCIPLINARY);
		
		prisoners = new ArrayList<Player>();
		
		for( Player plyr:prisoners)
			this.prisoners.add( new Player(plyr));
	}
	/*
	 * Copy Constructor of Disciplinary Location
	 */
    public Disciplinary(Disciplinary copy) {
    	super(copy);
    	prisoners = new ArrayList<Player>();
 
    	
    	if( copy.prisoners == null)
    		return;
    	
    	for(int i = 0; i < copy.prisoners.size(); i++) {
    		Player plyr = new Player(copy.prisoners.get(i));
    		this.prisoners.add(plyr);
    	}
    }

    @Override
    public void activate() {
        super.activate();
    }
	/*
	 * Frees a player if in prison
	 */
    public void freePlayer(Player player){
        for (int i = 0; i < prisoners.size(); i++){
            if (prisoners.get(i).equals(player)){
                prisoners.remove(i);
                return;
            }
        }
    }

    /**
     * Arrests a player
     * @param player
     */
    public void arrestPlayer(Player player){
        prisoners.add(player);
    }

}