package userInterface.menus;
import userInterface.components.RoundedPanel;
import utilities.Utils;

import java.awt.*;

import javax.swing.*;

public class HowToPlayMenu extends Menu {

	private String instructions;
	private JTextArea instructionDisplayer;

	private  String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
	
	public HowToPlayMenu() {
		super("./resources/MainBG.png");
		setLayout(null);
		//addTextArea();
		addLabel();
	}

	private void addLabel() {
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.ITALIC, 26));
		label.setForeground(Color.white);
		label.setBounds(860, 50, 200, 40);
		add(label);
	}

	private void addTextArea() {

		JPanel instrPanel = new RoundedPanel(new Dimension(100, 100));
		instrPanel.setBounds(600, 100, 700, 600);
		instrPanel.setBackground(Utils.getBgColor().brighter());
		instrPanel.setLayout(null);

		JTextArea instructions = new JTextArea();
		instructions.setLineWrap(true);
		instructions.setEditable(false);
		instructions.setBackground(Utils.getBgColor().brighter());
		instructions.setText(text + text + text + text + text);
		instructions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructions.setForeground(Color.white);

		// add pading
		instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(instructions);
		// remove border
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		scrollPane.setBounds(0, 0, 700, 600);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		instrPanel.add(scrollPane);

		add(instrPanel);

	}

}
