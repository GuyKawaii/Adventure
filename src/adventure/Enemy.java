package adventure;

import static adventure.enum_and_color.Color.*;

public class Enemy extends Character {
  private String name;
  private String description;
  
  public Enemy(int healthPoints, String name, String description) {
    super(healthPoints);
    setName(name);
    setDescription(description);
  }
  
  public Enemy(int healthPoints, String name, String description, Weapon weapon) {
    super(healthPoints);
    setName(name);
    setDescription(description);
    setEquippedWeapon(weapon);
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
  
  @Override
  public void takeDamage(int damage) {
    addHealthPoints(-damage);
    
    // If dead drop item and remove this from room
    if (getHealthPoints() == 0) {
      dropEquipment();
      getCurrentRoom().getEnemies().remove(this);
    }
  }
  
  @Override
  public String toString() {
    int damage;
    if (getEquippedWeapon() == null)
      damage = 1;
    else
      damage = getEquippedWeapon().getDamage();
    
    return String.format("%s%s%s: %s %s(%s DMG, %s HP)%s",
        ANSI_RED, getName(),
        ANSI_RESET, getDescription(),
        ANSI_YELLOW, damage, getHealthPoints(), ANSI_RESET);
  }
  

}
