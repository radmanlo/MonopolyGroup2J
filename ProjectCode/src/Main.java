import gamePresenter.SoundManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import settingsPresenter.LocalDataManager;
import java.io.File;
import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) {
        String path = "resources/sounds/bg-music.wav";

        SoundManager soundManager = SoundManager.getInstance();
        soundManager.playDiceSound();
        soundManager.playBackgroundSound();
    }
}
