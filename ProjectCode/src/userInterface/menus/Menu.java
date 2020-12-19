package userInterface.menus;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import models.Token;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

abstract class Menu extends JPanel{ //instead of it having a panel attribute, I made it directly extend JPanel
	//to make working with windowBuilder easier
	
	public JButton backBtn;
    private BufferedImage img;
    private String backgroundImagePath;
    
    
	public Menu(String path) {
		backgroundImagePath = path;
		setLayout(null);
        try {

            img = ImageIO.read(new File(backgroundImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
		backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBackPanel();
			}
		});
		backBtn.setBounds(10, 10, 89, 34);
		add(backBtn);
	}

	  // use g2d.drawImage methods to paint your background …
	   @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Get screen resolution
		   Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		   // Paint the image with max height and width
		   g.drawImage(img, 0, 0, (int) size.getWidth(), (int) size.getHeight(), this);
	    }
	public void goBackPanel() {
		MenuManager.getInstance().openMenu(5);//was private in class diagram but does not work when private -G
	}
}
