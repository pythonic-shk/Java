package snakeLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 21-Jul-2020.
 */
public class BoardPlay {
	
	private BoardSetting board;
	private static BoardPlay board_instance = null;
	private HashMap<String, Dice> user_dice = new HashMap<String, Dice>();
	
	private BoardPlay() {
		 this.board = BoardSetting.get_instance();
	}
	
	public static BoardPlay get_instance() {
		if (board_instance == null) 
			board_instance = new BoardPlay(); 
        return board_instance; 
	}
	
	public List<Integer> move(User user, int value, int max) {
		if(user.getPos() + value > max) {
			return new ArrayList<Integer>(){{
                add(0);
                add(user.getPos());
                  }};
		}
		else {
				int val = this.board.checkHead(user.getPos() + value);
				if (val > -1) {
					user.setPos(val);
					return new ArrayList<Integer>(){{
		                add(-1);
		                add(user.getPos());
		                  }};
				}
				val = this.board.checkBottom(user.getPos() + value);
				if (val > -1) {
					user.setPos(val);
					return new ArrayList<Integer>(){{
		                add(2);
		                add(user.getPos());
		                  }};
			}
				user.setPos(user.getPos() + value);
				return new ArrayList<Integer>(){{
		            add(1);
		            add(user.getPos());
		              }};	
		}
		
	}
	
	public int roll(User user) {
		if (this.user_dice.containsKey(user.getName())) {
			return this.user_dice.get(user.getName()).rollDie();
		}
		else {
			Dice dice = new Dice();
			this.user_dice.put(user.getName(), dice);
			return dice.rollDie();
		}
	}
	

	
}
