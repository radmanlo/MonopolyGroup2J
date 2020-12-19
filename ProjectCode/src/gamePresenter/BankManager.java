package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;

import models.BankAccount;
import models.*;
//import models.Player;

public class BankManager implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2031025429409145348L;
	private static BankManager bank = null;
	private double INTEREST_RATE = 0.10;
	private ArrayList<BankAccount> accounts;
	
	public BankManager() {
		accounts = new ArrayList<BankAccount>();
	}
	
	public static BankManager getInstance() {
		if( bank == null ) {
			bank = new BankManager();
		}
		return bank;
	}
	
	public void depositMoney(Player plyr, int money) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getId() == plyr.getId()) {
				accounts.get(i).setMoneyAmount(accounts.get(i).getMoneyAmount() + money);
				plyr.setUsableMoney(plyr.getUsableMoney() - money);
				// COOLDOWN COUNT AFTER DEPOSIT MONEY
				accounts.get(i).setCooldownCount(3);
			}
		}
	}
	public boolean withdrawMoney(Player plyr, int money) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getId() == plyr.getId()) {
				if(accounts.get(i).getMoneyAmount() < money) {
					return false;
				}
				accounts.get(i).setMoneyAmount(accounts.get(i).getMoneyAmount() - money);
				plyr.setUsableMoney(plyr.getUsableMoney() + money);
			}
		}
		return false;
	}
	public void applyInterest(Player plyr) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getId() == plyr.getId()) {
				accounts.get(i).setMoneyAmount((int)((double)accounts.get(i).getMoneyAmount()*(1+INTEREST_RATE)));
			}
		}
	}
	public void openAccount(Player plyr) {
		BankAccount nwBnkAccnt = new BankAccount(plyr.getId(), 0, 0);
		accounts.add(nwBnkAccnt);
	}
	public void setINTEREST_RATE(double iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}
}
