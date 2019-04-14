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
import javafx.scene.layout.Pane;

/**
 * @author Zachary Ellis (ebl533)
 *
 */
public class GameController { //no implements/extends for controller
	/**
	 * GameController Variables
	 */
	@FXML private Group root;
	@FXML private Pane backgroundPane;
	@FXML private ImageView background;
	@FXML private Canvas pipesCanvas;
	@FXML private Canvas birdCanvas;
	@FXML private Label count;
	private Bird bird;
	private ArrayList<Pipe> pipes;
	private GraphicsContext pipeSprite, birdSprite;
	private AnimationTimer gameplay; //for game loop
	private boolean running, jumping;
	
	/**
	 * Constructor for GameController
	 */
	public GameController() {
		bird = new Bird();
		pipes = new ArrayList<Pipe>();
		running = true;
		jumping = false;
	}

	@FXML
	private void initialize() {
		Scene scene = Main.stage.getScene(); 
		backgroundPane.setOnKeyTyped(e -> { //Adding event listener to scene (Space to jump)
			if (e.getCode().equals(KeyCode.SPACE))
				System.out.print("hello");
				jumping = true;
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
		
		refreshBird();
		initializePipeSet();

		count.setText(String.valueOf(0));	
		start();
	}
	
	/**
	 * Initializes pipe pair
	 */
	private void initializePipeSet() {                                        
		double height = (double) (new Random()).nextInt(700) + 1;
		
		Pipe upPipe = new Pipe(true, height);
		Pipe downPipe = new Pipe(false, height);
		// System.out.print("\n down Y: " + String.valueOf(downPipe.getYPosition()) + "\nUp Y: " + String.valueOf(upPipe.getYPosition()));
		
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
	private void start() {
		gameplay = new AnimationTimer() {
			public void handle(long now) { // gameplay loop
				//System.out.print("\n" + pipes.size());
				pipeSprite.clearRect(0, 0, 800, 800);
				birdSprite.clearRect(0, 0, 800, 800);
				if (running) {
					generatePipes();
					refreshPipes();
					refreshBird();
					if (checkCollision())
						running = false;
					if (jumping) {
						jump();
						System.out.print("HI");
					}
					
				}
				else {
					//death screen
					System.exit(0);
				}
			}
		};
		gameplay.start();
	}
	
	/**
	 * jump - Updates bird when jump detected
	 */
	private void jump() { 
		bird.refresh(-1);
		bird.setXYVelocity(bird.getXVelocity(), bird.getYPosition()+50);
		jumping = false;
	}
	
	/**
	 * generatePipes - Removes pipes off screen, determines when new pipe is generated  
	 */
	private void generatePipes() {
		if (pipes.get(0).getXPosition() <= -pipes.get(0).getWidth()) {
			pipes.remove(0);
			pipes.remove(0);
		}
		initializePipeSet();
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
