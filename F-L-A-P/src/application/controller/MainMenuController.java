/**
 * MainMenuController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 * Contains methods for the main menu selection handling
 */
package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class MainMenuController implements EventHandler<ActionEvent>{
	
	@FXML
	ImageView background = new ImageView();
	
	//Play Button method
	public void playButton(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/CharacterSelection.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
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
