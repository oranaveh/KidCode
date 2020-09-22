package kidCode;

public class PlayerInfo {
	// possible values for commands
	public enum Cmd_e {
		MOVE,
		TURN_L,
		TURN_R
	}
	
	// sets players level
	int curLevel = 0;
	
	// 2d array of a list of commands per level
	Cmd_e savedGames[][];
}
