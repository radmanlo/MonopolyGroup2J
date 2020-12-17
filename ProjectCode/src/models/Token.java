package models;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Token {
	private Image image;
	
	public Token(String path) {
		image = new ImageIcon(path).getImage();
	}
	
	public Image getImage() {
		return image;
	}
}
