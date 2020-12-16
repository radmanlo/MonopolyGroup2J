package models;
public class BankAccount {
	//Attributes
	private int id, moneyAmount, cooldownCount;
	//Constructor
	public BankAccount(int id, int moneyAmount, int cooldownCount) {
		this.id = id;
		this.setMoneyAmount(moneyAmount);
		this.cooldownCount = cooldownCount;
	}
	
	//Methods
	public int getCooldownCount() {
		return cooldownCount;
	}
	public void setCooldownCount(int cooldownCount) {
		this.cooldownCount = cooldownCount;
	}
	public int getId() {
		return id;
	}
	public int getMoneyAmount() {
		return moneyAmount;
	}
	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	
}
