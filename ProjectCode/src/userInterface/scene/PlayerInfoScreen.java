package userInterface.scene;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gamePresenter.BoardManager;
import models.Player;
import models.PlayerColor;
import userInterface.scene.Map.ImageToDraw;
import utilities.Utils;

import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;

public class PlayerInfoScreen extends JPanel{
	JLabel playerLbl;
	JButton inventoryBtn;
	PlayerColor backgroundColor;
	JPanel namePanel;
	Image tokenImg;

	public PlayerInfoScreen(Player player){
		setLayout(null);
		setBounds(0, 0, 400, 60);
		
		inventoryBtn = new JButton("view inventory");
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardManager.getInstance().openInventoryScreen(player);
			}
		});
		inventoryBtn.setBounds(179, 19, 128, 23);
		add(inventoryBtn);
		
		backgroundColor = player.getColorId();
		setBackground(getUsableColor(backgroundColor));
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(21, 11, 148, 36);
		add(namePanel);
		playerLbl = new JLabel();
		playerLbl.setText(player.getName());
		namePanel.add(playerLbl);
		playerLbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
		namePanel.setBackground(Color.WHITE);
		
		String imgPath = player.getToken().getPath();
		tokenImg = Utils.scaleImage(40,40,imgPath);
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(getUsableColor(backgroundColor));
		g.fillRect(0, 0, 400, 60);
		g.drawImage(tokenImg, 350, 10, this);
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