package userInterface.menus;

import gamePresenter.SoundManager;

import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends Menu {

	JSlider sliderMusicVolume;
	JButton applyButton;
	
	public SettingsMenu() {
		super("./resources/SettingsMenu.jpg");
		this.sliderMusicVolume = new JSlider(0, 100, 40);
		this.sliderMusicVolume.setMajorTickSpacing(10);
		this.sliderMusicVolume.setMinorTickSpacing(1);
		this.sliderMusicVolume.setPaintTicks(true);
		this.sliderMusicVolume.setPaintLabels(true);
		this.sliderMusicVolume.setBounds(100, 100, 500, 100);

		this.sliderMusicVolume.addChangeListener(e -> onSliderChanged());
		add(this.sliderMusicVolume);
	}

	private void onSliderChanged() {
		SoundManager.getInstance().setVolumeLevel(this.sliderMusicVolume.getValue());
	}
	
	private void applyMusicVolume() {
		
	}
}
