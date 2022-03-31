package adventure;

public class Map {
  private Room startRoom;
  
  public Map() {
    createMap();
  }
  
  public Room getStartRoom() {
    return startRoom;
  }
  
  public void createMap() {
    // Create rooms
    Room r1 = new Room("r1 Forest entrance", " you are at the entrance to the forest, the green trees bit you welcome");
    Room r2 = new Room("r2 The green forest", "You continue through the entrance to the green forest, and are now deep inside the green forest");
    Room r3 = new Room("r3 the outer part of the green forest", "The green forest is beginning to look a litte black, and you starts to wonder, what is ahead?");
    Room r4 = new Room("r4 the entrance to the black forest", "You have just stepped through the entrance to the black forest, everything is black and dark. It is hard to see anything...");
    Room r5 = new Room("r5 Welcome to the Treasure Room", "It's bright and large room with granit walls");
    Room r6 = new Room("r6 Outer part of the black forest", "As you continue you notice that there is not as many black trees as there used to be. you something in the distant...");
    Room r7 = new Room("r7 Entrance to the swamp", "You have just left the Black forest and are now and the entrance to the swamp, the smell is weird and you begin to feel a little anxious.");
    Room r8 = new Room("r8 Deep swamp", "You have walked inside the swamp for a bit, the strong smell keeps increasing and you are starting to feel a little lightweight");
    Room r9 = new Room("r9 Dark cliffside", "you are hearing noises all around you, but can you can't tell which direction they are coming from.");
    startRoom = r1;
    
    // Connect rooms (both ways)
    // Rows connect  (west to east iteration)
    r1.setEast(r2);
    r2.setEast(r3);
    r7.setEast(r8);
    r8.setEast(r9);
    // Cols connect  (north to south iteration)
    r1.setSouth(r4);
    r4.setSouth(r7);
    r5.setSouth(r8);
    r3.setSouth(r6);
    r6.setSouth(r9);
    
    
    // create items
    Item item1 = new Item("GOLD NUGGET", "gold oy vay");
    Item item2 = new Item("KEY", "rusty and old");
    Item item3 = new Item("KEY", "rusty and old");
    
    // create food
    Item food1 = new Food("PIE", "Strawberry pie", 10);
    Item food2 = new Food("CHOCOLATE", "Chocolate Bar", 15);
    
    // create weapons
    Item weapon1 = new RangedWeapon("PISTOL", "GLOCK", 5, 8);
    Item weapon2 = new RangedWeapon("BOW", "LONG BOW", 8, 4);
    Item weapon3 = new MeleeWeapon("DAGGER", "sharp and short", 3);
    
    // Create enemies and equip weapon
    Enemy enemy1 = new Enemy(3, "SNAKEMAN", "he is a snake and a man", new RangedWeapon("PEA GUN", "pew pew", 2, 20));
    Enemy enemy2 = new Enemy(3, "WILL", "Will Smith", new RangedWeapon("HAND", "the slapping hand", 25, 6));
    Enemy enemy3 = new Enemy(3, "MANLET", "only 5 feet 9 inches");
    
    // Add entities
    r1.addItem(item1);
    r1.addItem(item2);
    r1.addItem(item3);
    r1.addItem(food1);
    r1.addItem(food2);
    r1.addItem(weapon1);
    r1.addItem(weapon2);
    r1.addItem(weapon3);
    r1.addEnemy(enemy1);
    r1.addEnemy(enemy2);
    r1.addEnemy(enemy3);
    
  }
  
}
