package userInterface.scene;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import models.Player;
import models.TradeDeal;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class TradeScreen extends JPanel{
	private JButton offerTradeBtn;
	private JButton closeBtn;
	private JButton addToOfferBtn;
	
	public TradeScreen(Player currentPlayer) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(1100,210, 600, 600);
		setBackground(Color.WHITE);
		
		JLabel constantLblOffer = new JLabel("Offer a Trade");
		constantLblOffer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		constantLblOffer.setBounds(179, 11, 196, 56);
		add(constantLblOffer);
		
		JComboBox receiverComboBox = new JComboBox();
		receiverComboBox.setBounds(186, 95, 230, 20);
		add(receiverComboBox);
		
		JLabel constantLblOfferTo = new JLabel("Offer To");
		constantLblOfferTo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		constantLblOfferTo.setBounds(82, 98, 75, 17);
		add(constantLblOfferTo);
		
		JPanel offeredPanel = new JPanel();
		offeredPanel.setBounds(42, 140, 242, 360);
		add(offeredPanel);
		offeredPanel.setLayout(null);
		
		JLabel constantLblItemsOffered = new JLabel("Items Offered");
		constantLblItemsOffered.setFont(new Font("Tahoma", Font.PLAIN, 15));
		constantLblItemsOffered.setBounds(39, 11, 110, 25);
		offeredPanel.add(constantLblItemsOffered);
		
		JPanel wantedPanel = new JPanel();
		wantedPanel.setBounds(337, 140, 236, 360);
		add(wantedPanel);
		wantedPanel.setLayout(null);
		
		JLabel constantLblItemsWanted = new JLabel("Items Wanted");
		constantLblItemsWanted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		constantLblItemsWanted.setBounds(60, 11, 105, 25);
		wantedPanel.add(constantLblItemsWanted);
		
		JButton sendOfferBtn = new JButton("Send Offer");
		sendOfferBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sendOfferBtn.setBounds(169, 551, 127, 38);
		add(sendOfferBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cancelBtn.setBounds(327, 551, 115, 38);
		add(cancelBtn);
	}
	
	private TradeDeal createTradeDeal() {
		return null;
	}
}
