package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Main.java
 * Contains main method, and is the driver for the program
 * @author Jackson Dumas (llt190)
 * @author
 * @author
 * @author
 * @author
 *
 */
public class Main extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		
		//Add Application icon and convert from file to image
		File iconFile = new File("data/images/icon.png");
		Image icon = new Image(iconFile.toURI().toString());
		stage = primaryStage;
		
		//Load Scene
		try {
			Parent root = FXMLLoader.load(getClass().getResource( "view/MainMenu.fxml" ));
			primaryStage.setScene(new Scene(root, 800, 800));
			primaryStage.setTitle("FLAP");
			primaryStage.getIcons().add(icon);
			primaryStage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}