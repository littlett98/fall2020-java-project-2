package spellingbee.client;

public interface ISpellingBeeGame {
	
	int getPointsForWord(String attemt);
	
	String getMessage(String attemp);
	
	String getAllLetters();
	
	char getCenterLetter();
	
	int getScore();
	
	int[] getBrackets();
	
}
