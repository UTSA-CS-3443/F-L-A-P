package application.controller;

import application.model.Statistics;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * StatisticsMenuController.java
 * Event handling for the statistics view
 * @author Logan Poole (bct883)
 * @author Zachary Ellis (ebl533)
 */
public class StatisticsMenuController {
	/**
	 * StatisticsMenuController Variables
	 */
	@FXML private Label longestRunLabel;
	@FXML private Label averageRunLabel;
	@FXML private Label deathsLabel;
	@FXML private Label totalDistanceLabel;
	@FXML private Label obstaclesClearedLabel;
	@FXML private Label statisticsError;
	private Statistics stats = Statistics.getInstance();
	
	@FXML
	public void initialize() {		
		try {
			stats.loadStatData();
			setLabels();
		} catch (Exception e){
			this.statisticsError.setText( "Unable to access statistics." );
			e.printStackTrace();	
		}
		return;
	}
	
	/**
	 * setLabels - sets labels to stat values
	 */
	public void setLabels()	{
		this.longestRunLabel.setText( Integer.toString( stats.getLongestRun() ) );
		this.averageRunLabel.setText( Integer.toString( stats.getAverageRun() ) ); 
		this.deathsLabel.setText( Integer.toString( stats.getDeaths() ) );
		this.totalDistanceLabel.setText( Integer.toString( stats.getTotalDistance() ) );
		this.obstaclesClearedLabel.setText( Integer.toString( stats.getObstaclesCleared() ) );
		return;
	}
	
	/**
	 * @param event Reset button pressed
	 */
	public void resetButton( ActionEvent event ) {
		System.out.print( "StatisticsMenu resetButton pressed.\n" );
		try {
			stats.resetStatData();
			stats.saveStatData();
			setLabels();
		} catch (Exception e) {
			this.statisticsError.setText( "Unable to access statistics." );
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * @param event Main Menu button pressed
	 */
	public void mainMenu( ActionEvent event ) {
		try {
			Parent root = FXMLLoader.load( getClass().getResource( "../view/MainMenu.fxml" ) );
			root.setId( "background" );
			Scene scene = new Scene( root, 800, 800 );
			scene.getStylesheets().add( getClass().getResource( "../MainMenu.css" ).toExternalForm() );
			Main.stage.setScene( scene );
			Main.stage.show();	
		} catch( Exception e ) {
			e.printStackTrace();	
		}
		return;
	}
}
