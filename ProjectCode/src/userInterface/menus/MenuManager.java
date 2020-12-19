package userInterface.menus;


import javax.swing.*;

import gamePresenter.BoardManager;
import gamePresenter.SoundManager;

public class MenuManager { //it is finished -G
	private static MenuManager menuManager = null;
	
	private AppFrame appFrame;
	private SettingsMenu settingsMenu;
	private LoadGameMenu loadGameMenu;
	private NewGameMenu newGameMenu;
	private CreditsMenu creditsMenu;
	private MainMenu mainMenu;
	private HowToPlayMenu howToPlayMenu;
	private JButton quitBtn;
	
	
	private MenuManager() {
		appFrame = new AppFrame();
		appFrame.setVisible(true);
		//appFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainMenu = new MainMenu();
		mainMenu.setBounds(0, 0, 1900, 1000);
		appFrame.contentPane.add(mainMenu);
		
		newGameMenu = new NewGameMenu();
		newGameMenu.setBounds(0, 0, 1900, 1000);
		
		loadGameMenu = new LoadGameMenu();
		loadGameMenu.setBounds(0, 0, 1900, 1000);
		
		howToPlayMenu = new HowToPlayMenu();
		howToPlayMenu.setBounds(0, 0, 1900, 1000);
		
		settingsMenu = new SettingsMenu();
		settingsMenu.setBounds(0, 0, 1900, 1000);
		
		creditsMenu = new CreditsMenu();
		creditsMenu.setBounds(0, 0, 1900, 1000);

		SoundManager soundManager = SoundManager.getInstance();
		soundManager.playBackgroundSound();
		soundManager.setVolumeLevel(10);
//		System.out.println(soundManager.setVolumeLevel(100));
	}
	
	public static MenuManager getInstance() {
		if( menuManager == null ) {
			menuManager = new MenuManager();
		}
		return menuManager;
	}
	
	public void openMenu( int indexOfMenu) {  //TODO should convert the indexes to enum -G
		switch ( indexOfMenu ) {
		case 0: 
			appFrame.contentPane.remove(mainMenu);
			appFrame.contentPane.add(newGameMenu);
			break;
		case 1:
			appFrame.contentPane.remove(mainMenu);
			BoardManager board = BoardManager.getInstance();
			appFrame.contentPane.add(board);
			//appFrame.contentPane.add(loadGameMenu); //temporary change to test Board
			break;
		case 2:
			appFrame.contentPane.remove(mainMenu);
			appFrame.contentPane.add(howToPlayMenu);
			break;
		case 3:
			appFrame.contentPane.remove(mainMenu);
			appFrame.contentPane.add(settingsMenu);
			break;
		case 4:
			appFrame.contentPane.remove(mainMenu);
			appFrame.contentPane.add(creditsMenu);
			break;
		case 5: 
			appFrame.contentPane.remove(newGameMenu);
			appFrame.contentPane.remove(loadGameMenu);
			appFrame.contentPane.remove(howToPlayMenu);
			appFrame.contentPane.remove(settingsMenu);
			appFrame.contentPane.remove(creditsMenu);
			appFrame.contentPane.add(mainMenu);
			break;
		default:
			System.out.println("An error occurred on openMenu(int)");
		}
		appFrame.contentPane.revalidate();
		appFrame.contentPane.repaint();
	}
	
	public void quitGame() {
		System.exit(0);
	}
}
