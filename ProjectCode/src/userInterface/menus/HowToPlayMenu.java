package userInterface.menus;
import userInterface.components.RoundedButton;
import userInterface.components.RoundedPanel;
import utilities.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HowToPlayMenu extends Menu {

	private String instructions;
	private JTextArea instructionDisplayer;

	private  String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
	
	public HowToPlayMenu() {
		super("./resources/MainBG.png");
		setLayout(null);
		addTextArea();
	}

	private void addTextArea() {

		JPanel instrPanel = new RoundedPanel();
		instrPanel.setBounds(600, 100, 700, 600);
		instrPanel.setBackground(Utils.getBgColor().brighter());
		add(instrPanel);

//		JTextArea instructions = new JTextArea();
//		instructions.setLineWrap(true);
//
//		JScrollPane scrollPane = new JScrollPane(instructions);
//		scrollPane.setPreferredSize(new Dimension(300, 300));
//		scrollPane.setBounds(500, 200, 500, 500);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		add(scrollPane);

	}

}
