package userInterface.menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AppFrame extends JFrame {

	public JPanel contentPane;

	
	public AppFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//		setBounds(0, 0, 1900, 1000);
		setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
		// Start the JFrame with full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
