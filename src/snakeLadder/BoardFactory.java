package snakeLadder;

/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 23-Jul-2020.
 */
public class BoardFactory {
	
	public static BoardInterface modeSelect() {
		
		// pass parameter to this function and switch on it to select different 
		//concrete classes in future.
		return BoardTextConcrete.get_instance();
	}

}
