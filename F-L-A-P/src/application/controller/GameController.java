package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Bird;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameController implements EventHandler<KeyEvent>, Initializable{
	/*
	 * Class Variables
	 */
	Bird bird = new Bird();
	
	@FXML
	ImageView birdImage = new ImageView();
	@FXML 
	Pane pane = new Pane();

	@Override
	public void handle( KeyEvent event ) {
		if(event.getCode().equals(KeyCode.SPACE)) {
			bird.jump();
		}

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeGame();
	}
	
	
	/**
	 * initializeGame method.  loads assets onto screen and initializes timers, game loop, and score
	 */
	private void initializeGame() {
		pane.setFocusTraversable(true);	//idk why, but this makes the input work
		/*
		 * initialize the bird image
		 */
		File birdImageFile = new File("data/images/bird.png");
		Image birdImageFromFile = new Image(birdImageFile.toURI().toString());
		birdImage.setImage(birdImageFromFile);
		birdImage.setCache(true);
		birdImage.setFitWidth(bird.getWidth());
		birdImage.setFitHeight(bird.getHeight());
		birdImage.setLayoutX(bird.getxPos());
		birdImage.setLayoutY(bird.getyPos());
		
	}
}
