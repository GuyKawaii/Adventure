package adventure;

public class Map {
	private Room startRoom;
	
	Map() {
		createRooms();
	}
	
	public Room getStartRoom() {
		return startRoom;
	}
	
	public void createRooms() {
		// init rooms
		Room r1 = new Room("r1 Forest entrance", " you are at the entrance to the forest, the green trees bit you welcome");
		Room r2 = new Room("r2 The green forest", "You continue through the entrance to the green forest, and are now deep inside the green forest");
		Room r3 = new Room("r3 the outer part of the green forest", "The green forest is beginning to look a litte black, and you starts to wonder, what is ahead?");
		Room r4 = new Room("r4 the entrance to the black forest", "You have just stepped through the entrance to the black forest, everything is black and dark. It is hard to see anything...");
		Room r5 = new Room("r5 Welcome to the Treasure Room", ""); //TODO når der implimenteres våben og fjender kan vi lave noget sjovt med treasure room, indtil da lader jeg den være
		Room r6 = new Room("r6 Outer part of the black forest", "As you continue you notice that there is not as many black trees as there used to be. you something in the distant...");
		Room r7 = new Room("r7 Entrance to the swamp", "You have just left the Black forest and are now and the entrance to the swamp, the smell is weird and you begin to feel a little anxious.");
		Room r8 = new Room("r8 Deep swamp", "You have walked inside the swamp for a bit, the strong smell keeps increasing and you are starting to feel a little lightweight");
		Room r9 = new Room("r9 Dark cliffside", "you are hearing noises all around you, but can you can't tell which direction they are coming from.");
		startRoom = r1;
		
		// Connect rooms (both ways)
		// Rows connect  (west to east iteration)
		r1.setEast(r2);
		r2.setEast(r3);
		r7.setEast(r8);
		r8.setEast(r9);
		// Cols connect  (north to south iteration)
		r1.setSouth(r4);
		r4.setSouth(r7);
		r5.setSouth(r8);
		r3.setSouth(r6);
		r6.setSouth(r9);
	}
	
}
