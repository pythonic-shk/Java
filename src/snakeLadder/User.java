package snakeLadder;

import javax.sound.sampled.LineListener;
import javax.xml.ws.AsyncHandler;

/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 21-Jul-2020.
 */
public class User {
		
		private String name;
		
		private int position;
		
		public User() {
			int position = 1;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setPos(int value) {
			this.position = value;
		}
		
		public int getPos() {
			return this.position;
		}
		
}
