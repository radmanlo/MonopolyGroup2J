package userInterface.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import gamePresenter.SoundManager;
import settingsPresenter.LocalDataManager;
import userInterface.components.RoundedButton;
import userInterface.components.RoundedPanel;
import utilities.Utils;
import javax.swing.JLabel;

public class PauseMenu extends Menu{
	private RoundedButton saveBtn; 
	private JButton goSettingsBtn;
	private RoundedButton quitBtn;
	private JTextField saveNameTxtField; 
	private JSlider sliderMusicVolume;
	private JSlider sliderSFXVolume;
	private JPanel innerPanel;
	private JLabel lblSaveName;
	private JLabel lblPauseMenu;
	
	public PauseMenu() {
		super("./resources/baseBackground.png");
		setLayout(null);
		setBounds(0,0,1900,1000);
		
		innerPanel = new RoundedPanel(new Dimension(100, 100));
		innerPanel.setBounds(700, 100, 1080, 750);
		innerPanel.setLayout(null);
		innerPanel.setBackground(new Color(230, 112, 112, 38));
		
		addControls();
		addButtons();
		addLabels();
		addTextFields();
	}

	@Override
	public void goBackPanel() {
		MenuManager.getInstance().openMenu(6);
	}
	
	private void saveGame() {
		LocalDataManager.getInstance().saveGame( saveNameTxtField.getText() );
	}
	
	private void addLabels() {
		lblPauseMenu = new JLabel("Pause Menu");
		lblPauseMenu.setForeground(Color.WHITE);
		lblPauseMenu.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPauseMenu.setBounds(950, 41, 170, 48);
		add(lblPauseMenu);
		
		lblSaveName = new JLabel("Save Name");
		lblSaveName.setForeground(Color.WHITE);
		lblSaveName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSaveName.setBounds(274, 360, 117, 42);
		innerPanel.add(lblSaveName);
	}
	
	private void addButtons() {
		quitBtn = new RoundedButton("Quit");
		quitBtn.setBounds(120, 649, 340, 70);
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuManager.getInstance().quitGame();
			}
		});
		innerPanel.add(quitBtn);
		
		saveBtn = new RoundedButton("Save Game");
		saveBtn.setBounds(663, 424, 340, 70);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});
		innerPanel.add(saveBtn);
		
		RoundedButton mainMenuBtn = new RoundedButton("Main Menu");
		mainMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuManager.getInstance().openMenu(5);
			}
		});
		mainMenuBtn.setBounds(120, 538, 340, 70);
		innerPanel.add(mainMenuBtn);
		
		backBtn.setLabel("Back to Game");
	}
	
	private void addTextFields() {
		saveNameTxtField = new JTextField();
		saveNameTxtField.setText("Save1");
		saveNameTxtField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		saveNameTxtField.setBounds(120, 424, 463, 56);
		innerPanel.add(saveNameTxtField);
		saveNameTxtField.setColumns(10);	
	}
	
	private void addControls() {
		// Music controller
		this.sliderMusicVolume = new JSlider(0, 100, 40);
		this.sliderMusicVolume.setMajorTickSpacing(10);
		this.sliderMusicVolume.setMinorTickSpacing(1);
		this.sliderMusicVolume.setPaintTicks(true);
		this.sliderMusicVolume.setPaintLabels(true);
		this.sliderMusicVolume.setForeground(Color.white);
		this.sliderMusicVolume.setBounds(40, 120, 1000, 70);
		this.sliderMusicVolume.setPreferredSize(new Dimension(400, 70));
		this.sliderMusicVolume.setBackground(Utils.getBgColor().brighter());
		Border br = BorderFactory.createTitledBorder(null, "Music Volume", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma",Font.PLAIN,15), Color.white);
		this.sliderMusicVolume.setBorder(br);

		this.sliderMusicVolume.addChangeListener(e -> onSliderChanged());
		innerPanel.add(this.sliderMusicVolume);

		// SFX controller
		this.sliderSFXVolume = new JSlider(0, 100, 40);
		this.sliderSFXVolume.setMajorTickSpacing(10);
		this.sliderSFXVolume.setMinorTickSpacing(1);
		this.sliderSFXVolume.setPaintTicks(true);
		this.sliderSFXVolume.setPaintLabels(true);
		this.sliderSFXVolume.setForeground(Color.white);
		this.sliderSFXVolume.setBounds(40, 240, 1000, 70);
		this.sliderSFXVolume.setPreferredSize(new Dimension(400, 70));
		this.sliderSFXVolume.setBackground(Utils.getBgColor().brighter());
		Border br1 = BorderFactory.createTitledBorder(null, "SFX Volume", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma",Font.PLAIN,15), Color.white);
		this.sliderSFXVolume.setBorder(br1);
		innerPanel.add(this.sliderSFXVolume);

		add(innerPanel);
	}

	private void onSliderChanged() {
		SoundManager.getInstance().setVolumeLevel(this.sliderMusicVolume.getValue());
	}
}
