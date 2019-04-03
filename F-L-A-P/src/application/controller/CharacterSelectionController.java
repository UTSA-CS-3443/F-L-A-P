/**
 * CharacterSelectionController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 */
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CharacterSelectionController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void mainMenu(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
			Main.stage.setScene(new Scene(root, 800,800));
			Main.stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void options(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/OptionsMenu.fxml"));
			Main.stage.setScene(new Scene(root, 800,800));
			Main.stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void play(ActionEvent e) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/GameView.fxml"));
			Main.stage.setScene(new Scene(root, 800,800));
			Main.stage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
