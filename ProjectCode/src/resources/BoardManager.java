package resources;

import javax.swing.JPanel;

public class BoardManager extends JPanel {
	private Map map;
	private InteractionArea interactionArea;
	
	public BoardManager() {
		setBounds(0, 0, 1500, 900);
		setLayout(null);
		map = new Map();
		add(map);
		interactionArea = new InteractionArea();
		add(interactionArea);
	}
	
	public void updateMap(PlayerManager playerMngr) {
		
	}
	
	public void updateInteractionArea(PlayerManager playerMngr) {
		
	}
	
	public void updateDiceResults(Dice dice) {
		
	}
	
	private void pauseGame() {  //should be public I think -G
		
	}
}
