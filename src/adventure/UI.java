package adventure;

import adventure.enum_and_color.Action;

import java.util.ArrayList;
import java.util.Scanner;

import static adventure.enum_and_color.Action.*;
import static adventure.enum_and_color.Color.*;

public class UI {
  Scanner in = new Scanner(System.in);
  
  void printGameIntro() {
    System.out.print("""
        Welcome to the forest, an adventure awaits!
        - Enter to continue:""");
    in.nextLine();
    printControls();
  }
  
  void printControls() {
    System.out.println("""
        
        CONTROLS
        Navigate around using the four cardinal directions:
        - [n] or [north] or [go north]      = go north
        - [s] or [south] or [go south]      = go south
        - [w] or [west]  or [go west]       = go west
        - [e] or [east]  or [go east]       = go east
        
        Content specific actions
        - [t "item"]  or [take "item"]      = take item from room
        - [d "item"]  or [drop "item"]      = drop item from inventory to room
        - [eq "weapon"] or [equip "weapon"] = equip weapon from room or inventory
        - [eat "food"]                      = eat food from room or inventory
        - [at "enemy"] or [attack "enemy"]  = attack enemy using equipped weapon
        
        Other actions:
        - [hp]    or [health]               = display health-points
        - [i]     or [inventory]            = display inventory
        - [l]     or [look]                 = look around you
        - [h]     or [help]                 = print help screen
        - [exit]                            = exit game""");
  }
  
  public void printHealthPoints(Player player) {
    int healthPoints = player.getHealthPoints();
    System.out.printf("- Your health [%s%s\u2764HP%s]\n", healthPoints, ANSI_RED, ANSI_RESET);
    
    if (healthPoints <= 33)
      System.out.println("- Your health is critical");
    else if (healthPoints <= 66)
      System.out.println("- You have taken som damage, watch your health-points!");
    else if (healthPoints <= 99)
      System.out.println("- You have taken minimal damage");
    else System.out.println("- You have taken no damage");
  }
  
  public void printPlayerAttack(Player player, Enemy enemy, int damage) {
    // no enemy
    if (enemy == null) {
      System.out.println("- there is no enemy like that to attack");
      return;
    }
    // no weapon
    if (player.getEquippedWeapon() == null) {
      System.out.println("- Your attack failed as you have no weapon");
      return;
    }
    // weapon and enemy
    String coloredDamage = ANSI_YELLOW + "(" + damage + " DMG)" + ANSI_RESET;
    String coloredEnemyName = ANSI_RED + enemy.getName() + ANSI_RESET;
    String coloredEnemyHP = ANSI_YELLOW + "(" + enemy.getHealthPoints() + " HP)" + ANSI_RESET;
    String coloredWeaponName = ANSI_PURPLE + player.getEquippedWeapon().getName() + ANSI_RESET;
    
    if (enemy.getHealthPoints() == 0)
      System.out.printf("- %s is dead and dropped some loot", coloredEnemyName);
    else if (player.getEquippedWeapon().canAttack())
      System.out.printf("- you dealt %s to %s %s with %s\n", coloredDamage, coloredEnemyName, coloredEnemyHP, coloredWeaponName);
    else // player.getEquippedWeapon().canAttack() == false
      System.out.println("- you have no ammunition left and therefore your attack failed");
  }
  
  public void printEnemyAttack(Player player, Enemy enemy, int damage) {
    String coloredDamage = ANSI_YELLOW + "(" + damage + " DMG)" + ANSI_RESET;
    String coloredEnemyName = ANSI_RED + enemy.getName() + ANSI_RESET;
    String coloredPlayerHP = "[" + player.getHealthPoints() + ANSI_RED + "\u2764HP" + ANSI_RESET + "]";
    
    if (enemy.getEquippedWeapon() != null) {
      String coloredWeaponName = ANSI_PURPLE + enemy.getEquippedWeapon().getName() + ANSI_RESET;
      System.out.printf("- %s attacked with %s dealing %s to player %s\n", coloredEnemyName, coloredWeaponName, coloredDamage, coloredPlayerHP);
      
    } else
      System.out.printf("- %s attacked without a weapon dealing %s to player %s\n", coloredEnemyName, coloredDamage, coloredPlayerHP);
  }
  
