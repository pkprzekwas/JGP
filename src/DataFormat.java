import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class DataFormat{
	
    private String username, timeDate;
    private int totalTime, blowTime, totalScore;
    LinkedList<String> records = new LinkedList<String>(); 
    
    public DataFormat(String username, int totalTime, int blowTime, int totalScore){
   
    	this.totalTime = totalTime;
    	this.blowTime = blowTime;
    	this.username = username;
    	this.totalScore = totalScore;
    	
    	SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        this.timeDate = simpleDateHere.format(new Date());
    }
    
    public void updateData(String username, int totalTime, int blowTime, int totalScore){
   
    	this.totalTime = totalTime;
    	this.blowTime = blowTime;
    	this.username = username;
    	this.totalScore = totalScore;
    	
    	SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        this.timeDate = simpleDateHere.format(new Date());
    }
    
    public String createRecord(){
    	return username + ", " + totalScore + ", " + blowTime + ", " + totalTime + ", " + timeDate + ";";
    }
    
    /**
     * Adds 
     * @param record
     */
    public void addReadRecord(String record){
    	records.add(record);
    }
    
    /**
     * Takes LinkedList of records, chooses record with best score and returns it as a list of Strings.
     * Record has format (String[4]):
     * | [0]User name | [1]Score | [2]Blow Time | [3]Total time | [4]Date |
     * @return
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