package models;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Token implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1858717624870311197L;
	String path;
	
	public Token(String path) {
		this.path = path;
	}

	public Image getImage() {
		Image image = new ImageIcon(path).getImage();
		return image;
	}
}
