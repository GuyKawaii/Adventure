package adventure;

import static adventure.Direction.*;

public class Game {
  private boolean isPlaying = true;
  private Map map;
  private UI ui;
  private Player player;
  
  public Game() {
    // setup gameState
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
        case "n", "north", "go north" -> selectDirection(player, NORTH);
        case "s", "south", "go south" -> selectDirection(player, SOUTH);
        case "w", "west", "go west" -> selectDirection(player, WEST);
        case "e", "east", "go east" -> selectDirection(player, EAST);
        // Context specific actions
        case "t", "take" -> takeItem(player, item);
        case "d", "drop" -> dropItem(player, item);
        case "eq", "equip" -> equipItem(player, item);
        case "at", "attack" -> ui.printAttack(player);
        case "eat" -> eatItem(player, item);
        // Other actions
        case "i", "inventory" -> ui.printInventory(player);
        case "hp", "health" -> ui.printHealthPoints(player);
        case "h", "help" -> ui.printControls();
        case "l", "look" -> ui.printRoomDescription(player.getCurrentRoom(), "your inside");
        case "exit" -> isPlaying = false;
        default -> ui.printInvalidUserInput();
      }
      
    } while (isPlaying);
  }
  
  // Player movement
  public void selectDirection(Player player, Direction direction) {
    boolean playerMoved = player.movement(direction);
    
    if (playerMoved)
      ui.printRoomDescription(player.getCurrentRoom(), "entering");
    else
      ui.printInvalidDirection();
  }
  
  // Player actions
  public void takeItem(Player player, String itemName) {
    Room currentRoom = player.getCurrentRoom();
    Item roomItem = currentRoom.findItem(itemName);
    
    if (itemName == null)
      ui.printItemNotSpecified();
    else if (roomItem == null)
      ui.printRoomItemNotFound(itemName);
    else {
      player.takeItem(roomItem);
      ui.printAddInventory();
    }
  }
  
  public void dropItem(Player player, String itemName) {
    Item playerItem = player.findItem(itemName);
    
    if (itemName == null)
      ui.printItemNotSpecified();
    else if (playerItem == null)
      ui.printRoomItemNotFound(itemName);
    else {
      player.dropItem(playerItem);
      ui.printRemoveInventory();
    }
  }
  
  public void eatItem(Player player, String foodName) {
    if (foodName == null) {
      ui.printItemNotSpecified();
    } else {
      // get reference for item in inventory or room
      Item item = player.findItem(foodName);
      if (item == null) item = player.getCurrentRoom().findItem(foodName);
      
      Action action = player.eat(foodName);
      ui.printEat(player, foodName, item, action);
    }
  }
  
  public void equipItem(Player player, String itemName) {
    if (itemName == null) {
      ui.printItemNotSpecified();
    } else {
      Action action = player.equip(itemName);
      Item weapon = player.getEquippedWeapon();
      ui.printEquip(weapon, itemName, action);
    }
  }
  
  
}
