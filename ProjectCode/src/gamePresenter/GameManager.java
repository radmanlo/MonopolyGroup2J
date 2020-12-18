package gamePresenter;

import models.Dice;
import models.Player;
import models.Property;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GameManager {
	private static GameManager gameManager = null;
	
	private PlayerManager playerMngr = PlayerManager.getInstance();
	private BoardManager boardMngr;
	private Dice dice = new Dice();
	private SoundManager soundMngr = SoundManager.getInstance();
	private CardDeck cardDeck = new CardDeck();
	private LocationManager locationManager = LocationManager.getInstance();

	private GameManager() {
		boardMngr = new BoardManager();
	}

	public void initDefaultGame(Document doc){
		// Parse the information from the default game document
		NodeList bilopoly = doc.getElementsByTagName("Bilopoly");
		NodeList cards = doc.getElementsByTagName("card");
		getCards(cards);
	}

	public void getCards(NodeList cards){
		for (int i = 0; i < cards.getLength(); i++){
			Node aCard = cards.item(i);
			if (aCard.getNodeType() == Node.ELEMENT_NODE){
				Element aCardElement = (Element) aCard;

				System.out.println("Card Name: " + aCardElement.getAttribute("name"));
				System.out.println("Card description: " + aCardElement.getAttribute("description"));
				System.out.println("Card storable: " + aCardElement.getAttribute("storable"));
			}
		}
	}

	public void getLocations(NodeList locations){

	}

	private String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
	
	public static GameManager getInstance() {
		if( gameManager == null ) {
			gameManager = new GameManager();
		}
		return gameManager;
	}
	
	public static void executePurchase() {
		
	}
	
	public static void rollDice() {
		
	}
	
	public static void tradeRequest(Property property, int value) {
		
	}
	
	public static boolean upgradeProperty(Property property) {
		return false;
	}
	
	public static LocationManager getInventoryManager() {
		return null;
	}
	
	public void handleNewTurn() {
		
	}
	
	private boolean payRent() {
		return false;
	}
	
	private void getAllTaxesFromMayfest() {
		
	}

	public BoardManager getBoardMngr() {
		return boardMngr;
	}
	
	
}

