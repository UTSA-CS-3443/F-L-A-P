package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Zachary Ellis (ebl533)
 * @author Ivy Vasquez Sandoval (egi444)
 *
 */
public class Bird extends Sprite {	
	/**
	 * Bird variables
	 */
	private final int GRAVITY = 50;
	
	/**
	 * Constructor
	 * Initializes bird
	 */
	public Bird(String birdFile) {
		try {
			setImage(new Image(new FileInputStream(birdFile), 55, 55, false, false));
			setWidthHeight((int) getImage().getWidth()-54, (int) getImage().getHeight()-54); //HANDICAP
			setXYPosition(75, 400);
			setXYVelocity(0, GRAVITY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * jump - Updates bird when jump detected
	 */
	public void jump(GraphicsContext birdSprite) { 
		refresh(-1);
		setXYPosition(getXPosition(), getYPosition()+46);
		drawRotatedImage(birdSprite, getImage(), -25, getXPosition(), getYPosition());
		///drawRotatedImage(birdSprite, bird.getImage(), 0, bird.getXPosition(), bird.getYPosition());
	}
	
	/**
	 * fall - Updates bird when not jumping
	 */
	public void fall(GraphicsContext birdSprite) {
		refresh(-1);
		setXYPosition(getXPosition(), getYPosition()+54);
		drawRotatedImage(birdSprite, getImage(), 25, getXPosition(), getYPosition());
	}
	
	/**
	 * reset - resets bird position
	 * @return self
	 */
	public Bird reset() {
		setXYPosition(75, 400);
		setXYVelocity(0, GRAVITY);
		return this;
	}
}
