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

public class SpellingBeeClient extends Application {
	
	//private Client client = new Client();
	
	public static void main(String[] args) {
        launch(args);
    }
	
	public void start(Stage stage) {
		
		TabPane tabPane = new TabPane();
		// Trevor (Obi-Wan) tab
        Tab score = new Tab("Score", new Label("Show all score related values"));
        tabPane.getTabs().add(score);
        
        VBox vBox = new VBox(tabPane);

        // scene is associated with container, dimensions
      	Scene scene = new Scene(vBox, 1000, 300); 
      	scene.setFill(Color.BLACK);
        
        stage.setScene(scene);
        stage.setTitle("Spelling Bee");

        stage.show();
	}
}
