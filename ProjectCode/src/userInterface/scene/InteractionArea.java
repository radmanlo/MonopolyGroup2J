package userInterface.scene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import settingsPresenter.LocalDataManager;

public class InteractionArea extends JPanel{ 
	private JButton diceRollButton;
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	
	public InteractionArea() {
		setLayout(null);
		setBounds(0, 50, 700, 800);
		diceRollButton = new JButton("SaveGame");
		diceRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDataManager.getInstance().saveGame("First"); // CHANGE SAVE LOCATION -T
			}
		});
		diceRollButton.setBounds(155, 50, 89, 23);
		add(diceRollButton);
		
		JButton diceRollButtons = new JButton("LoadGame");
		diceRollButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDataManager.getInstance().loadGame("First");
			}
		});
		diceRollButtons.setBounds(300, 50, 89, 23);
		add(diceRollButtons);
	}
	
}
