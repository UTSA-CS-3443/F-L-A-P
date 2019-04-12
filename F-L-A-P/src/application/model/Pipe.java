package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
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
	
	/**
	 * Constructor for Pipe
	 * @param up Pipe orientation
	 * @param height pipe gap location
	 */
	public Pipe(boolean up, double height) {
		try {
			this.up = up;
			if (up)
				setImage(new Image(new FileInputStream("src/application/data/images/pipe_up.png"), 70, height, false, false));
			else 
				setImage(new Image(new FileInputStream("src/application/data/images/pipe_down.png"), 70, height, false, false));
			setWidthHeight(getImage().getWidth(), getImage().getHeight());
			setXYVelocity(-.5, 0);
			setXYPosition(600, height); //Testing parameters ACTUAL: (800, ??height??)
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
