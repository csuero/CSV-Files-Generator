package csvGenerator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class csvReader {
	public static final String delimeter = ",";
	public static String FILE,DIR,PREFIX;
	public static String[] HEADER;
	public static void generateFiles()
	{
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(FILE));
			String[] row;
			String currentFile, parentDir;
			HEADER = reader.readNext();
						
			while ((row = reader.readNext()) != null) {
				
				parentDir =DIR+"/"+row[3]+"/";
				currentFile =parentDir+"Period "+row[2]+" - "+row[0]+"-"+row[1]+".csv";	 
				appendToFile(parentDir,currentFile, row);				
			}
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Boolean fileExists(String file)
	{
		File f = new File(file);
		if (f.exists())
			return true;
		else
			return false;
		
	}
	
	
	public static void appendToFile(String TDir, String file, String[] row) throws IOException
	{

		if(new File(file).exists())
		{
			CSVWriter writer = new CSVWriter(new FileWriter(file,true));
			writer.writeNext(row);
		    writer.close();
		}
		else if(!new File(TDir).exists())
		{
		 //If Directory does not exists, create it and recall appendToFile
		 new File(TDir).mkdirs();
		 appendToFile(TDir,file,row);
		}
		else 
		{
			
			new File(file).createNewFile();
			CSVWriter writer = new CSVWriter(new FileWriter(file,true));
		    writer.writeNext(HEADER);
		    writer.close();
		    appendToFile(TDir,file,row);
		}
		

		
		
	}
	
	
	
	
	
	

}
