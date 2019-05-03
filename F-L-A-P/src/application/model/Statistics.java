package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Singleton class containing methods for maintaining user statistics.
 * 
 * @author Kevin Tully (xxc738)
 * @author Logan Poole (bct883)
 */
public class Statistics {
	
	/**
	 * Singleton object of this class
	 */
	private static Statistics singleInstance = null;
	
	private final String filePath = "src/application/data/statistics.txt";
	private File file;
	
	private ArrayList<Integer> runs;
	private int totalDistance;
	
	/**
	 * Class constructor 
	 */
	private Statistics()
	{
		
		runs = new ArrayList<Integer>();
		file = new File( filePath );
		totalDistance = 0;
		
	}
	
	/**
	 * Creates a singleton object if it doesn't exist 
	 * @return singleton object 
	 */
	public static Statistics getInstance()
	{
		if( singleInstance == null)
		{
			
			singleInstance = new Statistics();
			
		}
		return ( singleInstance );
		
	}
	
	/**
	 * Reads in data file 
	 */
	public void loadStatData() throws Exception
	{
		
		//If file doesn't exist, create it then leave method
		if( !( this.file.exists() ) )
		{
			
			this.file.createNewFile();
			return;
			
		}
		
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
	
	/**
	 * Save function that saves statistics to data/statistics.txt
	 */
	public void saveStatData() throws Exception
	{
		//If file doesn't exist, create it then write to it
		if( !( this.file.exists() ) )
		{
			
			this.file.createNewFile();
			
		}
		
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
	
	/**
	 * Resets statistics data in game
	 */
	public void resetStatData() throws Exception
	{
		
		this.runs.clear();
		this.totalDistance = 0;
		
	}
	
	/**
	 * Run method adds to the arraylist, maintains a descending sort
	 */
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
	
	/**
	 * 
	 * @return number of deaths  
	 */
	public int getDeaths()
	{
		
		return ( this.runs.size() );
		
	}
	
	/**
	 * 
	 * @return returns the longest run
	 */
	public int getLongestRun()
	{
		if( this.runs.size() == 0 )
			return ( 0 );
		
		return ( this.runs.get( 0 ) );
		
	}
	
	/**
	 * 
	 * @return number of obstacles cleared 
	 */
	public int getObstaclesCleared()
	{
		
		int total = 0;
		int i;
		
		for( i=0; i < this.runs.size(); i++ )
		{
			
			total = total + this.runs.get( i );
			
		}
		
		return ( total );
		
	}
	
	/**
	 * 
	 * @return average of all runs by the player
	 */
	public int getAverageRun()
	{
		if( this.runs.size() == 0 )
			return ( 0 );
		
		return ( this.getObstaclesCleared() / this.runs.size() );
		
	}
	
	/**
	 * 
	 * @return total distance traveled 
	 */
	public int getTotalDistance()
	{
		
		return ( this.totalDistance );
		
	}
	
	/**
	 * 
	 * @param newDistance
	 */
	public void setTotalDistance( int newDistance )
	{
		
		this.totalDistance = newDistance;
	
	}
	
}
