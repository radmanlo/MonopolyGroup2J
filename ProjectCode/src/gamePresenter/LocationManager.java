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
    
    public void create(LocationManager copy) {
    	locationManager = new LocationManager(copy);
    }

    public void addBuyable(BuyableLocation aBuyable){
        this.buyableLocations.add(aBuyable);
    }

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

    public Player groupHasSameOwner(BuyableLocation.GroupColor groupColor){
        HashMap<String, Integer> owners = new HashMap<String, Integer>();
        String ownerName = "";
        Player owner = null;
        Integer propertyCount = 0;

        for (BuyableLocation loc : buyableLocations){
            if (loc.getGroupColor() == groupColor){
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
            if (currentPlayer.getUsableMoney() >= ((BuyableLocation) busLoc).getPrice()){
                // TODO Prompt to buy location

                // if OK Buy the property
                boolean boughtTheBusStation = buyLocation(busLoc, currentPlayer);
                if (boughtTheBusStation){
                    busStationOwner = currentPlayer; // Current player bought it successfully
                }
            }
        }

        if (busStationOwner.getName() != currentPlayer.getName()){ // Rent the bus station
            deductRentValue(busStationOwner, currentPlayer, rentValue);
        }
    }

    public void activateProperty(Location propertyLoc){
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player propertyOwner = ((BuyableLocation)propertyLoc).getOwner();
        int rentValue = ((BuyableLocation)propertyLoc).getCurrentRentValue();

        if (propertyOwner == null){ // Buy property
            // Check money if enough
            if (currentPlayer.getUsableMoney() >= ((Property) propertyLoc).getPrice()){
                // TODO Prompt to buy property

                // if OK Buy the property
                boolean boughtTheProperty = buyLocation(propertyLoc, currentPlayer);
                if (boughtTheProperty){
                    propertyOwner = currentPlayer;
                }
            }
        } else { // Upgrade property
            if (isPropertyUpgradeable((Property)propertyLoc)){
                if (((Property)propertyLoc).getUpgradeCost() <= currentPlayer.getUsableMoney()) { //Player has enough money
                    // TODO Yes, then Suggest to upgrade by prompt
                    // If accepts upgrade
                    ((Property) propertyLoc).upgrade();
                }
            }
        }

        if (propertyOwner.getName() != currentPlayer.getName()){ // Pay rent
            deductRentValue(propertyOwner, currentPlayer, rentValue);
        }
    }

    public void activateUtility(Location utilityLoc){
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player utilityOwner = ((BuyableLocation)utilityLoc).getOwner();
        int rentValue = ((BuyableLocation)utilityLoc).getCurrentRentValue();

        if (utilityOwner == null){ // Buy the utility
            if (currentPlayer.getUsableMoney() >= ((BuyableLocation) utilityLoc).getPrice()){
                // TODO Prompt to buy property

                // if OK Buy the property
                boolean boughtTheUtility = buyLocation(utilityLoc, currentPlayer);
                if (boughtTheUtility){
                    utilityOwner = currentPlayer;
                }
            }
        }

        if (utilityOwner.getName() != currentPlayer.getName()){ // Rent the utility
            deductRentValue(utilityOwner, currentPlayer, rentValue);
        }
    }

    public void activateIncomeTax(Location incomeTaxLoc){
        int taxValue = ((IncomeTaxTile)incomeTaxLoc).getTaxValue();
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();

        PlayerManager.getInstance().deductMoneyFromPlayer(currentPlayer, taxValue);
    }

    public void activateMayfest(Location mayfestLoc){
        int collectedTax = ((MayfestTile)mayfestLoc).getCollectedTax();
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();

        PlayerManager.getInstance().addMoneyToPlayer(currentPlayer, collectedTax);
    }

    public void activateChance(Location chanceLoc){
        Card aCard = CardManager.getInstance().getTopCard();
        Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

        if (aCard.isStorable()){ // If card is storable just store it
            curPlayer.addCard(aCard);
        } else { // Not storable execute it
            CardManager.getInstance().executeCardAction(aCard);
        }
    }

    public void activateDisciplinary(Location disciplinaryLoc){
        // NOTHING
    }

    public void activateGoToDisciplinary(Location goToDisciplinaryLoc){
        final int DISTANCE_TO_DISCIPLINARY = 20;
        Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
        GameManager.getInstance().movePlayer(curPlayer, DISTANCE_TO_DISCIPLINARY);
    }

    public void activateStart(Location startLoc){
        // TODO don't know
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

    @Override
    public String toString() {
        return "LocationManager{" +
                "buyableLocations=" + buyableLocations.toString() +
                ", nonBuyableLocations=" + nonBuyableLocations.toString() +
                '}';
    }
    
}