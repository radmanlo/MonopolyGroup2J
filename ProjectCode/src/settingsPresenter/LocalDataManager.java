package settingsPresenter;
import javax.xml.parsers.*;
import org.xml.sax.*;

import gamePresenter.CardManager;
import gamePresenter.LocationManager;
import models.Card;
import models.Player;
import models.location.BusStop;
import models.location.BuyableLocation;
import models.location.ChanceTile;
import models.location.Disciplinary;
import models.location.GoToDisciplinaryTile;
import models.location.IncomeTaxTile;
import models.location.MayfestTile;
import models.location.Property;
import models.location.StartTile;
import models.location.Utility;

import org.w3c.dom.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;

public class LocalDataManager {
	private String localPath;
	private static LocalDataManager localManager = null;

	private LocalDataManager() {}

	public static LocalDataManager getInstance(){
		if (localManager == null)
			localManager = new LocalDataManager();
		return localManager;
	}
	
	public ArrayList<Integer> getStoredGamesIds(){ 
		return null;
	}
	
	public boolean deleteSavedGames(int id) {
		return false;
	}
	
	public String getSavedGameNameById(int id) { 
		return "";
	}
	
	public boolean saveGame() {
		return false;
	}

	private String readFileToString (String path) throws Exception {
		String xmlString = "";
		File file = null;
		int line;

		FileInputStream fileInputStream = null;
		try {
			file = new File(path);
			fileInputStream = new FileInputStream(file);

			while ((line=fileInputStream.read()) != -1) {
				xmlString += (char)line;
			}
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}

		return xmlString;
	}

	public Document getDefaultValues(){
		String xmlString = "";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			Path xmlPath = Paths.get( "resources", "defaultGame.xml");
			xmlString = readFileToString(xmlPath.toAbsolutePath().normalize().toString());

			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
				return doc;
			} catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		} catch (Exception ex){
			System.out.println("An exception" + ex.toString());
			return null;
		}
	}
	
	public void initialize(Document doc) {
		// Parse the information from the default game document
				NodeList cards = doc.getElementsByTagName("card");

				NodeList properties = doc.getElementsByTagName("property");
				NodeList chanceLocs = doc.getElementsByTagName("chance");
				NodeList incomeTaxLocs = doc.getElementsByTagName("incomeTax");
				NodeList busLines = doc.getElementsByTagName("bus");
				NodeList utilities = doc.getElementsByTagName("utility");

				Node startLoc = doc.getElementsByTagName("startLoc").item(0);
				Node goToDisciplinaryTile = doc.getElementsByTagName("goToDisciplinaryTile").item(0);
				Node mayfest = doc.getElementsByTagName("mayfest").item(0);
				Node disciplinary = doc.getElementsByTagName("disciplinary").item(0);

				addCardsToCardManager(cards);
				addPropertiesToLocationManager(properties);
				addChanceLocationsToLocsManager(chanceLocs);
				addIncomeTaxLocsToLocationManager(incomeTaxLocs);
				addBusLocsToLocationsManager(busLines);
				addUtilitiesToLocationManager(utilities);
				addStartTileToLocationManager(startLoc);
				addDisciplinaryTileToLocationManager(disciplinary);
				addMayfestTileToLocationManager(mayfest);
				addGoToDisciplinaryTileToLocationManager(goToDisciplinaryTile);

				System.out.println(LocationManager.getInstance().toString());
				System.out.println(CardManager.getInstance().toString());		
	
}

/**
 * Parse the contents of cards nodeList into Cards and add them to CardManager
	 * @param cards
 * @return
 */
