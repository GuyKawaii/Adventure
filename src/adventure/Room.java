package adventure;

import java.util.ArrayList;

public class Room {
  private ArrayList<Item> items;
  private ArrayList<Enemy> enemies;
  private String name;
  private String description;
  
  // rooms relative to given room
  private Room north;
  private Room south;
  private Room east;
  private Room west;
  
  Room(String name, String description) {
    setName(name);
    setDescription(description);
    setNorth(null);
    setSouth(null);
    setEast(null);
    setWest(null);
    items = new ArrayList<>();
    enemies = new ArrayList<>();
  }
  
  public Enemy findEnemy(String enemyName) {
    // find enemy instance by name
    if (enemyName != null) {
  
      for (Enemy enemy : enemies) {
        String name = enemy.getName();
        String description = enemy.getDescription();
        // find item by "name" or "description" or "name description"
        if (enemyName.equalsIgnoreCase(name) ||
            enemyName.equalsIgnoreCase(description) ||
            enemyName.equalsIgnoreCase(name + " " + description)) {
          return enemy;
        }
      }
    }
    
    return null;
  }
  
  public Item findItem(String itemName) {
    // find item instance by name
    if (itemName != null) {
      
      for (Item item : items) {
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
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public ArrayList<Item> getItems() {
    return items;
  }
  
  public void addItem(Item item) {
    items.add(item);
  }
  
  public void removeItem(Item item) {
    items.remove(item);
  }
  
  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }
  
  public void addEnemy(Enemy enemy) {
    // let enemy know what room it is in
    enemies.add(enemy);
    enemy.setCurrentRoom(this);
  }
  
  public void setNorth(Room north) {
    this.north = north;
    if (north != null)
      north.south = this;
  }
  
  public void setSouth(Room south) {
    this.south = south;
    if (south != null)
      south.north = this;
  }
  
  public void setEast(Room east) {
    this.east = east;
    if (east != null)
      east.west = this;
  }
  
  public void setWest(Room west) {
    this.west = west;
    if (west != null)
      west.east = this;
  }
  
  public Room getNorth() {
    return north;
  }
  
  public Room getSouth() {
    return south;
  }
  
  public Room getEast() {
    return east;
  }
  
  public Room getWest() {
    return west;
  }

//  public Item takeItem(String itemName) {
//    // removes item from room and returns it
//    for (Item item : items) {
//      if (item.getName().equalsIgnoreCase(itemName) || item.getLongName().equalsIgnoreCase(itemName)) {
//        items.remove(item);
//        return item;
//      }
//    }
//
//    return null;
//  }
}
