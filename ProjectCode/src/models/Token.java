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
	
	/*
	 * Constructor with given img path
	 */
	public Token(String path) {
		this.path = path;
	}

	/*
	 * Constructor with given token
	 */
	public Token(Token token) {
		// TODO Auto-generated constructor stub
		this.path = token.path;
	}

	//Changing it and giving image as attribute of token crashes serialization please dont make changes.
	public Image getImage() {
		Image image = image = Utils.scaleImage(90, 65, path);
		return image;
	}
	
	/*
	 * Returns path of image
	 */
	public String getPath() {
		return path;
	}
}
