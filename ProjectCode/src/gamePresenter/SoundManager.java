package gamePresenter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundManager {

	private final String DICE_SOUND_PATH = "resources/sounds/roll-dice.wav";
	private final String BUYING_PROPERTY_SOUND_PATH = "resources/sounds/bg-music.wav";
	private final String BACKGROUND_SOUND_PATH = "resources/sounds/bg-music.wav";

	private static SoundManager soundManager = null;
	private int volumeLevel;
	private int sfxVolume;

	// New varaibles
	private Clip backgroundMusicClip;

	
	private SoundManager() {
		this.volumeLevel = 40;
		this.sfxVolume = 40;
		// Create a Clip for playing the music
		this.backgroundMusicClip = playMusic(this.BACKGROUND_SOUND_PATH);
		this.setVolumeLevel(this.volumeLevel);
	}
	
	public static SoundManager getInstance() {
		if( soundManager == null ) {
			soundManager = new SoundManager();
		}
		return soundManager;
	}

	public int getVolumeLevel() {
		FloatControl volumeController = (FloatControl) this.backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
		return (int) Math.pow(10f, volumeController.getValue() / 20f);
	}

	/**
	 * Set background music volume.
	 * @param volume
	 */
	public void setVolumeLevel(int volume) {
		if (volume < 0 || volume > 100)
			throw new IllegalArgumentException("Invalid volume: " + volume);

		// Update volume
		this.volumeLevel = volume;
		FloatControl volumeContoller = (FloatControl) this.backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
		double gain = this.volumeLevel / 100.0;
		// Calculate equivalent decibels
		float DB =  (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		volumeContoller.setValue(DB);
	}

	/**
	 * Creates a Clip object and loads the audio from the given path
	 * @param path path of the audio to be played
	 * @return
	 */
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
		musicPlayer.start();
	}
	
	public void playBuyingPropertySound() {
		playMusic(BUYING_PROPERTY_SOUND_PATH);
	}
	
	public void playBackgroundSound() {
		backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	private void applyChanges() {

		
	}
}
