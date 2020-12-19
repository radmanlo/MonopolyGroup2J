package userInterface.menus;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class HowToPlayMenu extends Menu {

	private String instructions;
	private JTextArea instructionDisplayer;
	
	public HowToPlayMenu() {
		super("./resources/HowToPlayMenu.jpg");
	}

}
