package gamePresenter;

import models.location.BusStop;
//import com.sun.javafx.image.IntPixelGetter;
import models.location.BuyableLocation;
import models.location.ChanceTile;
import models.location.Disciplinary;
import models.location.GoToDisciplinaryTile;
import models.location.IncomeTaxTile;
import models.location.Location;
import models.location.MayfestTile;
import models.location.Property;
import models.location.StartTile;
import models.location.Utility;
import models.*;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LocationManager implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9040597026391464212L;
	private static LocationManager locationManager = null;
    private ArrayList<BuyableLocation> buyableLocations;
    private ArrayList<Location> nonBuyableLocations;

    private LocationManager(){
        this.buyableLocations = new ArrayList<BuyableLocation>();
        this.nonBuyableLocations = new ArrayList<Location>();
    }

    public static LocationManager getInstance(){
        if (locationManager == null){
            locationManager = new LocationManager();
        }

        return locationManager;
    }
    
    public Location movePlayer(Player playerToMove, int distance){
        Location newLocation = null;

        // get the current location of the player
        Location currentLocation = getPlayerLocation(playerToMove);

        // calculate the next location
        int curLocId = currentLocation.getLocationId();
        int nextLocId = (curLocId + distance) % 40; // There are 40 locations in total with ids: 0-39

        // remove player from current location
        currentLocation.removePlayerFromHere(playerToMove);

        // add player to new location
        newLocation = getLocationById(nextLocId);
        newLocation.addPlayerHere(playerToMove);

        return newLocation;
    }

    public Location getPlayerLocation(Player thePlayer){
        Location playerLocation = null;
        boolean found = false;

        for (Location loc : buyableLocations){
            if (loc.hasPlayer(thePlayer)){
                playerLocation = loc;
                found = true;
                break;
            }
        }

        if (!found)
            for (Location loc : nonBuyableLocations){
                if (loc.hasPlayer(thePlayer)){
                    playerLocation = loc;
                    break;
                }
            }

        return playerLocation;
    }

    public Location getLocationById(int id){
        Location tmp = null;

        // Try the buyable locations
        for (Location loc : buyableLocations){
            if (loc.getLocationId() == id){
                tmp = loc;
            }
        }

        // Try the nonBuyable ones
        if (tmp == null){
            for (Location loc : nonBuyableLocations){
                if (loc.getLocationId() == id){
                    tmp = loc;
                }
            }
        }

        return tmp;
    }

    public ArrayList<Location> getLocationList(){
        ArrayList<Location> locationList = new ArrayList<>(0);

        for (Location loc : buyableLocations)
            locationList.add(loc);
        for (Location loc : nonBuyableLocations)
            locationList.add(loc);

        return  locationList;
    }

    public ArrayList<BuyableLocation> getBuyableList(){
        return this.buyableLocations;
    }

    public boolean groupHasSameOwner(BuyableLocation.GroupColor groupColor){
        HashMap<String, Integer> owners = new HashMap<String, Integer>();
        String ownerName = "";

        for (BuyableLocation loc : buyableLocations){
            if (loc.getGroupColor() == groupColor){
                Integer propertyCount = owners.get(loc.getOwner().getName());
                if (propertyCount == null)
                    propertyCount = 0;
                ownerName = loc.getOwner().getName();

                owners.put(ownerName, propertyCount+1);
            }
        }

        if (owners.size() ==1 && owners.get(ownerName) > 3){ // TODO not always 3!!
            return true;
        } else {
            return false;
        }
    }

    public int noOfOwnedByPlayerInGroup(Player player, BuyableLocation.GroupColor groupColor){
        String playerName = player.getName();
        int count = 0;

        for (BuyableLocation loc : buyableLocations){
            if (loc.getGroupColor() == groupColor && loc.getOwner().getName() == playerName){
                count ++;
            }
        }

        return count;
    }

    public void addBuyable(BuyableLocation aBuyable){
        this.buyableLocations.add(aBuyable);
    }

    public void addNonBuyable(Location aNonBuyable){
        this.nonBuyableLocations.add(aNonBuyable);
    }

    public void activateLocation(Location locationToActivate){
        // Check the location type
        if (locationToActivate.getType() == Location.LOCATION_TYPES.BUS){
            this.activateBus(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.CHANCE){
            this.activateChance(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.DISCIPLINARY){
            this.activateDisciplinary(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.GO_TO_DISCIPLINARY){
            this.activateGoToDisciplinary(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.INCOME_TAX){
            this.activateIncomeTax(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.MAYFEST){
            this.activateMayfest(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.PROPERTY){
            this.activateProperty(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.START){
            this.activateStart(locationToActivate);
        } else if (locationToActivate.getType() == Location.LOCATION_TYPES.UTILITY){
            this.activateUtility(locationToActivate);
        }
    }

    public void activateBus(Location busLoc){
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player busStationOwner = ((BuyableLocation)busLoc).getOwner();
        int rentValue = ((BuyableLocation)busLoc).getCurrentRentValue();

        if (busStationOwner == null){
            // Check money if enough
            // Prompt to buy property

            // if OK Buy the property
            buyProperty(busLoc, currentPlayer);
        } else {
            deductRentValue(busStationOwner, currentPlayer, rentValue);
        }
    }

    public void activateChance(Location chanceLoc){

    }

    public void activateDisciplinary(Location disciplinaryLoc){

    }

    public void activateGoToDisciplinary(Location goToDisciplinaryLoc){

    }

    public void activateIncomeTax(Location incomeTaxLoc){

    }

    public void activateMayfest(Location mayfestLoc){

    }

    public void activateProperty(Location propertyLoc){

    }

    public void activateStart(Location startLoc){

    }

    public void activateUtility(Location utilityLoc){

    }

    /**
     * Deducts money from the visitor and gives it to the owner
     * @param owner
     * @param visitor
     * @param amount
     */
    public void deductRentValue(Player owner, Player visitor, int amount){
        visitor.setUsableMoney(visitor.getUsableMoney() - amount);
        owner.setUsableMoney(owner.getUsableMoney() + amount);
    }

    public boolean buyProperty(Location location, Player buyer){
        int price = ((BuyableLocation)location).getPrice();
        boolean successful = false;

        if (buyer.getUsableMoney() > price){
            buyer.setUsableMoney(buyer.getUsableMoney() - price);
            ((BuyableLocation)location).setOwner(buyer);
            successful = true;
        }

        return successful;
    }

    @Override
    public String toString() {
        return "LocationManager{" +
                "buyableLocations=" + buyableLocations.toString() +
                ", nonBuyableLocations=" + nonBuyableLocations.toString() +
                '}';
    }
    
}