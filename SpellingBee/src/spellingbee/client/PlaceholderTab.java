package spellingbee.client;

import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import spellingbee.network.Client;

public class PlaceholderTab extends Tab{
	
	private Client client;
	private TextField tf;
	
	public PlaceholderTab(Client c) {
		super("Test");
		this.client = c;
		tf = new TextField("Score Field");
		this.setContent(makeGridPane());
	}
	
	public GridPane makeGridPane() {
		GridPane gp = new GridPane();
		
		tf.setText("Hello");
		gp.getChildren().add(tf);
		
		return gp;
	}
	
	public TextField getScoreField() {
		return tf;
	}
}
