package models;

import java.io.Serializable;

public class Dice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7701328154586915869L;
	private int firstDiceResult, secondDiceResult, totalResult;
	/*
	 * Default construcor
	 */
	public Dice() {
		rollDices();
	}
	/*
	 * Rolls dices
	 */
	public void rollDices() {
	    firstDiceResult=(int)(Math.random()*6+1);
	    secondDiceResult=(int)(Math.random()*6+1);
	    totalResult = firstDiceResult + secondDiceResult;

	}

	/*
	 * Get first dice rsult
	 */
	public int getFirstDiceResult() {
		return firstDiceResult;
	}

	/*
	 * Get second dice result
	 */
	public int getSecondDiceResult() {
		return secondDiceResult;
	}

	/*
	 * Get total result
	 */
	public int getTotalResult() {
		return totalResult;
	}
	
	/*
	 * Get the double stiuation
	 */
	public boolean isDoubleDice() {
		return (firstDiceResult == secondDiceResult);
	}

}
