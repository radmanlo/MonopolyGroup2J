import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MenuManager {
	private static MenuManager menuManager = null; 
	
	public AppFrame appFrame;
	private SettingsMenu settingsMenu;
	private LoadGameMenu loadGameMenu;
	private NewGameMenu newGameMenu;
	private CreditsMenu creditsMenu;
	private HowToPlayMenu howToPlayMenu;
	private JButton quitBtn; 
	private MainMenu mainMenu;
	
	
	public static void main(String[] args) {  
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
		
		mainMenu.setBounds(0, 0, 1500, 900);
		appFrame.contentPane.add(mainMenu);
		
		
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
			appFrame.contentPane.remove(mainMenu);
			BoardManager board = GameManager.getInstance().getBoardMngr();
			appFrame.contentPane.add(board);
			break;
			
		}
		appFrame.contentPane.revalidate();
		appFrame.contentPane.repaint();
	}
	
	public void quitGame() {  
		
	}
}
