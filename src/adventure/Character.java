package adventure;

public abstract class Character {
  private int healthPoints;
  private final int maxHealthPoints;
  private Weapon equippedWeapon;
  private Room currentRoom;
  
  public Character(int healthPoints) {
    setHealthPoints(healthPoints);
    this.maxHealthPoints = healthPoints;
    currentRoom = null;
  }
  
  public int getMaxHealthPoints() {
    return maxHealthPoints;
  }
  
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }
  
  public void setEquippedWeapon(Weapon weapon) {
    this.equippedWeapon = weapon;
  }
  
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }
  
  public int getHealthPoints() {
    return healthPoints;
  }
  
  public void addHealthPoints(int points) {
    if (healthPoints + points < 0) healthPoints = 0;
    else if (healthPoints  + points > maxHealthPoints) healthPoints = maxHealthPoints;
    else healthPoints += points;
  }
  
  public void takeDamage(int damage) {
    addHealthPoints(-damage);
  }
  
  public int attack() {
    int damage;
    if (getEquippedWeapon() == null)
      damage = 1;
    else
      damage = getEquippedWeapon().getDamage();
    
    return damage;
  }
  
  public Room getCurrentRoom() {
    return currentRoom;
  }
  
  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }
  
  public void dropEquipment() {
    if (equippedWeapon != null) {
      getCurrentRoom().addItem(getEquippedWeapon());
      setEquippedWeapon(null);
    }
  }
}
