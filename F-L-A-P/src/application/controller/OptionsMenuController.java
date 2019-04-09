/**
 * OptionsMenuController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 */
package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class OptionsMenuController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
//Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
//root.setId("mainPane");
//Scene scene = new Scene(root, 800, 800);
//scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
//Main.stage.setScene(scene);
//Main.stage.show();