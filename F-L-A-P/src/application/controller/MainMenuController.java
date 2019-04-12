package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * MainMenuController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 * Contains methods for the main menu selection handling
 */
public class MainMenuController {
	/**
	 * MainMenuController Variables
	 */
	@FXML private Label title;

	@FXML
	public void initialize() {
		title.setId("title");
	}
	
	/**
	 * @param event Play button pressed
	 */
	public void playButton(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/CharacterSelection.fxml"));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../CharacterSelection.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param event Options button clicked
	 */
	public void optionsButton(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/OptionsMenu.fxml"));
			Stage stage = new Stage();
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../Options.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param event Statistics button pressed
	 */
	public void statisticsButton(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/StatisticsMenu.fxml"));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../Statistics.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param event Quit button pressed
	 */
	public void quit(ActionEvent event) {
		System.exit(0);
	}
}
