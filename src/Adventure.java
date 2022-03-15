import java.util.Scanner;

public class Adventure {
	boolean isPlaying = true;
	Room currentRoom;
	String userChoice;
	Scanner in = new Scanner(System.in);
	
	void printControls() {
		System.out.println("""
				
				CONTROLS
				Navigate around using the four cardinal directions:
				- [n] or [north] or [go north] to go north
				- [s] or [south] or [go south] to go south
				- [w] or [west]  or [go west]  to go west
				- [e] or [east]  or [go east]  to go east
				
				Other actions:
				- [l] or [look] to look around you
				- [h] or [help] to print help screen
				- [exit]        to exit game""");
	}
	
	public Room selectDirection(Room currentRoom, Room nextRoom) {
		if (nextRoom == null) {
			System.out.println("- You cannot go that way [no room entry in that direction]");
			return currentRoom;
			
		} else {
			System.out.printf("- Entering [%S] \"%s\"\n",
					nextRoom.getName(),
					currentRoom.getDescription());
			return nextRoom;
		}
	}
	
	public void setupGameState() {
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
		currentRoom = r1;
		
		// Connect rooms (both ways)
		// Rows connect  (south to east iteration)
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
	
	public void run() {
		setupGameState();
		
		// Print game intro info
		System.out.print("""
				Welcome to the forest, an adventure awaits!
				- Enter to continue:""");
		in.nextLine();
		printControls();
		
		
		// Main game loop
		do {
			System.out.printf("""
							
							[h] for help - current room [%s]
							enter choice:\040""",
					currentRoom.getName());
			
			userChoice = in.nextLine().toLowerCase();
			
			switch (userChoice) {
				// Movement choices
				case "n", "north", "go north" -> currentRoom = selectDirection(currentRoom, currentRoom.getNorth());
				case "s", "south", "go south" -> currentRoom = selectDirection(currentRoom, currentRoom.getSouth());
				case "w", "west", "go west" 	-> currentRoom = selectDirection(currentRoom, currentRoom.getWest());
				case "e", "east", "go east" 	-> currentRoom = selectDirection(currentRoom, currentRoom.getEast());
				// Other actions(also always available)
				case "h", "help" -> printControls();
				case "l", "look" -> {
					System.out.printf("""
									- You are in room [%s] "%s"
									""",
							currentRoom.getName(),
							currentRoom.getDescription());
				}
				case "exit" -> isPlaying = false;
				default -> System.out.println("invalid user input");
			}
			
		} while (isPlaying);
		
	}
	
	public static void main(String[] args) {
		new Adventure().run();
	}
}
