package spellingbee.client;

import java.io.FileNotFoundException;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import spellingbee.network.Client;
import spellingbee.server.*;

public class SpellingBeeClient extends Application {
	
	private Client client = new Client();
	
	public static void main(String[] args) throws FileNotFoundException {
        launch(args);
        SpellingBeeGame sbg = new SpellingBeeGame();
        sbg.createWordsFromFile("C:\\Users\\mccub\\Documents\\420-310 (Java 3)\\fall2020javaproject2\\english.txt");
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
