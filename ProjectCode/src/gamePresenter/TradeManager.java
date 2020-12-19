package gamePresenter;

import java.util.ArrayList;

import models.Player;
import models.TradeDeal;

public class TradeManager {
	private static TradeManager tradeMngr = null;
	private ArrayList<TradeDeal> tradeDeals;
	public TradeManager() {
		tradeDeals = new ArrayList<TradeDeal>();
	}
	
	public static TradeManager getInstance() {
		if( tradeMngr == null ) {
			tradeMngr = new TradeManager();
		}
		return tradeMngr;
	}
	
	public boolean checkTradeDeals(Player plyr) {
		for(int i = 0 ; i < tradeDeals.size(); i++) {
			if( tradeDeals.get(i).getReceiver().getName().equalsIgnoreCase(plyr.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public void addTradeDeal(TradeDeal trade) {
		tradeDeals.add(trade);
	}
	
	public void executeTrade(TradeDeal trade) {
		trade.execute();
		tradeDeals.remove(trade);
	}
	
	public TradeDeal getTradeDeal(Player plyr) {
		TradeDeal trade;
		for(int i = 0; i < tradeDeals.size(); i++) {
			if(tradeDeals.get(i).getReceiver().getName().equalsIgnoreCase(plyr.getName())){
				trade = tradeDeals.get(i);
				return trade;
			}
		}
		return null;
	}
}
