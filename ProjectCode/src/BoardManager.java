import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class BoardManager extends JPanel { //changed to JPanel -G
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
