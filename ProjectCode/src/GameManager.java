public class GameManager {   //made singleton -G
	private static GameManager gameManager = null;//made singleton -G
	
	private PlayerManager playerMngr;
	private BoardManager boardMngr;
	private Dice dice;
	private SoundManager soundMngr;
	private CardDeck cardDeck;
	private InventoryManager inventoryMngr;

	private GameManager() {
		boardMngr = new BoardManager();
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
	
	public static InventoryManager getInventoryManager() {
		return null;
	}
	
	public void handleNewTurn() {
		
	}
	
	private boolean payRent() {
		return false;
	}
	
	private void getAllTaxesFromMayfest() {
		
	}

	public BoardManager getBoardMngr() { //added -G
		return boardMngr;
	}
	
	
}

