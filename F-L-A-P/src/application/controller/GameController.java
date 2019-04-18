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
	//Thread t1 = new Thread(new Runnable() {public void run() {}});
	private final long createdMillis = System.currentTimeMillis();
	
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
		Pipe downPipe = new Pipe(false, height-100);
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
					//System.exit(0);
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
		bird.setXYPosition(bird.getXPosition(), bird.getYPosition()-200);
		drawRotatedImage(birdSprite, bird.getImage(), -45, bird.getXPosition(), bird.getYPosition());
		drawRotatedImage(birdSprite, bird.getImage(), 0, bird.getXPosition(), bird.getYPosition());
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
			pipes.remove(0);
			pipes.remove(0);
		}
		if(this.getAgeInSeconds() > .5) {
			initializePipeSet();
		}
	}
	/**
	 * refreshPipes - redraws pipes to screen
	 */
	private void refreshPipes() {
		pipeSprite.drawImage(pipes.get(0).getImage(), pipes.get(0).getXPosition(), pipes.get(0).getYPosition());
		pipeSprite.drawImage(pipes.get(1).getImage(), pipes.get(1).getXPosition(), pipes.get(1).getYPosition());
		pipes.get(0).refresh(10);
		pipes.get(1).refresh(10);
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
		if(bird.getYPosition() >= 800)
			return true;
		//if(bird.getXPosition() == pipes.getXPosition() || bird.getYPosition() == pipes.getYPosition())
			//return true; 
		if(bird.getYPosition() <= 0) {
			bird.setXYPosition(bird.getXPosition(), 0);
			return false;
		}
		return false;
	}
	
	public int getAgeInSeconds() {
		long nowMillis = System.currentTimeMillis();
	    return (int)((nowMillis - this.createdMillis) / 1000);
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
