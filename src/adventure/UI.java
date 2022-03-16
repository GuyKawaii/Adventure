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
				- [n] or [north] or [go north] to go north
				- [s] or [south] or [go south] to go south
				- [w] or [west]  or [go west]  to go west
				- [e] or [east]  or [go east]  to go east
				
				Other actions:
				- [l] or [look] to look around you
				- [h] or [help] to print help screen
				- [exit]        to exit game""");
	}
	

	
	
	
}
