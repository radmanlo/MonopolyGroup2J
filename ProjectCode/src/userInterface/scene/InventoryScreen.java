package userInterface.scene;
import javax.swing.JPanel;

import models.Player;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

import gamePresenter.LocationManager;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class InventoryScreen extends JPanel{
	private JButton degradeBtn;
	private JList locsList_1;

	public InventoryScreen(Player player) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(1100,210, 600, 600);
		setBackground(Color.WHITE);
		
		JLabel playerNameLbl = new JLabel( player.getName() + "'s Inventory");
		playerNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		playerNameLbl.setBounds(129, 22, 350, 53);
		add(playerNameLbl);
		
		JLabel constantLblMoney = new JLabel("Money");
		constantLblMoney.setFont(new Font("Tahoma", Font.PLAIN, 22));
		constantLblMoney.setBounds(76, 112, 72, 27);
		add(constantLblMoney);
		
		JLabel moneyLbl = new JLabel( player.getUsableMoney() +" TL");
		moneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		moneyLbl.setBounds(60, 151, 148, 34);
		add(moneyLbl);
		
		JLabel constantLblCards = new JLabel("Cards");
		constantLblCards.setFont(new Font("Tahoma", Font.PLAIN, 22));
		constantLblCards.setBounds(87, 232, 64, 27);
		add(constantLblCards);
		
		JLabel constantLblLocs = new JLabel("Owned Locations");
		constantLblLocs.setFont(new Font("Tahoma", Font.PLAIN, 22));
		constantLblLocs.setBounds(336, 112, 177, 27);
		add(constantLblLocs);
		
		JPanel locsPanel = new JPanel();
		locsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
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
					System.out.println("asdfdf");
				}
			});
		
		locsPanel.add(locsList);
		
		JPanel cardsPanel = new JPanel();
		cardsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		cardsPanel.setBounds(20, 270, 198, 269);
		add(cardsPanel);
		cardsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JList cardsList = new JList(player.getCards().toArray());
		cardsPanel.add(cardsList);
		
		JButton useCardBtn = new JButton("Use Card");
		useCardBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		useCardBtn.setBounds(20, 545, 198, 44);
		add(useCardBtn);
		
		JButton upgradeBtn = new JButton("Upgrade by giving some TL");
		upgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.getOwnedLocations().get(cardsList.getSelectedIndex()).upgrade();
			}
		});
		upgradeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		upgradeBtn.setBounds(278, 451, 293, 39);
		add(upgradeBtn);
		
		degradeBtn = new JButton("Degrade to get some TL");
		degradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.getOwnedLocations().get(cardsList.getSelectedIndex()).degrade();
			}
		});
		degradeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		degradeBtn.setBounds(278, 501, 293, 39);
		add(degradeBtn);
		
		JButton sellBtn = new JButton("Sell to get some TL");
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
