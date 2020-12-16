import javax.swing.JButton;
import javax.swing.JTextField;

public class PauseMenu implements Menu{
	private JButton saveBtn; //changed name for consistency -G
	private JButton goSettingsBtn; //changed name for consistency -G
	private JButton quitBtn; //changed name for consistency -G
	private JTextField saveNameTxtField; //changed name for consistency -G
	
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
