package application.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import application.Main;
import application.model.Bird;
import application.model.Pipe;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * @author Zachary Ellis (ebl533)
 *
 */
public class GameController {
	/**
	 * GameController Variables
	 */
	@FXML private Group root;
	@FXML private ImageView background;
	@FXML private Canvas pipesCanvas;
	@FXML private Canvas birdCanvas;
	@FXML private Label count;
	private Bird bird;
	private ArrayList<Pipe> pipes;
	private GraphicsContext pipeSprite, birdSprite;
	private AnimationTimer gameplay; //for game loop
	private long gameTime; //required for game loop
	
	/**
	 * Constructor for GameController
	 */
	public GameController() {
		bird = new Bird();
		pipes = new ArrayList<Pipe>();
	}

	@FXML
	public void initialize() {
		try {
			Scene scene = Main.stage.getScene(); 
			scene.setOnKeyPressed(e -> { //Adding event listener to scene (Space to jump)
				if (e.getCode().equals(KeyCode.SPACE)) 
					jump();
			});
			Main.stage.setScene(scene);
			Main.stage.show();
			
			background.setId("background");
			initializeGame();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}		
	}
	
	/**
	 * Loads assets onto screen and sets up game
	 * @throws FileNotFoundException Background Image not Found
	 */
	private void initializeGame() throws FileNotFoundException {
		pipeSprite = pipesCanvas.getGraphicsContext2D();
		birdSprite = birdCanvas.getGraphicsContext2D();
		
		initializePipeSet();
		initializeBird();
		
		count.setText(String.valueOf(0));		
	}
	
	/**
	 * Initializes pipe pair
	 */
	private void initializePipeSet() {
		double gap = (double) (new Random()).nextInt(800) + 1;
		Pipe upPipe = new Pipe(true, gap);
		Pipe downPipe = new Pipe(false, gap - 45);
		
		upPipe.setXYVelocity(-.5, 0);
		downPipe.setXYVelocity(-.5, 0);
		
		pipeSprite.drawImage(upPipe.getImage(), upPipe.getXPosition(), upPipe.getYPosition());
		pipeSprite.drawImage(downPipe.getImage(), downPipe.getXPosition(), downPipe.getYPosition());
		
		pipes.addAll(Arrays.asList(upPipe, downPipe));
		return;
	}
	
	/**
	 * Initializes bird at beginning of game
	 */
	private void initializeBird() {
		birdSprite.drawImage(bird.getImage(), bird.getXPosition(), bird.getYPosition());
	}
	
	private void start() {
		gameplay = new AnimationTimer() {
			public void handle(long currentTime) {
				//Do game loop here
			}
		};
	}
	/**
	 * Updates bird when jump detected
	 */
	private void jump() {
		
	}
}
