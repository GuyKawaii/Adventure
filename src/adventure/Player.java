package adventure;

import java.util.ArrayList;

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
  
  public Room getCurrentRoom() {
    return currentRoom;
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

  public void setEquippedWeapon(Weapon equippedWeapon){
    this.equippedWeapon = equippedWeapon;
  }

  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }

  public void takeItem(Item item) {
    currentRoom.removeItem(item);
    addItem(item);
  }
  
  public Item takeItem(String itemName) {
    // removes item from player and returns it
    for (Item item : inventory) {
      if (item.getName().equalsIgnoreCase(itemName) || item.getLongName().equalsIgnoreCase(itemName)) {
        inventory.remove(item);
        return item;
      }
    }
    
    return null;
  }
  
  public eatAction eat(String foodName) {
    Item itemEat;
    boolean itemEatFromInventory = true;
    
    itemEat = takeItem(foodName); // take from player inventory
    if (itemEat == null) {
      itemEat = currentRoom.takeItem(foodName);
      itemEatFromInventory = false;
    }
    
    // Item checks
    if (itemEat == null) {
      return eatAction.IS_NOT_AN_ITEM;
      
    } else if (itemEat instanceof Food) {
      healthPoints = healthPoints + ((Food) itemEat).getHealthPoints();
      return eatAction.FOOD_ITEM;
    
    } else {
      
      // return non-food items to their original place
      if (itemEatFromInventory)
        inventory.add(itemEat);
      else
        currentRoom.addItem(itemEat);
      
      return eatAction.NOT_A_FOOD_ITEM;
    }
  }
  
  
  public int getHealthPoints() {
    return healthPoints;
  }
  
  public void dropItem(Item item) {
    removeItem(item);
    currentRoom.addItem(item);
  }

  public EquipWeapon equipWeapon(String weaponName) {
    Item itemWeapon;
    boolean itemWeaponFromInventory = true;

    itemWeapon = takeItem(weaponName); // take from player inventory
    if (itemWeapon == null) {
      itemWeapon = currentRoom.takeItem(weaponName);
      itemWeaponFromInventory = false;
    }

    // Item checks
    if (itemWeapon == null) {
      return EquipWeapon.there_isnt_an_item;

    } else if (itemWeapon instanceof Weapon) {
      inventory.add(itemWeapon);
      equippedWeapon = (Weapon) itemWeapon;
      return EquipWeapon.is_equipped;

    } else {

      // return non-food items to their original place
      if (itemWeaponFromInventory)
        inventory.add(itemWeapon);
      else
        currentRoom.addItem(itemWeapon);

      return EquipWeapon.is_not_equipped;
    }
  }
  
}
