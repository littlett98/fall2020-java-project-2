package spellingbee.client;

import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import spellingbee.network.Client;
import javafx.stage.*;
import javafx.scene.*;
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
	
	/**
	 * GameTab constructor
	 * @param Client o is given to GameTab
	 */
	public GameTab(Client o) {
		super("Game");
		
		client=o;
		this.setContent(box());
	}
	public VBox box() {
		VBox vbox=new VBox();
		HBox buttons=new HBox();
		Button submit=new Button("Submit");
		TextField answer=new TextField();
		HBox info=new HBox();
		TextField msg=new TextField();
		TextField score=new TextField();
		HBox textaction=new HBox();
		Button delete=new Button("Delete");
		Button clear=new Button("Clear");
		
		
		String letters=client.sendAndWaitMessage("getAllExceptCenter");
		String center=client.sendAndWaitMessage("getCenter");
			
		for(int i=0;i<letters.length()-1;i++) {
			Button b=new Button(""+letters.charAt(i));
			buttons.getChildren().add(b);
		}
		Button bcenter=new Button(center);
		bcenter.setTextFill(Color.RED);
		buttons.getChildren().add(bcenter);
		
		info.getChildren().addAll(msg,score);
		
		vbox.getChildren().addAll(buttons,answer,submit,info);
		
		return vbox;
	}
}
