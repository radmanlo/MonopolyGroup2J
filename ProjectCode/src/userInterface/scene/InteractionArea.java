package userInterface.scene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		JButton buyBtn = new JButton("Buy");
		buyBtn.setBounds(53, 202, 89, 23);
		add(buyBtn);
		
		JButton rollDiceBtn = new JButton("Roll Dice");
		rollDiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().rollDice();
			}
		});
		rollDiceBtn.setBounds(53, 168, 89, 23);
		add(rollDiceBtn);
		
		JButton move1Btn = new JButton("Move Player 1 Forward");
		move1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 LocationManager.getInstance().movePlayer(PlayerManager.getInstance().getCurrentPlayer(), 1);
				 BoardManager.getInstance().updateMap();
			}
		});
		move1Btn.setBounds(405, 809, 199, 34);
		add(move1Btn);
		
		JButton offerTradeBtn = new JButton("Offer Trade");
		offerTradeBtn.setBounds(152, 168, 112, 23);
		add(offerTradeBtn);
		
		saveField = new JTextField();
		saveField.setBounds(53, 950, 86, 20);
		add(saveField);
		saveField.setColumns(10);
		
		
		
		JButton addMoneyBtn = new JButton("Add Money");
		addMoneyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentMoney = PlayerManager.getInstance().getCurrentPlayer().getUsableMoney();
				PlayerManager.getInstance().getCurrentPlayer().setUsableMoney(currentMoney + 100 );
			}
		});
		addMoneyBtn.setBounds(299, 851, 89, 23);
		add(addMoneyBtn);
		
		JLabel moneyLbl = new JLabel("");
		moneyLbl.setBounds(196, 607, 46, 14);
		add(moneyLbl);
		
		JButton updateMoneyBtn = new JButton("UpdateMoney");
		updateMoneyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moneyLbl.setText(PlayerManager.getInstance().getCurrentPlayer().getUsableMoney() + "");
			}
		});
		updateMoneyBtn.setBounds(53, 806, 131, 23);
		add(updateMoneyBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				BoardManager.getInstance().updateMap();
			}
		});
		updateBtn.setBounds(53, 840, 207, 44);
		add(updateBtn);
		
		JButton setOwnerBtn = new JButton("setOwnerHereFromCurrent");
		setOwnerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
				for( BuyableLocation loc: LocationManager.getInstance().getBuyableList()) {
					loc.setOwner(currentPlayer);
				}
				//LocationManager.getInstance().getLocationById(currentPlayer.getLocation)
				//LocationManager.getInstance().getBuyableList().get();
			}
		});
		setOwnerBtn.setBounds(392, 927, 232, 23);
		add(setOwnerBtn);
		
		JButton upgradeBtn = new JButton("Upgrade");
		upgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for( BuyableLocation loc: LocationManager.getInstance().getBuyableList()) {
					if( loc instanceof Property)
						loc.upgrade();
				}
			}
		});
		upgradeBtn.setBounds(167, 895, 131, 23);
		add(upgradeBtn);
		
		JButton degradeBtn = new JButton("Degrade");
		degradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for( BuyableLocation loc: LocationManager.getInstance().getBuyableList()) {
					if( loc instanceof Property)
						loc.degrade();
				}
			}
		});
		degradeBtn.setBounds(231, 925, 112, 27);
		add(degradeBtn);
		
		JButton pauseBtn = new JButton("Pause");
		pauseBtn.setBounds(10, 23, 89, 23);
		add(pauseBtn);
		
		JLabel constantLblCurrentPlyr = new JLabel("Current Player");
		constantLblCurrentPlyr.setFont(new Font("Tahoma", Font.PLAIN, 33));
		constantLblCurrentPlyr.setBounds(53, 57, 232, 34);
		add(constantLblCurrentPlyr);
		
		JPanel currentPlayerPanel = new JPanel();
		currentPlayerPanel.setBounds(53, 113, 317, 44);
		add(currentPlayerPanel);
		currentPlayerPanel.setLayout(null);
		
		JLabel currentPlayerLbl = new JLabel("A name");
		currentPlayerLbl.setBounds(10, 6, 77, 28);
		currentPlayerPanel.add(currentPlayerLbl);
		currentPlayerLbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		JButton inventoryBtn = new JButton("view inventory");
		inventoryBtn.setBounds(179, 11, 128, 23);
		currentPlayerPanel.add(inventoryBtn);
		
		JButton endTurnBtn = new JButton("End Turn");
		endTurnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		endTurnBtn.setBounds(152, 202, 112, 23);
		add(endTurnBtn);
		
		JPanel tradeOfferPanel = new JPanel();
		tradeOfferPanel.setBounds(523, 124, 280, 256);
		add(tradeOfferPanel);
		
		JList tradeOffersList = new JList();
		tradeOfferPanel.add(tradeOffersList);
		
		JLabel constantLblOffers = new JLabel("Offers");
		constantLblOffers.setFont(new Font("Tahoma", Font.PLAIN, 23));
		constantLblOffers.setBounds(606, 93, 112, 20);
		add(constantLblOffers);
		
		JLabel lblOtherPlayers = new JLabel("Other Players");
		lblOtherPlayers.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblOtherPlayers.setBounds(53, 458, 232, 34);
		add(lblOtherPlayers);
		
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
	}
}
