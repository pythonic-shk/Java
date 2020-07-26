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
public class BoardSetting {
	
	private List<User> uList;
	private HashMap<Integer, Integer> snakeMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> ladderMap = new HashMap<Integer, Integer>();
	private static BoardSetting bsetting_instance = null;
	
	
	
	private BoardSetting() {
		this.uList = new ArrayList<User>();
	}
	
	public static BoardSetting get_instance() {
		if (bsetting_instance == null) 
			bsetting_instance = new BoardSetting(); 
        return bsetting_instance; 
	}
	
	public int checkHead(int key) {
		if(this.snakeMap.containsKey(key))
			return this.snakeMap.get(key);
		return -1;
	}
	
	public int checkBottom(int key) {
		if(this.ladderMap.containsKey(key))
			return this.ladderMap.get(key);
		return -1;
	}
	
	public String getLadders() {
		StringBuilder mapAsString = new StringBuilder("{");
		mapAsString.append("(BOTTOM, TOP)\n");
	    for (Integer key : this.ladderMap.keySet()) {
	        mapAsString.append("(" + key + ", " + this.ladderMap.get(key) + ") ");
	    }
	    return this.ladderMap.toString();
	}
	
	public String getSnakes() {
		StringBuilder mapAsString = new StringBuilder("{");
		 mapAsString.append("(HEAD, TAIL)\n");
	    for (Integer key : this.snakeMap.keySet()) {
	        mapAsString.append("(" + key + ", " + this.snakeMap.get(key) + ") ");
	    }
	    return this.snakeMap.toString();
	}
	
	public List<User> getUsers() {
		return this.uList;
	}
	
	public void setdefault() {
		this.snakeMap = new HashMap<Integer, Integer>(){
		    {
		        put(98, 64);
		        put(83, 67);
		        put(77, 53);
		        put(68, 41);
		        put(52, 25);
		        put(30, 8);
		    }
		};
		this.ladderMap = new HashMap<Integer, Integer>(){
		    {
		        put(70, 94);
		        put(62, 80);
		        put(57, 71);
		        put(33, 62);
		        put(28, 59);
		        put(15, 45);
		    }
		};
	}
	
	public int setSnake(int head, int tail) {
		if (head <= tail) {
			return -1;
		}
		if(head > 100 || head < 1 || tail > 100 || tail < 1) {
			return -1;
		}
		if (this.snakeMap.containsKey(head))
			return -2;
		if (this.ladderMap.containsKey(head) || this.ladderMap.containsKey(tail)
			|| this.ladderMap.values().contains(head)  ) {
			return -3;
		}
		this.snakeMap.put(head, tail);
		return 0;
	}
	
	public int setLadder(int bottom, int top) {
		if (top <= bottom) {
			return -1;
		}
		if(top > 100 || top < 1 || bottom > 100 || bottom < 1) {
			return -1;
		}
		if (this.snakeMap.containsKey(top) || this.snakeMap.containsKey(bottom)
			|| this.snakeMap.values().contains(bottom)) {
			return -2;
		}
		if (this.ladderMap.containsKey(bottom)) {
			return -3;
		}
		this.ladderMap.put(bottom, top);
		return 0;
	}
	
	public boolean setUsers(String name) {
		
		if (name.length() < 9 && name.length() > 2) {
			for (User user : this.uList) {
				if(user.getName() == name) {
					return false;
				}
			}
			User user = new User();
			user.setName(name);
			this.uList.add(user);
			return true;
		}
		else
			return false;	
	}
	
	public void reset() {
		this.uList.clear();
		this.snakeMap.clear();
		this.ladderMap.clear();
	}
	
}
