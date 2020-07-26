package snakeLadder;

import java.util.Random;

/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 21-Jul-2020.
 */
public class Dice {
	private static Random random = new Random(); 
	
	public int rollDie() {
		return random.nextInt(6) + 1; 	
	}

}
