package userInterface.scene;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InteractionArea extends JPanel{ 
	private JButton diceRollButton;
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	
	public InteractionArea() {
		setLayout(null);
		setBounds(750, 50, 700, 800);
		diceRollButton = new JButton("Roll game.Dice");
		diceRollButton.setBounds(155, 50, 89, 23);
		add(diceRollButton);
	}
	
}
