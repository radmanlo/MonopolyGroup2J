package models;

import utilities.Utils;
import java.awt.Image;
import java.io.Serializable;

import java.awt.*;

public class Token implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1858717624870311197L;
	String path;
	private Image image;
	
	public Token(String path) {
		image = Utils.scaleImage(90, 65, path);
		this.path = path;
	}

	public Image getImage() {
		return image;
	}

}
