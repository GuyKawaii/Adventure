package adventure;

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
      return damage;
    }
    
    return 0;
  }
}
