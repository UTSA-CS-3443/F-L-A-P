package application.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Singleton class containing methods for maintaining user statistics.
 * 
 * @author Logan Poole (bct883)
 */
public class Statistics {
	
	//Singleton object of this class
	private static Statistics singleInstance = null;
	
	private final String inputFilePath = "data/statistics.txt";
	private File file;
	
	private ArrayList<Integer> runs;
	private int totalDistance;
	
	//Private constructor to protect singleton instance
	private Statistics()
	{
		
		runs = new ArrayList<Integer>();
		file = new File( inputFilePath );
		
	}
	
	//Creates the singleton object if it doesn't exist, then returns the singleton object
	public static Statistics getInstance()
	{
		if( singleInstance == null)
		{
			
			singleInstance = new Statistics();
			
		}
		return ( singleInstance );
		
	}
	
	//Reads statistics in from file
	public void loadStatData() throws Exception
	{
		Scanner inpfScan = new Scanner( this.file );
		String[] tokens;
		String line;
		
		//Single statistics like distance on first line in csv format
		if( inpfScan.hasNextLine() )
		{
			
			line = inpfScan.nextLine();
			tokens = line.split( "," );
			
			this.totalDistance = Integer.parseInt( tokens[0] );
			
		}
		
		//Individual runs on separate lines
		while( inpfScan.hasNextLine() )
		{
			line = inpfScan.nextLine();
			this.runs.add( Integer.parseInt( line ) );
		}
		
		inpfScan.close();
		
	}
	
	public void saveStatData() throws Exception
	{
		
		
		
	}
	
	//Adds a run to the arraylist, maintaining a descending sort
	public void addRun( int obstaclesCleared )
	{
		for( int i=0; i<this.runs.size(); i++ )
		{
			
			if( obstaclesCleared > this.runs.get( i ) )
			{
				
				this.runs.add( i, obstaclesCleared );
				return;
				
			}
			
		}
		this.runs.add( obstaclesCleared );
		
	}
	
	public int getDeaths()
	{
		
		return ( this.runs.size() );
		
	}
	
}
