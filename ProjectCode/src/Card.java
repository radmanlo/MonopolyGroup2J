
public class Card {
	private String name;
	private int id;
	private String description;
	
	public Card( String name, int id, String description) {
		this.name = name;
		this.id = id;
		this.description = description;
		}
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