public void addCardsToCardManager(NodeList cards){
	Card theCard = null;
	int id = 0;
	String cardName = "";
	String cardDescription = "";
	boolean storable = false;

	for (int i = 0; i < cards.getLength(); i++){
		Node aCardNode = cards.item(i);
		if (aCardNode.getNodeType() == Node.ELEMENT_NODE){
			Element aCardElement = (Element) aCardNode;

			id = Integer.parseInt(aCardElement.getAttribute("id"));
			cardName = aCardElement.getAttribute("name");
			cardDescription = aCardElement.getAttribute("description");
			storable = Boolean.parseBoolean(aCardElement.getAttribute("storable"));

			theCard = new Card(id, cardName, cardDescription, storable);
			CardManager.getInstance().addCard(theCard);
		}
	}
}


/**
 * Parse the contents of properties nodeList into Properties and add them to LocationManager
 * @param properties
 */
public void addPropertiesToLocationManager(NodeList properties){
	// Variables
	Property theProperty = null;
	int id = 0;
	int x = 0, y = 0;
	int price = 0, upgradeCost = 0, mortgage = 0, breakMortgage = 0;
	String name = "";
	BuyableLocation.GroupColor groupColor = null;
	ArrayList<Integer> rentValues = new ArrayList<Integer>(0);

	// Processing
	for (int i = 0; i < properties.getLength(); i++){
		Node aPropertyNode = properties.item(i);
		if (aPropertyNode.getNodeType() == Node.ELEMENT_NODE){
			Element aPropertyElement = (Element) aPropertyNode;

			id = Integer.parseInt(aPropertyElement.getAttribute("id"));
			x = Integer.parseInt(aPropertyElement.getAttribute("x"));
			y = Integer.parseInt(aPropertyElement.getAttribute("y"));
			price = Integer.parseInt(aPropertyElement.getAttribute("price"));
			upgradeCost = Integer.parseInt(aPropertyElement.getAttribute("upgradeCost"));
			mortgage = Integer.parseInt(aPropertyElement.getAttribute("mortgage"));
			breakMortgage = Integer.parseInt(aPropertyElement.getAttribute("breakMortgage"));

			name = aPropertyElement.getAttribute("name");
			groupColor = BuyableLocation.parseGroupColor(aPropertyElement.getAttribute("groupColor"));

			String[] parts = aPropertyElement.getAttribute("rentValues").split(",");
			ArrayList<Integer> rents = new ArrayList<Integer>();
			for (int j = 0; j < parts.length; j++) {
				rents.add(Integer.parseInt(parts[j]));
			}


			theProperty = new Property(id, name, new Point2D.Double(x, y), new ArrayList<Player>(0), groupColor,
					null, price, rents.get(0), mortgage, breakMortgage, false, rents, 0, false);
			LocationManager.getInstance().addBuyable(theProperty);
		}
	}
}

public void addChanceLocationsToLocsManager(NodeList chanceLocs){
	// Variables
	ChanceTile theChanceLoc = null;
	int id = 0;
	int x = 0, y = 0;
	String name = "";

	// Processing
	for (int i = 0; i < chanceLocs.getLength(); i++){
		Node aChanceNode = chanceLocs.item(i);
		if (aChanceNode.getNodeType() == Node.ELEMENT_NODE){
			Element aChanceElement = (Element) aChanceNode;

			id = Integer.parseInt(aChanceElement.getAttribute("id"));
			x = Integer.parseInt(aChanceElement.getAttribute("x"));
			y = Integer.parseInt(aChanceElement.getAttribute("y"));
			name = aChanceElement.getAttribute("name");

			theChanceLoc = new ChanceTile(id, name, new Point2D.Double(x, y), new ArrayList<Player>(0));
			LocationManager.getInstance().addNonBuyable(theChanceLoc);
		}
	}
}

