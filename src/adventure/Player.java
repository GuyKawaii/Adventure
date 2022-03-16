package adventure;

public class Player {
    Room currentRoom;
    String userChoice;

    public Player(Room room) {
        currentRoom = room;
    }


    public boolean movement(Direction direction) {
        Room nextRoom;

        switch (direction) {
            case NORTH -> nextRoom = currentRoom.getNorth();
            case EAST -> nextRoom = currentRoom.getEast();
            case WEST -> nextRoom = currentRoom.getWest();
            case SOUTH -> nextRoom = currentRoom.getSouth();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

        if (nextRoom == null) {
            return false;
        }
        currentRoom = nextRoom;
        return true;
    }

    public Room getCurrentroom() {
        return currentRoom;
    }

}
