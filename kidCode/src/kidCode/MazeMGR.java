package kidCode;

import kidCode.Maze.Cell_e;
import kidCode.Maze.Dir_e;
import kidCode.PlayerInfo.Cmd_e;

public class MazeMGR {
	// indicates current maze number
	int curMazeNum;
	
	// holds current maze state in object Maze
	Maze curMaze;
	
	// initial state of all levels in an array of Maze
	Maze mazesArr[] = new Maze[1];
	
	// constructor that initializes mazesArr
	public MazeMGR() {
		mazesArr[0] = new Maze(3,3);
		mazesArr[0].setCell(0, 0, Cell_e.OBSTACLE);
		mazesArr[0].setCell(0, 1, Cell_e.PLAYER);
		mazesArr[0].setCell(0, 2, Cell_e.EMPTY);
		mazesArr[0].setCell(1, 0, Cell_e.OBSTACLE);
		mazesArr[0].setCell(1, 1, Cell_e.EMPTY);
		mazesArr[0].setCell(1, 2, Cell_e.OBSTACLE);
		mazesArr[0].setCell(2, 0, Cell_e.EMPTY);
		mazesArr[0].setCell(2, 1, Cell_e.GOAL);
		mazesArr[0].setCell(2, 2, Cell_e.OBSTACLE);
		mazesArr[0].setPlayerLoc(0, 1, Dir_e.NORTH);
	}
	
	// initializes curMaze with a copy of the initial state from mazesArr according to level
	public void cloneMaze(int mazeNum) {
		curMazeNum = mazeNum;
		try {
			curMaze = mazesArr [curMazeNum].clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// prints curMaze
	public void printMaze() {
		curMaze.toScreen();
	}
	
	// applies current command on the current maze
	public boolean runCmd (Cmd_e curCmd) {
		switch (curCmd){
		case TURN_R:
			curMaze.turnPlayerR();
			break;
		case TURN_L:
			curMaze.turnPlayerL();
			break;
		case MOVE:
			return curMaze.movePlayer();
		}	
		return true;
	}
	
	// checks if maze was solved correctly
	public boolean isWin() {
		return (curMaze.isWin);
	}
}
