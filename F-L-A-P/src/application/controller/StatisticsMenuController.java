package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Event handling for the statistics view
 * 
 * @author Logan Poole (bct883)
 */
public class StatisticsMenuController implements EventHandler<ActionEvent>
{
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
