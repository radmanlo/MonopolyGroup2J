import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class LoadGameMenu extends Menu{
	
	private JTable gamesList;
	private JButton initializeGameBtn;
	private JButton deleteGameBtn;
	
	public LoadGameMenu() {
		panel = new JPanel();
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
