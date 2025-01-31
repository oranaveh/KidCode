
package kidCode;

import java.io.IOException;
import java.util.*;

public class PlayScreenMGR {
	// object that manages players information
	static PlayersMGR player = new PlayersMGR();
	
	// object that manages the maze
	static MazeMGR maze = new MazeMGR();
	
	// object that manages the code sequence and command bank
	static CmdMGR userCode = new CmdMGR();
	
	// manages users input
	static Scanner input = new Scanner(System.in);
	
	// asks player to enter name or select a player
	public static void welcomeScreen() {
		Set<String> names= new HashSet<String>();
		names = player.getNamesSet();
		System.out.println("Select a player:");
		Iterator<String> i = names.iterator();
		while(i.hasNext()) {
			System.out.println(" "+ i.next());
		}
		System.out.println();
		System.out.println("Or type in a new name:");
		String name = input.nextLine();
		if(!player.addPlayer(name)) {
			player.selectPlayer(name);			
		}
	}

	// runs users code sequentially 
	public static void runCode() {
		userCode.initCodeRun();
		//
		while (userCode.hasNextCmd() && maze.runCmd(userCode.getNextCmd())) {
			maze.printMaze();
			System.out.println();
			if (maze.isWin()) {
				return;
			}
			//what happens if runcmd returns false because a bad move
		}
		
	}

	// manages the screen with the maze game
	public static void mazeScreen() {
		int numCmd;
		while (true) {
			maze.cloneMaze(player.getPlayerLevel());
			maze.printMaze();
			userCode.setLevel(player.getPlayerLevel());
			userCode.print();
			if (userCode.cmdSeq.size() > 0) {
				System.out.println("Please select the desired action number:");
				System.out.println("  (1) Add a command");
				System.out.println("  (2) Delete a command");
				System.out.println("  (3) Run your code");
				System.out.println("  (4) Exit game");
				int sel = input.nextInt();
				switch (sel) {
				case 1:
					System.out.println(" please select the command number you would like to add");
					numCmd = input.nextInt();
					userCode.addCmd(numCmd - 1);
					break;
				case 2:
					System.out.println(" please select command number to delete");
					numCmd = input.nextInt();
					userCode.delCmd(numCmd);
					break;
				case 3:
					runCode();
					if (maze.isWin()) {
						System.out.println("Excellent Job!!");
						return;
					} else {
						System.out.println("Try again - press <Enter>");

						try {
							System.in.read();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
					break;
				case 4:
					System.out.println("Goodbye :)");
					return;
				}
				// player hasn't entered a command yet
			} else {
				System.out.println(" please select the command number you would like to add");
				numCmd = input.nextInt();
				userCode.addCmd(numCmd - 1);
				userCode.print();
			}
		}
	}
	
	public static void main(String[] args) {
		// running welcome screen 3 times to see if select player works
		for (int i=0; i<3; i++) {
			welcomeScreen();
		}
		mazeScreen();
	}
}