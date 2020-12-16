package bilopoly;

public class SoundManager {
	private static SoundManager soundManager = null;
	private String diceSoundPath;
	private String buyingPropertySoundPath;
	private String backgroundSoundPath;
	
	private SoundManager() {
		
	}
	
	public static SoundManager getInstance() {
		if( soundManager == null ) {
			soundManager = new SoundManager();
		}
		return soundManager;
	}
	
	public void playMusic( String path ) {
		
	}
	
	public void playDiceSound() {
		
	}
	
	public void playBuyingPropertySound() {
		
	}
	
	public void playBackgroundSound() {
		
	}
	
	private void applyChanges() {
		
	}
}
