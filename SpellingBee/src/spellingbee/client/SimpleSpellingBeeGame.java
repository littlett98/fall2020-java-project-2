package spellingbee.client;

public class SimpleSpellingBeeGame implements ISpellingBeeGame{

	@Override
	public int getPointsForWord(String attempt) {
		return 1;
	}

	@Override
	public String getMessage(String attempt) {
		return "pain";
	}

	@Override
	public String getAllLetters() {
		return "abcdefg";
	}

	@Override
	public char getCenterLetter() {
		return 'd';
	}

	@Override
	public int getScore() {
		return 100;
	}

	@Override
	public int[] getBrackets() {
		int[] arr=new int[] {0,1,2,4,7};
		return arr;
	}

	
}
