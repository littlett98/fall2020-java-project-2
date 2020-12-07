package spellingbee.client;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
		GameTab gameTab = new GameTab(client);
		
		gameTab.getScoreField().textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
		        score.refresh();
		    }
		});
		
        tabPane.getTabs().addAll(score, gameTab);
        
        VBox vBox = new VBox(tabPane);

        // scene is associated with container, dimensions
      	Scene scene = new Scene(vBox, 500, 300); 
      	scene.setFill(Color.BLACK);
        
        stage.setScene(scene);
        stage.setTitle("Spelling Bee");

        stage.show();
	}
}