public void addIncomeTaxLocsToLocationManager(NodeList incomeTaxLocs){
	// Variables
	IncomeTaxTile theIncomeTax = null;
	int id = 0;
	int x = 0, y = 0;
	int taxValue = 0;
	String name = "";

	// Processing
	for (int i = 0; i < incomeTaxLocs.getLength(); i++){
		Node anIncomeTaxNode = incomeTaxLocs.item(i);
		if (anIncomeTaxNode.getNodeType() == Node.ELEMENT_NODE){
			Element anIncomeTaxElement = (Element) anIncomeTaxNode;

			id = Integer.parseInt(anIncomeTaxElement.getAttribute("id"));
			x = Integer.parseInt(anIncomeTaxElement.getAttribute("x"));
			y = Integer.parseInt(anIncomeTaxElement.getAttribute("y"));
			taxValue = Integer.parseInt(anIncomeTaxElement.getAttribute("tax"));
			name = anIncomeTaxElement.getAttribute("name");

			theIncomeTax = new IncomeTaxTile(id, name, new Point2D.Double(x, y), new ArrayList<Player>(0), taxValue);
			LocationManager.getInstance().addNonBuyable(theIncomeTax);
		}
	}
}

public void addBusLocsToLocationsManager(NodeList busLines){
	// Variables
	BusStop theBus = null;
	int id = 0;
	int x = 0, y = 0;
	int price = 0, mortgage = 0, breakMortgage = 0;
	String name = "";
	BuyableLocation.GroupColor groupColor = null;
	ArrayList<Integer> rentValues = new ArrayList<Integer>(0);

	// Processing
	for (int i = 0; i < busLines.getLength(); i++){
		Node aBusStopNode = busLines.item(i);
		if (aBusStopNode.getNodeType() == Node.ELEMENT_NODE){
			Element aBusStopElement = (Element) aBusStopNode;

			id = Integer.parseInt(aBusStopElement.getAttribute("id"));
			x = Integer.parseInt(aBusStopElement.getAttribute("x"));
			y = Integer.parseInt(aBusStopElement.getAttribute("y"));
			price = Integer.parseInt(aBusStopElement.getAttribute("price"));
			mortgage = Integer.parseInt(aBusStopElement.getAttribute("mortgage"));
			breakMortgage = Integer.parseInt(aBusStopElement.getAttribute("breakMortgage"));

			name = aBusStopElement.getAttribute("name");
			groupColor = BuyableLocation.parseGroupColor(aBusStopElement.getAttribute("groupColor"));

			String[] parts = aBusStopElement.getAttribute("rentValues").split(",");
			ArrayList<Integer> rents = new ArrayList<Integer>();
			for (int j = 0; j < parts.length; j++) {
				rents.add(Integer.parseInt(parts[j]));
			}

			theBus = new BusStop(id, name, new Point2D.Double(x, y), new ArrayList<Player>(0), groupColor, null,
					price, rents.get(0), mortgage, breakMortgage, false, rents);
			LocationManager.getInstance().addBuyable(theBus);
		}
	}
}

public void addUtilitiesToLocationManager(NodeList utilities){
	// Variables
	Utility theUtility = null;
	int id = 0;
	int x = 0, y = 0;
	int price = 0, mortgage = 0, breakMortgage = 0;
	String name = "";
	BuyableLocation.GroupColor groupColor = null;
	ArrayList<Integer> rentValues = new ArrayList<Integer>(0);

	// Processing
	for (int i = 0; i < utilities.getLength(); i++){
		Node aUtilityNode = utilities.item(i);
		if (aUtilityNode.getNodeType() == Node.ELEMENT_NODE){
			Element aUtilityElement = (Element) aUtilityNode;

			id = Integer.parseInt(aUtilityElement.getAttribute("id"));
			x = Integer.parseInt(aUtilityElement.getAttribute("x"));
			y = Integer.parseInt(aUtilityElement.getAttribute("y"));
			price = Integer.parseInt(aUtilityElement.getAttribute("price"));
			mortgage = Integer.parseInt(aUtilityElement.getAttribute("mortgage"));
			breakMortgage = Integer.parseInt(aUtilityElement.getAttribute("breakMortgage"));

			name = aUtilityElement.getAttribute("name");
			groupColor = BuyableLocation.parseGroupColor(aUtilityElement.getAttribute("groupColor"));

			String[] parts = aUtilityElement.getAttribute("rentValues").split(",");
			ArrayList<Integer> rents = new ArrayList<Integer>();
			for (int j = 0; j < parts.length; j++) {
				rents.add(Integer.parseInt(parts[j]));
			}

			theUtility = new Utility(id, name, new Point2D.Double(x,y), new ArrayList<Player>(0),
					groupColor, null, price, rents.get(0), mortgage, breakMortgage, false, rents);
			LocationManager.getInstance().addBuyable(theUtility);
		}
	}
}

