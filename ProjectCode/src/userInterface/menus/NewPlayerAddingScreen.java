package userInterface.menus;

import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Player;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class NewPlayerAddingScreen extends JPanel{
	private JTextField nameField;
	private JComboBox colorComboBox;
	private JComboBox tokenComboBox;
	private JButton removePlayerBtn;
	
	public NewPlayerAddingScreen() {
		setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(25, 11, 195, 20);
		add(nameField);
		nameField.setColumns(10);
		
		colorComboBox = new JComboBox();
		colorComboBox.setBounds(257, 11, 38, 20);
		add(colorComboBox);
		
		tokenComboBox = new JComboBox();
		tokenComboBox.setBounds(326, 11, 66, 20);
		add(tokenComboBox);
		
		removePlayerBtn = new JButton("X");
		removePlayerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removePlayerBtn.setBounds(402, 10, 35, 29);
		add(removePlayerBtn);
	}
	
	public Player createPlayer() {
		return null;
	}
	
	public void updateComboBoxes() {
		
	}
}
