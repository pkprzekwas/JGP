import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * DataFormat object is responsible for storing game data in format convenient to
 * file operations. It includes methods which help with parsing data both from 
 * and to file.
 * @author Patryk
 *
 */
public class DataFormat{
	
    private String username, timeDate;
    private int totalTime, blowTime, totalScore;
    LinkedList<String> records = new LinkedList<String>(); 
    SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
    
    /**
     * Constructor initialized with default properties data and current date.
     * @param username - nickname of player
     * @param totalTime - total time of playing
     * @param blowTime - total summed time of player blowing
     * @param totalScore - total score of points
     */
    public DataFormat(String username, int totalTime, int blowTime, int totalScore){
   
    	this.totalTime = totalTime;
    	this.blowTime = blowTime;
    	this.username = username;
    	this.totalScore = totalScore;
    	
        this.timeDate = simpleDateHere.format(new Date());
    }
    
    /**
     * Updates data object to be compatible with current data.
     * @param username - nickname of player
     * @param totalTime - total time of playing
     * @param blowTime - total summed time of player blowing
     * @param totalScore - total score of points
     */
    public void updateData(String username, int totalTime, int blowTime, int totalScore){
   
    	this.totalTime = totalTime;
    	this.blowTime = blowTime;
    	this.username = username;
    	this.totalScore = totalScore;
    	
        this.timeDate = simpleDateHere.format(new Date());
    }
    
    /**
     * Creates and returns formated record of game data. Format:
     * "USERNAME, TOTALSCORE, BLOWTIME, TOTALTIME, DATE;"
     * @return record
     */
    public String createRecord(){
    	return username + ", " + totalScore + ", " + blowTime + ", " + totalTime + ", " + timeDate + ";";
    }
    
    /**
     * Adds records to LinkedList.
     * @param record - data record
     */
    public void addReadRecord(String record){
    	records.add(record);
    }
    
    /**
     * Takes LinkedList of records, chooses record with best score and returns it as a list of Strings.
     * Record has format (String[4]):
     * | [0]User name | [1]Score | [2]Blow Time | [3]Total time | [4]Date |
     * @return - record with the highest score
     */
    public String[] returnBestScore(){
    	
    	String[] bestScore = new String[5];
    	int best = -1; 
    	
    	for(int i=0; i<records.size(); i++){
    		String[] parts = records.get(i).split(", ");
    		String score = parts[1];
    		if(best < Integer.parseInt(score)){
    			best = Integer.parseInt(score);
    			bestScore = parts;
    		} 		
    	}
    	return bestScore;
    }

}