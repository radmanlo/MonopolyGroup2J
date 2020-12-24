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
		inventoryBtn.setBackground(new Color(60, 60, 60, 255));
		inventoryBtn.setForeground(Color.WHITE);
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

		if (backgroundColor == PlayerColor.RED || backgroundColor == PlayerColor.BLACK)
			playerLbl.setForeground(new Color(245, 245, 245, 255));
		else
			playerLbl.setForeground(new Color(36, 36, 36, 255));

		namePanel.setBackground(getUsableColor(backgroundColor));

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
			return new Color(36, 36, 36, 255);
		case BLUE:
			return new Color(126, 181, 243, 255);
		case GREEN:
			return new Color(79, 180, 136, 255);
		case ORANGE:
			return new Color(198, 104, 104, 255);
		case PURPLE:
			return new Color(202, 118, 223, 255);
		case RED:
			return new Color(172, 0, 0, 255);
		case WHITE:
			return new Color(245, 245, 245, 255);
		case YELLOW:
			return new Color(180, 173, 79, 255);
		default:
			return null;
		}
	}
}