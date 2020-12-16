import javax.swing.JButton;
import javax.swing.JTable;

public class LoadGameMenu implements Menu{
	
	private JTable gamesList;
	private JButton initializeGameBtn;
	private JButton deleteGameBtn;
	
	public LoadGameMenu() {
		
	}
	
	@Override
	public void goBackPanel() {
		
	}
	
	private boolean initializeLoadGame() {
		return false;
	}
	
	private boolean deleteGame() {
		return false;
	}
}
