package application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import application.Main;
import application.model.Bird;
import application.model.Pipe;
import application.model.Statistics;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
 * @author Ivy Vasquez Sandoval (egi444)
 */
public class GameController{ //no implements/extends for controller
	/**
	 * GameController Variables
	 */
	public int score = 0;
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
	private boolean running, passPipe;
	private final long createdMillis;
	private int jumping;
	
	/**
	 * Constructor for GameController
	 * @param birdFile Path to bird file
	 */
	public GameController(String birdFile) {
		bird = new Bird(birdFile);
		pipes = new ArrayList<Pipe>();
		running = true;
		createdMillis = System.currentTimeMillis();
	}

	/**
	 * Constructor for GameController
	 * @param bird Sprite from previous run
	 */
	public GameController(Bird bird) {
		this.bird = bird.reset();
		pipes = new ArrayList<Pipe>();
		running = true;
		createdMillis = System.currentTimeMillis();
	}
	
	@FXML
	private void initialize() {
		Scene scene = Main.stage.getScene(); 
		backgroundPane.setOnKeyTyped(e -> { //Adding event listener to scene (Space to jump)
			if (e.getCode().equals(KeyCode.SPACE))
				System.out.print("hello");
				jumping = 30;
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
		
//		refreshBird(); DON'T NEED
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
		Pipe downPipe = new Pipe(false, height-100);
		
		upPipe.setXYVelocity(-.5, 0);
		downPipe.setXYVelocity(-.5, 0);
		
//		pipeSprite.drawImage(upPipe.getImage(), upPipe.getXPosition(), upPipe.getYPosition()); DON'T NEED
//		pipeSprite.drawImage(downPipe.getImage(), downPipe.getXPosition(), downPipe.getYPosition()); DON'T NEED
		
		pipes.addAll(Arrays.asList(upPipe, downPipe));
		return;
	}
	
	/**
	 * start - Starts game
	 */
	private void start() {
		gameplay = new AnimationTimer() {
			public void handle(long now) { // gameplay loop
				pipeSprite.clearRect(0, 0, 800, 800);
				birdSprite.clearRect(0, 0, 800, 800);
				if (running) {
					if (pipes.get(0).checkCollision(bird) || pipes.get(1).checkCollision(bird)) {
						running = false;
						return;
					}
					if (jumping>0) {
						bird.jump(birdSprite);
						jumping--;
					}
					else 
						bird.fall(birdSprite);
					generatePipes();
					refreshPipes();
//					refreshBird(); DON'T NEED
					incrementScore();
					}
				else {
					gameplay.stop();
					Statistics.getInstance().addRun(Integer.parseInt(count.getText()));
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeathScreen.fxml"));
						Parent root = loader.load();
						DeathScreenController dsc = loader.getController();
						dsc.bird = bird;
						Main.stage.setResizable(false);
						root.setId("background");
						Scene scene = new Scene(root, 800,800);
						scene.getStylesheets().add(getClass().getResource("../DeathScreen.css").toExternalForm());
						Main.stage.setScene(scene);
						Main.stage.show();
						
					} catch(Exception e) {
						System.out.println("Error loading the files.");
						e.printStackTrace();
					}
				}
			}
		};
		gameplay.start();
	}
	
	/**
	 * generatePipes - Removes pipes off screen, determines when new pipe is generated  
	 */
	private void generatePipes() {
		if (pipes.get(0).getXPosition() <= -pipes.get(0).getWidth()) {
			passPipe = false;
			pipes.remove(0);
			pipes.remove(0);
		}
		
		if (pipes.get(pipes.size()-1).getXPosition() <= 400)
			initializePipeSet();
	}
	
	/**
	 * refreshPipes - redraws pipes to screen
	 */
	private void refreshPipes() {
		for (Pipe p : pipes) {
			pipeSprite.drawImage(p.getImage(), p.getXPosition(), p.getYPosition());
			p.refresh(6);
		}
	}
	
//	/**DON'T NEED CREATES DOUBLE IMAGE
//	 * refreshBird - initializes/redraws bird to screen
//	 */
//	private void refreshBird() {
//		birdSprite.drawImage(bird.getImage(), bird.getXPosition(), bird.getYPosition());
//	}
	
	/**
	 * getAgeInSeconds
	 * @return the elapsed time
	 */
	public int getAgeInSeconds() {
		long nowMillis = System.currentTimeMillis();
	    return (int)((nowMillis - this.createdMillis) / 1000);
	}
	
	/**
	 * incrementScore - adds to the score and sets the text
	 */
	private void incrementScore() {
		if(!passPipe && (bird.getXPosition() > pipes.get(0).getXPosition())) {
			score++;
			passPipe = true;
		}
		count.setText(String.valueOf(score));
	}
}
