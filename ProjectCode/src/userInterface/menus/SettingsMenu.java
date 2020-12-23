package userInterface.menus;

import gamePresenter.SoundManager;
import userInterface.components.RoundedPanel;
import utilities.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SettingsMenu extends Menu {

	JSlider sliderMusicVolume;
	JSlider sliderSFXVolume;
	JButton applyButton;
	
	public SettingsMenu() {
		super("./resources/baseBackground.png");
		setLayout(null);
		addLabel();
		addControls();
	}

	private void addLabel() {
//		JLabel label = new JLabel("Settings");
//		label.setFont(new Font("Tahoma", Font.BOLD, 26));
//		label.setForeground(Color.white);
//		label.setBounds(1240, 150, 1000, 70);
//		add(label);
	}

	private void addControls() {
		JPanel panel;
		panel = new RoundedPanel(new Dimension(100, 100));
		panel.setBounds(700, 100, 1080, 750);
		panel.setLayout(null);
		panel.setBackground(new Color(230, 112, 112, 38));

		// Music controller
		int currBGVolume = SoundManager.getInstance().getBGVolume();
		this.sliderMusicVolume = new JSlider(0, 100, currBGVolume);
		this.sliderMusicVolume.setMajorTickSpacing(10);
		this.sliderMusicVolume.setMinorTickSpacing(1);
		this.sliderMusicVolume.setPaintTicks(true);
		this.sliderMusicVolume.setPaintLabels(true);
		this.sliderMusicVolume.setForeground(Color.white);
		this.sliderMusicVolume.setBounds(40, 300, 1000, 70);
		this.sliderMusicVolume.setPreferredSize(new Dimension(400, 70));
		this.sliderMusicVolume.setBackground(Utils.getBgColor().brighter());
		Border br = BorderFactory.createTitledBorder(null, "Music Volume", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma",Font.PLAIN,15), Color.white);
		this.sliderMusicVolume.setBorder(br);

		this.sliderMusicVolume.addChangeListener(e -> onBGSliderChange());
		panel.add(this.sliderMusicVolume);

		// SFX controller
		int currSFXVolume = SoundManager.getInstance().getSfxVolume();
		this.sliderSFXVolume = new JSlider(0, 100, currSFXVolume);
		this.sliderSFXVolume.setMajorTickSpacing(10);
		this.sliderSFXVolume.setMinorTickSpacing(1);
		this.sliderSFXVolume.setPaintTicks(true);
		this.sliderSFXVolume.setPaintLabels(true);
		this.sliderSFXVolume.setForeground(Color.white);
		this.sliderSFXVolume.setBounds(40, 400, 1000, 70);
		this.sliderSFXVolume.setPreferredSize(new Dimension(400, 70));
		this.sliderSFXVolume.setBackground(Utils.getBgColor().brighter());
		Border br1 = BorderFactory.createTitledBorder(null, "SFX Volume", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma",Font.PLAIN,15), Color.white);
		this.sliderSFXVolume.setBorder(br1);
		this.sliderSFXVolume.addChangeListener(e -> onSFXSliderChange());
		panel.add(this.sliderSFXVolume);

		add(panel);
	}

	private void onBGSliderChange() {
		SoundManager.getInstance().setBGVolume(this.sliderMusicVolume.getValue());
	}

	private void onSFXSliderChange() {
		SoundManager.getInstance().setSfxVolume(this.sliderSFXVolume.getValue());
	}
	
	private void applyMusicVolume() {
		
	}
}
