package userInterface.scene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import gamePresenter.BoardManager;
import gamePresenter.GameManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;
import models.Player;
import models.location.BuyableLocation;
import models.location.Location;
import models.location.Property;
import settingsPresenter.LocalDataManager;
import userInterface.menus.MenuManager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;

public class InteractionArea extends JPanel{ 
	public JButton diceRollButton;
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	private JTextField saveField;
	private JTextField loadField;
	public JButton buyBtn;
	private JButton rollDiceBtn;
	private JButton offerTradeBtn;
	private JButton pauseBtn;
	private JButton endTurnBtn;
	private JPanel tradeOfferPanel;
	private JList tradeOffersList;
	private JLabel currentPlayerMoneyLbl;
	private JLabel diceRollResultLbl;
	private PlayerInfoScreen currentPlayerPanel;
	private ArrayList<PlayerInfoScreen> otherPlayers;
	private JButton movePlayerBy1;

	public InteractionArea() {
		setLayout(null);
		setBounds(0, 0, 900, 1000);
		diceRollButton = new JButton("SaveGame");
		diceRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDataManager.getInstance().saveGame(saveField.getText()); // CHANGE SAVE LOCATION -T
			}
		});
		diceRollButton.setBounds(53, 916, 89, 23);
		add(diceRollButton);

		buyBtn = new JButton("Buy");
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().executePurchase();
			}
		});
		buyBtn.setBounds(302, 285, 89, 23);
		add(buyBtn);

		rollDiceBtn = new JButton("Roll Dice");
		rollDiceBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rollDiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().rollDice();
			}
		});
		rollDiceBtn.setBounds(53, 283, 168, 23);
		add(rollDiceBtn);

		offerTradeBtn = new JButton("Offer Trade");
		offerTradeBtn.setBounds(372, 236, 112, 23);
		add(offerTradeBtn);

		saveField = new JTextField();
		saveField.setBounds(53, 950, 86, 20);
		add(saveField);
		saveField.setColumns(10);

		JButton updateBtn = new JButton("Update");  //sil
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardManager.getInstance().updateMap();
				BoardManager.getInstance().updateInteractionArea();
			}
		});
		updateBtn.setBounds(53, 840, 207, 44); 
		add(updateBtn);     //sil



		pauseBtn = new JButton("Pause");
		pauseBtn.setBounds(10, 23, 89, 23);
		add(pauseBtn);

		JLabel constantLblCurrentPlyr = new JLabel("Current Player");
		constantLblCurrentPlyr.setFont(new Font("Tahoma", Font.PLAIN, 33));
		constantLblCurrentPlyr.setBounds(53, 57, 232, 34);
		add(constantLblCurrentPlyr);

		endTurnBtn = new JButton("End Turn");
		endTurnBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		endTurnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				GameManager.getInstance().handleEndTurn();
			}
		});
		endTurnBtn.setBounds(332, 396, 152, 59);
		add(endTurnBtn);

		tradeOfferPanel = new JPanel();
		tradeOfferPanel.setBounds(523, 124, 280, 256);
		add(tradeOfferPanel);

		tradeOffersList = new JList();
		tradeOfferPanel.add(tradeOffersList);

		JLabel constantLblOffers = new JLabel("Offers");
		constantLblOffers.setFont(new Font("Tahoma", Font.PLAIN, 23));
		constantLblOffers.setBounds(606, 93, 112, 20);
		add(constantLblOffers);

		JLabel constantLblOtherPlyrs = new JLabel("Other Players");
		constantLblOtherPlyrs.setFont(new Font("Tahoma", Font.PLAIN, 33));
		constantLblOtherPlyrs.setBounds(53, 458, 232, 34);
		add(constantLblOtherPlyrs);

		currentPlayerMoneyLbl = new JLabel("some tl");
		currentPlayerMoneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentPlayerMoneyLbl.setBounds(305, 316, 152, 23);
		add(currentPlayerMoneyLbl);

		diceRollResultLbl = new JLabel("Roll the dice please...");
		diceRollResultLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		diceRollResultLbl.setBounds(53, 317, 168, 23);
		add(diceRollResultLbl);
		
		movePlayerBy1 = new JButton("Move Player By 1");   //button for testing, will be deleted -G
		movePlayerBy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().rollDiceForTesting();
			}
		});
		movePlayerBy1.setBounds(302, 916, 207, 23);
		add(movePlayerBy1);

		otherPlayers = new ArrayList<PlayerInfoScreen>();
	}

	public void setCurrentPlayerMoneyLbl(int money) {
		currentPlayerMoneyLbl.setText( money + " TL");
	}

	public void setDiceRollResultLbl(int result) {
		if(result != -1)
			diceRollResultLbl.setText( "The dice result is: " + result);
		else
			diceRollResultLbl.setText( "Please roll the dice");
	}

	public JButton getRollDiceButton() {
		return rollDiceBtn;
	}
	
	public void update() {
		if( currentPlayerPanel != null )
			remove(currentPlayerPanel);

		for( PlayerInfoScreen screen: otherPlayers)
			remove(screen);
		otherPlayers = new ArrayList<PlayerInfoScreen>();

		if(PlayerManager.getInstance().getCurrentPlayer() != null ) {
			currentPlayerPanel = new PlayerInfoScreen(PlayerManager.getInstance().getCurrentPlayer());
			currentPlayerPanel.setBounds(53, 113, 320, 60);
			add(currentPlayerPanel);
		}
		
		int currentPlayerIndex = -1;
		for( int i = 0; i < PlayerManager.getInstance().getPlayers().size(); ++i) {
			if( PlayerManager.getInstance().getPlayers().get(i).getId() == PlayerManager.getInstance().getCurrentPlayer().getId() ) {
				currentPlayerIndex = i;
			}	
		}

		for( int i = 1; i < PlayerManager.getInstance().getPlayers().size() ; ++i) {
			int desiredIndex = (currentPlayerIndex + i) % PlayerManager.getInstance().getPlayers().size();
			if( PlayerManager.getInstance().getPlayers().get(desiredIndex).getId() != PlayerManager.getInstance().getCurrentPlayer().getId() )
				otherPlayers.add(new PlayerInfoScreen(PlayerManager.getInstance().getPlayers().get(desiredIndex)));
		}

		for( int i = 0; i< otherPlayers.size(); ++i ) {
			otherPlayers.get(i).setBounds(53, 500 + 100 * i, 320, 60);
			add(otherPlayers.get(i));
			otherPlayers.get(i).revalidate();
			otherPlayers.get(i).repaint();
		}
	}
}
