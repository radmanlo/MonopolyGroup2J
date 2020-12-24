package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ListModel;

import models.Player;
import models.TradeDeal;

public class TradeManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7017738273559935287L;
	private static TradeManager tradeMngr = null;
	private ArrayList<TradeDeal> tradeDeals;
	/*
	 * Default Constructor
	 */
	public TradeManager() {
		tradeDeals = new ArrayList<TradeDeal>();
	}
	
	/*
	 * Copy Construcotr
	 */
	public TradeManager(TradeManager mngr) {
		// TODO Auto-generated constructor stub
		tradeDeals = new ArrayList<TradeDeal>();
		for(int i = 0; i < mngr.tradeDeals.size(); i++) {
			TradeDeal td = new TradeDeal(mngr.tradeDeals.get(i));
			this.tradeDeals.add(td);
		}
	}

	/*
	 * Get instance method for singleton
	 */
	public static TradeManager getInstance() {
		if( tradeMngr == null ) {
			tradeMngr = new TradeManager();
		}
		return tradeMngr;
	}
	
	/*
	 * Checking trade deals of given player
	 */
	public boolean checkTradeDeals(Player plyr) {
		for(int i = 0 ; i < tradeDeals.size(); i++) {
			if( tradeDeals.get(i).getReceiver().getName().equalsIgnoreCase(plyr.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Adds a new deal
	 */
	public void addTradeDeal(TradeDeal trade) {
		tradeDeals.add(trade);
	}
	/*
	 * Execute instructions of tradement
	 */
	public void executeTrade(TradeDeal trade) {
		trade.execute();
		tradeDeals.remove(trade);
	}
	
	/*
	 * Getting trade deals as array list for given player
	 */
	public ArrayList<TradeDeal> getTradeDeals(Player plyr) {
		ArrayList<TradeDeal> trade = new ArrayList<TradeDeal>(0);

		for(int i = 0; i < tradeDeals.size(); i++) {
			if(tradeDeals.get(i).getReceiver().getName().equalsIgnoreCase(plyr.getName())){
				trade.add(tradeDeals.get(i));
			}
		}
		return trade;
	}

	/*
	 * Removing given trade deal from list
	 */
	public void removeDeal(TradeDeal trdeal) {
		for(int i = 0; i < tradeDeals.size(); i++) {
			if(trdeal.getOfferer().getName().equalsIgnoreCase(tradeDeals.get(i).getOfferer().getName()) ){
				if(trdeal.getReceiver().getName().equalsIgnoreCase(tradeDeals.get(i).getReceiver().getName())) {
					if(trdeal.getOfferedMoney() == tradeDeals.get(i).getOfferedMoney()) {
						if(trdeal.getRequestedMoney() == tradeDeals.get(i).getRequestedMoney()) {
							
							tradeDeals.remove(i);
						}
					}
				}
			}
		}
	}
	public void create(TradeManager mngr) {
		// TODO Auto-generated method stub
	}

	/*
	 * Setting values of the manager with given manager
	 */
	public void set(TradeManager mngr) {
		// TODO Auto-generated constructor stub
		tradeDeals = new ArrayList<TradeDeal>();
		for(int i = 0; i < mngr.tradeDeals.size(); i++) {
			TradeDeal td = new TradeDeal(mngr.tradeDeals.get(i));
			this.tradeDeals.add(td);
		}
	}
	/*
	 * Get trade Deal names of player
	 */
	public ArrayList<String> getTradeDealNames(Player currentPlayer) {
		ArrayList<String> dealNames = new ArrayList<String>();
		for( TradeDeal deal : tradeDeals)
			dealNames.add(deal.getOfferer().getName() + "'s Offer");
		return dealNames;
	}
	/*
	 * Make ready for initialization player
	 */
	public void readyForInitialize() {
		tradeDeals = new ArrayList<TradeDeal>();
	}
}
