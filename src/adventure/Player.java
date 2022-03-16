package adventure;

public class Player {
	private Room currentRoom;
	
	public Player(Room startRoom) {
		currentRoom = startRoom;
	}
	
	public boolean movement(Direction direction) {
		// returns boolean if player can move
		Room nextRoom;
		
		switch (direction) {
			case NORTH -> nextRoom = currentRoom.getNorth();
			case EAST -> nextRoom = currentRoom.getEast();
			case WEST -> nextRoom = currentRoom.getWest();
			case SOUTH -> nextRoom = currentRoom.getSouth();
			default -> throw new IllegalStateException("Unexpected value: " + direction);
		}
		
		if (nextRoom == null)
			return false;
		currentRoom = nextRoom;
		return true;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}
