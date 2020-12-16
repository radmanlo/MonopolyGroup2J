import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MenuManager { //should be finished -G
	private static MenuManager menuManager = null;
	
	private AppFrame appFrame;
	private SettingsMenu settingsMenu;
	private LoadGameMenu loadGameMenu;
	private NewGameMenu newGameMenu;
	private CreditsMenu creditsMenu;
	private MainMenu mainMenu;
	private HowToPlayMenu howToPlayMenu;
	private JButton quitBtn;
	
	public static void main(String[] args) { //probably needs its own file
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuManager.getInstance();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private MenuManager() {
		appFrame = new AppFrame();
		appFrame.setVisible(true);
		
		mainMenu = new MainMenu();
		mainMenu.panel.setBounds(0, 0, 1500, 900);
		appFrame.contentPane.add(mainMenu.panel);
		
		newGameMenu = new NewGameMenu();
		newGameMenu.panel.setBounds(0, 0, 1500, 900);
		
		loadGameMenu = new LoadGameMenu();
		loadGameMenu.panel.setBounds(0, 0, 1500, 900);
		
		howToPlayMenu = new HowToPlayMenu();
		howToPlayMenu.panel.setBounds(0, 0, 1500, 900);
		
		settingsMenu = new SettingsMenu();
		settingsMenu.panel.setBounds(0, 0, 1500, 900);
		
		creditsMenu = new CreditsMenu();
		creditsMenu.panel.setBounds(0, 0, 1500, 900);
	}
	
	public static MenuManager getInstance() {
		if( menuManager == null ) {
			menuManager = new MenuManager();
		}
		return menuManager;
	}
	
	public void openMenu( int indexOfMenu) {
		switch ( indexOfMenu ) {
		case 0: 
			appFrame.contentPane.remove(mainMenu.panel);
			appFrame.contentPane.add(newGameMenu.panel);
			break;
		case 1:
			appFrame.contentPane.remove(mainMenu.panel);
			appFrame.contentPane.add(loadGameMenu.panel);
			break;
		case 2:
			appFrame.contentPane.remove(mainMenu.panel);
			appFrame.contentPane.add(howToPlayMenu.panel);
			break;
		case 3:
			appFrame.contentPane.remove(mainMenu.panel);
			appFrame.contentPane.add(settingsMenu.panel);
			break;
		case 4:
			appFrame.contentPane.remove(mainMenu.panel);
			appFrame.contentPane.add(creditsMenu.panel);
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
