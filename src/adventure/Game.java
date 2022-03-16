package adventure;

import java.util.Scanner;

public class Game {
    private boolean isPlaying = true;
    //Room currentRoom;
    private String userChoice;
    Scanner in = new Scanner(System.in);
    Map map = new Map();
    UI ui = new UI();
    Player player;

    public Game() {
        //Setup Gamestate
        map.createRooms();
        player = new Player(map.getStartRoom());
    }


    public void selectDirection(Direction direction, Player player) {
        boolean couldMove = player.movement(direction);
        if (couldMove) {
            System.out.println("- You cannot go that way [no room entry in that direction]");

        } else {
            System.out.printf("- Entering [%S] \"%s\"\n",
                    player.getCurrentroom().getName(),
                    player.getCurrentroom().getDescription());
        }
    }

    public void run() {

        // Print game intro info
        System.out.print("""
                Welcome to the forest, an adventure awaits!
                - Enter to continue:""");
        in.nextLine();
        ui.printControls();


        // Main game loop
        do {
            System.out.printf("""
                            							
                            [h] for help - current room [%s]
                            enter choice:\040""",
                    player.getCurrentroom().getName());

            userChoice = in.nextLine().toLowerCase();

            switch (userChoice) {
                // Movement choices
                case "n", "north", "go north" -> selectDirection(Direction.NORTH, player);
                case "s", "south", "go south" -> selectDirection(Direction.SOUTH, player);
                case "w", "west", "go west" -> selectDirection(Direction.WEST, player);
                case "e", "east", "go east" -> selectDirection(Direction.EAST, player);
                // Other actions(also always available)
                case "h", "help" -> ui.printControls();
                case "l", "look" -> {
                    System.out.printf("""
                                    - You are in room [%s] "%s"
                                    """,
                            player.getCurrentroom().getName(),
                            player.getCurrentroom().getDescription());
                }
                case "exit" -> isPlaying = false;
                default -> System.out.println("invalid user input");
            }

        } while (isPlaying);

    }

    public static void main(String[] args) {

    }
}
