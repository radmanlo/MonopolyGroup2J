package gamePresenter;

import java.io.Serializable;
import java.util.ArrayList;

import models.BankAccount;
import models.location.BuyableLocation;
import models.location.Location;
import models.*;
//import models.Player;

public class BankManager implements Serializable {
	

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = -2031025429409145348L;
	private static BankManager bank = null;
	private double INTEREST_RATE = 0.10;
	private ArrayList<BankAccount> accounts;
	
	//Copy Constructor
    private BankManager( BankManager copy) {
        this.accounts = new ArrayList<BankAccount>();
    	for(int i = 0; i < copy.accounts.size(); i++ ) {
    		BankAccount bac = new BankAccount(copy.accounts.get(i));
    		this.accounts.add(bac);
    	}
    }
    //Default Constructor
	public BankManager() {
		accounts = new ArrayList<BankAccount>();
	}
	
	//Singleton getting manager method
	public static BankManager getInstance() {
		if( bank == null ) {
			bank = new BankManager();
		}
		return bank;
	}
	//Creating banks for initializing method
    public void create(BankManager copy) {
    	bank = new BankManager(copy);
    }
    
    //Method for deposit money for players
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
    //Method for withdraw money for players

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
	
    //Method for applying interest rate to the money of players

	public void applyInterest(Player plyr) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getId() == plyr.getId()) {
				accounts.get(i).setMoneyAmount((int)((double)accounts.get(i).getMoneyAmount()*(1+INTEREST_RATE)));
			}
		}
	}
	//Method for creating accounts for players

	public void openAccount(Player plyr) {
		BankAccount nwBnkAccnt = new BankAccount(plyr.getId(), 0, 0);
		accounts.add(nwBnkAccnt);
	}
	
    //Setter for Interest RATE

	public void setINTEREST_RATE(double iNTEREST_RATE) {
		INTEREST_RATE = iNTEREST_RATE;
	}
	
	//Making manager ready for loading new data
	public void readyForInitialize() {
		accounts = new ArrayList<BankAccount>();
	}
}
