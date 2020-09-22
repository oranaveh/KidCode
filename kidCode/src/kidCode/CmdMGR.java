package kidCode;

import java.util.List;
import java.util.ArrayList;

import kidCode.PlayerInfo.Cmd_e;

public class CmdMGR {
	
	////////////
	// Fields //
	////////////
	// User's command sequence (to solve maze)
	List<Cmd_e> cmdSeq = new ArrayList<Cmd_e>();
	
	// Pointer to current command in cmdSeq (For run-time)
	int curCmdPtr = 0 ;
	
	// bank of commands for current level, for the user, to add to cmdSeq
	List<Cmd_e> curCmdBank = new ArrayList<Cmd_e>();
	
	// bank of commands per-level
	List<List<Cmd_e>> cmdBanks = new ArrayList<List<Cmd_e>>();
	
	/////////////
	// Methods //
	/////////////
	// Add a new command to the codeSeq from cmdBank
	public void addCmd(int indexInBank) {
		cmdSeq.add(curCmdBank.get(indexInBank));
	}

	// Delete a command from the codeSeq (cmdNum is the index to delete)
	public void delCmd (int cmdNum) {
		cmdSeq.remove(cmdNum);
	}

	// When running the code, get the next command to execute
	public Cmd_e getNextCmd () {
		return cmdSeq.get(curCmdPtr++);
	}

	// Initialize code run
	public void initCodeRun() {
		curCmdPtr = 0;
	}
	
	// Sets curCmdBank from cmdBanks according to level number
	public void setLevel(int levelNum) {
		curCmdBank = cmdBanks.get(levelNum);
	}
	
	//
	public CmdMGR() {
		List<Cmd_e> lvl1 = new ArrayList<Cmd_e>();
		lvl1.add(Cmd_e.MOVE);
		lvl1.add(Cmd_e.TURN_L);
		lvl1.add(Cmd_e.TURN_R);
		cmdBanks.add(lvl1);
	}
	
	public boolean hasNextCmd() {
		return (curCmdPtr < cmdSeq.size());
	}
	
	public void print() {
		System.out.println("Code Sequence:");
		for (int i=0; i<cmdSeq.size(); i++) {
			System.out.println(" "+cmdSeq.get(i));
		}
		System.out.println("Command Bank:");
		for (int i=0; i<curCmdBank.size(); i++) {
			int iPlus1 = i+1;
			System.out.print(iPlus1 + ")" + curCmdBank.get(i)+" ");
		}
		System.out.println();
	}
}
