package adventure;

public class Item {
    private String name;
    private String longName;

    public Item(String name, String longName) {
        setName(name);
        setLongName(longName);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public String toString() {
        return longName;
    }

}
