import javax.swing.JButton;
import javax.swing.JPanel;

interface Menu { // should be abstract class -G -new
	JPanel panel = new JPanel();
	JButton backButton = new JButton();
	
	public void goBackPanel(); //diagram needs to change, private isn't
	//allowed in interfaces, we have them in diagram -G
}
