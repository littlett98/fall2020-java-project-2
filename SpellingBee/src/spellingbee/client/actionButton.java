package spellingbee.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import spellingbee.network.Client;

public class actionButton implements EventHandler<ActionEvent>{
	private static TextField answer;
	private static TextField msg;
	private static TextField score;
	private static Client c;
	private Button b;
	@Override
	public void handle(ActionEvent e) {
		if(b.getText().equals("Submit")) {
			msg.setText(c.sendAndWaitMessage("wordCheck "+answer.getText()));
			score.setText(c.sendAndWaitMessage("getScore"));
			answer.setText("");
		}
		else if(b.getText().equals("Clear")) {
			answer.setText("");
		}
		else if(b.getText().equals("Delete")) {
			String s=answer.getText();
			if(s.length()>1) {
				answer.setText(s.substring(0,s.length()-1));
			}
			else {
				answer.setText("");
			}
		}
		else {
			answer.setText(answer.getText()+b.getText());
		}
	}
	public actionButton(Button b) {
		this.b=b;
	}
	public actionButton(TextField answer, TextField msg, TextField score,Client c) {
		actionButton.answer=answer;
		actionButton.msg=msg;
		actionButton.score=score;
		actionButton.c=c;
	}
}