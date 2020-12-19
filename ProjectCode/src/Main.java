import java.awt.EventQueue;

import gamePresenter.SoundManager;
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
