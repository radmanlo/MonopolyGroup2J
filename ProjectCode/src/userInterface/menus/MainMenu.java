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
		// Get device resolution
		setBounds(0, 0, 1500, 900);
		remove(backBtn);

		RoundedButton newGameButton = new RoundedButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		newGameButton.setBounds(600, 50, 300, 100);
		add(newGameButton);

		loadGameBtn = new RoundedButton("Load Game");
		loadGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadGame();
			}
		});
		loadGameBtn.setBounds(600, 160, 300, 100);
		add(loadGameBtn);
		
		RoundedButton howToPlayBtn = new RoundedButton("How To Play");
		howToPlayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				howToPlay();
			}
		});
		howToPlayBtn.setBounds(600, 270, 300, 100);
		add(howToPlayBtn);
		
		RoundedButton settingsBtn = new RoundedButton("Settings");
		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		});
		settingsBtn.setBounds(600, 380, 300, 100);
		add(settingsBtn);
		
		RoundedButton creditsBtn = new RoundedButton("Credits");
		creditsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credits();
			}
		});
		creditsBtn.setBounds(600, 490, 300, 100);
		add(creditsBtn);
		
		RoundedButton quitBtn = new RoundedButton("Quit");
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackPanel();
			}
		});
		quitBtn.setBounds(600, 600, 300, 100);
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