package userInterface.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import gamePresenter.BoardManager;
import settingsPresenter.LocalDataManager;

public class LoadGameMenu extends Menu{
	
	private JTable gamesList;
	private JButton initializeGameBtn;
	private JButton deleteGameBtn;
	
	public LoadGameMenu() {
		super("./resources/LoadMenu.jpg");
		JTextField loadField = new JTextField();
		loadField.setBounds(284, 454, 86, 20);
		add(loadField);
		loadField.setColumns(10);
		
		
		JButton diceRollButtons = new JButton("LoadGame");
		diceRollButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDataManager.getInstance().loadGame(loadField.getText());
				
				MenuManager.getInstance().openMenu(6);
				BoardManager.getInstance().updateMap();
			}
		});
		diceRollButtons.setBounds(300, 50, 89, 23);
		add(diceRollButtons);
	}
	
	private boolean initializeLoadGame() {
		return false;
	}
	
	private boolean deleteGame() {
		return false;
	}
}
