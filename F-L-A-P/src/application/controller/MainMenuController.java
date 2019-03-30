package application.controller;

import application.Main;
import application.model.Statistics;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements EventHandler<ActionEvent> {
	
	@FXML
	ImageView background = new ImageView();
	
	//Play Button method
	public void playButton(ActionEvent event) {
		
	}
	
	//Options button method
	public void optionsButton(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/OptionsMenu.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Statistics button method
	public void statisticsButton(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/StatisticsMenu.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Quit Button method
	@Override
	public void handle(ActionEvent event) {
		System.exit(0);
	}
}
