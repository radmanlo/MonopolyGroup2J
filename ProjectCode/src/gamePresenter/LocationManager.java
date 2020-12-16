package gamePresenter;

import com.sun.javafx.image.IntPixelGetter;
import models.location.BuyableLocation;
import models.location.Location;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationManager {
    private static LocationManager locationManager = null;
    private ArrayList<BuyableLocation> buyableLocations;
    private ArrayList<Location> nonBuyableLocations;

    private LocationManager(){ }

    public static LocationManager getInstance(){
        if (locationManager == null){
            locationManager = new LocationManager();
        }

        return locationManager;
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

//    public boolean groupHasSameOwner(int groupId){
//        HashMap<String, Integer> owners = new HashMap<String, Integer>();
//
//        for (BuyableLocation loc : buyableLocations){
//            Integer propertyCount = owners.get(loc.getOwner().getName());
//            if (propertyCount == null)
//                propertyCount = 0;
//
//            owners.put(loc.getOwner().getName(), propertyCount+1);
//        }
//    }

    public int noOfOwnerByPlayerInGroup(Player player, int groupId){
        return 0;
    }
}
