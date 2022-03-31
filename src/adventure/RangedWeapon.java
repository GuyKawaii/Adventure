package adventure;

import static adventure.enum_and_color.Color.*;

public class RangedWeapon extends Weapon {
  int ammunition;
  
  public RangedWeapon(String name, String longName, int damage, int ammunition) {
    super(name, longName, damage);
    this.ammunition = ammunition;
  }
  
  @Override
  public int attack() {
    if (ammunition >= 0) {
      ammunition--;
      return getDamage();
    }
    
    return 0;
  }
  
  @Override
  public boolean canAttack() {
    return ammunition > 0;
  }
  
  @Override
  public String toString() {
    return String.format("%s%s%s: %s %s(%s DMG, %s AMMO)%s",
        ANSI_PURPLE, getName(),
        ANSI_RESET, getDescription(),
        ANSI_YELLOW, getDamage(), ammunition, ANSI_RESET);
  }
}
