package application;
	
import application.model.Statistics;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Main.java
 * Contains main method, and is the driver for the program
 * @author Jackson Dumas (llt190)
 *
 */
public class Main extends Application {
	
	public static Stage stage;
	public static Statistics stats;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.stats = new Statistics();
			
			Parent root = FXMLLoader.load(getClass().getResource( "view/MainMenu.fxml" ));
			primaryStage.setScene(new Scene(root, 800, 800));
			primaryStage.show();
			
			stage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}