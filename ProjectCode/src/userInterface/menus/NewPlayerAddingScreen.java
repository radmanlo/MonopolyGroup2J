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
		
		colorComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if( chosenColor != PlayerColor.WHITE)
					parentMenu.addChoosableColor(chosenColor);
				chosenColor = PlayerColor.values()[colorComboBox.getSelectedIndex()];
				parentMenu.removeChoosableColor(chosenColor);
			}
		});
		
		add(colorComboBox);
		
		
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

		updateComboBoxes();
	}

	public Player createPlayer() {
		return null;
	}

	public void updateComboBoxes() {
		ArrayList<PlayerColor> choosableColors = parentMenu.getChoosableColors();
		/*
		for(int i = 0; i < choosableColors.size(); ++i) {
			
			colorComboBox.addItem( new ImageIcon("./resources/" + choosableColors.get(i).toString() + ".jpg"));
		}
		*/
		

		ArrayList<Token> choosableTokens = parentMenu.getChoosableTokens();

		Object[] items = new Object[choosableTokens.size()];
		for(int i = 0; i < choosableTokens.size(); ++i) {
			items[i] = new ImageIcon( choosableTokens.get(i).getImage());
		}
		tokenComboBox = new JComboBox(items);
		tokenComboBox.setBounds(775, 6, 90, 65);
		add(tokenComboBox);
	}
}
