import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InteractionArea extends JPanel{ 
	private JButton diceRollButton;
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	
	public InteractionArea() {
		setLayout(null);
		setBounds(750, 50, 700, 800);
		diceRollButton = new JButton("Roll Dice");
		
		diceRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameManager.getInstance().getBoardMngr().updateMap(null);
			}
		});
		diceRollButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		diceRollButton.setBounds(155, 50, 182, 80);
		add(diceRollButton);
	}
	
}
