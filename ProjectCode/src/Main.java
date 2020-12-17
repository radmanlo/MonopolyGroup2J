import gamePresenter.SoundManager;
import settingsPresenter.LocalDataManager;

public class Main {

    public static void main(String[] args) {
        String path = "resources/sounds/bg-music.wav";

//        SoundManager soundManager = SoundManager.getInstance();
//        soundManager.playDiceSound();
//        soundManager.playBackgroundSound();

        LocalDataManager ldm = LocalDataManager.getInstance();
        ldm.getDefaultValues();
    }
}
