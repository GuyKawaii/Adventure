package adventure;

public class Game {
	private boolean isPlaying = true;
	private Map map;
	private UI ui;
	private Player player;
	
	public Game() {
		// setup gateState
		ui = new UI();
		map = new Map();
		player = new Player(map.getStartRoom());
		
		// print
		ui.printGameIntro();
	}
	
	public void run() {
		String[] userCommandElements;
		String command;
		String item;
		
		// Main game loop
		do {
			ui.printUserSelect(player);
			
			// split command
			userCommandElements = ui.getUserCommandElements();
			command = userCommandElements[0];
			item = userCommandElements[1];
			
			switch (command) {
				// Movement
				case "n", "north", "go north" -> selectDirection(player, Direction.NORTH);
				case "s", "south", "go south" -> selectDirection(player, Direction.SOUTH);
				case "w", "west", "go west" -> selectDirection(player, Direction.WEST);
				case "e", "east", "go east" -> selectDirection(player, Direction.EAST);
				// Other actions
				case "t", "take" -> takeItem(player, item);
				case "d", "drop" -> dropItem(player, item);
				case "i", "inventory" -> ui.printInventory(player);
				case "h", "help" -> ui.printControls();
				case "l", "look" -> ui.printRoomDescription(player.getCurrentRoom(), "your inside");
				case "health", "hp", "healthPoints" -> ui.printHealthPoints(player);
				case "eat" -> itemAte(player, item);
				case "exit" -> isPlaying = false;
				default -> ui.printInvalidUserInput();
			}
			
		} while (isPlaying);
	}
	
	public void selectDirection(Player player, Direction direction) {
		boolean playerMoved = player.movement(direction);
		
		if (playerMoved)
			ui.printRoomDescription(player.getCurrentRoom(), "entering");
		else
			ui.printInvalidDirection();
	}
	
	public void takeItem(Player player, String itemName) {
		Room currentRoom = player.getCurrentRoom();
		
		if (itemName == null) {
			ui.printItemNotSpecified();
			return;
		}
		
		Item removedRoomItem = currentRoom.takeItemByName(itemName);
		if (removedRoomItem == null)
			ui.printItemNotInRoom(itemName);
		else
			player.addItem(removedRoomItem);
		ui.printAddInventory();
	}
	
	public void dropItem(Player player, String itemName) {
		if (itemName == null) {
			ui.printItemNotSpecified();
			return;
		}
		
		Item removedPlayerItem = player.takeItemByName(itemName);
		if (removedPlayerItem == null)
			ui.printItemNotInRoom(itemName);
		else
			player.getCurrentRoom().addItem(removedPlayerItem);
		ui.printRemoveInventory();
	}

	public void itemAte(Player player, String itemName) {
		if (itemName == null) {
			ui.printItemNotSpecified();
			return;
		}
		eatAction action = player.eat(itemName);
		ui.printCanEat(player, itemName, action);
	}
	
}
