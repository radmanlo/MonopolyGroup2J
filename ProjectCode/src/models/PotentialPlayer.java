package models;

public class PotentialPlayer {
	private Token token;
	private PlayerColor color;
	private String name;
	
	public PotentialPlayer(Token token, PlayerColor color, String name) {
		this.token = token;
		this.color = color;
		this.name = name;
	}
	public Token getToken() {
		return token;
	}
	public PlayerColor getColor() {
		return color;
	}
	public String getName() {
		return name;
	}
}
