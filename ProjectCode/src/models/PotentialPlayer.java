package models;

/*
 * Potential players for use in new game menu for creating new players on game manager
 */
public class PotentialPlayer {
	private Token token;
	private PlayerColor color;
	private String name;
	
	/*
	 * Constructor with given token color and name
	 */
	public PotentialPlayer(Token token, PlayerColor color, String name) {
		this.token = token;
		this.color = color;
		this.name = name;
	}
	/*
	 * Returns its token
	 */
	public Token getToken() {
		return token;
	}
	/*
	 * Returns color of player
	 */
	public PlayerColor getColor() {
		return color;
	}
	/*
	 * Returns name of player
	 */
	public String getName() {
		return name;
	}
}
