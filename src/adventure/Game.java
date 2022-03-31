package adventure;

import adventure.enum_and_color.Action;
import adventure.enum_and_color.Direction;

import static adventure.enum_and_color.Direction.*;

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
    String entityName;
    
    // Main game loop
    do {
      ui.printUserSelect(player);
      
      // split command
      userCommandElements = ui.getUserCommandElements();
      command = userCommandElements[0];
      entityName = userCommandElements[1];
      
      switch (command) {
        // Movement
        case "n", "north", "go north" -> selectDirection(player, NORTH);
        case "s", "south", "go south" -> selectDirection(player, SOUTH);
        case "e", "east", "go east" -> selectDirection(player, EAST);
        case "w", "west", "go west" -> selectDirection(player, WEST);
        // Context specific actions
        case "t", "take" -> takeItem(player, entityName);
        case "d", "drop" -> dropItem(player, entityName);
        case "eq", "equip" -> equipItem(player, entityName);
        case "at", "attack" -> attackEnemy(player, entityName);
        case "eat" -> eatItem(player, entityName);
        // Other actions
        case "i", "inventory" -> ui.printInventory(player);
        case "hp", "health" -> ui.printHealthPoints(player);
        case "h", "help" -> ui.printControls();
        case "l", "look" -> ui.printRoomDescription(player.getCurrentRoom(), "your inside");
        case "exit" -> isPlaying = false;
        default -> ui.printInvalidUserInput();
      }
      
      if (player.getHealthPoints() == 0) {
        isPlaying = false;
        ui.printGameOver();
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
  public void attackEnemy(Player player, String enemyName) {
    Room currentRoom = player.getCurrentRoom();
    // player select specific enemy or find first one
    Enemy enemy = null;
    if (enemyName != null) enemy = currentRoom.findEnemy(enemyName);
    else if (!(currentRoom.getEnemies().isEmpty())) enemy = currentRoom.getEnemies().get(0);
    
    int damage = player.attack();
    if (enemy != null)
      enemy.takeDamage(damage);
    
    ui.printPlayerAttack(player, enemy, damage);
    
    if (enemy != null && enemy.getHealthPoints() != 0) {
      damage = enemy.attack();
      player.takeDamage(damage);
      ui.printEnemyAttack(player, enemy, damage);
    }
  }
  
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
  
  public void eatItem(Player player, String candidateFoodName) {
    if (candidateFoodName == null) {
      ui.printItemNotSpecified();
    } else {
      // get reference for item in inventory or room
      Item item = player.findItem(candidateFoodName);
      if (item == null) item = player.getCurrentRoom().findItem(candidateFoodName);
      
      Action action = player.eat(candidateFoodName);
      ui.printEat(player, candidateFoodName, item, action);
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
