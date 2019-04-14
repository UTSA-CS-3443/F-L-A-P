package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * @author Zachary Ellis (ebl533)
 *
 */
public class Bird extends Sprite {	
	public final int GRAVITY = 50;
	public Bird() {
		try {
			setImage(new Image(new FileInputStream("src/application/data/images/bird.png"), 55, 55, false, false));
			setWidthHeight((int) getImage().getWidth(), (int) getImage().getHeight());
			setXYPosition(75, 400);
			setXYVelocity(0, GRAVITY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
