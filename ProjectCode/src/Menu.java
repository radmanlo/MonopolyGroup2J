import javax.swing.JButton;
import javax.swing.JPanel;

abstract class Menu {
	public JPanel panel;
	JButton backButton;
	
	public abstract void goBackPanel(); //was private in class diagram but
	//does not work when private -G
}
