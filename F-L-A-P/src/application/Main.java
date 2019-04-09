/**
 * Main.java
 * Contains main method, and is the driver for the program
 * @author Jackson Dumas (llt190)
 * @author Zachary Ellis (ebl533)
 * @author
 * @author
 * @author
 *
 */
package application;
	
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

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
			root.setId("mainPane");
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("FLAP");
			stage.getIcons().add(icon);
			stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}