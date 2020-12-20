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
    public Location(int locationId, String name, Point2D point, ArrayList<Player> playersHere) {
        this.locationId = locationId;
        this.name = name;
        this.point = point;
        this.playersHere = playersHere;
        this.type = type;
    }

    public Location(Location copy) {
    	this.locationId = copy.locationId;
    	this.name = copy.name;
    	this.point = copy.point;
    	this.playersHere = copy.playersHere;
    }
    
    
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", playersHere=" + playersHere +
                '}';
    }

    // Methods
    public void activate(){
        // To be implemented
    }

    public boolean hasPlayer(Player thePlayer){
        for (Player player : playersHere){
            if (player.getName() == thePlayer.getName()){
                return true;
            }
        }

        return false;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    public void removePlayerFromHere(Player player) {
        for (int i = 0; i < playersHere.size(); i++){
            if (playersHere.get(i).equals(player)){
                playersHere.remove(i);
                return;
            }
        }
    }

    public void addPlayerHere(Player player) {
        this.playersHere.add(player);
    }

    public int getNoOfPlayersHere(){
        return this.playersHere.size();
    }

    public ArrayList<Player> getAllPlayersHere(){
        return this.playersHere;
    }

    public LOCATION_TYPES getType(){
        return this.type;
    }
}
