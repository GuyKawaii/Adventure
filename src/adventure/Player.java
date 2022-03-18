package adventure;

import java.util.ArrayList;

public class Player {
    private Room            currentRoom;
    private ArrayList<Item> inventory;

    public Player(Room startRoom) {
        currentRoom = startRoom;
        inventory = new ArrayList<>();
    }

    public boolean movement(Direction direction) {
        // returns boolean if player can move
        Room nextRoom;

        switch (direction) {
            case NORTH -> nextRoom = currentRoom.getNorth();
            case SOUTH -> nextRoom = currentRoom.getSouth();
            case EAST -> nextRoom = currentRoom.getEast();
            case WEST -> nextRoom = currentRoom.getWest();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

        if (nextRoom == null)
            return false;

        currentRoom = nextRoom;
        return true;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void takeItem(Item item) {
        getCurrentRoom().removeItem(item);
        inventory.add(item);
    }

    public void dropItem(Item item) {
        inventory.remove(item);
        getCurrentRoom().addItem(item);
    }

}
