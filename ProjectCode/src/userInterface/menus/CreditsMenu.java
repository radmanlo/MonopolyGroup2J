package userInterface.menus;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class CreditsMenu extends Menu{
	private String credits;
	private JTextArea creditDisplayer;
	
	public CreditsMenu() {
		super("./resources/credits.png");
	}
}
