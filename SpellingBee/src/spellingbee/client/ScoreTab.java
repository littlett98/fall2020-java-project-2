package spellingbee.client;

import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spellingbee.network.Client;

/**
 * @author Trevor McCubbin
 */
public class ScoreTab extends Tab{
	
	// Global Variables
	private Client client;
	private Text[] bracketsText;
	private Text[] bracketsPoints;
	private Text score;
	
	/**
	 * Constructor that creates this tab, sets the client that the tab will use,
	 * and sets the content of the tab to a gridpane
	 * @param c Client passed down from SpellingBeeClient
	 */
	public ScoreTab(Client c) {
		super("Score");
		this.client = c;
		this.setContent(makeGridPane());
	}

	/**
	 * Sets up all the Text fields for the ScoreTab gridpane
	 * @return GridPane returns the gridpane for the score tab
	 */
	public GridPane makeGridPane() {
		// Gets an int array for the point values of each brackets
		int[] brackets = unpackBrackets(client.sendAndWaitMessage("getBrackets"));
		// Gets the current score from the client
		int currentScore = Integer.parseInt(client.sendAndWaitMessage("getScore"));
		// Sets up the variables used in the GridPane
		bracketsText = new Text[5];
		bracketsPoints = new Text[5];
		GridPane gp = new GridPane();
		gp.setHgap(10);
		
		// Sets up the Text values for the brackets from worst to best. 
		// Ex: the 1st Text value in the array is getting started and the last value with be the highest rank
		bracketsText[4] = new Text("Queen Bee");;
		bracketsText[3] = new Text("Genius");;
		bracketsText[2] = new Text("Amazing");
		bracketsText[1] = new Text("Good");
		bracketsText[0] = new Text("Getting Started");
		
		// Sets the color of each bracket text to Grey since the that should be their default value
		for (int i = 0; i < bracketsText.length; i++) {
			bracketsText[i].setFill(Color.GREY);
		}
		
		// Sets the point value for each bracket.
		// The first entry (bracketPoints[0]) is set to the lowest rank
		// and the last entry (bracketPoints[4] is set to the highest rank
		bracketsPoints[4] = new Text("" + brackets[4]);
		bracketsPoints[3] = new Text("" + brackets[3]);
		bracketsPoints[2] = new Text("" + brackets[2]);
		bracketsPoints[1] = new Text("" + brackets[1]);
		bracketsPoints[0] = new Text("" + brackets[0]);
		
		// Sets all score related value
		Text scoreText = new Text("Current Score");
		score = new Text("" + currentScore);
		score.setFill(Color.RED);
		checkCurrentBracket(currentScore);
		
		// Sets up the positioning of all the values in the gridpane
		// All the values are set up exactly the way they would look when the tab is rendered
		gp.add(bracketsText[4], 0, 0);	gp.add(bracketsPoints[4], 1, 0);
		gp.add(bracketsText[3], 0, 1); gp.add(bracketsPoints[3], 1, 1);
		gp.add(bracketsText[2], 0, 2); gp.add(bracketsPoints[2], 1, 2);
		gp.add(bracketsText[1], 0, 3); gp.add(bracketsPoints[1], 1, 3);
		gp.add(bracketsText[0], 0, 4); gp.add(bracketsPoints[0], 1, 4);
		gp.add(scoreText, 0, 5); gp.add(score, 1, 5);
		
		return gp;
	}
	
	/**
	 * Whenever there is a change to the game, the refresh method should be called.
	 * The refresh method updates the players score and updates the colors of the brackets
	 * based on the players score.
	 */
	public void refresh() {
		int currentScore = Integer.parseInt(client.sendAndWaitMessage("getScore"));
		checkCurrentBracket(currentScore);
		score.setText("" + currentScore);
	}
	
	/**
	 * This method checks if your current score is greater than a bracket.
	 * If your current score is equal or higher than a bracket,
	 * it changes the bracket color to black to signify that you surpassed that bracket.
	 * @param currentScore the players current score
	 */
	public void checkCurrentBracket(int currentScore) {
		for (int i = 0; i < bracketsPoints.length; i++) {
			if (currentScore >= Integer.parseInt(bracketsPoints[i].getText())) {
				bracketsText[i].setFill(Color.BLACK);
			}
		}
	}
	
	/**
	 * This method breaks up the String of brackets returned by the server into individual numbers
	 * and are then stored into an array
	 * @param b the String of brackets returned by the server
	 * @return an int[] array with the bracket values separated so that they can be read easier later on.
	 */
	public int[] unpackBrackets(String b) {
		String[] brackets = b.split(" ");
		int[] bracketNums = new int[brackets.length];
		for (int i = 0; i < bracketNums.length; i++) {
			bracketNums[i] = Integer.parseInt(brackets[i]);
		}
		return bracketNums;
	}
}
