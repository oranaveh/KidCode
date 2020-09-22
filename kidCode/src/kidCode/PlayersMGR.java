
package kidCode;

import java.util.HashMap;
import java.util.Set;

public class PlayersMGR {
	// hash with key: players name and value: players information
	HashMap<String, PlayerInfo> players = new HashMap<String, PlayerInfo>();

	// name of current player
	String curPlayer;
	
	// sets current player to name
	public void selectPlayer(String name) {
		curPlayer=name;
	}
	
	// returns current player's level
	public int getPlayerLevel() {
		return players.get(curPlayer).curLevel;		
	}
	
	// returns false if name exists otherwise adds name to hashmap 
	public boolean addPlayer(String name) {
		if (players.containsKey(name)) {
			
			return false;
		} 
		else {
			PlayerInfo newInfo = new PlayerInfo();
			players.put(name, newInfo);
			selectPlayer(name);
			return true;
		}
	}
	
	// returns a set of names from hashmap
	public Set<String> getNamesSet() {
		return players.keySet();
	}
}
