package models;

import java.io.Serializable;

public class Dice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7701328154586915869L;
	private int firstDiceResult, secondDiceResult, totalResult;
	
	public Dice() {
		rollDices();
	}
	
	public void rollDices() {
	    firstDiceResult=(int)(Math.random()*6+1);
	    secondDiceResult=(int)(Math.random()*6+1);
	    totalResult = firstDiceResult + secondDiceResult;

	}

	public int getFirstDiceResult() {
		return firstDiceResult;
	}

	public int getSecondDiceResult() {
		return secondDiceResult;
	}

	public int getTotalResult() {
		return totalResult;
	}
	
	public boolean isDoubleDice() {
		return (firstDiceResult == secondDiceResult);
	}

}
