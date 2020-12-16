import javax.swing.JButton;
import javax.swing.JTextField;

public class PauseMenu implements Menu{
	private JButton saveBtn; 
	private JButton goSettingsBtn; 
	private JButton quitBtn; 
	private JTextField saveNameTxtField; 
	
	public PauseMenu() {
		
	}
	
	@Override
	public void goBackPanel() {
		
	}
	
	private boolean saveGame( String savedName ) {
		return false;
	}
	
	private void quitGame() {
		
	}
	
	private void goSettings() {
		
	}
}
