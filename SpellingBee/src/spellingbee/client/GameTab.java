package spellingbee.client;

import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import spellingbee.network.Client;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * 
 * 
 * @author Jamin Huang
 */

public class GameTab extends Tab{
	private Client client;
	
	private TextField score;
	/**
	 * GameTab constructor
	 * @param Client o is given to GameTab
	 */
	public GameTab(Client o) {
		super("Game");
		
		client=o;
		this.setContent(box());
	}
	/**
	 * Returns a VBox that sets up all the buttons, textfields which will end up as the gametab
	 * @return VBox
	 */
	public VBox box() {
		VBox vbox=new VBox();
		HBox buttons=new HBox();
		HBox info=new HBox();
		TextField msg=new TextField("Start!");
		msg.setPrefWidth(400);
		score=new TextField("0");
		score.setPrefWidth(80);
		HBox textaction=new HBox();
		TextField answer=new TextField();
		actionButton astatics=new actionButton(answer,msg,score,client);
		answer.setOnAction(astatics);
		
		Button submit=new Button("Submit");
		actionButton asubmit=new actionButton(submit);
		submit.setOnAction(asubmit);
		Button delete=new Button("Delete");
		actionButton adelete=new actionButton(delete);
		delete.setOnAction(adelete);
		Button clear=new Button("Clear");
		actionButton aclear=new actionButton(clear);
		clear.setOnAction(aclear);
		
		String letters=client.sendAndWaitMessage("getAllExceptCenter");
		String center=client.sendAndWaitMessage("getCenter");
			
		for(int i=0;i<letters.length();i++) {
			Button b=new Button(""+letters.charAt(i));
			actionButton ab=new actionButton(b);
			b.setOnAction(ab);
			buttons.getChildren().add(b);
		}
		Button bcenter=new Button(center);
		bcenter.setTextFill(Color.RED);
		actionButton ab=new actionButton(bcenter);
		bcenter.setOnAction(ab);
		buttons.getChildren().add(bcenter);
		
		textaction.getChildren().addAll(delete,clear,submit);
		info.getChildren().addAll(msg,score);
		vbox.getChildren().addAll(buttons,answer,textaction,info);
				
		return vbox;
	}
	
	public TextField getScoreField() {
		return score;
	}
}
