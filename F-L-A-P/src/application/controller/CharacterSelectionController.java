package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * CharacterSelectionController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 */
public class CharacterSelectionController {
	/**
	 * CharacterSelectionController variables
	 */
	@FXML private Button chara;
	@FXML private Button mainMenu;
	@FXML private Button options;
	@FXML private Button play;
	
	@FXML
	public void initialize() {
		chara.setId("character");
		play.setId("play");
	}
	
	/**
	 * @param e Main Menu button pressed
	 */
	public void mainMenu(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
			root.setId("background");
			Scene scene = new Scene(root, 800,800);
			scene.getStylesheets().add(getClass().getResource("../MainMenu.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * @param e Options button pressed
	 */
	public void options(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/OptionsMenu.fxml"));
			Stage stage = new Stage();
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../Options.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * @param e Play button pressed
	 */
	public void play(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/GameView.fxml"));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../GameScreen.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
