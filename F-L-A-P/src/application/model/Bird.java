package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Main;
import javafx.scene.image.Image;

/**
 * @author Zachary Ellis (ebl533)
 *
 */
public class Bird extends Sprite {	
	/**
	 * Bird variables
	 */
	public final int GRAVITY = 50;
	
	/**
	 * Constructor
	 * Initializes bird
	 */
	public Bird() {
		try {
			setImage(new Image(new FileInputStream(Main.charaSelectedPath), 55, 55, false, false));
			setWidthHeight((int) getImage().getWidth(), (int) getImage().getHeight());
			setXYPosition(75, 400);
			setXYVelocity(0, GRAVITY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
