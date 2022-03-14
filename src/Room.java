public class Room {
	String name;
	String description;
	// rooms relative to given room
	Room north;
	Room south;
	Room east;
	Room west;
	
	
	public String getWelcomeMessage() {
		return "Welcome to Cave of Cud";
	}
	
	Room(String name, String description) {
		setName(name);
		setDescription(description);
		north = null;
		south = null;
		east = null;
		west = null;
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
	}
	
	public void setSouth(Room south) {
		this.south = south;
	}
	
	public void setEast(Room east) {
		this.east = east;
	}
	
	public void setWest(Room west) {
		this.west = west;
	}
	
	public void setNorthSouthWestEast(Room north, Room south, Room west, Room east) {
		// Parameter order important! - assigning in given order
		setNorth(north);
		setSouth(south);
		setEast(east);
		setWest(west);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
