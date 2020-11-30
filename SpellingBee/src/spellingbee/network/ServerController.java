package spellingbee.network;

import spellingbee.client.ISpellingBeeGame;
import spellingbee.server.*;

/**
 * This class will run on the server side and is used to connect the server code to the backend business code.
 * This class is where the "protocol" will be defined.
 */
public class ServerController {
	// This is the interface you will be creating!
	// Since we are storing the object as an interface, you can use any type of ISpellingBeeGame object.
	// This means you can work with either the SpellingBeeGame OR SimpleSpellingBeeGame objects and can
	// seamlessly change between the two.
	private ISpellingBeeGame spellingBee = new SpellingBeeGame();
	
	/**
	 * Action is the method where the protocol translation takes place.
	 * This method is called every single time that the client sends a request to the server.
	 * It takes as input a String which is the request coming from the client. 
	 * It then does some actions on the server (using the ISpellingBeeGame object)
	 * and returns a String representing the message to send to the client
	 * @author Trevor McCubbin
	 * @param inputLine The String from the client
	 * @return The String to return to the client
	 */
	public String action(String inputLine) {
		String[] args = inputLine.split(" ");
		if (args[0].equals("getAllLetters")) {
			String allLetters = spellingBee.getAllLetters();
			return allLetters;
		}
		else if (args[0].equals("getCenter")) {
			char middle = spellingBee.getCenterLetter();
			String center = "" + middle;
			return center;
		}
		else if (args[0].equals("getAllExceptCenter")) {
			String allLetters = spellingBee.getAllLetters();
			char middle = spellingBee.getCenterLetter();
			String allNotMiddle = "";
			for (int i = 0; i < allLetters.length(); i++) {
				if (allLetters.charAt(i) != middle) {
					allNotMiddle += allLetters.charAt(i);
				}
			}
			return allNotMiddle;
		}
		else if (args[0].equals("getPointsForWord")) {
			int points = spellingBee.getPointsForWord(args[1]);
			String p = "" + points;
			return p;
		}
		else if (args[0].equals("getMessage")) {
			String msg = spellingBee.getMessage(args[1]);
			return msg;
		}
		else if (args[0].equals("getScore")) {
			int score = spellingBee.getScore();
			String s = "" + score;
			return s;
		}
		else if (args[0].equals("getBrackets")) {
			int[] brackets = spellingBee.getBrackets();
			String b = "";
			for (int i = 0; i < brackets.length; i++) {
				if (brackets.length - 1 == i) {
					b += brackets[i]; 
				}
				else {
					b += brackets[i] + " "; 
				}
			}
			// This will return a String with spaces in it so that you can turn it back into an int[] array if you need
			return b;
		}
		else if (args[0].equals("wordCheck")) {
			String msg = spellingBee.getMessage(args[1]);
			int points = spellingBee.getPointsForWord(args[1]);
			String wc = msg + " " + points;
			return wc;
		}
		return "input did nothing";
	}
}
