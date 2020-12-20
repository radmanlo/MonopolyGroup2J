package gamePresenter;

//import com.sun.javafx.image.IntPixelGetter;
import models.location.*;
import models.*;

        import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class LocationManager implements Serializable {
    // Properties
	private static final long serialVersionUID = -9040597026391464212L;
	private static LocationManager locationManager = null;
    private ArrayList<BuyableLocation> buyableLocations;
    private ArrayList<Location> nonBuyableLocations;

    // Copy constructor
    private LocationManager( LocationManager copy) {
        this.buyableLocations = new ArrayList<BuyableLocation>();
        this.nonBuyableLocations = new ArrayList<Location>();
    	for(int i = 0; i < copy.buyableLocations.size(); i++ ) {
    		buyableLocations.add(copy.buyableLocations.get(i));
    	}
    	for(int i = 0; i < copy.nonBuyableLocations.size(); i++ ) {
    		nonBuyableLocations.add(copy.nonBuyableLocations.get(i));
    	}
		for(int i = 0; i < copy.buyableLocations.size();i++) {
			//this.ownedLocations.add(copy.ownedLocations.get(i));
			Location loc = copy.buyableLocations.get(i);
			switch(loc.getClass().toString()) {
			case "class models.location.Property":
				Property pr = new Property((Property)copy.buyableLocations.get(i));
				this.buyableLocations.add(pr);
				break;
			case "class models.location.Utility":
				Utility ut = new Utility((Utility)copy.buyableLocations.get(i));
				this.buyableLocations.add(ut);
				break;
			case "class models.location.BusStop":
				BusStop bs = new BusStop((BusStop)copy.buyableLocations.get(i));
				this.buyableLocations.add(bs);
				break;	
			default:
			}
		}
		for(int i = 0; i < copy.nonBuyableLocations.size(); i++) {
			Location loc = copy.nonBuyableLocations.get(i);
			switch(loc.getClass().toString()) {
			
			case "class models.location.StartTile":
				StartTile bs = new StartTile((StartTile)copy.nonBuyableLocations.get(i));
				this.nonBuyableLocations.add(bs);
				break;
			case "class models.location.Disciplinary":
				Disciplinary dc = new Disciplinary((Disciplinary)copy.nonBuyableLocations.get(i));
				this.nonBuyableLocations.add(dc);
				break;
			case "class models.location.GoToDisciplinaryTile":
				GoToDisciplinaryTile gdc = new GoToDisciplinaryTile((GoToDisciplinaryTile)copy.nonBuyableLocations.get(i));
				this.nonBuyableLocations.add(gdc);
				break;

			case "class models.location.MayfestTile":
				MayfestTile mft = new MayfestTile((MayfestTile)copy.nonBuyableLocations.get(i));
				this.nonBuyableLocations.add(mft);
				break;
			case "class models.location.ChanceTile":
				ChanceTile cht = new ChanceTile((ChanceTile)copy.nonBuyableLocations.get(i));
				this.nonBuyableLocations.add(cht);
				break;
			case "class models.location.IncomeTaxTile":
				IncomeTaxTile itt = new IncomeTaxTile((IncomeTaxTile)copy.nonBuyableLocations.get(i));
				this.nonBuyableLocations.add(itt);
				break;	
			default:
				System.out.println("An error occurred on Map.paint()");
			}
		}
    }

    // Constructor
    private LocationManager(){
        this.buyableLocations = new ArrayList<BuyableLocation>();
        this.nonBuyableLocations = new ArrayList<Location>();
    }

    // Operational Methods
    public static LocationManager getInstance(){
        if (locationManager == null){
            locationManager = new LocationManager();
        }

        return locationManager;
    }
    
    //For Copying location Manager
    public void create(LocationManager copy) {
    	locationManager = new LocationManager(copy);
    }

    //Adds a new Buyable
    public void addBuyable(BuyableLocation aBuyable){
        this.buyableLocations.add(aBuyable);
    }
  
    //Adds a new NonBuyable
    public void addNonBuyable(Location aNonBuyable){
        this.nonBuyableLocations.add(aNonBuyable);
    }

    // Game Logic Methods
    public Location movePlayer(Player playerToMove, int distance){
        Location newLocation = null;

        // get the current location of the player
        Location currentLocation = getPlayerLocation(playerToMove);
        // calculate the next location
        int curLocId = currentLocation.getLocationId();
        if((curLocId + distance) >= 40) {
        	playerToMove.setUsableMoney(playerToMove.getUsableMoney()+200);
        }
        int nextLocId = (curLocId + distance) % 40; // There are 40 locations in total with ids: 0-39
        if(nextLocId == 0)
        	nextLocId = 40;
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

    //Returns location with given location id
    public Location getLocationById(int id){
        Location tmp = null;

        // Try the buyable locations
        for (Location loc : buyableLocations){
            if (loc.getLocationId() == id){
                tmp = loc;
            }
        }

        // Try the nonBuyable ones
        //if (tmp == null){
            for (Location loc : nonBuyableLocations){
                if (loc.getLocationId() == id){
                    tmp = loc;
                }
            }
        //}

        return tmp;
    }

    //Returns location list
    public ArrayList<Location> getLocationList(){
        ArrayList<Location> locationList = new ArrayList<>(0);

        for (Location loc : buyableLocations)
            locationList.add(loc);
        for (Location loc : nonBuyableLocations)
            locationList.add(loc);

        return  locationList;
    }

    //returns buyable list
    public ArrayList<BuyableLocation> getBuyableList(){
        return this.buyableLocations;
    }

    //Returns player if given all group color is his own and 
    public Player groupHasSameOwner(BuyableLocation.GroupColor groupColor){
        HashMap<String, Integer> owners = new HashMap<String, Integer>();
        String ownerName = "";
        Player owner = null;
        Integer propertyCount = 0;

        for (BuyableLocation loc : buyableLocations){
            if (loc.getGroupColor() == groupColor && loc.getOwner() != null){
                owner = loc.getOwner();
                ownerName = owner.getName();
                propertyCount = owners.get(ownerName); // Get the current count

                if (propertyCount == null) // To avoid crashing
                    propertyCount = 0;

                owners.put(ownerName, propertyCount+1); // update the count
            }
        }

        if (owners.size() == 1 && owners.get(ownerName) == Property.noOfPropertyPerColor(groupColor)){
            return owner;
        } else {
            return null;
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

    /**
     * Decides what to do based on the location type
     * @param locationToActivate
     */
    public void activateLocation(Location locationToActivate){
        System.out.println("Activate location called");
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
        System.out.println("Bus location activated");
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player busStationOwner = ((BuyableLocation)busLoc).getOwner();

        if (busStationOwner != null && busStationOwner.getName() != currentPlayer.getName()){ // Pay the rent
            GameManager.getInstance().askPlayerPaymentChoice();
        }
    }

    public void activateProperty(Location propertyLoc){
        System.out.println("Property location activated");

        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player propertyOwner = ((BuyableLocation)propertyLoc).getOwner();

        if(propertyOwner != null && propertyOwner.getName() != currentPlayer.getName()) // Pay the rent
            GameManager.getInstance().askPlayerPaymentChoice();
    }

    public void activateUtility(Location utilityLoc){
        System.out.println("Utility location activated");

        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player utilityOwner = ((BuyableLocation)utilityLoc).getOwner();

        if (utilityOwner != null && utilityOwner.getName() != currentPlayer.getName()) // Pay the rent
            GameManager.getInstance().askPlayerPaymentChoice();
    }

    public void activateIncomeTax(Location incomeTaxLoc){
        System.out.println("IncomeTax location activated");

        int taxValue = ((IncomeTaxTile)incomeTaxLoc).getTaxValue();
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();

        PlayerManager.getInstance().deductMoneyFromPlayer(currentPlayer, taxValue);
        GameManager.getInstance().updateUI();
    }

    public void activateMayfest(Location mayfestLoc){
        System.out.println("Mayfest location activated");

        int collectedTax = ((MayfestTile)mayfestLoc).getCollectedTax();
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();

        PlayerManager.getInstance().addMoneyToPlayer(currentPlayer, collectedTax);
    }

    public void activateChance(Location chanceLoc){
        System.out.println("chance location activated");

        Card aCard = CardManager.getInstance().getTopCard();
        Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

        if (aCard.isStorable()){ // If card is storable just store it
            curPlayer.addCard(aCard);
        } else { // Not storable execute it
            CardManager.getInstance().executeCardAction(aCard);
        }
    }

    public void activateDisciplinary(Location disciplinaryLoc){
        System.out.println("Disciplinary location activated");

        // NOTHING
    }

    public void activateGoToDisciplinary(Location goToDisciplinaryLoc){
        System.out.println("GOTODIsciplinary location activated");

        final int DISTANCE_TO_DISCIPLINARY = 20;
        Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
        curPlayer.setIsInJail(true);
        curPlayer.setInJailCount(3);
        GameManager.getInstance().movePlayer(curPlayer, DISTANCE_TO_DISCIPLINARY);
    }

    public void activateStart(Location startLoc){
        System.out.println("Start location activated");

        // NOTHING
    }

    /**
     * Checks if a certain property is upgradeable using the fact that the user has enough money and owns all group color
     * @param property
     * @return
     */
    public boolean isPropertyUpgradeable(Property property){
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        boolean upgradeable = false;

        if (groupHasSameOwner(property.getGroupColor()).getName() == currentPlayer.getName()){ // Player is the owner of the whole group
            upgradeable = true;
        }

        return upgradeable;
    }

    /**
     * Deducts money from the visitor and gives it to the owner
     * @param owner
     * @param visitor
     * @param amount
     */
    public void deductRentValue(Player owner, Player visitor, int amount){
        PlayerManager.getInstance().deductMoneyFromPlayer(visitor, amount);

        if (owner != null)
            PlayerManager.getInstance().addMoneyToPlayer(owner, amount);
    }

    /**
     * Buys a buyable location
     * @param location
     * @param buyer
     * @return
     */
    public boolean buyLocation(Location location, Player buyer){
        int price = ((BuyableLocation)location).getPrice();
        boolean successful = false;

        if (buyer.getUsableMoney() > price){
            PlayerManager.getInstance().deductMoneyFromPlayer(buyer, price);
            ((BuyableLocation)location).setOwner(buyer);
            successful = true;
        }

        return successful;
    }

    public void setLocationOwner(BuyableLocation location, Player player){
        location.setOwner(player);
    }

    public ArrayList<BuyableLocation> getAllLocationsOf(Player player){
        ArrayList<BuyableLocation> locations = new ArrayList<BuyableLocation>(0);

        for (BuyableLocation loc : buyableLocations){
            if (loc.getOwner().getName() == player.getName()){
                locations.add(loc);
            }
        }

        return locations;
    }

    public void freeAllLocationsOf(Player player){
        ArrayList<BuyableLocation> playersLocs = this.getAllLocationsOf(player);

        for (BuyableLocation loc : playersLocs){
            loc.resetToDefault();
        }
    }

    @Override
    public String toString() {
        return "LocationManager{" +
                "buyableLocations=" + buyableLocations.toString() +
                ", nonBuyableLocations=" + nonBuyableLocations.toString() +
                '}';
    }
    
}