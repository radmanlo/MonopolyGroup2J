package gamePresenter;

//import com.sun.javafx.image.IntPixelGetter;
import models.location.*;
import models.*;

        import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    	//	buyableLocations.add(copy.buyableLocations.get(i));
    	}
    	for(int i = 0; i < copy.nonBuyableLocations.size(); i++ ) {
    //		nonBuyableLocations.add(copy.nonBuyableLocations.get(i));
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
    
    //Changes the values of the location manager with given manager
    public void set(LocationManager copy) {
        this.buyableLocations = new ArrayList<BuyableLocation>();
        this.nonBuyableLocations = new ArrayList<Location>();
    	for(int i = 0; i < copy.buyableLocations.size(); i++ ) {
    	//	buyableLocations.add(copy.buyableLocations.get(i));
    	}
    	for(int i = 0; i < copy.nonBuyableLocations.size(); i++ ) {
    //		nonBuyableLocations.add(copy.nonBuyableLocations.get(i));
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
    //For Copying location Manager
    public void create(LocationManager copy) {
    	this.set(copy);
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
        if((curLocId + distance) > 40) {
        	if(playerToMove.getIsInJail() == false) {
        		playerToMove.setUsableMoney(playerToMove.getUsableMoney()+200);
        		JFrame f =new JFrame();  
        		JOptionPane.showMessageDialog(f, "Congratulations, you passed Start Tile and WON 200 TL !!!");  
        		SoundManager.getInstance().playOnPassingStartTile();
        	}
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

    //Get location of given player
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
    
    //Returns whether current place of player is buyable
    public boolean isPlaceBuyable() {
    	Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
 
		Location loc = curPlayer.getLocation();
		System.out.println(loc.getClass().toString());

		switch(loc.getClass().toString()) {
		case "class models.location.Property":
			return true;
		case "class models.location.Utility":
			return true;
		case "class models.location.BusStop":
			return true;
		default:
			return false;
		}
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
    public ArrayList<String> getBuyablesOfPlayer(Player curPlayer){
    	ArrayList<String> arr = new ArrayList<String>();
        for (BuyableLocation loc : buyableLocations) {
        	if(loc != null) {
        		if(loc.getOwner() != null) {
        			if(loc.getOwner().getName().equals(curPlayer.getName())) {
        				arr.add(loc.getName());
        			}
        		}
        	}
        }
        return arr;
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

                System.out.println("The property count so far : " + propertyCount);

                owners.put(ownerName, propertyCount+1); // update the count
            }
        }

        System.out.println("number of buyables of the color : " + Property.noOfBuyablePerColor(groupColor));

        if (owners.size() == 1){
            ownerName = getKey(owners, Property.noOfBuyablePerColor(groupColor)); // get the owner with the value
            System.out.println("the group owner is : " + ownerName);
            return PlayerManager.getInstance().getPlayerByName(ownerName);
        } else {
            System.out.println("Group has no owner");
            return null;
        }
    }

    public static String getKey(HashMap<String, Integer> map, Integer value) {
        System.out.println("finding the key for: " + value);

        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("an intry : " + entry.getValue() + "  " + entry.getKey());
            if (value.equals(entry.getValue())) {
                System.out.println("Found that is : " + entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }

    public int noOfOwnedByPlayerInGroup(Player player, BuyableLocation.GroupColor groupColor){
        String playerName = player.getName();
        int count = 0;

        for (BuyableLocation loc : buyableLocations){
            if (loc.getOwner() != null){
                System.out.println("the Location owner is " + loc.getOwner().getName() + " and we are lokking for properties of " + playerName);
                if (loc.getGroupColor() == groupColor && loc.getOwner().getName() == playerName){
                    count ++;
                }
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

        if(busStationOwner != null) {
        	GameManager.getInstance().disableBuyIfSameOwner();
        }
        if (busStationOwner != null && busStationOwner.getName() != currentPlayer.getName()){ // Pay the rent
            GameManager.getInstance().askPlayerPaymentChoice();
        }
    }

    public void activateProperty(Location propertyLoc){
        System.out.println("Property location activated");

        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player propertyOwner = ((BuyableLocation)propertyLoc).getOwner();
        
        //System.out.println(currentPlayer.getName() +" Current Player Name ||| Property Owner " + propertyOwner.getName());

        if(propertyOwner != null ) {
        	GameManager.getInstance().disableBuyIfSameOwner();
        }
        if(propertyOwner != null && propertyOwner.getName() != currentPlayer.getName()) { // Pay the rent
            SoundManager.getInstance().playLandedOnBuyableWithOwnerSound();
            GameManager.getInstance().askPlayerPaymentChoice();
        }
    }

    public void activateUtility(Location utilityLoc){
        System.out.println("Utility location activated");

        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Player utilityOwner = ((BuyableLocation)utilityLoc).getOwner();
        if(utilityOwner != null ) {
        	GameManager.getInstance().disableBuyIfSameOwner();
        }
        if (utilityOwner != null && utilityOwner.getName() != currentPlayer.getName()) // Pay the rent
            GameManager.getInstance().askPlayerPaymentChoice();
    }

    public void activateIncomeTax(Location incomeTaxLoc){
        System.out.println("IncomeTax location activated");

        //Play sound effect
        SoundManager.getInstance().playPayTaxSound();
        int taxValue = ((IncomeTaxTile)incomeTaxLoc).getTaxValue();
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
		JFrame f =new JFrame();  
		JOptionPane.showMessageDialog(f, "Oops! You have to pay taxes: " + taxValue + " TL");  
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

        // Play sound effect
        SoundManager.getInstance().playLandedOnChanceCardSound();

        Card aCard = CardManager.getInstance().getTopCard();
        Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();

        JFrame f =new JFrame();
        JOptionPane.showMessageDialog(f, aCard.getDescription());

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

        // Play sound effect
        SoundManager.getInstance().playLandedOnGotoJailSound();

        final int DISTANCE_TO_DISCIPLINARY = 20;
        Player curPlayer = PlayerManager.getInstance().getCurrentPlayer();
        curPlayer.setIsInJail(true);
        curPlayer.setInJailCount(3);
        GameManager.getInstance().movePlayer(curPlayer, DISTANCE_TO_DISCIPLINARY);
		 JFrame f =new JFrame();  
		 JOptionPane.showMessageDialog(f,"You got caught cheating. You are suspended for 3 turns.");  
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
        Player owner = groupHasSameOwner(property.getGroupColor());

        if (owner != null && owner.getName() == currentPlayer.getName()
                && currentPlayer.getUsableMoney() > property.getPrice()
                && !property.hasStarbucks()){ // Player is the owner of the whole group

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

    public void freeAllLocationsOf(Player player){
        //ArrayList<BuyableLocation> playersLocs = this.getAllLocationsOf(player);

       // for (BuyableLocation loc : playersLocs){
          //  loc.resetToDefault();
        //}
    }

    @Override
    public String toString() {
        return "LocationManager{" +
                "buyableLocations=" + buyableLocations.toString() +
                ", nonBuyableLocations=" + nonBuyableLocations.toString() +
                '}';
    }
    
    
    /*
     * gets the name of the location and returns the location
     */
	public Location getLocationByName(String locName) {
		ArrayList<Location> list = getLocationList();
		
	    for(int i = 0; i < list.size();i++) {
	    	if(list.get(i).getName().equals(locName))
	    		return list.get(i);
	    }
		System.out.println("Lctn mngr getlocationbyname returned null");
		return null;
	}

	/*
	 * returns if the property is sellable (if it has upgrades on it, it cant be selled)
	 */
	public boolean isPropertySellable(Property property) {
        boolean sellable = true;

        for (BuyableLocation prop : buyableLocations){
            if (prop.getType() == Location.LOCATION_TYPES.PROPERTY && prop.getGroupColor() == property.getGroupColor()){
                if (((Property)prop).getVendingMachinesNo() != 0 || ((Property)prop).hasStarbucks()){
                    sellable = false;
                }
            }
        }

		return sellable;
	}

	/*
	 * returns if the property is degradeable (if it has no upgrades on it, it cant be degraded)
	 */
	public boolean isPropertyDegradeable(Property property) {
		if(property.getVendingMachinesNo() == 0) {
			if(property.hasStarbucks() == false) {
				return false;
			}
			return true;
		}
		
		return true;
	}

	/*
	 * takes an array of strings of the names of locations and returns an array of buyables
	 */
	public ArrayList<BuyableLocation> getBuyablesByStrings(ArrayList<String> wantedNames) {
		ArrayList<BuyableLocation> temp = new ArrayList<BuyableLocation>();
		for(int i = 0; i < this.buyableLocations.size(); i++) {
			for(int j = 0; j < wantedNames.size(); j++) {
				if(buyableLocations.get(i).getName().equals(wantedNames.get(j))){
					temp.add(buyableLocations.get(i));
				}
			}		
		}
		return temp;
	}

	/*
	 * Get buyables of player
	 */
	public ArrayList<Property> getPropertiesOfPlayer(Player aPlayer){
	    ArrayList<Property> propertiesOfPlayer = new ArrayList<Property>(0);

	    for (BuyableLocation buyable : buyableLocations){
	        if (buyable.getType() == Location.LOCATION_TYPES.PROPERTY){
	            if (buyable.getOwner() != null && buyable.getOwner().getName() == aPlayer.getName())
	                propertiesOfPlayer.add((Property) buyable);
            }
        }

	    return propertiesOfPlayer;
    }
	/*
	 * Makes maanager ready for initialization load data
	 */
	public void readyForInitialize() {
		this.buyableLocations = new ArrayList<BuyableLocation>();
		this.nonBuyableLocations = new ArrayList<Location>();
	}
}