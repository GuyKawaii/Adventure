package adventure;

import static adventure.Color.*;

public abstract class Weapon extends Item {
  int damage;
  
  public Weapon(String name, String longName, int damage) {
    super(name, longName);
    this.damage = damage;
  }
  
  public int attack() {
    return 0;
  }
  
  
  public boolean canAttack() {
    return true;
  }
  
  @Override
  public String toString() {
    return String.format("%s%s%s: %s %s(%s DMG)%s", ANSI_RED, getName(), ANSI_RESET, getDescription(), ANSI_YELLOW, damage, ANSI_RESET);
  }
}
