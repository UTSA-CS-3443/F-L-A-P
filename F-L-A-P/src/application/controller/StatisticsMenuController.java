/**
 * StatisticsMenuController.java
 * Event handling for the statistics view
 * @author Logan Poole (bct883)
 * @author Zachary Ellis (ebl533)
 */
package application.controller;

import application.model.Statistics;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsMenuController implements EventHandler<ActionEvent>, Initializable
{
	
	@FXML
	Label longestRunLabel;
	
	@FXML
	Label averageRunLabel;
	
	@FXML
	Label deathsLabel;
	
	@FXML
	Label totalDistanceLabel;
	
	@FXML
	Label obstaclesClearedLabel;
	
	@FXML
	Label statisticsError;
	
	private Statistics stats = Statistics.getInstance();
	
	public void setLabels()
	{
		
		this.longestRunLabel.setText( Integer.toString( stats.getLongestRun() ) );
		this.averageRunLabel.setText( Integer.toString( stats.getAverageRun() ) ); 
		this.deathsLabel.setText( Integer.toString( stats.getDeaths() ) );
		this.totalDistanceLabel.setText( Integer.toString( stats.getTotalDistance() ) );
		this.obstaclesClearedLabel.setText( Integer.toString( stats.getObstaclesCleared() ) );
		
	}
	
	@Override
	public void initialize( URL location, ResourceBundle resources )
	{
		
		try 
		{
			
			stats.loadStatData();
			
		} 
		catch (Exception e) 
		{
			
			this.statisticsError.setText( "Unable to access statistics." );
			e.printStackTrace();
			
		}
		
		setLabels();
		
	}
	
	public void resetButton( ActionEvent event )
	{
		
		System.out.print( "StatisticsMenu resetButton pressed.\n" );
		
		try 
		{
			
			stats.resetStatData();
			stats.saveStatData();
			setLabels();
			
		} 
		catch (Exception e) 
		{
			
			this.statisticsError.setText( "Unable to access statistics." );
			e.printStackTrace();
			
		}
		
	}
	
	//return to previous page
	@Override
	public void handle( ActionEvent event ) 
	{
		
		try 
		{
			
			Parent root = FXMLLoader.load( getClass().getResource( "../view/MainMenu.fxml" ) );
			root.setId( "mainPane" );
			Scene scene = new Scene( root, 800, 800 );
			scene.getStylesheets().add( getClass().getResource( "../application.css" ).toExternalForm() );
			Main.stage.setScene( scene );
			Main.stage.show();	
			
		} 
		catch( Exception e ) 
		{
			
			e.printStackTrace();	
			
		}	
		
	}
	
}
