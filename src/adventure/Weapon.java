package adventure;

public abstract class Weapon extends Item {
  int damage;
  
  public Weapon(String name, String longName, int damage) {
    super(name, longName);
    this.damage = damage;
  }
  
  public int attack() {
    return 0;
  }
}
