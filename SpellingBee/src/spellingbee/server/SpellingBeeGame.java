package spellingbee.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import spellingbee.client.ISpellingBeeGame;
/**
 * An implementation of the SpellingBeeGame interface. Handles all game related features.
 * @author Trevor McCubbin
 */
public class SpellingBeeGame implements ISpellingBeeGame {

	private String allLetters;
	private char centerLetter;
	private int score;
	private HashSet<String> wordsFound = new HashSet<String>();
	private static HashSet<String> possibleWords;
	
	/** 
	 * Constructor method to create a new SpellingBeeGame.
	 * Does not take an input and gets a random String of letters
	 * Sets the center letter to the 4th letter in the allLetters string.
	 * Initializes the game score to 0
	 */
	public SpellingBeeGame() {
		this.allLetters = getRandomLetters();
		possibleWords = createWordsFromFile("datafiles\\english.txt");
		centerLetter = allLetters.charAt(3);
		score = 0;
	}
	
	/** 
	 * Constructor method to create a new SpellingBeeGame.
	 * Sets the center letter to the 4th letter in the allLetters string.
	 * Initializes the game score to 0
	 * @param letters Takes an input of the letters instead getting a random String of letters
	 */
	public SpellingBeeGame(String letters) {
		this.allLetters = letters;
		possibleWords = createWordsFromFile("datafiles\\english.txt");
		centerLetter = allLetters.charAt(3);
		score = 0;
	}
	
	/** 
	 * This method generates a random numbers and fetches that string of letters from
	 * the generated ArrayList of Strings
	 * @return String returns a String of letters that were fetched from a file
	 */
	public String getRandomLetters() {
		Random rand = new Random();
		ArrayList<String> letterCombinations = new ArrayList<String>(createWordsFromFile("datafiles\\letterCombinations.txt"));
		return letterCombinations.get(rand.nextInt(letterCombinations.size()));
	}
	
	/**
	 * This method takes a file and turns all lines in the file into a HashSet of Strings
	 * @param path takes as input the path to the file to get all the lines out of.
	 * @return HashSet<String> of all the values within the given file
	 */
	public static HashSet<String> createWordsFromFile(String path) {
		HashSet<String> lines = null;
		HashSet<String> toBeReturned = new HashSet<String>();
		try {
			Path p = Paths.get(path);
			lines = new HashSet<String>(Files.readAllLines(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String word: lines) {
			if (checkLetterCount(word)) {
				toBeReturned.add(word);
			}
		}
		return toBeReturned;
	}
	
	/**
	 * This checks the amount of different letters within a word.
	 * It returns true if the amount of letters is less than or equal to 7
	 * Otherwise, the word cannot be discovered by SpellingBeeGame using only 7 letters
	 * and makes the max points calculation inaccurate because the user will never be able
	 * to guess words with 8 or more unique letters
	 * @param word String you want to count the amount of letters
	 * @return true or false depending on how many unique letters in a word
	 */
	public static boolean checkLetterCount(String word) {
		int letterCount = 0;
		for (char letter = 'a'; letter <= 'z'; letter++) {
			for (int i = 0; i < word.length(); i++) {
				if (word.toLowerCase().charAt(i) == letter) {
					letterCount++;
					break;
				}
			}
		}
		if (letterCount <= 7) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This method determines how many points a user gets based on their word
	 * @param attempt String of the attempt the user input
	 * @return number of points this attempt gave the user
	 */
	@Override
	public int getPointsForWord(String attempt) {
		int wordLength = attempt.length();
		if (wordLength < 4) {
			return 0;
		}
		// if it isn't a valid word for the letters you get no points
		else if (allowedWord(attempt) == false) {
			return 0;
		}
		else if (wordLength == 4) {
			return 1;
		}
		else if (checkAttemptContainsAllLetters(attempt)) {
			return 7 + wordLength;
		}
		else if (wordLength > 4) {
			return wordLength;
		}
		return 0;
	}
	
	/**
	 * This method tests to see if a word is allowed and only uses letters from the given letters.
	 * @param word the word the user inputs into the game
	 * @return returns true if the word contains only letters from the allowed letters
	 */
	public boolean allowedWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (allLetters.indexOf(word.charAt(i)) == -1) {
				return false;
			}
		}
		wordsFound.add(word);
		return true;
	}
	
	/**
	 * This method checks whether a user used all the letters available at least once in their current attempt
	 * @param attempt String of the attempt the user input
	 * @return a true or false value depending if the attempt used all the letters at least once
	 */
	public boolean checkAttemptContainsAllLetters(String attempt) {
		if (attempt.length() < allLetters.length()) {
			return false;
		}
		for (int i = 0; i < allLetters.length(); i++) {
			// If the current char in allLetters isn't found in the attempt then they didn't use all the letters
			if (attempt.indexOf(allLetters.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gets a message based on the attempt made by the user
	 * If there is an error the method returns the attempt String
	 * @param attempt String of the attempt the user input
	 * @return a good or rejection message
	 */
	@Override
	public String getMessage(String attempt) {
		if (wordsFound.contains(attempt)) {
			return "You have already guessed this word";
		}
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
	
	/**
	 * Gets the value of allLetters variable
	 * @return all letters
	 */
	@Override
	public String getAllLetters() {
		return allLetters;
	}
	
	/**
	 * Gets the value of centerLetter variable
	 * @return the middle letter
	 */
	@Override
	public char getCenterLetter() {
		return centerLetter;
	}
	
	/**
	 * Gets the players current score
	 * @return player's score
	 */
	@Override
	public int getScore() {
		return score;
	}
	
	/**
	 * Calculates the brackets a player can achieve in game based on the number of points they have
	 * @return int[] array of the 5 brackets of points a player can reach
	 */
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
