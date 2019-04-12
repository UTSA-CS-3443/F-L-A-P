package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

/**
 * @author Zachary Ellis (ebl533)
 *
 */
public class Bird extends Sprite implements Initializable {	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			setImage(new Image(new FileInputStream("data/images/bird.png")));
			setWidthHeight(getImage().getWidth(), getImage().getHeight());
			setXYPosition(50, 400);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
