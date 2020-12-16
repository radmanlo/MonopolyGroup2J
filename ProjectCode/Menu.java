import javax.swing.JButton;
import javax.swing.JPanel;

interface Menu {
	JPanel panel = new JPanel();
	JButton backButton = new JButton();
	
	public void goBackPanel();
}
