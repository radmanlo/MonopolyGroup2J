
public class Property {
	private int value;
	private int location;
	private int groupId;
	private int level;
	private String name;
	
	public Property() {
		
	}

	public int getLevel() {
		return level;
	}

	public void increaseLevel() {
		level++;
	}
	
	public void decreaseLevel() {
		level--;
	}

	public int getValue() {
		return value;
	}

	public int getLocation() {
		return location;
	}

	public int getGroupId() {
		return groupId;
	}
	
	
}
