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
	private JButton diceRollButton;
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	private JTextField saveField;
	private JTextField loadField;
	private JButton buyBtn;
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

		JPanel currentPlayerPanel_1 = new JPanel();
		currentPlayerPanel_1.setLayout(null);
		currentPlayerPanel_1.setBounds(53, 531, 317, 44);
		add(currentPlayerPanel_1);

		JLabel currentPlayerLbl_1 = new JLabel("A name");
		currentPlayerLbl_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_1.setBounds(10, 6, 77, 28);
		currentPlayerPanel_1.add(currentPlayerLbl_1);

		JButton inventoryBtn_1 = new JButton("view inventory");
		inventoryBtn_1.setBounds(179, 11, 128, 23);
		currentPlayerPanel_1.add(inventoryBtn_1);

		JPanel currentPlayerPanel_2 = new JPanel();
		currentPlayerPanel_2.setLayout(null);
		currentPlayerPanel_2.setBounds(405, 531, 317, 44);
		add(currentPlayerPanel_2);

		JLabel currentPlayerLbl_2 = new JLabel("A name");
		currentPlayerLbl_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_2.setBounds(10, 6, 77, 28);
		currentPlayerPanel_2.add(currentPlayerLbl_2);

		JButton inventoryBtn_2 = new JButton("view inventory");
		inventoryBtn_2.setBounds(179, 11, 128, 23);
		currentPlayerPanel_2.add(inventoryBtn_2);

		JPanel currentPlayerPanel_3 = new JPanel();
		currentPlayerPanel_3.setLayout(null);
		currentPlayerPanel_3.setBounds(53, 607, 317, 44);
		add(currentPlayerPanel_3);

		JLabel currentPlayerLbl_3 = new JLabel("A name");
		currentPlayerLbl_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_3.setBounds(10, 6, 77, 28);
		currentPlayerPanel_3.add(currentPlayerLbl_3);

		JButton inventoryBtn_3 = new JButton("view inventory");
		inventoryBtn_3.setBounds(179, 11, 128, 23);
		currentPlayerPanel_3.add(inventoryBtn_3);

		JPanel currentPlayerPanel_4 = new JPanel();
		currentPlayerPanel_4.setLayout(null);
		currentPlayerPanel_4.setBounds(405, 607, 317, 44);
		add(currentPlayerPanel_4);

		JLabel currentPlayerLbl_4 = new JLabel("A name");
		currentPlayerLbl_4.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_4.setBounds(10, 6, 77, 28);
		currentPlayerPanel_4.add(currentPlayerLbl_4);

		JButton inventoryBtn_4 = new JButton("view inventory");
		inventoryBtn_4.setBounds(179, 11, 128, 23);
		currentPlayerPanel_4.add(inventoryBtn_4);

		JPanel currentPlayerPanel_5 = new JPanel();
		currentPlayerPanel_5.setLayout(null);
		currentPlayerPanel_5.setBounds(53, 683, 317, 44);
		add(currentPlayerPanel_5);

		JLabel currentPlayerLbl_5 = new JLabel("A name");
		currentPlayerLbl_5.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_5.setBounds(10, 6, 77, 28);
		currentPlayerPanel_5.add(currentPlayerLbl_5);

		JButton inventoryBtn_5 = new JButton("view inventory");
		inventoryBtn_5.setBounds(179, 11, 128, 23);
		currentPlayerPanel_5.add(inventoryBtn_5);

		JPanel currentPlayerPanel_6 = new JPanel();
		currentPlayerPanel_6.setLayout(null);
		currentPlayerPanel_6.setBounds(405, 683, 317, 44);
		add(currentPlayerPanel_6);

		JLabel currentPlayerLbl_6 = new JLabel("A name");
		currentPlayerLbl_6.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_6.setBounds(10, 6, 77, 28);
		currentPlayerPanel_6.add(currentPlayerLbl_6);

		JButton inventoryBtn_6 = new JButton("view inventory");
		inventoryBtn_6.setBounds(179, 11, 128, 23);
		currentPlayerPanel_6.add(inventoryBtn_6);

		JPanel currentPlayerPanel_7 = new JPanel();
		currentPlayerPanel_7.setLayout(null);
		currentPlayerPanel_7.setBounds(53, 751, 317, 44);
		add(currentPlayerPanel_7);

		JLabel currentPlayerLbl_7 = new JLabel("A name");
		currentPlayerLbl_7.setFont(new Font("Tahoma", Font.PLAIN, 23));
		currentPlayerLbl_7.setBounds(10, 6, 77, 28);
		currentPlayerPanel_7.add(currentPlayerLbl_7);

		JButton inventoryBtn_7 = new JButton("view inventory");
		inventoryBtn_7.setBounds(179, 11, 128, 23);
		currentPlayerPanel_7.add(inventoryBtn_7);

		currentPlayerMoneyLbl = new JLabel("some tl");
		currentPlayerMoneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentPlayerMoneyLbl.setBounds(305, 316, 152, 23);
		add(currentPlayerMoneyLbl);

		diceRollResultLbl = new JLabel("Roll the dice please...");
		diceRollResultLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		diceRollResultLbl.setBounds(53, 317, 168, 23);
		add(diceRollResultLbl);

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

	public void update() {

		if( currentPlayerPanel != null )
			remove(currentPlayerPanel);


		for( PlayerInfoScreen screen: otherPlayers)
			remove(screen);
		otherPlayers.clear();

		if(PlayerManager.getInstance().getCurrentPlayer() != null ) {
			currentPlayerPanel = new PlayerInfoScreen(PlayerManager.getInstance().getCurrentPlayer());
			currentPlayerPanel.setBounds(53, 113, 320, 60);
			add(currentPlayerPanel);
		}
		int currentPlayerIndex = -1;
		for( int i = 0; i < PlayerManager.getInstance().getPlayers().size(); ++i) {
			if( PlayerManager.getInstance().getPlayers().get(i).getId() == PlayerManager.getInstance().getCurrentPlayer().getId() ) {
				currentPlayerIndex = i;
				System.out.println("currentPlayerIndex: " + currentPlayerIndex);
			}	
		}

		for( int i = 1; i < PlayerManager.getInstance().getPlayers().size() ; ++i) {
			int desiredIndex = (currentPlayerIndex + i) % PlayerManager.getInstance().getPlayers().size();
			System.out.print( " i:" + i + " desiredIndex: " + desiredIndex );
			if( PlayerManager.getInstance().getPlayers().get(desiredIndex).getId() != PlayerManager.getInstance().getCurrentPlayer().getId() )
				otherPlayers.add(new PlayerInfoScreen(PlayerManager.getInstance().getPlayers().get(desiredIndex)));
		}
		System.out.println("");


		for( int i = 0; i< otherPlayers.size(); ++i ) {
			otherPlayers.get(i).setBounds(53, 400 + 100 * i, 320, 60);
			add(otherPlayers.get(i));
		}
	}
}
