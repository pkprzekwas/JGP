import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class manage all game - file communication.
 * @author Patryk Przekwas
 *
 */
public class ResourceManager {

	/**
	 * Reads data form file and put it into FormatData object.
	 * @param data
	 */
    public static void read(DataFormat data) {
    	
    	// The name of the file to open.
        String fileName = "data.txt";
        // This will reference one line at a time
        String record = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);
            // Wrapping FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            while((record = bufferedReader.readLine()) != null) {
                data.addReadRecord(record);
            }   
            // Closing file.
            bufferedReader.close();   
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");                  
        }
    }

    /**
     * Saves data to file.
     * @param data - game data
     */
    public static void save(DataFormat data){
    // The name of the file to open.
    String fileName = "data.txt";
    
    // The try-catch block in case of error.
    try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
        out.println(data.createRecord());
    }catch (IOException e) {
        System.out.println(
                "Error writing to file '" + fileName + "'");
   		}    
    }
}
