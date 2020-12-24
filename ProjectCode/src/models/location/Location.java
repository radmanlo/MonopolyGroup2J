package models.location;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import models.Player;

public class Location implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1454862449987284592L;
	// Properties
    private int locationId;
    private String name;
    private Point2D point;
    private ArrayList<Player> playersHere;
    private LOCATION_TYPES type;
    public enum LOCATION_TYPES {BUS, CHANCE, DISCIPLINARY, GO_TO_DISCIPLINARY, INCOME_TAX, MAYFEST, PROPERTY, START, UTILITY};

    // Constructor
    public Location(int locationId, String name, Point2D point, ArrayList<Player> playersHere, LOCATION_TYPES type) {
        this.locationId = locationId;
        this.name = name;
        this.point = point;
        this.playersHere = playersHere;
        this.type = type;
    }

    /**
     * Copy constructor
     * @param copy
     */
    public Location(Location copy) {
    	this.locationId = copy.locationId;
    	this.name = copy.name;
    	this.point = copy.point;
    	this.playersHere = copy.playersHere;
    	this.type = copy.type;
    }
    
    
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                ", point=" + point +
//                ", playersHere=" + playersHere + // causes stack over flow
                '}';
    }

    // Methods
    public void activate(){
        // To be implemented
    }

    /**
     * Checks players if here
     * @param thePlayer
     * @return
     */
    public boolean hasPlayer(Player thePlayer){
        for (Player player : playersHere){
            if (player.getName().equals( thePlayer.getName())){
                return true;
            }
        }

        return false;
    }

    /**
     * Returns location id
     * @return
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets location id
     * @param locationId
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * Returns name of location
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of location
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns point of location on map
     * @return
     */
    public Point2D getPoint() {
        return point;
    }

    /**
     * Sets point of location on map
     * @param point
     */
    public void setPoint(Point2D point) {
        this.point = point;
    }

    /**
     * Removes player from inside
     * @param player
     */
    public void removePlayerFromHere(Player player) {
        for (int i = 0; i < playersHere.size(); i++){
            if (playersHere.get(i).getName().equals(player.getName())){
                playersHere.remove(i);
                return;
            }
        }
    }

    /**
     * Adds player for playersHere
     * @param player
     */
    public void addPlayerHere(Player player) {
    	
        this.playersHere.add(player);
    }

    /**
     * Returns number of player in here
     * @return
     */
    public int getNoOfPlayersHere(){
        return this.playersHere.size();
    }

    /**
     * Returns player list who are on location
     * @return
     */
    public ArrayList<Player> getAllPlayersHere(){
        return this.playersHere;
    }

    /**
     * returns location type
     * @return
     */
    public LOCATION_TYPES getType(){
        return this.type;
    }

    /*
     * Prints players here
     */
    public void printPlayersHere() {
    	for(int i = 0; i < this.getAllPlayersHere().size(); i++) {
    		System.out.print(this.playersHere.get(i).getName() + " - ");
    	}
    }
}
