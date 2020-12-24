package models;

import java.io.Serializable;

public class BankAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1902277139686464403L;
	//Attributes
	private int id, moneyAmount, cooldownCount;
	//Constructor
	public BankAccount( BankAccount copy) {
		this.id = copy.id;
		this.moneyAmount = copy.moneyAmount;
		this.cooldownCount = copy.cooldownCount;
	}
	public BankAccount(int id, int moneyAmount, int cooldownCount) {
		this.id = id;
		this.setMoneyAmount(moneyAmount);
		this.cooldownCount = cooldownCount;
	}
	
	//Methods
	//Get cooldown count of withdrawing money
	public int getCooldownCount() {
		return cooldownCount;
	}
	//Set cooldown count of withdrawing money

	public void setCooldownCount(int cooldownCount) {
		this.cooldownCount = cooldownCount;
	}
	//Get ID of Account
	public int getId() {
		return id;
	}
	//Get all money in account
	public int getMoneyAmount() {
		return moneyAmount;
	}
	//Set money in account
	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	
}
