package userInterface.scene;
import javax.swing.JPanel;

import models.Player;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;

public class InventoryScreen extends JPanel{

	public InventoryScreen(Player player) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(1100,210, 600, 600);
		setBackground(Color.WHITE);
		
		JLabel playerNameLbl = new JLabel( player.getName() + "'s Inventory");
		playerNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		playerNameLbl.setBounds(129, 22, 350, 53);
		add(playerNameLbl);
		System.out.println("Hello!");
		revalidate();
		repaint();
		//setVisible(true);
	}
}
