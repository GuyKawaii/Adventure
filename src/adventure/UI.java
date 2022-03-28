package adventure;

import java.util.Scanner;

public class UI {
    Scanner in = new Scanner(System.in);

    void printGameIntro() {
        System.out.print("""
                Welcome to the forest, an adventure awaits!
                - Enter to continue:""");
        in.nextLine();
        printControls();
    }

    void printControls() {
        System.out.println("""
                				
                CONTROLS
                Navigate around using the four cardinal directions:
                - [n] or [north] or [go north] = go north
                - [s] or [south] or [go south] = go south
                - [w] or [west]  or [go west]  = go west
                - [e] or [east]  or [go east]  = go east
                				
                Other actions:
                - [t "item"] or [take "item"] = take item if it's in the current room
                - [d "item"] or [drop "item"] = drop item into the current room
                - [i]        or [inventory]   = display inventory
                - [l]        or [look]        = look around you
                - [h]        or [help]        = print help screen
                - [exit]                      = exit game""");
    }
    public void printHealthPoints(Player player) {
        int healthPoints = player.getHealthPoints();
        System.out.printf("Players HP is %s \u2764 \n", healthPoints);
        if (healthPoints <= 33){
            System.out.println("Your health is critical");
        }
        else if (healthPoints <= 66) {
            System.out.println("You have taken som damage, watch your healthpoints!");
        }
        else if (healthPoints <= 99) {
            System.out.println("You have taken minimal damage");
        }
        else {
            System.out.println("You have taken no damage");
        }
    }

    public void printCanEat(Player player,String foodName, eatAction action) {
        if (action == eatAction.FOOD_ITEM) {
            System.out.printf("""
        you have ate: %s
        Your HealthPoint is now %s
        """,foodName, player.getHealthPoints());
        } else if (action == eatAction.NOT_FOOD){
            System.out.printf("You can not eat %s item \n" ,foodName );
        }
        else {
            System.out.println("Could not find Item");   //(action == eatAction.NOT_ITEM)
        }

    }

    public void printInventory(Player player) {
        if (player.getInventory().isEmpty())
            System.out.println("- Inventory is empty");
        else
            System.out.printf("- Inventory %s\n", player.getInventory());
    }

    public void printUserSelect(Player player) {
        System.out.printf("""
                        						
                        [h] for help - current room [%s]
                        enter choice:\040""",
                player.getCurrentRoom().getName());
    }

    public void printRoomDescription(Room room, String preamble) {
        // preamble is just some introduction text
        System.out.printf("- %s [%s] \"%s\"\n",
                preamble,
                room.getName(),
                room.getDescription());

        if (room.getItems().isEmpty())
            System.out.println("- no items in room");
        else
            System.out.printf("- the room has items - %s\n", room.getItems());
    }

    public String[] getUserCommandElements() {
        String   userInput   = in.nextLine().trim().toLowerCase();
        String[] userActions = userInput.split(" ", 2);

        if (userActions.length == 1)
            return new String[]{userActions[0], null};

        return userActions;
    }

    public void printItemNotSpecified() {
        System.out.println("- you need to specify an object by name to take that action");
    }

    public void printItemNotInRoom(String itemName) {
        System.out.printf("- There is nothing like %s to take around here\n", itemName);
    }

    public void printAddInventory() {
        System.out.println("- Added item to inventory");
    }

    public void printRemoveInventory() {
        System.out.println("- Removed item from inventory");
    }

    public void printInvalidUserInput() {
        System.out.println("invalid user input");
    }

    public void printInvalidDirection() {
        System.out.println("- You cannot go that way [no room entry in that direction]");
    }

}
