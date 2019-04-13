package application.controller;

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
public class GameController { //no implements/extends for controller
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
	private boolean running;
	
	/**
	 * Constructor for GameController
	 */
	public GameController() {
		bird = new Bird();
		pipes = new ArrayList<Pipe>();
		running = true;
	}

	@FXML
	public void initialize() {
		Scene scene = Main.stage.getScene(); 
		scene.setOnKeyPressed(e -> { //Adding event listener to scene (Space to jump)
			if (e.getCode().equals(KeyCode.SPACE)) 
				jump();
		});
		Main.stage.setScene(scene);
		Main.stage.show();
		
		background.setId("background");
		initializeGame();		
	}
	
	/**
	 * Loads assets onto screen and sets up game
	 */
	private void initializeGame() {
		pipeSprite = pipesCanvas.getGraphicsContext2D();
		birdSprite = birdCanvas.getGraphicsContext2D();
		pipeSprite.clearRect(0, 0, 800, 800);
		birdSprite.clearRect(0, 0, 800, 800);
		
		refreshBird();
		initializePipeSet();

		
		count.setText(String.valueOf(0));		
	}
	
	/**
	 * Initializes pipe pair
	 */
	private void initializePipeSet() {                                        
		double height = (double) (new Random()).nextInt(700) + 1;                //ISSUE: pipes not loading correctly EVERY time
		System.out.print("\nRandom Height Value: " + String.valueOf(height)); 
		Pipe upPipe = new Pipe(true, height);
		Pipe downPipe = new Pipe(false, height);
		System.out.print("\n down Y: " + String.valueOf(downPipe.getYPosition()) + "\nUp Y: " + String.valueOf(upPipe.getYPosition()));
		
		upPipe.setXYVelocity(-.5, 0);
		downPipe.setXYVelocity(-.5, 0);
		
		pipeSprite.drawImage(upPipe.getImage(), upPipe.getXPosition(), upPipe.getYPosition());
		pipeSprite.drawImage(downPipe.getImage(), downPipe.getXPosition(), downPipe.getYPosition());
		
		pipes.addAll(Arrays.asList(upPipe, downPipe));
		return;
	}
	
	/**
	 * start - Starts game
	 */
	private void start() { //not running... yet
		gameplay = new AnimationTimer() {
			public void handle(long now) { // gameplay loop
				if (running) {
					refreshPipes();
					
				}
			}
		};
		gameplay.start();
	}
	
	/**
	 * Updates bird when jump detected
	 */
	private void jump() { //update bird class data
		
	}
	
	/**
	 * refreshPipes - redraws pipes to screen
	 */
	private void refreshPipes() {
		for (Pipe p : pipes) {
			pipeSprite.drawImage(p.getImage(), p.getXPosition(), p.getYPosition());
			p.refresh(10);
		}
	}
	
	/**
	 * refreshBird - initializes/redraws bird to screen
	 */
	private void refreshBird() {
		birdSprite.drawImage(bird.getImage(), bird.getXPosition(), bird.getYPosition());
	}
	
	/**
	 * checkCollision - checks for collisions
	 * @return Boolean if bird hit pipe
	 */
	private boolean checkCollision() { //floor at ~650 Y Coordinate
		return false;
	}
}
