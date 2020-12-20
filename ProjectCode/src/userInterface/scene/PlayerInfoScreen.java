package userInterface.scene;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Player;
import models.PlayerColor;

public class PlayerInfoScreen extends JPanel{
	JLabel playerLbl;
	JButton inventoryBtn;
	PlayerColor backgroundColor;
	JPanel namePanel;

	public PlayerInfoScreen(Player player){
		setLayout(null);
		setBounds(0, 0, 320, 60);
		
		inventoryBtn = new JButton("view inventory");
		inventoryBtn.setBounds(179, 19, 128, 23);
		add(inventoryBtn);
		
		backgroundColor = player.getColorId();
		setBackground(getUsableColor(backgroundColor));
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(21, 11, 148, 36);
		add(namePanel);
		System.out.println(player.getName());
		playerLbl = new JLabel();
		playerLbl.setText(player.getName());
		namePanel.add(playerLbl);
		playerLbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
		namePanel.setBackground(Color.WHITE);
		repaint();
	}

	private Color getUsableColor(PlayerColor playerColor) {
		Color PURPLE = new Color(102,0,153);
		switch(playerColor) {
		case BLACK:
			return java.awt.Color.BLACK;
		case BLUE:
			return java.awt.Color.BLUE;
		case GREEN:
			return java.awt.Color.GREEN;
		case ORANGE:
			return java.awt.Color.ORANGE;
		case PURPLE:
			return PURPLE;
		case RED:
			return java.awt.Color.RED;
		case WHITE:
			return java.awt.Color.WHITE;
		case YELLOW:
			return java.awt.Color.YELLOW;
		default:
			return null;
		}
	}
}