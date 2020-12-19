package userInterface.scene;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map extends JPanel{ 
	Image mapImage;
	
	public Map() {
		mapImage = new ImageIcon("./resources/Board.jpg").getImage();
		setBounds(950, 25, 900, 900);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(mapImage, 0, 0, null); 
		
		g.drawRect(120, 0, 73, 120);
		
		
	}

}
