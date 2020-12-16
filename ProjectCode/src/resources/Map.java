package bilopoly;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map extends JPanel{ 
	Image mapImage;
	
	public Map() {
		mapImage = new ImageIcon("./src/resources/Board.jpg").getImage();
		setBounds(50, 50, 700, 800);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		
		g.drawImage(mapImage, 0, 0, null); 
	}

}
