package userInterface.menus;

import javax.swing.*;

import models.Player;
import models.PlayerColor;
import models.Token;
import userInterface.components.RoundedButton;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
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
	private RoundedButton removePlayerBtn;
	private NewGameMenu parentMenu;
	private int chosenTokenIndex;
	private int chosenColorIndex;
	public int getChosenColorIndex() {
		return chosenColorIndex;
	}

	private Token chosenToken;
	private PlayerColor chosenColor;
	private String chosenName;

	public NewPlayerAddingScreen(NewGameMenu parentMenu, int indexNumber) {
		this.parentMenu = parentMenu;
		chosenToken = null;
		chosenColor = PlayerColor.WHITE;
		setLayout(null);
		setBackground(new Color(0f,0f,0f,.0f ));

		ArrayList<PlayerColor> choosableColors = parentMenu.getChoosableColors();
		colorComboBox = new JComboBox();

		colorComboBox.setBounds(625, 11, 90, 35);
		for(int i = 0; i < choosableColors.size(); ++i) {
			colorComboBox.addItem( new ImageIcon("./resources/" + choosableColors.get(i).toString() + ".jpg"));
		}
		add(colorComboBox);
		
		ArrayList<Token> choosableTokens = parentMenu.getChoosableTokens();
		tokenComboBox = new JComboBox();
		for(int i = 0; i < choosableTokens.size(); ++i) {
			tokenComboBox.addItem( new ImageIcon( choosableTokens.get(i).getImage()));
		}
		tokenComboBox.setBounds(775, 11, 90, 35);
		add(tokenComboBox);
		
		
		nameField = new JTextField(20);
		nameField.setBounds(47, 11, 530, 35);

		add(nameField);
		nameField.setColumns(10);

		NewPlayerAddingScreen copyOfThis = this;

		removePlayerBtn = new RoundedButton("X", 20, 20, Color.red);
		removePlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentMenu.removePotentialPlayer(copyOfThis);
			}
		});
		removePlayerBtn.setBackground(Color.RED);
		removePlayerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removePlayerBtn.setBounds(941, 11, 66, 36);
		add(removePlayerBtn);
		
		nameField.setText("Player " + indexNumber);
		colorComboBox.setSelectedIndex(indexNumber - 1);
		tokenComboBox.setSelectedIndex(indexNumber - 1);
	}
	
	public void updateChosens() {
		chosenTokenIndex = tokenComboBox.getSelectedIndex();
		//chosenToken = new Token(((ImageIcon) tokenComboBox.getSelectedItem()).getImage());
		chosenColorIndex = colorComboBox.getSelectedIndex();
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

	public int getChosenTokenIndex() {
		return chosenTokenIndex;
	}
}
//
//class RoundJTextField extends JTextField {
//	private Shape shape;
//	public RoundJTextField(int size) {
//		super(size);
//		setOpaque(false); // As suggested by @AVD in comment.
//	}
//	protected void paintComponent(Graphics g) {
//		g.setColor(getBackground());
//		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
//		super.paintComponent(g);
//	}
//	protected void paintBorder(Graphics g) {
//		g.setColor(getForeground());
//		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
//	}
//	public boolean contains(int x, int y) {
//		if (shape == null || !shape.getBounds().equals(getBounds())) {
//			shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
//		}
//		return shape.contains(x, y);
//	}
//}
