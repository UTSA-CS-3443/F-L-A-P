package application.controller;

import java.io.File;
import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class DeathScreenController implements EventHandler<ActionEvent>{
	
	@FXML
	private Button PlayAgain;

	@FXML
	private Button Quit;

	@FXML
	private Button MainMenu;
	
	private String fxmlPath;
	private String cssPath;

	@Override
	public void handle(ActionEvent event) {
		
		if(event.getSource().equals(MainMenu)) {
			fxmlPath = "../view/MainMenu.fxml";
			cssPath = "../MainMenu.css";
		}else if(event.getSource().equals(PlayAgain)) {
			fxmlPath = "../view/CharacterSelection.fxml";
			cssPath = "../CharacterSelection.css";
		}else if(event.getSource().equals(Quit)) {
			System.exit(0);
		}
		
		File iconFile = new File("src/application/data/images/icon.png");
		Image icon = new Image(iconFile.toURI().toString());
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
			root.setId("background");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("FLAP");
			Main.stage.getIcons().add(icon);
			Main.stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
