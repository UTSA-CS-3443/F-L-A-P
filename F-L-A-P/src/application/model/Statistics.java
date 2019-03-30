package application.model;

import java.io.File;

/**
 * Methods for maintaining in-game statistics
 * 
 * @author Logan Poole (bct883)
 */
public class Statistics {
	
	private final String inputFilePath = "data/statistics.txt";
	private boolean isAccessible = true;
	private int deaths;
	
	public Statistics()
	{
		
		File file = new File( this.inputFilePath );
		
		//check if file exists, if not create one
		if( !( file.isFile() ) )
		{
			
			try
			{
				
				file.createNewFile();
				
			}
			catch( Exception e )
			{
				
				e.printStackTrace();
				this.isAccessible = false;
				return;
				
			}
			
		}
		
		//check if file is readable
		if( !( file.canRead() ) )
		{
			
			this.isAccessible = false;
			return;
			
		}
		
	}
	
	public boolean isAccessible()
	{
		
		return ( this.isAccessible );
		
	}
	
}
