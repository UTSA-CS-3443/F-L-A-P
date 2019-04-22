package application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import application.Main;
import application.model.Bird;
import application.model.Pipe;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 * @author Zachary Ellis (ebl533)
 * @author Jackson Dumas (llt190)
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
	private boolean running, jumping, passPipe;
	private final long createdMillis;
	
	/**
	 * Constructor for GameController
	 */
	public GameController() {
		bird = new Bird();
		pipes = new ArrayList<Pipe>();
		running = true;
		jumping = false;
		createdMillis = System.currentTimeMillis();
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
		Pipe downPipe = new Pipe(false, height-100);
		
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
				pipeSprite.clearRect(0, 0, 800, 800);
				birdSprite.clearRect(0, 0, 800, 800);
				if (running) {
					generatePipes();
					refreshPipes();
					refreshBird();
					if (jumping) {
						jump();
					}else {
						fall();
					
					if (checkCollision())
						running = false;
					}
					incrementScore();
				}
				else {
					gameplay.stop();
					try {
						Parent root = FXMLLoader.load(getClass().getResource("../view/DeathScreen.fxml"));
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
	 * jump - Updates bird when jump detected
	 */
	private void jump() { 
		bird.refresh(-1);
		bird.setXYPosition(bird.getXPosition(), bird.getYPosition()-100);
		drawRotatedImage(birdSprite, bird.getImage(), -45, bird.getXPosition(), bird.getYPosition());
		//drawRotatedImage(birdSprite, bird.getImage(), 0, bird.getXPosition(), bird.getYPosition());
		jumping = false;
	}
	
	/**
	 * fall - Updates bird when not jumping
	 */
	private void fall() {
		bird.refresh(-1);
		bird.setXYPosition(bird.getXPosition(), bird.getYPosition()+55);
		drawRotatedImage(birdSprite, bird.getImage(), 45, bird.getXPosition(), bird.getYPosition());
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
	private boolean checkCollision() { //floor at ~800 Y Coordinate
		for(Pipe p: pipes){
			if(bird.getYPosition() >= 800)
				return true;
			if(bird.getBounds().intersects(p.getBounds()))
				return true;
			if(bird.getYPosition() <= 0) {
				bird.setXYPosition(bird.getXPosition(), 0);
				return false;
			}
		}
		return false;
	}
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
	public void incrementScore() {
		if(!passPipe && (bird.getXPosition() > pipes.get(0).getXPosition())) {
			score++;
			passPipe = true;
		}
		count.setText(String.valueOf(score));
	}
	/**
	 * rotate - rotates the bird sprite
	 * @param gc - the graphics context
	 * @param angle - the angle to rotate
	 * @param px coordinate in pixels, relative to the canvas
     * @param py coordinate in pixels, relative to the canvas
	 */
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
	
	/**
     * Draws an image on a graphics context.
     *
     * @param gc the graphics context the image is to be drawn on.
     * @param the image to be drawn
     * @param the angle of rotation.
     * @param x coordinate in pixels, relative to the canvas
     * @param y coordinate in pixels, relative to the canvas
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

}
