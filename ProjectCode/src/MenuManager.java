import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MenuManager {
	private static MenuManager menuManager = null; //made singleton -G
	
	public AppFrame appFrame;
	private SettingsMenu settingsMenu;
	private LoadGameMenu loadGameMenu;
	private NewGameMenu newGameMenu;
	private CreditsMenu creditsMenu;
	private HowToPlayMenu howToPlayMenu;
	private JButton quitBtn; //changed name for consistency -G
	private MainMenu mainMenu;
	
	
	public static void main(String[] args) {  //added main -G
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
	
	
	private MenuManager() {  //made singleton -G
		appFrame = new AppFrame();
		appFrame.setVisible(true);
		
		mainMenu = new MainMenu();
		
		mainMenu.setBounds(0, 0, 1500, 900);
		appFrame.contentPane.add(mainMenu);
		
		
	}
	
	public static MenuManager getInstance() {  //made singleton -G
		if( menuManager == null ) {
			menuManager = new MenuManager();
		}
		return menuManager;
	}
	
	public void openMenu( int indexOfMenu) {  //made public -G
		switch ( indexOfMenu ) {
		case 0: 
			appFrame.contentPane.remove(mainMenu);
			BoardManager board = GameManager.getInstance().getBoardMngr();
			appFrame.contentPane.add(board);
			break;
			
		}
		appFrame.contentPane.revalidate();
		appFrame.contentPane.repaint();
	}
	
	public void quitGame() {   //made public -G
		
	}
}
