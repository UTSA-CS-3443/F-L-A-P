package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * Main.java
 * Contains main method, program driver
 * @author Jackson Dumas (llt190)
 * @author Zachary Ellis (ebl533)
 * @author Ivy Vasquez Sandoval (egi444)
 *
 */

public class Main extends Application {
	/**
	 * Main variables
	 */
	public static Stage stage;
	public static String charaSelectedPath;
	
	@Override
	public void start(Stage primaryStage) {
		
		// Play Music in background
		final Task<Object> music = new Task<Object>() {

	        @Override
	        protected Object call() throws Exception {
	            AudioClip audio = new AudioClip(getClass().getResource("data/music.mp3").toExternalForm());
	            audio.setCycleCount(100);
	            audio.play();
	            return null;
	        }
	    };
	    Thread thread = new Thread(music);
	    thread.start();
	    
		//System.out.print(javafx.scene.text.Font.getFamilies());
		//Add Application icon and convert from file to image
		File iconFile = new File("src/application/data/images/icon.png");
		Image icon = new Image(iconFile.toURI().toString());
		stage = primaryStage;
		
		//Load Scene
		try {
			Parent root = FXMLLoader.load(getClass().getResource( "view/MainMenu.fxml" ));
			Scene scene = new Scene(root, 800, 800);
			root.setId("background");
			scene.getStylesheets().add(getClass().getResource("MainMenu.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("FLAP");
			stage.getIcons().add(icon);
			stage.setResizable(false);
			stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}