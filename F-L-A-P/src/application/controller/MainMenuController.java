package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MainMenuController implements EventHandler<ActionEvent>{
	
	@FXML
	ImageView background = new ImageView();
	
	//Play Button method
	public void playButton(ActionEvent event) {
		
	}
	//Options button method
	public void optionsButton(ActionEvent event) {
		
	}
	//Quit Button method
	@Override
	public void handle(ActionEvent event) {
		System.exit(0);
	}
}
