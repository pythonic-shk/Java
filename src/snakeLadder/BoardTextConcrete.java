package snakeLadder;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;




/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 23-Jul-2020.
 */
public class BoardTextConcrete implements BoardFactory {

	private final static int maxBoard = 100;
	private static Text text = Text.get_instance();
	private static BoardTextConcrete bText_instance = null;
	private static BoardSetting bsetting = BoardSetting.get_instance();
	private static BoardPlay bplay = BoardPlay.get_instance();
	private static final Scanner sc = new Scanner(System.in);
	private boolean bFlag;
	
	private BoardTextConcrete() {
		BoardTextConcrete.text.write("Welcome to the Game: Snake And Ladder");
	}
	
	public static BoardTextConcrete get_instance() {
		if (bText_instance == null) 
			bText_instance = new BoardTextConcrete(); 
        return bText_instance; 
	}
	
	@Override
	public void setBoard() {
		
		int input = 0;
		try {
			clearScreen();
			input = begin();
		} catch (IOException exception) {
			exception.printStackTrace();
			text.write("Problem Occured with Input");
			input = 0;
		}
		switch(input) {
		case(1):
			try {
				bsetting.setdefault();
				addUsers();
				break;
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		case(2):
			try {
			addObjects();
			addUsers();	
			break;
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		default:
			text.write("Input Not Correct.");
			getUserInput();
			setBoard();
			break;

		}
		
	}

	@Override
	public void playBoard() {
		try {
			clearScreen();
			text.write("Game Started!");
			resetbFlag();
			outer: while(true) {
			for (User user: bsetting.getUsers()) {
					moveUser(user);
					if(getbFlag()) {
						break outer;
					}
				} 
				
			}
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	private void moveUser(User user) throws IOException {
		clearScreen();
		text.write("Snake Postions ");
		text.write(bsetting.getSnakes());
		text.write("Ladder Postions ");
		text.write(bsetting.getLadders());
		text.write("User " + user.getName());
		text.write("Press r to Roll the Die");
		String val = getUserName();
		if(!val.equals("r")) {
			text.write("Incorrect Key.");
			moveUser(user);
		}
		int result = bplay.roll(user);
		text.write("User " + user.getName() + " Rolled " + String.valueOf(result));
		List<Integer> moveRes = bplay.move(user,result,BoardTextConcrete.maxBoard);
		if (moveRes.get(0) == 0) {
			text.write("The die value is more than the moves left");
			text.write("User " + user.getName() + " current position is " + moveRes.get(1));
		}
		else if(moveRes.get(0) == -1){
			text.write("Oops.. Snake Bite");
			text.write("User " + user.getName() + " current position is " + moveRes.get(1));
		}
		else if(moveRes.get(0) == 2){
			text.write("Yay! Got Ladder");
			text.write("User " + user.getName() + " current position is " + moveRes.get(1));
		}
		else if(moveRes.get(0) == 1){
			text.write("User " + user.getName() + " current position is " + moveRes.get(1));
		}
		if(user.getPos() == 100) {
			text.write("User " + user.getName() + " is the Winner." );
			text.write("The Game has Ended.");
			text.write("Press 1 to Exit. Any other Key to Play Again.");
			if(getUserInput() == 1) {
				System.exit(0);
			}
			else {
				bsetting.reset();
				setbFlag();	
			}
		}
		
		else if(result == 6) {
			text.write("Gets Another Chance to Roll..");
			moveUser(user);
		}
	}
	
	private void clearScreen() throws IOException {
		//Runtime.getRuntime().exec("cls");
	}
	
	private int getUserInput() {
		if(sc.hasNext() && sc.hasNextInt()) {
			int num = sc.nextInt();
			//sc.close();
			return num;
		}
		else {
			sc.next();
			//sc.close();
			return -1;
		}
	}
		
	
	private String getUserName() { 
		if(sc.hasNext()) {
			String string  = sc.next();
			//sc.close();
			return string;
		}
		else
			return "";
	}
	
	private void setbFlag() {
		bFlag = true;
	}
	
	private boolean getbFlag() {
		return bFlag;
	}
	
	private void resetbFlag() {
		bFlag = false;
	}
	
	
	private void setUserName(int i) throws IOException {
		text.write("Enter Name for Player "  + String.valueOf(i+1) + " (3-8 Characters) ");
		if(bsetting.setUsers(getUserName())) {
			clearScreen();
		}
		else {
			clearScreen();
			text.write("Name Already Exists or Character out of range");
			setUserName(i);
		}
	}
	
	private int begin() throws IOException {
		text.write("To Continue, Select an Option Below");
		text.write("1. Play");
		text.write("2. Customize");
		int userInput = getUserInput();
		clearScreen();
		if (userInput != 1 && userInput != 2) {
			text.write("Option Unavailable. Try Again...");
			return begin();
		}
		else
			return userInput; 					
	}
	
	public void addUsers() throws IOException {
		text.write("Number of Players (Min 2)");
		int userInput = getUserInput();
		if (userInput < 2) {
			text.write("Input should be a number greater than 2");
			clearScreen();
			addUsers();
		}
		else {
			for(int i=0;i<userInput;i++) {
				setUserName(i);	
			}
		}
		
	}
	
	public void addObjects() throws IOException {
		text.write("Create Snakes and Ladders by providing postions");
		text.write("5 Snakes and Ladders to be created");
		text.write("You will be prompted to enter postion for Snakes and Ladders Alterntively.");
		text.write("Value should be in the Range (1 - 100)");
		for(int i=0;i<5;i++) {
			setSnakes(String.valueOf(i+1));
			setLadders(String.valueOf(i+1));
			
		}
	}
	
	public void setSnakes(String i) throws IOException {
		text.write(bsetting.getSnakes());
		text.write("Enter HEAD postion for Snake "+ i);
		int headPos = getUserInput();
		text.write("Enter TAIL postion for Snake "+ i);
		int tailPos = getUserInput();
		switch(bsetting.setSnake(headPos, tailPos)) {
		case(-1):
			text.write("Entry Invalid. HEAD postion should to be greater than TAIL position");
			clearScreen();
			setSnakes(i);
		case(-2):
			text.write("Entry Invalid. HEAD position conflicts with existing Snake");
			clearScreen();
			setSnakes(i);
		case(-3):
			text.write("Entry Invalid. Positions conflicts with Existing Ladder");
			clearScreen();
			setSnakes(i);
		case(0):
			text.write("Entry Accepted");
		}	
	}
	
	public void setLadders(String i) throws IOException {
		text.write(bsetting.getLadders());
		text.write("Enter BOTTOM postion for Ladder "+ i);
		int bottomPos = getUserInput();
		text.write("Enter TOP postion for Ladder "+ i);
		int topPos = getUserInput();
		switch(bsetting.setLadder(bottomPos, topPos)) {
		case(-1):
			text.write("Entry Invalid. TOP postion should to be greater than BOTTOM position");
			clearScreen();
			setSnakes(i);
		case(-2):
			text.write("Entry Invalid. BOTTOM position conflicts with existing Ladder");
			clearScreen();
			setSnakes(i);
		case(-3):
			text.write("Entry Invalid. Positions conflicts with Existing Snake");
			clearScreen();
			setSnakes(i);
		case(0):
			text.write("Entry Accepted");
		}
	}

	@Override
	public void game() {
		// TODO Auto-generated method stub.
		while(true) {
			setBoard();
			playBoard();
		}
	}
	

}
