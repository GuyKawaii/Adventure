package adventure;

import java.util.ArrayList;

public class Room {
    private ArrayList<Item> items;
    private String          name;
    private String          description;

    // rooms relative to given room
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    Room(String name, String description) {
        setName(name);
        setDescription(description);
        setNorth(null);
        setSouth(null);
        setEast(null);
        setWest(null);
        items = new ArrayList<>();
    }
    
    public Item takeItem(String itemName) {
        // removes item from room and returns it
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName) || item.getLongName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
        if (north != null)
            north.south = this;
    }

    public void setSouth(Room south) {
        this.south = south;
        if (south != null)
            south.north = this;
    }

    public void setEast(Room east) {
        this.east = east;
        if (east != null)
            east.west = this;
    }

    public void setWest(Room west) {
        this.west = west;
        if (west != null)
            west.east = this;
    }

}
