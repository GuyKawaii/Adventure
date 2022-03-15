import java.util.Scanner;

public class Game {
	boolean isPlaying = true;
	Room currentRoom;
	Room nextRoom;
	String userChoice;
	Scanner in = new Scanner(System.in);
	
	void WelcomeMessage() {
		System.out.println("Welcome to the forest");
		System.out.println("Navigate around using the four cardinal directions: (n)orth, (e)ast, (w)est and (s)outh");
	}
	
	public void run() {
		// init rooms
		Room r1 = new Room("r1 Forest entrance", " you are at the entrance to the forest, the green trees bit you welcome");
		Room r2 = new Room("r2 The green forest", "You continue through the entrance to the green forest, and are now deep inside the green forest");
		Room r3 = new Room("r3 the outer part of the green forest", "The green forest is beginning to look a litte black, and you starts to wonder, what is ahead?");
		Room r4 = new Room("r4 the entrance to the black forest", "You have just stepped through the entrance to the black forest, everything is black and dark. It is hard to see anything...");
		Room r5 = new Room("r5 Welcome to the Treasure Room", ""); //TODO når der implimenteres våben og fjender kan vi lave noget sjovt med treasure room, indtil da lader jeg den være
		Room r6 = new Room("r6 the outer part of the black forest", "As you continue you notice that there is not as many black trees as there used to be. you something in the distant...");
		Room r7 = new Room("r7 the entrance to the swamp", "You have just left the Black forest and are now and the entrance to the swamp, the smell is weird and you begin to feel a little anxious.");
		Room r8 = new Room("r8", "You have walked inside the swamp for a bit, the strong smell keeps increasing and you are starting to feel a little lightweight");
		Room r9 = new Room("r9", "you are hearing noises all around you, but can tell which direction they are coming from.");
		currentRoom = r1;


		// connect room (might have connections done from previous room)
		// r1
		connectWestToEast(r1, r2);
		connectNorthToSouth(r1, r4);
		// r2
		connectWestToEast(r2, r3);
		//r3
		connectNorthToSouth(r3, r6);
		//r4
		connectNorthToSouth(r1, r4);
		connectNorthToSouth(r4, r7);
		//r5
		connectNorthToSouth(r5, r8);
		//r6
		connectNorthToSouth(r3, r6);
		connectNorthToSouth(r6, r9);
		//r7
		connectNorthToSouth(r4, r7);
		connectWestToEast(r7, r8);
		//r8
		connectWestToEast(r8, r9);
		//r9
		// N/A
		
		
		// Game intro start message
		WelcomeMessage();
		
		
		do {
			System.out.printf("current room: %s\n", currentRoom.getName());
			
			
			System.out.println("enter [n]orth, [s]outh [e]ast [w]est to move");
			userChoice = in.nextLine().toLowerCase();
			
			switch (userChoice) {
				// Movement choices
				case "n", "north", "go north" -> currentRoom = selectDirection(currentRoom, currentRoom.getNorth());
				case "s", "south", "go south" -> currentRoom = selectDirection(currentRoom, currentRoom.getSouth());
				case "w", "west", "go west" -> currentRoom = selectDirection(currentRoom, currentRoom.getWest());
				case "e", "east", "go east" -> currentRoom = selectDirection(currentRoom, currentRoom.getEast());

				
				case "exit" -> {
					isPlaying = false;
					System.out.println("you have exit the game");
					System.exit(0);
				}
				
				case "h", "help" -> {
					System.out.println("""
                            You have the following options:
                             1. type (l)ook to repeat the description of the room\s
                             2. type exit to terminate the program""");
				}
				
				case "l", "look" -> {
					System.out.println("" + currentRoom.getDescription());
				}
			}
			
		} while (isPlaying);
		
	}
	
	public Room selectDirection(Room currentRoom, Room nextRoom) {
		if (nextRoom == null) {
			System.out.println("- You cannot go that way [no room entry in that direction]");
			return currentRoom;
		} else {
			System.out.printf("""
                            moved from [%s] to [%s]
                            Description of [%s]: %s
                            \n""",
					currentRoom.getName(),
					nextRoom.getName(),
					nextRoom.getName(),
					currentRoom.getDescription());
			return nextRoom;
		}
	}
	
	
	public void connectNorthToSouth(Room north, Room south) {
		// connects the two rooms such that they point to each-other, go back and forth
		north.setSouth(south);
		south.setNorth(north);
	}
	
	public void connectWestToEast(Room west, Room east) {
		// connects the two rooms such that they point to each-other, go back and forth
		east.setWest(west);
		west.setEast(east);
	}
	
	public static void main(String[] args) {
		new Game().run();
	}
}
