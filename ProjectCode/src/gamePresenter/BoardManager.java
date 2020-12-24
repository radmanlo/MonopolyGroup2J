package gamePresenter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import models.*;
import models.location.BuyableLocation;
import models.location.Location;
import userInterface.scene.EndTurnPrompt;
import userInterface.scene.InteractionArea;
import userInterface.scene.InventoryScreen;
import userInterface.scene.Map;
import userInterface.scene.RentChoicePrompt;
import userInterface.scene.TradeScreen;

public class BoardManager extends JPanel implements Serializable{    

	/**
	 * 
	 */
	private static final long serialVersionUID = -5498787588296943523L;
	private static BoardManager boardManager = null;

	private Map map;
	private InteractionArea interactionArea;
	private RentChoicePrompt rentChoicePrompt;
	private EndTurnPrompt endturnPrompt;
	private TradeScreen tradeScreen;
	private InventoryScreen inventoryScreen;
	private Image backgroundImage;


	//Default Constructor
	private BoardManager() {
		backgroundImage = new ImageIcon("./resources/BoardManager.png").getImage();
		setBounds(0, 0, 2000, 1200);
		setLayout(null);
		map = new Map();
		add(map);
		interactionArea = new InteractionArea();
		add(interactionArea);
	}

	//Method for getting instance of singleton manager
	public static BoardManager getInstance() {
		if( boardManager == null ) {
			boardManager = new BoardManager();
		}
		return boardManager;
	}

	//Updates map
	public void updateMap() {
		closeInventoryScreen();
		closeTradeScreen();
		ArrayList<Location> locationsList;
		locationsList = LocationManager.getInstance().getLocationList();
		map.update(locationsList);
	}

	//Updates Interaction Area
	public void updateInteractionArea() {
		closeInventoryScreen();
		closeTradeScreen();
		interactionArea.update();
		interactionArea.repaint();
		interactionArea.setCurrentPlayerMoneyLbl(PlayerManager.getInstance().getCurrentPlayer().getUsableMoney());
		showPendingTradeDeals();
		interactionArea.setDiceRollResultLbl(GameManager.getInstance().totalDiceResultForUtility());
		decideEnabledorNotForBuyBtn();
		interactionArea.update();
		interactionArea.revalidate();
		interactionArea.repaint();
	}
	//Opens inventory Screen

	public void openInventoryScreen(Player player) {
		closeTradeScreen();
		closeInventoryScreen();
		inventoryScreen = new InventoryScreen(player);
		add(inventoryScreen, 0);
		revalidate();
		repaint();
	}
	//Disabling buy button

	public void disableBuyButton() {
		interactionArea.getBuyButton().setEnabled(false);
	}
	//Enabling buy button
	public void enableBuyButton() {
		interactionArea.getBuyButton().setEnabled(true);
	}
	//Enabling roll dice button
	public void enableDice() {
		interactionArea.getRollDiceButton().setEnabled(true);
	}
	//Disabling roll dice button
	public void disableDice() {
		interactionArea.getRollDiceButton().setEnabled(false);
	}

	//Enable or disable buy button according to players' location
	public void decideEnabledorNotForBuyBtn() {
		if(LocationManager.getInstance().isPlaceBuyable()) {
			BuyableLocation loc = (BuyableLocation)LocationManager.getInstance().getLocationByName(PlayerManager.getInstance().getCurrentPlayer().getLocation().getName());
			if(loc.getOwner() == null && loc.getPrice() <= PlayerManager.getInstance().getCurrentPlayer().getUsableMoney()) {
				interactionArea.getBuyButton().setEnabled(true);
				BuyableLocation currentLoc = (BuyableLocation) PlayerManager.getInstance().getCurrentPlayer().getLocation();
				interactionArea.setTextOnBuyPriceLbl(currentLoc.getPrice() + " TL");
			}
		}
		else {
			interactionArea.getBuyButton().setEnabled(false);
			interactionArea.setTextOnBuyPriceLbl("");
		}

	}

	//Opens trade Screen Panel
	public void openTradeScreen(Player currentPlayer) {
		closeTradeScreen();
		closeInventoryScreen();
		tradeScreen = new TradeScreen(currentPlayer);
		add(tradeScreen, 0);
		revalidate();
		repaint();

	}

	//Shows pending trade deals
	public void showPendingTradeDeals(){

		if( TradeManager.getInstance().checkTradeDeals(PlayerManager.getInstance().getCurrentPlayer())) {

			for( TradeDeal deal : TradeManager.getInstance().getTradeDeals(PlayerManager.getInstance().getCurrentPlayer())) {

				JDialog.setDefaultLookAndFeelDecorated(true);

				String promptMessage = "Do you want accept the following Trade Offer?\n" + deal.toStringForPrompt();
				int response = JOptionPane.showConfirmDialog(null, promptMessage, "Trade Offer",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					TradeManager.getInstance().removeDeal(deal);;
				} else if (response == JOptionPane.YES_OPTION) {
					TradeManager.getInstance().executeTrade(deal);
					TradeManager.getInstance().removeDeal(deal);
				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}
			}
		}
	}

	//End Turn Visible or not maker with given boolean tmp
	public void setEndTurnButton(boolean tmp) {
		interactionArea.getEndTurnButton().setEnabled(tmp);
	}
	
	//Call Die animates
	public void animateDies(int firstDiceResult, int secondDiceResult) {
		map.animateDies(firstDiceResult, secondDiceResult);
	}

	//Closing Trade Screen
	public void closeTradeScreen() {
		if(tradeScreen != null)
			remove(tradeScreen);
		revalidate();
		repaint();
	}

	//Closing Inventory Screen
	public void closeInventoryScreen() {
		if(inventoryScreen != null)
			remove(inventoryScreen);
		revalidate();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0,0, null);
	}

	//Making ready for initialize load game
	public void readyForInitialize() {
		interactionArea.readyForInitialize();	
	}
}
