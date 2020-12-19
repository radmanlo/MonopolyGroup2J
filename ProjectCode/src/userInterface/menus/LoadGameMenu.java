package userInterface.menus;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;

public class LoadGameMenu extends Menu{
	
	private JTable gamesList;
	private JButton initializeGameBtn;
	private JButton deleteGameBtn;
	
	public LoadGameMenu() {
		super("./resources/LoadMenu.jpg");
	}
	
	private boolean initializeLoadGame() {
		return false;
	}
	
	private boolean deleteGame() {
		return false;
	}
}
