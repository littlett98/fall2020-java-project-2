package spellingbee.client;

import java.util.Arrays;

import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spellingbee.network.Client;

public class ScoreTab extends Tab{
	
	private Client client;
	
	public ScoreTab(Client c) {
		super("Score");
		this.client = c;
		this.setContent(makeGridPane());
	}

	public GridPane makeGridPane() {
		int[] brackets = unpackBrackets(client.sendAndWaitMessage("getBrackets"));
		int currentScore = Integer.parseInt(client.sendAndWaitMessage("getScore"));
		GridPane gp = new GridPane();
		gp.setHgap(10);
		 
		Text queenBee = new Text("Queen Bee");
		Text genius = new Text("Genius");
		Text amazing = new Text("Amazing");
		Text good = new Text("Good");
		Text gettingStarted = new Text("Getting Started");
		Text scoreText = new Text("Current Score");
		queenBee.setFill(Color.GREY);
		genius.setFill(Color.GREY);
		amazing.setFill(Color.GREY);
		good.setFill(Color.GREY);
		gettingStarted.setFill(Color.GREY);
		
		Text maxBracket = new Text("" + brackets[4]);
		Text highBracket = new Text("" + brackets[3]);
		Text middleBracket = new Text("" + brackets[2]);
		Text lowBracket = new Text("" + brackets[1]);
		Text lowestBracket = new Text("" + brackets[0]);
		Text score = new Text("" + currentScore);
		score.setFill(Color.RED);
		
		checkCurrentBracket(currentScore, brackets);
		
		gp.add(queenBee, 0, 0);	gp.add(maxBracket, 1, 0);
		gp.add(genius, 0, 1); gp.add(highBracket, 1, 1);
		gp.add(amazing, 0, 2); gp.add(middleBracket, 1, 2);
		gp.add(good, 0, 3); gp.add(lowBracket, 1, 3);
		gp.add(gettingStarted, 0, 4); gp.add(lowestBracket, 1, 4);
		gp.add(scoreText, 0, 5); gp.add(score, 1, 5);
		
		return gp;
	}
	
	public void checkCurrentBracket(int currentScore, int[] brackets) {
		for (int i = 0; i < brackets.length; i++) {
			if (currentScore > brackets[i]) {
				
			}
		}
	}
	
	public void refresh() {
		int currentScore = Integer.parseInt(client.sendAndWaitMessage("getScore"));
	}
	
	public int[] unpackBrackets(String b) {
		String[] brackets = b.split(" ");
		int[] bracketNums = new int[brackets.length];
		for (int i = 0; i < bracketNums.length; i++) {
			bracketNums[i] = Integer.parseInt(brackets[i]);
		}
		return bracketNums;
	}
}
