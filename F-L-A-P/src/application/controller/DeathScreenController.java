package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DeathScreenController{
	
	public void playAgain(ActionEvent e) {
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
	
	/**
	 * @param event Options button clicked
	 */
	public void mainMenu(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../MainMenu.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void quit(ActionEvent event) {
		System.exit(0);		
	}

}
