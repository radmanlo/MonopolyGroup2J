package userInterface.menus;
import userInterface.components.RoundedButton;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HowToPlayMenu extends Menu {

	private String instructions;
	private JTextArea instructionDisplayer;
	
	public HowToPlayMenu() {
		super(null);
		setBackground(new Color(93, 51, 52));
		addTextArea();

	}

	private void addTextArea() {
		JTextArea instructions = new JTextArea();
		instructions.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(instructions);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		scrollPane.setBounds(500, 200, 500, 500);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);



	}

}
