package models;

import utilities.Utils;

import java.awt.*;

public class Token {
	private Image image;
	
	public Token(String path) {
		image = Utils.scaleImage(90, 65, path);
	}

	public Image getImage() {
		return image;
	}

}
