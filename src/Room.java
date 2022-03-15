public class Room {
	private String name;
	private String description;
	
	// rooms relative to given room
	private Room north;
	private Room south;
	private Room east;
	private Room west;
	
	Room(String name, String description) {
		setName(name);
		setDescription(description);
		setNorth(null);
		setSouth(null);
		setEast(null);
		setWest(null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Room getNorth() {
		return north;
	}
	
	public Room getSouth() {
		return south;
	}
	
	public Room getEast() {
		return east;
	}
	
	public Room getWest() {
		return west;
	}
	
	public void setNorth(Room north) {
		this.north = north;
		if (north != null)
			north.south = this;
	}
	
	public void setSouth(Room south) {
		this.south = south;
		if (south != null)
			south.north = this;
	}
	
	public void setEast(Room east) {
		this.east = east;
		if (east != null)
			east.west = this;
	}
	
	public void setWest(Room west) {
		this.west = west;
		if (west != null)
			west.east = this;
	}
	
}
