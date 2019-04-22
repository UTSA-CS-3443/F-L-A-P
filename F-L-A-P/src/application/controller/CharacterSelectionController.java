package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * CharacterSelectionController.java
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 * @author Ivy Vasquez Sandoval (egi444)
 */
public class CharacterSelectionController {
	/**
	 * CharacterSelectionController variables
	 */
	@FXML private RadioButton chara1;
	@FXML private RadioButton chara2;
	@FXML private RadioButton chara3;
	@FXML private RadioButton chara4;
	@FXML private RadioButton chara5;
    @FXML private ToggleGroup charaButtons;
	@FXML private Button mainMenu;
	@FXML private Button options;
	@FXML private Button play;
	@FXML private Label charaPrompt;
	
	@FXML
	public void initialize() {
		chara1.setId("chara1");
		chara2.setId("chara2");
		chara3.setId("chara3");
		chara4.setId("chara4");
		chara5.setId("chara5");
		play.setId("play");
		charaPrompt.setId("prompt");
		
		chara1.getStyleClass().remove("radio-button");
		chara1.getStyleClass().add("toggle-button");		
		chara2.getStyleClass().remove("radio-button");
		chara2.getStyleClass().add("toggle-button");
		chara3.getStyleClass().remove("radio-button");
		chara3.getStyleClass().add("toggle-button");
		chara4.getStyleClass().remove("radio-button");
		chara4.getStyleClass().add("toggle-button");
		chara5.getStyleClass().remove("radio-button");
		chara5.getStyleClass().add("toggle-button");
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
		Main.optionParentMenuFXML = "../view/CharacterSelection.fxml";
		Main.optionParentMenuCSS = "../CharacterSelection.css";
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/OptionsMenu.fxml"));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("../Options.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * @param e Play button pressed
	 */
	public void play(ActionEvent e) {		
		try {
			GameController game;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/GameView.fxml"));
			
			if(charaButtons.getSelectedToggle()==null) 
				return;
			else if(charaButtons.getSelectedToggle().equals(chara1)) 
				game = new GameController("src/application/data/images/bird1.png");
			else if(charaButtons.getSelectedToggle().equals(chara2)) 
				game = new GameController("src/application/data/images/bird2.png");
			else if(charaButtons.getSelectedToggle().equals(chara3)) 
				game = new GameController("src/application/data/images/bird3.png");
			else if(charaButtons.getSelectedToggle().equals(chara4)) 
				game = new GameController("src/application/data/images/bird4.png");
			else if(charaButtons.getSelectedToggle().equals(chara5)) 
				game = new GameController("src/application/data/images/bird5.png");
			else 
				throw new Exception();
			
			loader.setController(game);
			Parent root = loader.load();
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
