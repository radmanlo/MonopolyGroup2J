package userInterface.menus;

import javax.swing.JButton;
import javax.swing.JSlider;

public class SettingsMenu extends Menu {

	JSlider sliderMusicVolume;
	JButton applyButton;
	
	public SettingsMenu() {
		super();
		this.sliderMusicVolume = new JSlider(0, 100, 50);
		this.sliderMusicVolume.setMajorTickSpacing(10);
		this.sliderMusicVolume.setMinorTickSpacing(1);
		this.sliderMusicVolume.setPaintTicks(true);
		this.sliderMusicVolume.setPaintLabels(true);
//		this.sliderMusicVolume.setBounds(100, 100);
		add(this.sliderMusicVolume);
	}
	
	private void applyMusicVolume() {
		
	}
}
