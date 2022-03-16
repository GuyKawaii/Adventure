package adventure;

import java.util.Scanner;

public class Game {
	private boolean isPlaying = true;
	private final Scanner in = new Scanner(System.in);
	private final Map map;
	private final UI ui;
	private final Player player;
	
	public Game() {
		// setup gateState
		ui = new UI();
		map = new Map();
		map.createRooms();
		player = new Player(map.getStartRoom());
		
		// print info
		ui.printGameIntro();
	}
	
	public void run() {
		String userChoice;
		
		// Main game loop
		do {
			System.out.printf("""
							
							[h] for help - current room [%s]
							enter choice:\040""",
					player.getCurrentRoom().getName());
			userChoice = in.nextLine().toLowerCase();
			
			switch (userChoice) {
				// Movement choices
				case "n", "north", "go north" -> selectDirection(player, Direction.NORTH);
				case "s", "south", "go south" -> selectDirection(player, Direction.SOUTH);
				case "w", "west", "go west" -> selectDirection(player, Direction.WEST);
				case "e", "east", "go east" -> selectDirection(player, Direction.EAST);
				// Other actions(also always available)
				case "h", "help" -> ui.printControls();
				case "l", "look" -> System.out.printf("""
								- You are in room [%s] "%s"
								""",
						player.getCurrentRoom().getName(),
						player.getCurrentRoom().getDescription());
				case "exit" -> isPlaying = false;
				default -> System.out.println("invalid user input");
			}
			
		} while (isPlaying);
	}
	
	public void selectDirection(Player player, Direction direction) {
		boolean couldMove = player.movement(direction);
		
		if (!couldMove) {
			System.out.println("- You cannot go that way [no room entry in that direction]");
		} else {
			System.out.printf("- Entering [%S] \"%s\"\n",
					player.getCurrentRoom().getName(),
					player.getCurrentRoom().getDescription());
		}
	}
	
}
