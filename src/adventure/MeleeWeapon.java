package adventure;

public class MeleeWeapon extends Weapon {
  
  public MeleeWeapon(String name, String longName, int damage) {
    super(name, longName, damage);
  }
  
  @Override
  public int attack() {
    return getDamage();
  }


}
