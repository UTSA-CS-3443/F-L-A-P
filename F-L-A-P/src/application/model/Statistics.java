package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
	
	private final String filePath = "data/statistics.txt";
	private File file;
	
	private ArrayList<Integer> runs;
	private int totalDistance;
	
	//Private constructor to protect singleton instance
	private Statistics()
	{
		
		runs = new ArrayList<Integer>();
		file = new File( filePath );
		
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
		Scanner inpfScanner = new Scanner( this.file );
		String[] tokens;
		String line;
		
		//Single metrics on one line, comma separated
		if( inpfScanner.hasNextLine() )
		{
			
			line = inpfScanner.nextLine();
			tokens = line.split( "," );
			
			this.totalDistance = Integer.parseInt( tokens[0] );
			
		}
		
		//Individual runs on separate lines
		while( inpfScanner.hasNextLine() )
		{
			
			line = inpfScanner.nextLine();
			this.runs.add( Integer.parseInt( line ) );
			
		}
		
		inpfScanner.close();
		
	}
	
	//Save statistics to data/statistics.txt
	public void saveStatData() throws Exception
	{
		
		int i;
		BufferedWriter oupfWriter = new BufferedWriter( new FileWriter( this.filePath ) );
		
		//Single metrics on one line, comma separated
		oupfWriter.write( this.totalDistance + "," );
		oupfWriter.write( "\n" );
		
		//Individual runs on separate lines
		for( i=0; i < this.runs.size(); i++ )
		{
			
			oupfWriter.write( this.runs.get( i ) + "\n" );
			
		}
		
		oupfWriter.close();
		
	}
	
	public void resetStatData() throws Exception
	{
		
		this.runs.clear();
		this.totalDistance = 0;
		
	}
	
	//Adds a run to the arraylist, maintaining a descending sort
	public void addRun( int obstaclesCleared )
	{
		for( int i=0; i < this.runs.size(); i++ )
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
