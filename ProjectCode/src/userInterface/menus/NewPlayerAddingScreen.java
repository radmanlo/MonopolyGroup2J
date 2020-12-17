package userInterface.menus;

import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Player;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

public class NewPlayerAddingScreen extends JPanel{
	private JTextField nameField;
	private JComboBox colorComboBox;
	private JComboBox tokenComboBox;
	private JButton removePlayerBtn;
	
	public NewPlayerAddingScreen() {
		setLayout(null);
		setBounds(400, 300, 1040, 70);
		
		nameField = new JTextField();
		nameField.setBounds(47, 15, 529, 28);
		add(nameField);
		nameField.setColumns(10);
		
		colorComboBox = new JComboBox();
		colorComboBox.setBounds(626, 11, 87, 36);
		add(colorComboBox);
		
		tokenComboBox = new JComboBox();
		tokenComboBox.setBounds(744, 11, 156, 36);
		add(tokenComboBox);
		
		removePlayerBtn = new JButton("X"); //I want to make it rounded -G
		removePlayerBtn.setBackground(Color.RED);
		removePlayerBtn.setOpaque(true);
		removePlayerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removePlayerBtn.setBounds(941, 11, 66, 36);
		add(removePlayerBtn);
	}
	
	public Player createPlayer() {
		return null;
	}
	
	public void updateComboBoxes() {
		
	}
}
