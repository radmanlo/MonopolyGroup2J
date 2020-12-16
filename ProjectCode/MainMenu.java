import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainMenu extends JPanel implements Menu {

	private JButton loadGameBtn;
	private JButton newGameBtn;
	private JButton creditsBtn;
	private JButton quitBtn;  	
	private JButton settingsBtn;
	private JButton howToPlayBtn;
	
	public MainMenu() {
		setLayout(null);
		setBounds(10, 11, 1460, 839);
		newGameBtn = new JButton("New Game");
		
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();	
			}
		});
		
		newGameBtn.setFont(new Font("Tahoma", Font.PLAIN, 33));
		newGameBtn.setBounds(600, 50, 300, 100);
		add(newGameBtn);
		loadGameBtn = new JButton("Load Game");
		loadGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadGame();
			}
		});
		loadGameBtn.setFont(new Font("Tahoma", Font.PLAIN, 33));
		loadGameBtn.setBounds(600, 160, 300, 100);
		add(loadGameBtn);
		
		JButton howToPlayBtn = new JButton("How To Play");
		howToPlayBtn.setFont(new Font("Tahoma", Font.PLAIN, 33));
		howToPlayBtn.setBounds(600, 270, 300, 100);
		add(howToPlayBtn);
		
		JButton settingsBtn = new JButton("Settings");
		settingsBtn.setFont(new Font("Tahoma", Font.PLAIN, 33));
		settingsBtn.setBounds(600, 380, 300, 100);
		add(settingsBtn);
		
		JButton creditsBtn = new JButton("Credits");
		creditsBtn.setFont(new Font("Tahoma", Font.PLAIN, 33));
		creditsBtn.setBounds(600, 490, 300, 100);
		add(creditsBtn);
		
		JButton quitBtn = new JButton("Quit");
		quitBtn.setFont(new Font("Tahoma", Font.PLAIN, 33));
		quitBtn.setBounds(600, 600, 300, 100);
		add(quitBtn);
	}
	
	@Override
	public void goBackPanel() {
		MenuManager.getInstance().quitGame();
	}
	
	private void loadGame() {
		MenuManager.getInstance().openMenu(1);
	}
	
	private void newGame() {
		MenuManager.getInstance().openMenu(0);
	}
	
	private void credits() {
		MenuManager.getInstance().openMenu(4);
	}
	
	private void settings() {
		MenuManager.getInstance().openMenu(3);
	}
	
	private void howToPlay() {
		MenuManager.getInstance().openMenu(2);
	}
}