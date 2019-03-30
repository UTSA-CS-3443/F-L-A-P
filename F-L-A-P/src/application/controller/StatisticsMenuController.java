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

/**
 * Event handling for the statistics view
 * 
 * @author Logan Poole (bct883)
 */
public class StatisticsMenuController implements EventHandler<ActionEvent>, Initializable
{
	
	@FXML
	Label deathCount;
	
	@FXML
	Label statisticsError;
	
	@Override
	public void initialize( URL location, ResourceBundle resources )
	{
		//check if the statistics file is accessible
		if( !( Main.stats.isAccessible() ) )
		{
			
			this.statisticsError.setText( "Unable to access statistics." );
			
		}
		
	}
	
	//return to main menu
	@Override
	public void handle( ActionEvent event ) 
	{
		try 
		{
			Parent root = FXMLLoader.load( getClass().getResource( "../view/MainMenu.fxml" ) );
			Main.stage.setScene( new Scene( root, 800, 800 ) );
			Main.stage.show();		
		} 
		catch( Exception e ) 
		{
			e.printStackTrace();	
		}	
	}
}
