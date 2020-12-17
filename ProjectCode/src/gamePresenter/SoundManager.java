package gamePresenter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {

	private final String DICE_SOUND_PATH = "resources/sounds/roll-dice.wav";
	private final String BUYING_PROPERTY_SOUND_PATH = "resources/sounds/bg-music.wav";
	private final String BACKGROUND_SOUND_PATH = "resources/sounds/bg-music.wav";

	private static SoundManager soundManager = null;
	private int volumeLevel;

	
	private SoundManager() {
		
	}
	
	public static SoundManager getInstance() {
		if( soundManager == null ) {
			soundManager = new SoundManager();
		}
		return soundManager;
	}

	public int getVolumeLevel() {
		return volumeLevel;
	}

	public void setVolumeLevel(int volumeLevel) {
		this.volumeLevel = volumeLevel;
	}


	private Clip playMusic(String path ) { // No need to be public (public in class diagram thou)
		Clip musicPlayer  = null;
		try {
			File musicPath = new File(path);
			if(musicPath.exists()) {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicPath);
				musicPlayer = AudioSystem.getClip();
				musicPlayer.open(audioStream);
				return musicPlayer;
			} else {
				System.out.println("File does not exits!");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return musicPlayer;
	}
	
	public void playDiceSound() {
		Clip musicPlayer = playMusic(DICE_SOUND_PATH);
		musicPlayer.loop(Clip.LOOP_CONTINUOUSLY);
		musicPlayer.start();
	}
	
	public void playBuyingPropertySound() {
		playMusic(BUYING_PROPERTY_SOUND_PATH);
	}
	
	public void playBackgroundSound() {
		Clip musicPlayer = playMusic(BACKGROUND_SOUND_PATH);
		musicPlayer.loop(Clip.LOOP_CONTINUOUSLY);
		musicPlayer.start();
	}
	
	private void applyChanges() {

		
	}
}
