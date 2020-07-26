package snakeLadder;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * TODO Put here a description of what this class does.
 *
 * @author SKH.
 *         Created 23-Jul-2020.
 */
public class Text {

	private static final Logger log = Logger.getLogger(BoardSetting.class.getName());
	
	private static Text text_instance = null;
	
	private Text() {
		
		Handler myHandler = new ConsoleHandler();
		myHandler.setFormatter(new MyCustomFormatter());
		log.addHandler(myHandler);
		log.setUseParentHandlers(false);
		
	}
	
	public static Text get_instance() {
		if (text_instance == null) 
			text_instance = new Text(); 
        return text_instance; 
	}
	
	public void write(String msg) {
		log.info(msg);
	}
	
	
	
	private static class MyCustomFormatter extends Formatter {
		 
        @Override
        public String format(LogRecord record) {
            return record.getMessage() + "\r\n";
        }
         
    }
}
