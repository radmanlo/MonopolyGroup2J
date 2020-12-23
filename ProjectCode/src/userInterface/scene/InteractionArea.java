package userInterface.scene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gamePresenter.BoardManager;
import gamePresenter.GameManager;
import gamePresenter.LocationManager;
import gamePresenter.PlayerManager;
import gamePresenter.TradeManager;
import models.Player;
import models.location.BuyableLocation;
import models.location.Location;
import models.location.Property;
import settingsPresenter.LocalDataManager;
import userInterface.components.RoundedButton;
import userInterface.menus.MenuManager;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class InteractionArea extends JPanel{
	private JButton buyButton;
	private JButton optionsButton;
	private JButton selectPropertyButton;
	private JTextField loadField;
	private JButton buyBtn;
	private JButton rollDiceBtn;
	private JButton offerTradeBtn;
	private RoundedButton pauseBtn;
	private JButton endTurnBtn;
	private JLabel currentPlayerMoneyLbl;
	private JLabel diceRollResultLbl;
	private PlayerInfoScreen currentPlayerPanel;
	private ArrayList<PlayerInfoScreen> otherPlayers;
	private JButton payToRerollBtn;
	private Image backgroundImage;
	private JTextField saveTxtField;
	
	public InteractionArea() {
		backgroundImage = new ImageIcon("./resources/BoardManager.png").getImage();
		setLayout(null);
		setBounds(0, 0, 900, 1000);
		
		addButtons();
		addLabels();

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
	
	public JButton getBuyButton() {
		return buyBtn;
	}
	
	public void update() {
		if( currentPlayerPanel != null )
			remove(currentPlayerPanel);

		for( PlayerInfoScreen screen: otherPlayers)
			remove(screen);
		otherPlayers = new ArrayList<PlayerInfoScreen>();

		if(PlayerManager.getInstance().getCurrentPlayer() != null ) {
			currentPlayerPanel = new PlayerInfoScreen(PlayerManager.getInstance().getCurrentPlayer());
			currentPlayerPanel.setBounds(53, 113, 400, 60);
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
			if(i < 4)
				otherPlayers.get(i).setBounds(53, 500 + 100 * i, 400, 60);
			else
				otherPlayers.get(i).setBounds(500, 550 + 100 * (i - 4), 400, 60);
			add(otherPlayers.get(i));
			otherPlayers.get(i).revalidate();
			otherPlayers.get(i).repaint();
		}
	}
	
	private void addButtons() {
		buyBtn = new JButton("Buy");
		buyBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().executePurchase();
			}
		});
		buyBtn.setBounds(505, 309, 108, 44);
		add(buyBtn);
		
		rollDiceBtn = new JButton("Roll Dice");
		rollDiceBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rollDiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().rollDice();
				payToRerollBtn.setEnabled(true);
			}
		});
		rollDiceBtn.setBounds(53, 312, 168, 38);
		add(rollDiceBtn);
		
		offerTradeBtn = new JButton("Offer Trade");
		offerTradeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		offerTradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardManager.getInstance().openTradeScreen(PlayerManager.getInstance().getCurrentPlayer());
			}
		});
		offerTradeBtn.setBounds(738, 302, 152, 52);
		add(offerTradeBtn);
		
		pauseBtn = new RoundedButton("Pause");
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuManager.getInstance().openMenu(7);
			}
		});
		pauseBtn.setBounds(10, 10, 128, 57);
		add(pauseBtn);
		
		endTurnBtn = new JButton("End Turn");
		endTurnBtn.setEnabled(false);
		endTurnBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		endTurnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				GameManager.getInstance().handleEndTurn();
				payToRerollBtn.setEnabled(false);
				endTurnBtn.setEnabled(false);
			}
		});
		endTurnBtn.setBounds(695, 390, 195, 59);
		add(endTurnBtn);
		
		payToRerollBtn = new JButton("Pay 200 TL to Reroll The Dice");
		payToRerollBtn.setEnabled(false);
		payToRerollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerManager.getInstance().getCurrentPlayer().setUsableMoney(PlayerManager.getInstance().getCurrentPlayer().getUsableMoney()-200);
				BoardManager.getInstance().updateInteractionArea();
				BoardManager.getInstance().enableDice();
				payToRerollBtn.setEnabled(false);
			}
		});
		payToRerollBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		payToRerollBtn.setBounds(53, 402, 311, 37);
		add(payToRerollBtn);
		
		JButton tempBtn = new JButton("Move everyone 1 Forward");  //should be removed after testing
		tempBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for( Player plyr: PlayerManager.getInstance().getPlayers())
					LocationManager.getInstance().movePlayer(plyr, 1);
				BoardManager.getInstance().updateMap();
			}
		});
		tempBtn.setBounds(613, 919, 232, 23);
		add(tempBtn);
	}
	public JButton getEndTurnButton() {
		return this.endTurnBtn;
	}
	
	private void addLabels() {
		JLabel constantLblCurrentPlyr = new JLabel("Current Player");
		constantLblCurrentPlyr.setForeground(Color.WHITE);
		constantLblCurrentPlyr.setFont(new Font("Tahoma", Font.PLAIN, 33));
		constantLblCurrentPlyr.setBounds(108, 73, 232, 34);
		add(constantLblCurrentPlyr);

		JLabel constantLblOtherPlyrs = new JLabel("Other Players");
		constantLblOtherPlyrs.setForeground(Color.WHITE);
		constantLblOtherPlyrs.setFont(new Font("Tahoma", Font.PLAIN, 33));
		constantLblOtherPlyrs.setBounds(53, 458, 232, 34);
		add(constantLblOtherPlyrs);

		currentPlayerMoneyLbl = new JLabel("some tl");
		currentPlayerMoneyLbl.setForeground(Color.WHITE);
		currentPlayerMoneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentPlayerMoneyLbl.setBounds(524, 361, 89, 23);
		add(currentPlayerMoneyLbl);

		diceRollResultLbl = new JLabel("Roll the dice please...");
		diceRollResultLbl.setForeground(Color.WHITE);
		diceRollResultLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		diceRollResultLbl.setBounds(63, 362, 168, 23);
		add(diceRollResultLbl);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(backgroundImage, 0,0, null);
    }
}