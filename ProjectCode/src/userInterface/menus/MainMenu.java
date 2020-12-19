package userInterface.menus;
import userInterface.components.RoundedButton;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends Menu {

	private RoundedButton loadGameBtn;
	private RoundedButton newGameBtn;
	private RoundedButton creditsBtn;
	private RoundedButton quitBtn;
	private RoundedButton settingsBtn;
	private RoundedButton howToPlayBtn;
	
	public MainMenu() {
		super("./resources/MainBG1.png");
		
		setLayout(null);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
		// Get device resolution
		remove(backBtn);

		newGameBtn = new RoundedButton("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		newGameBtn.setBounds(150, 100, 500, 70);
		add(newGameBtn);

		loadGameBtn = new RoundedButton("Load Game");
		loadGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadGame();
			}
		});
		loadGameBtn.setBounds(150, 190, 500, 70);
		add(loadGameBtn);
		
		howToPlayBtn = new RoundedButton("How To Play");
		howToPlayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				howToPlay();
			}
		});
		howToPlayBtn.setBounds(150, 280, 500, 70);
		add(howToPlayBtn);
		
		settingsBtn = new RoundedButton("Settings");
		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		});
		settingsBtn.setBounds(150, 370, 500, 70);
		add(settingsBtn);
		
		creditsBtn = new RoundedButton("Credits");
		creditsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credits();
			}
		});
		creditsBtn.setBounds(150, 460, 500, 70);
		add(creditsBtn);
		
		quitBtn = new RoundedButton("Quit");
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackPanel();
			}
		});
		quitBtn.setBounds(150, 550, 500, 70);
		add(quitBtn);
	}
	
	@Override
	public void goBackPanel() {
		MenuManager.getInstance().quitGame();
	}
	
	private void newGame() {
		MenuManager.getInstance().openMenu(0);
	}
	
	private void loadGame() {
		MenuManager.getInstance().openMenu(1);
	}
	
	private void howToPlay() {
		MenuManager.getInstance().openMenu(2);
	}
	
	private void settings() {
		MenuManager.getInstance().openMenu(3);
	}
	
	private void credits() {
		MenuManager.getInstance().openMenu(4);
	}
}