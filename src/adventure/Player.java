package adventure;

import java.util.ArrayList;

public class Player {
  private int healthPoints;
  private Room            currentRoom;
  private ArrayList<Item> inventory;
  
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
  
  public void takeItem(Item item) {
    currentRoom.removeItem(item);
    addItem(item);
  }

  public eatAction eat (String foodName) {
    Item itemEat;

    itemEat = takeItemByName(foodName);
    if (itemEat == null) {
      itemEat = currentRoom.takeItemByName(foodName);
    }
    if(itemEat == null) {
      return eatAction.NOT_ITEM;
    }
    else if(itemEat instanceof Food) {
      healthPoints = healthPoints + ((Food) itemEat).getHealthPoints();
      return eatAction.FOOD_ITEM;

    }
    return eatAction.NOT_FOOD;


  }
  
  public Item takeItemByName(String itemName) {
    // removes item from player and returns it
    for (Item item : inventory) {
      if (item.getName().equalsIgnoreCase(itemName) || item.getLongName().equalsIgnoreCase(itemName)) {
        inventory.remove(item);
        return item;
      }
    }
    
    return null;
  }

  public int getHealthPoints(){
    return healthPoints;
  }
  
  public void dropItem(Item item) {
    removeItem(item);
    currentRoom.addItem(item);
  }
  
}
