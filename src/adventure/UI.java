package adventure;

import java.util.Scanner;

import static adventure.Action.*;
import static adventure.Color.*;

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
    System.out.printf("Your health: %s [%s\u2764HP%s]\n", healthPoints, ANSI_RED, ANSI_RESET);
    
    if (healthPoints <= 33)
      System.out.println("- Your health is critical");
    else if (healthPoints <= 66)
      System.out.println("- You have taken som damage, watch your health-points!");
    else if (healthPoints <= 99)
      System.out.println("- You have taken minimal damage");
    else System.out.println("- You have taken no damage");
  }
  
  public void printAttack(Player player) {
    if (player.getEquippedWeapon() == null)
      System.out.println("you have not equipped an weapon, you are attacking with your bare hands, you deal less damage");
    else if (player.getEquippedWeapon().canAttack())
      System.out.printf("you have attacked the enemy with %s, deal %s damage", player.getEquippedWeapon(), player.getEquippedWeapon().attack());
    else
      System.out.println("you have no ammunition left, you cant attack");
  }
  
  public void printEat(Player player, String itemName, Item item, Action action) {
    if (action == EAT_ITEM) {
      System.out.printf("""
          - you ate: %s
          - Your health is now: %s [%s\u2764HP%s]
          """, ((Food) item), player.getHealthPoints(), ANSI_RED, ANSI_RESET);
      
    } else if (action == CANT_EAT_ITEM) {
      System.out.printf("You cannot eat: %s, as that is not food\n", itemName);
      
    } else { // Action.CANT_FIND_ITEM
      System.out.println("Could not find Item");
    }
  }
  
  public void printEquip(Item weapon, String itemName, Action action) {
    switch (action) {
      case EQUIP_ITEM -> System.out.printf("you have equipped: %s\n", weapon);
      case CANT_EQUIP_ITEM -> System.out.printf("You cannot equip %s, as that is not a weapon\n", weapon);
      default -> System.out.printf("Could not find %s\n", itemName);
    }
  }
  
  
  public void printInventory(Player player) {
    if (player.getInventory().isEmpty())
      System.out.println("- Inventory is empty");
    else System.out.printf("- Inventory: %s\n", player.getInventory());
    
    if (player.getEquippedWeapon() == null)
      System.out.println("- No item equipped");
    else
      System.out.printf("- Equipped weapon: %s\n", player.getEquippedWeapon());
    
  }
  
  public void printUserSelect(Player player) {
    System.out.printf("""
            
            [h] for help - current room [%s]
            enter choice:\040""",
        player.getCurrentRoom().getName());
  }
  
  public void printRoomDescription(Room room, String preamble) {
    // preamble is just some introduction text
    System.out.printf("- %s [%s] \"%s\"\n",
        preamble,
        room.getName(),
        room.getDescription());
    
    if (room.getItems().isEmpty())
      System.out.println("- no items in room");
    else
      System.out.printf("- the room has items - %s\n", room.getItems());
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
    System.out.println("invalid user input");
  }
  
  public void printInvalidDirection() {
    System.out.println("- You cannot go that way [no room entry in that direction]");
  }
  
}
