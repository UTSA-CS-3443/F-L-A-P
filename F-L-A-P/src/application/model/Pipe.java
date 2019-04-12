package application.model;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

/**
 * @author Zachary Ellis(ebl533)
 *
 */
public class Pipe extends Sprite implements Initializable {
	/**
	 * Pipe Variables
	 */
	private boolean up;
	private double gapLocation;
	
	/**
	 * Constructor for Pipe
	 * @param up Pipe orientation
	 * @param height pipe gap location
	 */
	public Pipe(boolean up, double gapLocation) {
		this.up = up;
		this.gapLocation = gapLocation;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (up)
			setImage(new Image("data/images/pipe_up.png"));
		else 
			setImage(new Image("data/images/pipe_down.png"));
		setWidthHeight(getImage().getWidth(), getImage().getHeight());
		setXYVelocity(-.5, 0);
	}	
}
