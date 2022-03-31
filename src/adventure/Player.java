package adventure;

import adventure.enum_and_color.Action;
import adventure.enum_and_color.Direction;

import java.util.ArrayList;

import static adventure.enum_and_color.Action.*;

public class Player extends Character {
  private ArrayList<Item> inventory;
  
  public Player(Room startRoom) {
    super(100);
    setCurrentRoom(startRoom);
    inventory = new ArrayList<>();
  }
  
  public boolean movement(Direction direction) {
    // returns boolean if player can move
    Room nextRoom;
    
    switch (direction) {
      case NORTH -> nextRoom = getCurrentRoom().getNorth();
      case SOUTH -> nextRoom = getCurrentRoom().getSouth();
      case EAST -> nextRoom = getCurrentRoom().getEast();
      case WEST -> nextRoom = getCurrentRoom().getWest();
      default -> throw new IllegalStateException("Unexpected value: " + direction);
    }
    
    if (nextRoom == null)
      return false;
    
    setCurrentRoom(nextRoom);
    return true;
  }
  
  public Action equip(String weaponName) {
    Item item;
    boolean itemInsideInventory = true;
    
    // find item in inventory
    item = findItem(weaponName);
    // find item in current room
    if (item == null) {
      item = getCurrentRoom().findItem(weaponName);
      itemInsideInventory = false;
    }
    
    // Item checks
    if (item instanceof Weapon) {
      
      // swap old and new equipped weapon
      if (getEquippedWeapon() != null)
        inventory.add(getEquippedWeapon());
      setEquippedWeapon((Weapon) item);
      
      // remove item from location
      if (itemInsideInventory) inventory.remove(item);
      else getCurrentRoom().removeItem(item);
      
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
      item = getCurrentRoom().findItem(foodName);
      itemInsideInventory = false;
    }
    
    // Item checks
    if (item instanceof Food) {
      
      // remove item from location
      if (itemInsideInventory) inventory.remove(item);
      else getCurrentRoom().removeItem(item);
      
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
  
  public void takeItem(Item item) {
    getCurrentRoom().removeItem(item);
    addItem(item);
  }
  
  public void dropItem(Item item) {
    removeItem(item);
    getCurrentRoom().addItem(item);
  }
  
  public Item findItem(String itemName) {
    // find item instance by name
    if (itemName != null) {
      
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
    }
    
    return null;
  }
  
  public int attack() {
    if (getEquippedWeapon() != null) return getEquippedWeapon().attack();
    else return 0;
  }
  
  public void takeDamage(int damage) {
    addHealthPoints(-damage);
  }
  
}
