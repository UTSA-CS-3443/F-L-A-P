package application.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;

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
			((Node) event.getSource()).getScene().getWindow().hide();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}	
}
