package adventure;

import static adventure.enum_and_color.Color.*;

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
    
    @Override
    public String toString() {
        return String.format("%s%s%s: %s %s(%+d HP)%s", ANSI_GREEN, getName(), ANSI_RESET, getDescription(), ANSI_YELLOW, getHealthPoints(), ANSI_RESET);
    }


}