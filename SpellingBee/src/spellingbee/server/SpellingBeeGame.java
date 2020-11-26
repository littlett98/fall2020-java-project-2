package spellingbee.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import spellingbee.client.ISpellingBeeGame;

public class SpellingBeeGame implements ISpellingBeeGame {

	private String allLetters;
	private char centerLetter;
	private int score;
	private HashSet<String> wordsFound = new HashSet<String>();
	private static HashSet<String> possibleWords = createWordsFromFile("\\datafiles\\english.txt");
	
	public SpellingBeeGame() {
		
	}
	
	public SpellingBeeGame(String letters) {
		this.allLetters = letters;
		
	}
	
	public static HashSet<String> createWordsFromFile(String path) {
		HashSet<String> allWords = new HashSet<String>();
		try {
			File dictionary = new File(path);
			Scanner input = new Scanner(dictionary);
			while (input.hasNext()) {
				allWords.add(input.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return allWords;
	}
	
	@Override
	public int getPointsForWord(String attempt) {
		int wordLength = attempt.length();
		if (wordLength == 4) {
			return 1;
		}
		else if (wordLength > 4) {
			return attempt.length();
		}
		else if (checkAttemptContainsAllLetters(attempt)) {
			return 7 + wordLength;
		}
		return 0;
	}

	public boolean checkAttemptContainsAllLetters(String attempt) {
		if (attempt.length() < allLetters.length()) {
			return false;
		}
		for (int i = 0; i < attempt.length(); i++) {
			if (allLetters.indexOf(attempt.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String getMessage(String attempt) {
		if (attempt.length() < 4) {
			return "Input word is too short";
		}
		for (String word: possibleWords) {
			if (attempt.indexOf(centerLetter) == -1) {
				return "Rejected because the center letter isn't included in the input word";
			}
			else if (word.equals(attempt)) {
				return "Good, word exists";
			}
		}
		return "Something broke for: " + attempt;
	}

	@Override
	public String getAllLetters() {
		return allLetters;
	}

	@Override
	public char getCenterLetter() {
		return centerLetter;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int[] getBrackets() {
		int maxPoints = 0;
		for (String word: possibleWords) {
			maxPoints += getPointsForWord(word);
		}
		int[] brackets = new int[5];
		brackets[0] = (int) (maxPoints * 0.25);
		brackets[1] = (int) (maxPoints * 0.50);
		brackets[2] = (int) (maxPoints * 0.75);
		brackets[3] = (int) (maxPoints * 0.90);
		brackets[4] = maxPoints;
		return brackets;
	}
	
}