  public void printEat(Player player, String itemName, Item item, Action action) {
    String color;
    if (action == EAT_ITEM) {
      System.out.printf("""
          - you ate - %s
          - Your health is now [%s%s\u2764HP%s]
          """, item, player.getHealthPoints(), ANSI_RED, ANSI_RESET);
      
    } else if (action == CANT_EAT_ITEM) {
      // Apply item specific color
      if (item instanceof Weapon) color = ANSI_PURPLE;
      else if (item instanceof Food) color = ANSI_GREEN;
      else color = ANSI_BLUE;
      System.out.printf("- you cannot eat: %s%s%s as that is not food\n", color, itemName, ANSI_RESET);
      
    } else { // Action.CANT_FIND_ITEM
      System.out.println("- could not find Item");
    }
  }
  
  public void printEquip(Item weapon, String itemName, Action action) {
    switch (action) {
      case EQUIP_ITEM -> System.out.printf("- you have equipped: %s\n", weapon);
      case CANT_EQUIP_ITEM -> System.out.println("- items that are not weapons cannot be equipped");
      default -> System.out.printf("- cant find %s\n", itemName);
    }
  }
  
  public void printInventory(Player player) {
    StringBuilder printedInventory = new StringBuilder();
    ArrayList<Item> inventory = player.getInventory();
    
    if (inventory.isEmpty())
      System.out.println("- Inventory - empty");
    else {
      for (int i = 0; i < inventory.size() - 1; i++)
        printedInventory.append(inventory.get(i) + ", ");
      printedInventory.append(inventory.get(inventory.size() - 1));
      
      System.out.printf("- Inventory - %s\n", printedInventory);
    }
    
    if (player.getEquippedWeapon() == null)
      System.out.println("- Equipped  - none");
    else
      System.out.printf("- Equipped  - %s\n", player.getEquippedWeapon());
  }
  
  public void printUserSelect(Player player) {
    System.out.printf("""
            
            %s[h] for help - current room%s [%s]
            enter choice:\040""",
        ANSI_YELLOW, ANSI_RESET,
        player.getCurrentRoom().getName());
  }
  
  public void printRoomDescription(Room room, String preamble) {
    StringBuilder printItems = new StringBuilder();
    StringBuilder printEnemies = new StringBuilder();
    ArrayList<Item> items = room.getItems();
    ArrayList<Enemy> enemies = room.getEnemies();
    
    // preamble is just some introduction text
    System.out.printf("- %s [%s] \"%s\"\n",
        preamble,
        room.getName(),
        room.getDescription());
    
    if (room.getItems().isEmpty())
      System.out.println("- no items in room");
    else {
      // pretty print items
      for (int i = 0; i < items.size() - 1; i++)
        printItems.append(items.get(i) + ", ");
      printItems.append(items.get(items.size() - 1));
      System.out.printf("- items   - %s\n", printItems);
    }
    if (!room.getEnemies().isEmpty()) {
      // pretty print enemies
      for (int i = 0; i < enemies.size() - 1; i++)
        printEnemies.append(enemies.get(i) + ", ");
      printEnemies.append(enemies.get(enemies.size() - 1));
      System.out.printf("- enemies - %s\n", printEnemies);
    }
    
  }
  
  public String[] getUserCommandElements() {
    String userInput = in.nextLine().trim().toLowerCase();
    String[] userActions = userInput.split(" ", 2);
    
    if (userActions.length == 1)
      return new String[]{userActions[0], null};
    
    return userActions;
  }
  
  public void printItemNotSpecified() {
    System.out.println("- you need to specify an object by name to take that action");
  }
  
  public void printRoomItemNotFound(String itemName) {
    System.out.printf("- There is nothing like %s to take around here\n", itemName);
  }
  
  public void printInventoryItemNotFound(String itemName) {
    System.out.printf("- There is nothing like %s in your inventory\n", itemName);
  }
  
  public void printAddInventory() {
    System.out.println("- Added item to inventory");
  }
  
  public void printRemoveInventory() {
    System.out.println("- Removed item from inventory");
  }
  
  public void printInvalidUserInput() {
    System.out.println("- invalid user input");
  }
  
  public void printInvalidDirection() {
    System.out.println("- You cannot go that way [no room entry in that direction]");
  }
  
  public void printGameOver() {
    System.out.println("- you died");
  }
  
}
