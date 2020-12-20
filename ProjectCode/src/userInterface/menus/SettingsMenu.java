package userInterface.menus;

import gamePresenter.SoundManager;
import userInterface.components.RoundedPanel;
import utilities.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends Menu {

	JSlider sliderMusicVolume;
	JSlider sliderSFXVolume;
	JButton applyButton;
	
	public SettingsMenu() {
		super("./resources/MainBG1.png");
		setLayout(null);
		addLabel();
		addControls();
	}

	private void addLabel() {
		JLabel label = new JLabel("Settings");
		label.setFont(new Font("Tahoma", Font.ITALIC, 26));
		label.setForeground(Color.white);
		label.setBounds(860, 50, 200, 40);
		add(label);
	}

	private void addControls() {
		JPanel panel = new RoundedPanel();
		panel.setBounds(600, 100, 600, 300);
		panel.setBackground(Utils.getBgColor().brighter());
		panel.setLayout(null);

		// Music controller
		this.sliderMusicVolume = new JSlider(0, 100, 40);
		this.sliderMusicVolume.setMajorTickSpacing(10);
		this.sliderMusicVolume.setMinorTickSpacing(1);
		this.sliderMusicVolume.setPaintTicks(true);
		this.sliderMusicVolume.setPaintLabels(true);
		this.sliderMusicVolume.setForeground(Color.white);
		this.sliderMusicVolume.setBounds(100, 40, 400, 70);
		this.sliderMusicVolume.setPreferredSize(new Dimension(400, 70));
		this.sliderMusicVolume.setBackground(Utils.getBgColor().brighter());
		Border br = BorderFactory.createTitledBorder(null, "Music Volume", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma",Font.PLAIN,15), Color.white);
		this.sliderMusicVolume.setBorder(br);

		this.sliderMusicVolume.addChangeListener(e -> onSliderChanged());
		panel.add(this.sliderMusicVolume);

		// SFX controller
		this.sliderSFXVolume = new JSlider(0, 100, 40);
		this.sliderSFXVolume.setMajorTickSpacing(10);
		this.sliderSFXVolume.setMinorTickSpacing(1);
		this.sliderSFXVolume.setPaintTicks(true);
		this.sliderSFXVolume.setPaintLabels(true);
		this.sliderSFXVolume.setForeground(Color.white);
		this.sliderSFXVolume.setBounds(100, 150, 400, 70);
		this.sliderSFXVolume.setPreferredSize(new Dimension(400, 70));
		this.sliderSFXVolume.setBackground(Utils.getBgColor().brighter());
		Border br1 = BorderFactory.createTitledBorder(null, "SFX Volume", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma",Font.PLAIN,15), Color.white);
		this.sliderSFXVolume.setBorder(br1);
		panel.add(this.sliderSFXVolume);

		add(panel);
	}

	private void onSliderChanged() {
		SoundManager.getInstance().setVolumeLevel(this.sliderMusicVolume.getValue());
	}
	
	private void applyMusicVolume() {
		
	}
}
