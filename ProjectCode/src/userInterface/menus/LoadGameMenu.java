package userInterface.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import settingsPresenter.LocalDataManager;
import userInterface.components.RoundedButton;
import userInterface.components.RoundedPanel;
import utilities.Utils;

import java.awt.Dimension;

public class LoadGameMenu extends Menu {//extends Menu{

	private RoundedButton loadGameBtn;
	private RoundedButton deleteGameBtn;
	private RoundedButton goBackBtn;
	private LocalDataManager localDataManager;
	private JPanel savedGamesPanel;
	// stores game details(name, fullname, date) in map format
	private ArrayList<Map<String, String>> savedGameDetails;
	private ArrayList<SavedGameTile> savedGamesTiles;
	
	public LoadGameMenu() {
		super("./resources/baseBackground.png");

		remove(backBtn);
		setLayout(null);

		localDataManager = LocalDataManager.getInstance();

		addButtons();
		parseGameDetails();
		drawGamesList();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private void addButtons() {
		loadGameBtn = new RoundedButton("Load Game");
		deleteGameBtn = new RoundedButton("Delete Game");
		goBackBtn = new RoundedButton("Back");

		loadGameBtn.setBounds(150, 100, 500, 70);
		deleteGameBtn.setBounds(150, 190,  500, 70);
		goBackBtn.setBounds(150, 280, 500, 70);
		goBackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBackPanel();
			}
		});

		loadGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initializeLoadGame();
			}
		});

		deleteGameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					deleteGame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		add(loadGameBtn);
		add(deleteGameBtn);
		add(goBackBtn);

	}

	private void parseGameDetails() {
		// get all saved games names
		ArrayList<String> savedGames = localDataManager.getSavedNames();
		savedGameDetails = new ArrayList<>();
		for(String item : savedGames) {
			// Split name at "_" characters
			// this will yield three items
			// name, date, and minute of the date
			String[] parts = item.split("_");

			// check if the name is valid
			if(parts.length >= 3) {
				String name = parts[0];
				String dateString = parts[1];
				String min = parts[2];

				// Remove _D from date
				dateString = dateString.substring(2);

				// Merge minute with rest of date
				dateString = dateString + ":" + min;

				// Parse string and get a Date object
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Date date = null;
				try {
					date = dateFormat.parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				Map<String, String> gameInfo = new HashMap<>();
				gameInfo.put("name", name);
				// filename is the name of file (used for calling loadGame of LocalDataManager)
				gameInfo.put("filename", item);
				String readableDate = calendar.DAY_OF_MONTH + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
				gameInfo.put("date", readableDate);

				savedGameDetails.add(gameInfo);
			}

		}
		System.out.println(savedGameDetails);
	}

	private void drawGamesList() {
		savedGamesTiles = new ArrayList<>();

		// Draw the panel
		savedGamesPanel = new RoundedPanel(new Dimension(100, 100));
		savedGamesPanel.setBounds(700, 100, 1080, 750);
		savedGamesPanel.setBackground(new Color(230, 112, 112, 38));

		// Add title
//		JLabel label = new JLabel("Saved Games");
//		label.setPreferredSize(new Dimension(200, 70));
//		label.setFont(new Font("Tahoma", Font.ITALIC, 33));
//		label.setForeground(Color.white);
//		savedGamesPanel.add(label);

		// Create ButtonGroup
		ButtonGroup btnGroup = new ButtonGroup();

		// Create game tiles
		for(int i = 0; i < savedGameDetails.size(); ++i) {
			Map<String, String> game = savedGameDetails.get(i);

			// Create RadioButton
			JRadioButton rBtn = new JRadioButton();
			btnGroup.add(rBtn);

			// Create details labels
			SavedGameTile g = new SavedGameTile(game.get("name"), game.get("date"), game.get("filename"), rBtn);

			// add to savedGamesTiles to later check if its selected
			savedGamesTiles.add(g);
			// add to panel
			g.setPreferredSize(new Dimension(450, 40));
			savedGamesPanel.add(g);
		}

		add(savedGamesPanel);
	}
	
	private boolean initializeLoadGame() {
		SavedGameTile item = searchForSelected();

		// initialize game if any is selected
		if(item != null) {
			System.out.println("Geldim item Null Değil");
			localDataManager.loadGame(item.getFilename());
		}
		else {
			System.out.println("Geldim item Null");
			System.out.println(item);
		return item != null;
		}
		return item != null;
	}

	/**
	 * Searches if any saved game is selected and returns the name of it
	 * @return the name of selected game
	 */
	private String getSelectedGameName() {
		SavedGameTile item = searchForSelected();

		if(item != null) {
			System.out.println(item.getFilename());
			return item.getFilename();
		}
		return null;
	}
	
	private boolean deleteGame() throws Exception {
		SavedGameTile item = searchForSelected();

		// initialize game if any is selected
		if(item != null) {
			localDataManager.deleteSavedGame(item.getFilename());
		}
		System.out.println(item);
		return item != null;
	}

	/**
	 * Searches through items to check if any is selected
	 * @return selected item if any, null otherwise
	 */
	private SavedGameTile searchForSelected() {
		SavedGameTile selectedItem = null;
		// Search whether a game is selected
		for(SavedGameTile item : savedGamesTiles) {
			if (item.isSelected()) {
				selectedItem = item;
			}
		}
		return selectedItem;
	}
}

class SavedGameTile extends RoundedPanel {
	private String name;
	private String date;
	private String filename;
	private JRadioButton radioButton;

	public SavedGameTile() {

	}

	public SavedGameTile(String name, String date, String filename, JRadioButton radioButton) {
		this.name = name;
		this.date = date;
		this.filename = filename;

		this.radioButton = radioButton;
		setLayout(null);

		drawDetails();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(0f,0f,0f,.0f ));
	}

	/**
	 * Returns whether this tile is selected or not
	 *
	 * @return
	 */
	public boolean isSelected() { return this.radioButton.isSelected(); }

	/**
	 * Returns this items filename
	 * @return
	 */
	public String getFilename() {return this.filename; }

	/**
	 * Draws the tile
	 */
	private void drawDetails() {
		// Create name label
		JLabel name = new JLabel(this.name);
		name.setBounds(10, 5, 200, 40);
		name.setFont(new Font("Tahoma", Font.PLAIN, 24));
		name.setForeground(Color.white);

		// Create date label
		JLabel date = new JLabel(this.date);
		date.setBounds(230, 5, 130, 40);
		date.setFont(new Font("Tahoma", Font.PLAIN, 20));
		date.setForeground(Color.white);

		setMaximumSize(new Dimension(getWidth(), getHeight()));

		add(name);
		add(date);

		radioButton.setBackground(new Color(0f,0f,0f,.0f ));
		radioButton.setBounds(400, 5, 100, 40);
		radioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		add(radioButton);

	}



}
