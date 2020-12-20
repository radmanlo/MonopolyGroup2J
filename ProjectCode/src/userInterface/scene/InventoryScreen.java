package userInterface.scene;
import javax.swing.JPanel;

import models.Player;

import javax.swing.JLabel;
import java.awt.Font;

public class InventoryScreen extends JPanel{

	public InventoryScreen(Player player) {
		setLayout(null);
		setBounds(300,300, 300, 300);

		JLabel Hello = new JLabel("Hello");
		Hello.setFont(new Font("Tahoma", Font.PLAIN, 54));
		Hello.setBounds(256, 113, 251, 212);
		add(Hello);
	}
}
