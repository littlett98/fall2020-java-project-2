package spellingbee.client;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import spellingbee.network.Client;
import spellingbee.network.Server;
import spellingbee.server.*;

/**
 * 
 * @author Trevor McCubbin
 * @author Jamin Huang
 */
public class SpellingBeeClient extends Application {
	
	private Client client = new Client();
	
	public static void main(String[] args) {
        launch(args);
    }
	
	public void start(Stage stage) {
		
		TabPane tabPane = new TabPane();
		ScoreTab score = new ScoreTab(client);
		PlaceholderTab placeholder = new PlaceholderTab(client);
		placeholder.getScoreField().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				score.refresh();
			}
		});
        tabPane.getTabs().add(score);
        tabPane.getTabs().add(placeholder);
        
        VBox vBox = new VBox(tabPane);

        // scene is associated with container, dimensions
      	Scene scene = new Scene(vBox, 500, 300); 
      	scene.setFill(Color.BLACK);
        
        stage.setScene(scene);
        stage.setTitle("Spelling Bee");

        stage.show();
	}
}
