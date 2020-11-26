package spellingbee.client;

public interface ISpellingBeeGame {
	
	int getPointsForWord(String attempt);
	
	String getMessage(String attempt);
	
	String getAllLetters();
	
	char getCenterLetter();
	
	int getScore();
	
	int[] getBrackets();
	
}
