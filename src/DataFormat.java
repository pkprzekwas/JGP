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
    
    public void addReadRecord(String record){
    	records.add(record);
    }
    
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
			/*
    		String username = parts[0];
    		String blowTime = parts[2];
    		String totalTime = parts[3];
    		String date = parts[4];
    		System.out.println("Name: " + username + " Score: " + score 
    				+ " Blow Time:  "+ blowTime + " Total: " + totalTime + " Date: " + date);
			 */
    	}
    	String username = bestScore[0];
		String score = bestScore[1];
		String blowTime = bestScore[2];
		String totalTime = bestScore[3];
		String date = bestScore[4];
		//System.out.println("|Name: " + username + " | Score: " + score 
		//		+ " | Blow Time:  "+ blowTime + " | Total: " + totalTime + " | Date: " + date);
    	return bestScore;
    }

}