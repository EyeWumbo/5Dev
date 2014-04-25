package handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileRenew 
{
	public FileRenew(){}
	
	public void restart(String filepath)
	{
		BufferedWriter writer = null;  
		try
		{
			File file = new File(filepath);
			writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("");
		}
		catch(Exception e){System.out.println("failed to renew");}
		finally
		{
			try
			{
				writer.close();
			}
			catch(Exception e){}
		}  
	}
	public void empty(String filepath)
	{
		try{
			if(doesFileExist(filepath) == true)
			{
				File file = new File(filepath);
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();	
			}
		}
		catch(Exception e)
		{			
		}

	}
	
	public Boolean doesFileExist(String filepath)
	{
		File f = new File(filepath);
		if(f.exists() == true)
		{
			return true;
		}
		return false; 
	}
}
