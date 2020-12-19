import java.awt.EventQueue;

import gamePresenter.SoundManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import settingsPresenter.LocalDataManager;
import java.io.File;
import java.io.FileInputStream;
import userInterface.menus.MenuManager;

public class Main {

    public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					MenuManager.getInstance();
    					
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});

    }
}
