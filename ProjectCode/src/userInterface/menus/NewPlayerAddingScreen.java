package userInterface.menus;

import javax.swing.JPanel;
import javax.swing.JTextField;
import models.Player;
import models.PlayerColor;
import models.Token;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class NewPlayerAddingScreen extends JPanel{
	private JTextField nameField;
	public JComboBox colorComboBox; //public as temp
	private JComboBox tokenComboBox;
	private JButton removePlayerBtn;
	private NewGameMenu parentMenu;

	private Token chosenToken;
	private PlayerColor chosenColor;
	private String chosenName;

	public NewPlayerAddingScreen(NewGameMenu parentMenu) {
		this.parentMenu = parentMenu;
		chosenToken = null;
		chosenColor = PlayerColor.WHITE;
		setLayout(null);
		setBounds(400, 300, 1040, 80);

		ArrayList<PlayerColor> choosableColors = parentMenu.getChoosableColors();
		colorComboBox = new JComboBox();
		colorComboBox.setBounds(626, 11, 90, 40);
		for(int i = 0; i < choosableColors.size(); ++i) {
			colorComboBox.addItem( new ImageIcon("./resources/" + choosableColors.get(i).toString() + ".jpg"));
		}
		add(colorComboBox);
		
		ArrayList<Token> choosableTokens = parentMenu.getChoosableTokens();
		tokenComboBox = new JComboBox();
		for(int i = 0; i < choosableTokens.size(); ++i) {
			tokenComboBox.addItem( new ImageIcon( choosableTokens.get(i).getImage()));
		}
		tokenComboBox.setBounds(775, 6, 90, 65);
		add(tokenComboBox);
		
		
		nameField = new JTextField();
		nameField.setBounds(47, 15, 529, 28);
		add(nameField);
		nameField.setColumns(10);

		NewPlayerAddingScreen copyOfThis = this;

		removePlayerBtn = new JButton("X"); //I want to make it rounded -G
		removePlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentMenu.removePotentialPlayer(copyOfThis);
			}
		});
		removePlayerBtn.setBackground(Color.RED);
		removePlayerBtn.setOpaque(true);
		removePlayerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removePlayerBtn.setBounds(941, 11, 66, 36);
		add(removePlayerBtn);
	}
	
	public void updateChosens() {
		chosenToken = new Token(((ImageIcon) tokenComboBox.getSelectedItem()).getImage());
		//chosenColor = (PlayerColor) colorComboBox.getSelectedItem();
		chosenName = nameField.getText();
	}
	
	public Token getChosenToken() {
		return chosenToken;
	}

	public PlayerColor getChosenColor() {
		return chosenColor;
	}

	public String getChosenName() {
		return chosenName;
	}

	public Player createPlayer() {
		return null;
	}
}