public void addStartTileToLocationManager(Node startTileNode){
	// Variables
	StartTile theStartTile = null;
	int id = 0;
	int x = 0, y = 0;
	int prizeValue = 0;
	String name = "";

	// Processing
	if (startTileNode.getNodeType() == Node.ELEMENT_NODE){
		Element startTileElement = (Element) startTileNode;

		id = Integer.parseInt(startTileElement.getAttribute("id"));
		x = Integer.parseInt(startTileElement.getAttribute("x"));
		y = Integer.parseInt(startTileElement.getAttribute("y"));
		prizeValue = Integer.parseInt(startTileElement.getAttribute("prizeValue"));

		name = startTileElement.getAttribute("name");

		theStartTile = new StartTile(id, name, new Point2D.Double(x,y), new ArrayList<Player>(0), prizeValue);
		LocationManager.getInstance().addNonBuyable(theStartTile);
	}
}

public void addMayfestTileToLocationManager(Node mayfestNode){
	// Variables
	MayfestTile mayfestTile = null;
	int id = 0;
	int x = 0, y = 0;
	String name = "";

	// Processing
	if (mayfestNode.getNodeType() == Node.ELEMENT_NODE){
		Element mayfestElement = (Element) mayfestNode;

		id = Integer.parseInt(mayfestElement.getAttribute("id"));
		x = Integer.parseInt(mayfestElement.getAttribute("x"));
		y = Integer.parseInt(mayfestElement.getAttribute("y"));
		name = mayfestElement.getAttribute("name");

		mayfestTile = new MayfestTile(id, name, new Point2D.Double(x,y), new ArrayList<Player>(0), 0);
		LocationManager.getInstance().addNonBuyable(mayfestTile);
	}
}

public void addDisciplinaryTileToLocationManager(Node jailNode){
	// Variables
	Disciplinary jailTile = null;
	int id = 0;
	int x = 0, y = 0;
	String name = "";

	// Processing
	if (jailNode.getNodeType() == Node.ELEMENT_NODE){
		Element jailElement = (Element) jailNode;

		id = Integer.parseInt(jailElement.getAttribute("id"));
		x = Integer.parseInt(jailElement.getAttribute("x"));
		y = Integer.parseInt(jailElement.getAttribute("y"));
		name = jailElement.getAttribute("name");

		jailTile = new Disciplinary(id, name, new Point2D.Double(x,y), new ArrayList<Player>(0), new ArrayList<Player>(0));
		LocationManager.getInstance().addNonBuyable(jailTile);
	}
}

public void addGoToDisciplinaryTileToLocationManager(Node disciplinaryNode){
	// Variables
	GoToDisciplinaryTile thedisciplinary = null;
	int id = 0;
	int x = 0, y = 0;
	String name = "";

	// Processing
	if (disciplinaryNode.getNodeType() == Node.ELEMENT_NODE){
		Element disciplinaryElement = (Element) disciplinaryNode;

		id = Integer.parseInt(disciplinaryElement.getAttribute("id"));
		x = Integer.parseInt(disciplinaryElement.getAttribute("x"));
		y = Integer.parseInt(disciplinaryElement.getAttribute("y"));
		name = disciplinaryElement.getAttribute("name");

		thedisciplinary = new GoToDisciplinaryTile(id, name, new Point2D.Double(x,y), new ArrayList<Player>(0));
		LocationManager.getInstance().addNonBuyable(thedisciplinary);
	}
}

private String getTagValue(String tag, Element element) {
	NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
	Node node = (Node) nodeList.item(0);
	return node.getNodeValue();
	}
}