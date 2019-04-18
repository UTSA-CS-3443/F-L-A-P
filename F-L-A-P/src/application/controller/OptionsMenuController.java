package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * OptionsMenuController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 */
public class OptionsMenuController {
	/**
	 * @param event Back button pressed
	 */
	public void back(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Main.optionParentMenuFXML));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource(Main.optionParentMenuCSS).toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
