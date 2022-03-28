package adventure;
public class Food extends Item{
    private int healthPoints;

    public Food(String name, String longName, int healthPoints) {
        super(name, longName);
        this.healthPoints = healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


}
