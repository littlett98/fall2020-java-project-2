package spellingbee.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Scanner;

import spellingbee.client.ISpellingBeeGame;

public class SpellingBeeGame implements ISpellingBeeGame {

	private String letters;
	private char middleLetter;
	private int currentScore;
	private HashSet<String> wordsFound = new HashSet<String>();
	private static HashSet<String> possibleWords = new HashSet<String>();
	
	public void createWordsFromFile(String path) throws FileNotFoundException {
		File dictionary = new File(path);
		Scanner input = new Scanner(dictionary);
		
		while (input.hasNext()) {
			possibleWords.add(input.nextLine());
		}
	}
	
	@Override
	public int getPointsForWord(String attemt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMessage(String attempt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllLetters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char getCenterLetter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getBrackets() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
