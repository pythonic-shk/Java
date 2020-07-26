package snakeLadder;



/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 23-Jul-2020.
 */
public class Play {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		BoardFactory bFactory = BoardMode.modeSelect();
		bFactory.game();

	}
	

}
