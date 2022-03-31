package adventure;

import static adventure.enum_and_color.Color.*;

public abstract class Weapon extends Item {
  private int damage;
  
  public Weapon(String name, String longName, int damage) {
    super(name, longName);
    setDamage(damage);
  }
  
  public int attack() {
    return 0;
  }
  
  
  public boolean canAttack() {
    return true;
  }
  
  public int getDamage() {
    return damage;
  }
  
  public void setDamage(int damage) {
    this.damage = damage;
  }
  
  @Override
  public String toString() {
    return String.format("%s%s%s: %s %s(%s DMG)%s",
        ANSI_PURPLE, getName(),
        ANSI_RESET, getDescription(),
        ANSI_YELLOW, damage, ANSI_RESET);
  }
}
