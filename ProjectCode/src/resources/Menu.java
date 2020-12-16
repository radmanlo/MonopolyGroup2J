package resources;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

abstract class Menu extends JPanel{ //instead of it having a panel attribute, I made it directly extend JPanel
	//to make working with windowBuilder easier
	
	public JButton backBtn;
	
	public Menu() {
		setLayout(null);
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBackPanel();
			}
		});
		backBtn.setBounds(10, 10, 89, 34);
		add(backBtn);
	}
	

	
	public void goBackPanel() {
		MenuManager.getInstance().openMenu(5);//was private in class diagram but does not work when private -G
	}
}
