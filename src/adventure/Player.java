package adventure;

import java.util.ArrayList;

import static adventure.Action.*;

public class Player {
  private int healthPoints;
  private Room currentRoom;
  private ArrayList<Item> inventory;
  private Weapon equippedWeapon;
  
  public Player(Room startRoom) {
    currentRoom = startRoom;
    inventory = new ArrayList<>();
    healthPoints = 100;
  }
  
  public boolean movement(Direction direction) {
    // returns boolean if player can move
    Room nextRoom;
    
    switch (direction) {
      case NORTH -> nextRoom = currentRoom.getNorth();
      case SOUTH -> nextRoom = currentRoom.getSouth();
      case EAST -> nextRoom = currentRoom.getEast();
      case WEST -> nextRoom = currentRoom.getWest();
      default -> throw new IllegalStateException("Unexpected value: " + direction);
    }
    
    if (nextRoom == null)
      return false;
    
    currentRoom = nextRoom;
    return true;
  }
  
  public Action equip(String weaponName) {
    Item item;
    boolean itemInsideInventory = true;
    
    // find item in inventory
    item = findItem(weaponName);
    // find item in current room
    if (item == null) {
      item = currentRoom.findItem(weaponName);
      itemInsideInventory = false;
    }
    
    // Item checks
    if (item instanceof Weapon) {
      
      // swap old and new equipped weapon
      if (equippedWeapon != null)
        inventory.add(equippedWeapon);
      equippedWeapon = (Weapon) item;
      
      // remove item from location
      if (itemInsideInventory) inventory.remove(item);
      else currentRoom.removeItem(item);
      
      return EQUIP_ITEM;
      
    } else if (item == null)
      return CANT_FIND_ITEM;
    else // not weapon item
      return CANT_EQUIP_ITEM;
  }
  
  public Action eat(String foodName) {
    // Gives HP and removes item after use
    Item item;
    boolean itemInsideInventory = true;
    
    // find item in inventory
    item = findItem(foodName);
    // find item in current room
    if (item == null) {
      item = currentRoom.findItem(foodName);
      itemInsideInventory = false;
    }
    
    // Item checks
    if (item instanceof Food) {
      
      // remove item from location
      if (itemInsideInventory) inventory.remove(item);
      else currentRoom.removeItem(item);
      
      addHealthPoints(((Food) item).getHealthPoints());
      return EAT_ITEM;
      
    } else if (item == null)
      return CANT_FIND_ITEM;
    else // not food item
      return CANT_EAT_ITEM;
  }
  
  public void addItem(Item item) {
    inventory.add(item);
  }
  
  public void removeItem(Item item) {
    inventory.remove(item);
  }
  
  public ArrayList<Item> getInventory() {
    return inventory;
  }
  
  public void setEquippedWeapon(Weapon weapon) {
    this.equippedWeapon = weapon;
  }
  
  public void takeItem(Item item) {
    currentRoom.removeItem(item);
    addItem(item);
  }
  
  public void dropItem(Item item) {
    removeItem(item);
    currentRoom.addItem(item);
  }
  
  public Item findItem(String itemName) {
    // find item instance by name
    
    for (Item item : inventory) {
      String name = item.getName();
      String description = item.getDescription();
      // find item by "name" or "description" or "name description"
      if (itemName.equalsIgnoreCase(name) ||
          itemName.equalsIgnoreCase(description) ||
          itemName.equalsIgnoreCase(name + " " + description)) {
        return item;
      }
    }
    
    return null;
  }
  
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }
  
  public int getHealthPoints() {
    return healthPoints;
  }
  
  public void addHealthPoints(int points) {
    if (healthPoints + points < 0) healthPoints = 0;
    else healthPoints += points;
  }
  
  public Room getCurrentRoom() {
    return currentRoom;
  }

//    public Item takeItem(String itemName) {
//    // removes item from player and returns it
//    for (Item item : inventory) {
//      if (item.getName().equalsIgnoreCase(itemName) || item.getLongName().equalsIgnoreCase(itemName)) {
//        inventory.remove(item);
//        return item;
//      }
//    }
//
//    return null;
//  }
}
