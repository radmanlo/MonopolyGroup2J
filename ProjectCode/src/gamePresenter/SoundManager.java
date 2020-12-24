package gamePresenter;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {

	private final String DICE_SOUND_PATH = "resources/sounds/roll-dice.wav";
	private final String BUYING_PROPERTY_SOUND_PATH = "resources/sounds/buy-property.wav";
	private final String BACKGROUND_SOUND_PATH = "resources/sounds/bg-music.wav";
	private final String BUYABLE_WITH_OWNER_PATH = "resources/sounds/land-buyable-with-owner.wav";
	private final String GOTO_JAIL_PATH = "resources/sounds/land-goto-jail.wav";
	private final String CHANCE_CARD_PATH = "resources/sounds/land-on-chance-card.wav";
	private final String PAY_TAX_PATH = "resources/sounds/pay-tax.wav";
	private final String START_TILE_PATH ="resources/sounds/startile.wav";
	
	private static SoundManager soundManager = null;
	private int volumeLevel;
	private int sfxVolume;

	// New varaibles
	private Clip backgroundMusicClip;
	private Clip diceClip;
	private Clip propertyClip;
	private Clip buyableWithOwnerClip;
	private Clip gotoJailClip;
	private Clip chanceCardClip;
	private Clip payTaxClip;
	private Clip startTileClip;


	private SoundManager() {
		this.volumeLevel = 40;
		this.sfxVolume = 40;
		// Initialize the clips
		this.backgroundMusicClip = playMusic(this.BACKGROUND_SOUND_PATH);
		this.diceClip = playMusic(this.DICE_SOUND_PATH);
		this.propertyClip = playMusic(this.BUYING_PROPERTY_SOUND_PATH);
		this.buyableWithOwnerClip = playMusic(this.BUYABLE_WITH_OWNER_PATH);
		this.gotoJailClip = playMusic(this.GOTO_JAIL_PATH);
		this.chanceCardClip = playMusic(this.CHANCE_CARD_PATH);
		this.payTaxClip = playMusic(this.PAY_TAX_PATH);
		this.startTileClip = playMusic(this.START_TILE_PATH);
		this.setSfxVolume(40);
		this.setBGVolume(10);
	}
	
	public static SoundManager getInstance() {
		if( soundManager == null ) {
			soundManager = new SoundManager();
		}
		return soundManager;
	}

	public int getBGVolume() {
		return this.volumeLevel;
	}

	/**
	 * Set background music volume.
	 * @param volume
	 */
	public void setBGVolume(int volume) {
		if (volume < 0 || volume > 100)
			throw new IllegalArgumentException("Invalid volume: " + volume);


		// Update volume
		this.volumeLevel = volume;
		FloatControl controller = (FloatControl) this.backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
		double gain = this.volumeLevel / 100.0;
		// Calculate equivalent decibels
		float DB =  (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		controller.setValue(DB);
	}

	public int getSfxVolume() { return this.sfxVolume; }

	/**
	 * Set sfx volume.
	 * @param volume
	 */
	public void setSfxVolume(int volume) {
		if (volume < 0 || volume > 100)
			throw new IllegalArgumentException("Invalid volume: " + volume);

		// Update volume
		this.sfxVolume = volume;
		FloatControl diceCtrl = (FloatControl) this.diceClip.getControl(FloatControl.Type.MASTER_GAIN);
		FloatControl buyPropCtrl = (FloatControl) this.propertyClip.getControl(FloatControl.Type.MASTER_GAIN);
		FloatControl buyableCtrl = (FloatControl) this.buyableWithOwnerClip.getControl(FloatControl.Type.MASTER_GAIN);
		FloatControl jailCtrl = (FloatControl) this.gotoJailClip.getControl(FloatControl.Type.MASTER_GAIN);
		FloatControl chanceCrl = (FloatControl) this.chanceCardClip.getControl(FloatControl.Type.MASTER_GAIN);
		FloatControl taxCtrl = (FloatControl) this.payTaxClip.getControl(FloatControl.Type.MASTER_GAIN);



		double gain = this.sfxVolume / 100.0;
		// Calculate equivalent decibels
		float DB =  (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		diceCtrl.setValue(DB);
		buyableCtrl.setValue(DB);
		buyPropCtrl.setValue(DB);
		jailCtrl.setValue(DB);
		chanceCrl.setValue(DB);
		taxCtrl.setValue(DB);
	}

	/**
	 * Creates a Clip object and loads the audio from the given path
	 * @param path path of the audio to be played
	 * @return
	 */
	private Clip playMusic(String path) { // No need to be public (public in class diagram thou)
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

/*
 * Plays Dice Sound
 */
	public void playDiceSound() {
		this.diceClip = playMusic(this.DICE_SOUND_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.diceClip.start();
	}
	
	/*
	 * Plays background music
	 */
	public void playBackgroundSound() {
		backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/*
	 * Plays effect of buying property
	 */
	public void playBuyingPropertySound() {
		this.propertyClip = playMusic(this.BUYING_PROPERTY_SOUND_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.propertyClip.start();
	}
/*
 * Plays effect of landed on buyable with owner
 */
	public void playLandedOnBuyableWithOwnerSound() {
		this.buyableWithOwnerClip = playMusic(this.BUYABLE_WITH_OWNER_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.buyableWithOwnerClip.start();
	}
	/*
	 * Plays effect of landed on buyable with owner
	 */
	public void playLandedOnGotoJailSound() {
		this.gotoJailClip = playMusic(this.GOTO_JAIL_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.gotoJailClip.start();
	}
	/*
	 * Plays effect of landed on buyable with owner
	 */
	public void playOnPassingStartTile() {
		this.startTileClip = playMusic(this.START_TILE_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.startTileClip.start();
	}
	/*
	 * Plays effect of landed on buyable with owner
	 */
	public void playLandedOnChanceCardSound() {
		this.chanceCardClip = playMusic(this.CHANCE_CARD_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.chanceCardClip.start();
	}
	/*
	 * Plays effect of landed on buyable with owner
	 */
	public void playPayTaxSound() {
		this.payTaxClip = playMusic(this.PAY_TAX_PATH);
		this.setSfxVolume(this.sfxVolume);
		this.payTaxClip.start();
	}
	
	private void applyChanges() {

	}
}
