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
import models.location.Location;
import settingsPresenter.LocalDataManager;
import userInterface.menus.MenuManager;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class InteractionArea extends JPanel{ 
	private JButton diceRollButton;
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	private JTextField saveField;
	private JTextField loadField;
	
	public InteractionArea() {
		setLayout(null);
		setBounds(0, 50, 700, 800);
		diceRollButton = new JButton("SaveGame");
		diceRollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDataManager.getInstance().saveGame(saveField.getText()); // CHANGE SAVE LOCATION -T
			}
		});
		diceRollButton.setBounds(155, 50, 89, 23);
		add(diceRollButton);
		
		JTextField loadField = new JTextField();
		loadField.setBounds(284, 454, 86, 20);
		add(loadField);
		loadField.setColumns(10);
		
		
		JButton diceRollButtons = new JButton("LoadGame");
		diceRollButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDataManager.getInstance().loadGame(loadField.getText());
				
				MenuManager.getInstance().openMenu(6);
				BoardManager.getInstance().updateMap();
			}
		});
		diceRollButtons.setBounds(300, 50, 89, 23);
		add(diceRollButtons);
		
		JButton buyBtn = new JButton("Buy");
		buyBtn.setBounds(36, 208, 89, 23);
		add(buyBtn);
		
		JButton rollBtn = new JButton("Roll Dice");
		rollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().rollDice();
			}
		});
		rollBtn.setBounds(196, 208, 89, 23);
		add(rollBtn);
		
		JButton move1Btn = new JButton("Move Player 1 Forward");
		move1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 LocationManager.getInstance().movePlayer(PlayerManager.getInstance().getCurrentPlayer(), 1);
				 BoardManager.getInstance().updateMap();
			}
		});
		move1Btn.setBounds(300, 202, 199, 34);
		add(move1Btn);
		
		JButton nextPlayerBtn = new JButton("Next Player");
		nextPlayerBtn.setBounds(528, 208, 89, 23);
		add(nextPlayerBtn);
		
		JButton offerTradeBtn = new JButton("Offer Trade");
		offerTradeBtn.setBounds(25, 338, 89, 23);
		add(offerTradeBtn);
		
		JButton showPlayersBtn = new JButton("Show All Players");
		showPlayersBtn.setBounds(155, 302, 153, 95);
		add(showPlayersBtn);
		
		JButton showLocsBtn = new JButton("Show All Locations");
		showLocsBtn.setBounds(332, 302, 147, 95);
		add(showLocsBtn);
		
		JButton rerollBtn = new JButton("Pay for reroll");
		rerollBtn.setBounds(528, 327, 147, 44);
		add(rerollBtn);
		
		saveField = new JTextField();
		saveField.setBounds(155, 454, 86, 20);
		add(saveField);
		saveField.setColumns(10);
		
		
		
		JButton addMoneyBtn = new JButton("Add Money");
		addMoneyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentMoney = PlayerManager.getInstance().getCurrentPlayer().getUsableMoney();
				PlayerManager.getInstance().getCurrentPlayer().setUsableMoney(currentMoney + 100 );
			}
		});
		addMoneyBtn.setBounds(25, 603, 89, 23);
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
		updateMoneyBtn.setBounds(25, 662, 89, 23);
		add(updateMoneyBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				System.out.println(PlayerManager.getInstance().getCurrentPlayer().getName());
				//System.out.println(LocationManager.getInstance().getLocationList().size() + "");
				
				//for( Location loc: LocationManager.getInstance().getLocationList())
					//System.out.println(loc.getLocationId());
				
				
				BoardManager.getInstance().updateMap();
			}
		});
		updateBtn.setBounds(25, 731, 89, 23);
		add(updateBtn);
		
		JButton setOwnerBtn = new JButton("setOwnerHereFromCurrent");
		setOwnerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
				//LocationManager.getInstance().getLocationById(currentPlayer.getLocation)
				//LocationManager.getInstance().getBuyableList().get();
				
				
			}
		});
		setOwnerBtn.setBounds(347, 562, 232, 23);
		add(setOwnerBtn);
	}
}
