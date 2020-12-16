package models;

public class Dice {
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
