package resources;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Location {
    // Properties
    private int locationId;
    private String name;
    private Point2D point;
    private ArrayList<Player> playersHere;

    // Constructor
    Location(){

    }

    // Methods
    public void activate(){

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
}
