package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * @author Zachary Ellis(ebl533)
 *
 */
public class Pipe extends Sprite {
	/**
	 * Pipe Variables
	 */
	private boolean up;
	private final int GAP = 150;
	
	/**
	 * Constructor for Pipe
	 * @param up Pipe orientation
	 * @param height pipe gap location
	 */
	public Pipe(boolean up, double yValue) {
		try {
			this.up = up;
			if (yValue < 200)
				yValue = 200;
			if (up) {
				setImage(new Image(new FileInputStream("src/application/data/images/pipe_up.png"), 70, 600, false, false));
				setXYPosition(800, yValue); //Testing parameters ACTUAL: (800, yValue)
			}
			else {
				setImage(new Image(new FileInputStream("src/application/data/images/pipe_down.png"), 70, 600, false, false));
				setXYPosition(800, (yValue - (600 + GAP))); //Testing parameters ACTUAL: (800, yValue - (600 + GAP))
			}
			setWidthHeight((int) getImage().getWidth(), (int) getImage().getHeight());
			setXYVelocity(-.5, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean detectCollision(Bird bird) {
		return false;
	}
	
	public boolean getFace() {
		return up;
	}
}
