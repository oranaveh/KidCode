package kidCode;

import java.util.HashMap;
import java.util.HashSet;

public class Maze implements Cloneable {
	
	//contains the possible content of each cell of maze
	public enum Cell_e{
		PLAYER,
		GOAL,
		OBSTACLE,
		EMPTY
	}
	
	public enum Dir_e{
		EAST,
		SOUTH,
		WEST,
		NORTH,	
	}
	
	//2d array of type Cell_e
	Cell_e cells[][];
	
	// number of columns
	int maze_width;
	
	// number of rows
	int maze_height;
	
	// location of player in maze
	int playerLocX;
	int playerLocY;
	Dir_e playerDir;
	
	// indicates that PLAYER has reached the GOAL
	boolean isWin;
	
	// defines a mapping of each Cell_e value to a char
	HashMap<Cell_e, String> charMap = new HashMap<Cell_e, String>();

	
	
	
	// constructor that takes maze dimensions as parameters
	public Maze(int x, int y) {
		cells = new Cell_e [x][y];
		maze_width = x;
		maze_height = y;
		charMap.put(Cell_e.EMPTY, " ");
		charMap.put(Cell_e.OBSTACLE, "X");
		charMap.put(Cell_e.PLAYER, ">");
		charMap.put(Cell_e.GOAL, "O");
	}
	
	public void setPlayerDir(Dir_e newDir ) {
		playerDir = newDir;
		switch (playerDir) {
		case EAST: 
			charMap.put(Cell_e.PLAYER, ">");
			break;
		case SOUTH:
			charMap.put(Cell_e.PLAYER, "v");
			break;
		case WEST:
			charMap.put(Cell_e.PLAYER, "<");
			break;
		case NORTH:
			charMap.put(Cell_e.PLAYER, "^");
			break;
		}
	}
	
	// sets player location
	public void setPlayerLoc(int x, int y, Dir_e dir) {
		playerLocX = x;
		playerLocY = y;
		setPlayerDir(dir);
	}
	
	// sets cell content
	public void setCell(int x, int y, Cell_e val) {
		cells [x][y] = val;
	}
	
	// returns cell content
	public Cell_e getCell(int x ,int y) {
		return cells[x][y];
	}
	
	public void turnPlayerR () {
		int dirInt = playerDir.ordinal();
		dirInt = (dirInt +1) % Dir_e.values().length;
		setPlayerDir(Dir_e.values()[dirInt]);
	}
	
	public void turnPlayerL () {
		int dirInt = playerDir.ordinal();
		dirInt = (dirInt + Dir_e.values().length - 1) % Dir_e.values().length;
		setPlayerDir(Dir_e.values()[dirInt]);
	}	
	
	public boolean movePlayer () {
		int newPlayerLocX = playerLocX;
		int newPlayerLocY = playerLocY;
		switch (playerDir) {
		case EAST:
			newPlayerLocX ++;
			break;
		case WEST:
			newPlayerLocX--;
			break;
		case NORTH:
			newPlayerLocY++;
			break;
		case SOUTH:
			newPlayerLocY--;
			break;
		}
		if (newPlayerLocX >= maze_width || newPlayerLocX < 0 || newPlayerLocY >= maze_height || newPlayerLocY < 0) {
			return false;
		}
		if (getCell(newPlayerLocX, newPlayerLocY) == Cell_e.OBSTACLE) {
			return false;
		}
		setCell(playerLocX, playerLocY, Cell_e.EMPTY);
		playerLocX = newPlayerLocX;
		playerLocY = newPlayerLocY;
		
		if (getCell(playerLocX, playerLocY)==Cell_e.GOAL) {
			isWin = true;
		}
		setCell(playerLocX, playerLocY, Cell_e.PLAYER);
		return true;
	}
			
	// creates and returns a new instance of maze that is identical to "this"
	public Maze clone() throws CloneNotSupportedException{
		Maze m = (Maze)super.clone();
		m.cells = new Cell_e [maze_width][maze_height];
		m.maze_width = this.maze_width;
		m.maze_height = this.maze_height;
		for (int x=0; x<cells.length; x++) {
			for (int y=0; y<cells[x].length; y++) {
				m.cells[x][y] = this.cells[x][y];
			}
		}
		m.playerLocX = this.playerLocX;
		m.playerLocY = this.playerLocY;
		m.playerDir = this.playerDir;
		return m;
	}
	
	// prints maze to console
	public void toScreen() {
		char separator = '#';
		
	
		// See example below:
		//    0 1 2 3 4
		// 0  # # # # #
		// 1  # - # - #
		// 2  # # # # #
		// 3  # - # - #
		// 4  # # # # #
		// 
		// in the above example, it has 2 rows in the maze, but we need to print 5 rows: one for the separator 
		// before each data row (rows 0 and 2), and one for the data (rows 1 and 3), and finally a closing separator 
		// row (row 4) - total of 2*mazeHeight+1
		
		for (int row=0; row<1+2*maze_width; row++) {
			for (int col=0; col<1+2*maze_height; col++) {
				if(col%2==1 && row%2==1 ) {//odd row and col has content
					System.out.print(charMap.get(cells[col/2][row/2]));
				}
				else {
					System.out.print(separator);
				}
			}
			System.out.println();
		}
	}
}
