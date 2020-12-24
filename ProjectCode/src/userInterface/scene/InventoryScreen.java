package userInterface.scene;
import javax.swing.JPanel;

import models.Card;
import models.Player;
import models.location.Location;
import models.location.Property;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

import gamePresenter.BoardManager;
import gamePresenter.GameManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;

import java.awt.Component;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class InventoryScreen extends JPanel{
	private JButton degradeBtn;
	private JButton upgradeBtn;
	private JButton sellBtn;
	private JButton useCardBtn;
	private JList locsList_1;

	public InventoryScreen(Player player) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(1100,210, 600, 600);
		setBackground(new Color(60, 60, 60, 230));

		JLabel playerNameLbl = new JLabel( player.getName() + "'s Inventory");
		playerNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		playerNameLbl.setBounds(129, 22, 350, 53);
		playerNameLbl.setForeground(Color.WHITE);
		add(playerNameLbl);

		JLabel constantLblMoney = new JLabel("Money");
		constantLblMoney.setFont(new Font("Tahoma", Font.PLAIN, 22));
		constantLblMoney.setBounds(76, 112, 72, 27);
		constantLblMoney.setForeground(Color.WHITE);
		add(constantLblMoney);

		JLabel moneyLbl = new JLabel( player.getUsableMoney() +" TL");
		moneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		moneyLbl.setBounds(60, 151, 148, 34);
		moneyLbl.setForeground(Color.WHITE);
		add(moneyLbl);

		JLabel constantLblCards = new JLabel("Cards");
		constantLblCards.setFont(new Font("Tahoma", Font.PLAIN, 22));
		constantLblCards.setBounds(87, 232, 64, 27);
		constantLblCards.setForeground(Color.WHITE);
		add(constantLblCards);

		JLabel constantLblLocs = new JLabel("Owned Locations");
		constantLblLocs.setFont(new Font("Tahoma", Font.PLAIN, 22));
		constantLblLocs.setBounds(336, 112, 177, 27);
		constantLblLocs.setForeground(Color.WHITE);
		add(constantLblLocs);

		JPanel locsPanel = new JPanel();
		locsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		locsPanel.setBackground(new Color(60, 60, 60, 100));
		locsPanel.setBounds(278, 151, 293, 289);
		add(locsPanel);
		locsPanel.setLayout(new GridLayout(1, 0, 0, 0));

		//System.out.println(LocationManager.getInstance().getAllLocationsOf(player).size()+ "");
		JList locsList;
		if(LocationManager.getInstance().getBuyablesOfPlayer(player).toArray() != null) {
			locsList = new JList(LocationManager.getInstance().getBuyablesOfPlayer(player).toArray());
		}
		else
			locsList = new JList();
		locsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				if( player != PlayerManager.getInstance().getCurrentPlayer())
					return;
				
				if(locsList.getSelectedValue() != null) {
					Location loc = LocationManager.getInstance().getLocationByName( (String) locsList.getSelectedValue());

					if( loc instanceof Property ) {
						if( LocationManager.getInstance().isPropertyUpgradeable((Property) loc))
							upgradeBtn.setEnabled(true);
						else
							upgradeBtn.setEnabled(false);


						if(LocationManager.getInstance().isPropertySellable((Property)loc))
							sellBtn.setEnabled(true);
						else
							sellBtn.setEnabled(false);

						if(LocationManager.getInstance().isPropertyDegradeable((Property)loc))
							degradeBtn.setEnabled(true);
						else
							degradeBtn.setEnabled(false);
					}
					else {
						sellBtn.setEnabled(true);
						degradeBtn.setEnabled(false);
						upgradeBtn.setEnabled(false);
					}
				}
				else {
					sellBtn.setEnabled(false);
					degradeBtn.setEnabled(false);
					upgradeBtn.setEnabled(false);
				}
			}
		});

		locsList.setBackground(new Color(60, 60, 60, 100));
		locsList.setForeground(Color.WHITE);
		locsPanel.add(locsList);

		JPanel cardsPanel = new JPanel();
		cardsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		cardsPanel.setBackground(new Color(60, 60, 60, 100));
		cardsPanel.setBounds(20, 270, 198, 269);
		cardsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		add(cardsPanel);

		JList cardsList = new JList(player.getCards().toArray());
		cardsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				if(cardsList.getSelectedValue() != null)
					useCardBtn.setEnabled(true);
				else
					useCardBtn.setEnabled(false);
			}
		});

		cardsList.setBackground(new Color(60, 60, 60, 255));
		cardsList.setForeground(Color.WHITE);
		cardsList.setForeground(Color.WHITE);
		cardsPanel.add(cardsList);

		useCardBtn = new JButton("Use Card");
		useCardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().useCard((Card) cardsList.getSelectedValue());
				BoardManager.getInstance().closeInventoryScreen();
			}
		});
		useCardBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		useCardBtn.setBackground(new Color(60, 60, 60, 255));
		useCardBtn.setForeground(Color.WHITE);
		useCardBtn.setBounds(20, 545, 198, 44);
		add(useCardBtn);

		upgradeBtn = new JButton("Upgrade by giving some TL");
		upgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().upgradeProperty((String) locsList.getSelectedValue());
				BoardManager.getInstance().closeInventoryScreen();
			}
		});
		upgradeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		upgradeBtn.setBounds(278, 451, 293, 39);
		upgradeBtn.setBackground(new Color(60, 60, 60, 255));
		upgradeBtn.setForeground(Color.WHITE);
		add(upgradeBtn);

		degradeBtn = new JButton("Degrade to get some TL");
		degradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().degradeProperty((String) locsList.getSelectedValue());
				BoardManager.getInstance().closeInventoryScreen();
			}
		});
		degradeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		degradeBtn.setBackground(new Color(60, 60, 60, 255));
		degradeBtn.setBounds(278, 501, 293, 39);
		degradeBtn.setForeground(Color.WHITE);
		add(degradeBtn);

		sellBtn = new JButton("Sell to get some TL");
		sellBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().sellProperty((String) locsList.getSelectedValue());
				BoardManager.getInstance().closeInventoryScreen();
			}
		});
		sellBtn.setBackground(new Color(60, 60, 60, 255));
		sellBtn.setForeground(Color.WHITE);
		sellBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sellBtn.setBounds(278, 550, 293, 39);
		add(sellBtn);

		sellBtn.setEnabled(false);
		degradeBtn.setEnabled(false);
		upgradeBtn.setEnabled(false);
		useCardBtn.setEnabled(false);
		revalidate();
		repaint();
		//setVisible(true);
	}
}
