package userInterface.scene;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gamePresenter.PlayerManager;
import models.Player;
import models.TradeDeal;
import models.location.BuyableLocation;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TradeScreen extends JPanel{
	private JButton offerTradeBtn;
	private JButton closeBtn;
	private JButton addToOfferBtn;
	private JTextField offeredMoneyField;
	private JTextField wantedMoneyField;
	private Player targetPlayer;
	private JPanel offeredPanel;
	private JList offeredList;
	private JComboBox offerComboBox;
	private JButton addItemToOfferedBtn;
	private JPanel wantedPanel;
	private JButton addItemToWantedBtn;
	private JComboBox wantedComboBox;
	private JList wantedList;
	private JButton sendOfferBtn;
	private JButton cancelBtn;

	public TradeScreen(Player currentPlayer) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(1100,210, 600, 600);
		setBackground(Color.WHITE);

		JLabel constantLblOffer = new JLabel("Offer a Trade");
		constantLblOffer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constantLblOffer.setBounds(179, 11, 196, 56);
		add(constantLblOffer);


		ArrayList<String> temp = new ArrayList<String>();
		for(Player plyr : PlayerManager.getInstance().getPlayers()) {
			if( plyr.getId() != currentPlayer.getId()) {
				temp.add(plyr.getName());
			}	
		}
		JComboBox receiverComboBox = new JComboBox(temp.toArray());
		receiverComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				targetPlayer = PlayerManager.getInstance().getPlayerByName((String)receiverComboBox.getSelectedItem());
				receiverComboBox.setEnabled(false);
				updateWantedPanel();
			}
		});
		receiverComboBox.setBounds(186, 95, 230, 20);
		add(receiverComboBox);

		JLabel constantLblOfferTo = new JLabel("Offer To");
		constantLblOfferTo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		constantLblOfferTo.setBounds(82, 98, 75, 17);
		add(constantLblOfferTo);

		offeredPanel = new JPanel();
		offeredPanel.setBounds(42, 140, 242, 360);
		add(offeredPanel);
		offeredPanel.setLayout(null);

		JLabel constantLblItemsOffered = new JLabel("Items Offered");
		constantLblItemsOffered.setFont(new Font("Tahoma", Font.PLAIN, 15));
		constantLblItemsOffered.setBounds(39, 11, 110, 25);
		offeredPanel.add(constantLblItemsOffered);

		offeredMoneyField = new JTextField();
		offeredMoneyField.setBounds(114, 329, 118, 20);
		offeredPanel.add(offeredMoneyField);
		offeredMoneyField.setColumns(10);

		JLabel constantLblOfferedMoney = new JLabel("Offered Money");
		constantLblOfferedMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		constantLblOfferedMoney.setBounds(10, 332, 110, 17);
		offeredPanel.add(constantLblOfferedMoney);

		offeredList = new JList();
		offeredList.setBounds(199, 305, -180, -205);
		offeredPanel.add(offeredList);

		offerComboBox = new JComboBox();
		offerComboBox.setBounds(10, 47, 150, 20);
		offeredPanel.add(offerComboBox);

		addItemToOfferedBtn = new JButton("Add");
		addItemToOfferedBtn.setBounds(170, 46, 62, 23);
		offeredPanel.add(addItemToOfferedBtn);

		wantedPanel = new JPanel();
		wantedPanel.setBounds(337, 140, 236, 360);
		add(wantedPanel);
		wantedPanel.setLayout(null);

		JLabel constantLblItemsWanted = new JLabel("Items Wanted");
		constantLblItemsWanted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		constantLblItemsWanted.setBounds(60, 11, 105, 25);
		wantedPanel.add(constantLblItemsWanted);

		wantedMoneyField = new JTextField();
		wantedMoneyField.setColumns(10);
		wantedMoneyField.setBounds(130, 329, 96, 20);
		wantedPanel.add(wantedMoneyField);

		JLabel constantLblWantedMoney = new JLabel("Wanted Money");
		constantLblWantedMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		constantLblWantedMoney.setBounds(10, 332, 110, 17);
		wantedPanel.add(constantLblWantedMoney);

		addItemToWantedBtn = new JButton("Add");
		addItemToWantedBtn.setBounds(170, 42, 56, 23);
		wantedPanel.add(addItemToWantedBtn);

		wantedList = new JList();
		wantedList.setBounds(222, 299, -201, -197);
		wantedPanel.add(wantedList);

		sendOfferBtn = new JButton("Send Offer");
		sendOfferBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sendOfferBtn.setBounds(169, 551, 148, 38);
		add(sendOfferBtn);

		cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelBtn.setBounds(327, 551, 115, 38);
		add(cancelBtn);
	}

	private void updateWantedPanel() {
		ArrayList<String> temp = new ArrayList<String>();
		for(BuyableLocation loc : targetPlayer.getOwnedLocations()) {
			temp.add(loc.getName());
		}
		
		wantedComboBox = new JComboBox(temp.toArray());
		wantedComboBox.setBounds(10, 42, 150, 20);
		wantedPanel.add(wantedComboBox);
		wantedPanel.revalidate();
		wantedPanel.repaint();
		revalidate();
		repaint();
	}

	private TradeDeal createTradeDeal() {
		return null;
	}
}
